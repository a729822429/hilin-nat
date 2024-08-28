package icu.hilin.nat.server.proxy;

import cn.hutool.json.JSONUtil;
import icu.hilin.nat.core.Constant;
import icu.hilin.nat.core.entity.vo.ProxyVO;
import io.vertx.core.json.impl.JsonUtil;
import io.vertx.core.net.NetSocket;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
public class ProxyServer {

    public static void startProxy(ProxyVO proxyVO) {
        if (proxyVO.getType() == 1) {
            startTcpProxy(proxyVO);
        } else if (proxyVO.getType() == 2) {
            startUdpProxy(proxyVO);
        }
    }

    private static boolean startTcpProxy(ProxyVO proxyVO) {
        CountDownLatch latch = new CountDownLatch(1);
        final LongAdder result = new LongAdder();
        // 启动本地服务端
        Constant.VERTX.createNetServer().connectHandler(serverSocket -> {
                    ProxyChannelBind proxyChannelBind = new ProxyChannelBind();
                    proxyChannelBind.setServerSocket(serverSocket);
                    // 收到外部数据
                    serverSocket.handler(proxyChannelBind::serverReceive);
                    Constant.VERTX.createNetClient().connect(proxyVO.getRemotePort(), proxyVO.getRemoteAddr(), r -> {
                        if (r.succeeded()) {
                            NetSocket clientSocket = r.result();
                            clientSocket.handler(proxyChannelBind::clientReceive);
                            clientSocket.closeHandler(v -> proxyChannelBind.close());
                            proxyChannelBind.setClientSocket(clientSocket);
                            proxyChannelBind.serverReceive(null);
                        } else {
                            proxyChannelBind.close();
                        }
                    });
                    serverSocket.closeHandler(v -> proxyChannelBind.close());
                })
                .listen(proxyVO.getLocalPort(), proxyVO.getLocalAddr(), r -> {
                    if (r.succeeded()) {
                        // 服务启动成功
                        result.add(1);
                        log.info("服务启动成功 {}", JSONUtil.toJsonStr(proxyVO));
                    } else {
                        // 服务启动失败
                        result.add(2);
                        log.info("服务启动失败 {}", JSONUtil.toJsonStr(proxyVO));
                    }
                    latch.countDown();
                });
        try {
            latch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException ignored) {
        }
        return result.longValue() == 1;
    }

    private static void startUdpProxy(ProxyVO proxyVO) {

    }

}
