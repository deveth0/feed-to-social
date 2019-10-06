package de.dev.eth0.feed2social;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import feign.Logger;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class Feed2SocialApplication {

  public static void main(String[] args) {
    SpringApplication.run(Feed2SocialApplication.class, args);
  }

  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

}
