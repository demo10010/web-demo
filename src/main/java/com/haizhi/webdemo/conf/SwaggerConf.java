package com.haizhi.webdemo.conf;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Slf4j
@EnableKnife4j
public class SwaggerConf {
    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())//全部包都扫描
//                //这里指定Controller扫描包路径(项目路径也行)
//                .apis(RequestHandlerSelectors.basePackage("com.haizhi.webdemo.controller"))//TODO
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))// 错误路径不监控
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Demo10086的测试项目")
                .description("简单的项目描述")
                .termsOfServiceUrl("http://127.0.0.1:8080/doc.html#/home")
                .version("1.0")
                .build();
    }
}