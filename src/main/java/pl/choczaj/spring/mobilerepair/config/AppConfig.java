package pl.choczaj.spring.mobilerepair.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.choczaj.spring.mobilerepair.web.converter.CustomerConverter;
import pl.choczaj.spring.mobilerepair.web.converter.EmployeeConverter;
import pl.choczaj.spring.mobilerepair.web.converter.PartConverter;

import javax.persistence.EntityManagerFactory;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public EmployeeConverter employeeConverter() {
        return new EmployeeConverter();
    }

    @Bean
    public PartConverter partConverter() {
        return new PartConverter();
    }

    @Bean
    public CustomerConverter customerConverter() {
        return new CustomerConverter();
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(employeeConverter());
        registry.addConverter(partConverter());
        registry.addConverter(customerConverter());
    }

}
