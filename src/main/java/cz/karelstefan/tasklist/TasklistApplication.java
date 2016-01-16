package cz.karelstefan.tasklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"cz.karelstefan.tasklist"})
@SpringBootApplication
public class TasklistApplication {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
            container.addErrorPages(error404);
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(TasklistApplication.class, args);
    }
}
