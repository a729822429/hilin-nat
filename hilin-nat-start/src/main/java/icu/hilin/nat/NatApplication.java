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
    }
}
