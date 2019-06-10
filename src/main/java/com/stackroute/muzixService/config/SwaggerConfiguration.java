/**
 * This program is used for configuration of swagger2
 */

package com.stackroute.muzixService.config;

import com.stackroute.muzixService.controller.TrackController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@PropertySource("classpath:application.properties")
@ComponentScan(basePackageClasses = TrackController.class)
@Configuration
public class SwaggerConfiguration {

    private static final String SWAGGER_API_VERSION="1.0";
    private static final String LICENSE_TEXT="license";
    private static final String title="Muzix Rest API";
    private static final String description="RESTful Api for Muzix";

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .version(SWAGGER_API_VERSION)
                .title(title)
                .description(description)
                .license(LICENSE_TEXT)
                .build();
    }

    @Bean
    public Docket muzixApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }
}
