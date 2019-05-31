package com.fakeghost.bbs.rbmq;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Runner implements CommandLineRunner {
    @Autowired
    Environment env;

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(env.getProperty("rabbitmq.TopicExchange"), "foo.bar.baz", "Hello from RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}
