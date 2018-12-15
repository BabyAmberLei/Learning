package com.amber.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@EnableFeignClients(basePackages= {"com.amber.springcloud"})
@ComponentScan("com.amber.springcloud")
public class ConsumerDept6001 {

    public static void main(String[] args){
        SpringApplication.run(ConsumerDept6001.class, args);
    }
}
