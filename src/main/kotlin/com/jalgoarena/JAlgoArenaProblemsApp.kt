package com.jalgoarena

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@SpringBootApplication
@EnableEurekaClient
@Configuration
@EnableSwagger2
open class JAlgoArenaProblemsApp {
    @Bean
    open fun restTemplate() = RestTemplate()
}

fun main(args: Array<String>) {
    SpringApplication.run(JAlgoArenaProblemsApp::class.java, *args)
	
	val thread_1 = EmailSenderThread(LocalDateTime.now(), 15)
    thread_1.start();
}

@Bean
fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
        .select() 
        .apis(RequestHandlerSelectors.basePackage("com.jalgoarena.web"))
        .paths(PathSelectors.any())                          
        .build()