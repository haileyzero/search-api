package com.hailey.search.api;

import com.hailey.search.core.search.CoreRoot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {SearchApiApplication.class, CoreRoot.class})
@EntityScan(basePackageClasses = {SearchApiApplication.class, CoreRoot.class})
@EnableRetry
@EnableAspectJAutoProxy
public class SearchApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApiApplication.class, args);
    }
}
