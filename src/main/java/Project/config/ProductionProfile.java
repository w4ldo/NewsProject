package Project.config;

import java.net.URI;
import java.net.URISyntaxException;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.apache.commons.dbcp2.BasicDataSource;

@Profile("production")
@Configuration
public class ProductionProfile {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        System.out.println("Hello world!");
        return DataSourceBuilder.create().build();
    }
}
