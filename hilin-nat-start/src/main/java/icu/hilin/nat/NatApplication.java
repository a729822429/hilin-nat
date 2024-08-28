package icu.hilin.nat;

import icu.hilin.nat.core.entity.vo.ProxyVO;
import icu.hilin.nat.server.proxy.ProxyServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class NatApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(NatApplication.class, args);
    }

    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10000));

    @Override
    public void run(ApplicationArguments args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executor.execute(()->{
                ProxyVO proxy = new ProxyVO()
                        .setId(finalI)
                        .setType(1)
                        .setLocalAddr("0.0.0.0")
                        .setLocalPort(10000 + finalI)
                        .setRemoteAddr("192.168.1.124")
                        .setRemotePort(11080);
                ProxyServer.startProxy(proxy);
            });
        }
    }
}
