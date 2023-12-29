package com.revy.api_server.infra.config;

import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Revy on 2023.12.29
 */

@EnableAsync
@Configuration
public class AsyncConfig {

    /**
     * 비지니스 로직 가상쓰레드 이용 처리를 위한 Bean 설정
     * @return TomcatProtocolHandlerCustomizer
     */

    @Bean
    public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
        return protocolHandler -> {
            protocolHandler.setExecutor(newVirtualThreadPerTaskExecutor("V-exec-"));
        };
    }

    private ExecutorService newVirtualThreadPerTaskExecutor(String threadName) {
        // 쓰레드 이름 지정을 위해 newVirtualThreadPerTaskExecutor 미사용
        ThreadFactory factory = Thread.ofVirtual()
                .name(threadName, 0)
                .factory();
        return Executors.newThreadPerTaskExecutor(factory);
    }
}
