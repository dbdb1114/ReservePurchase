package com.resurvepurchase.authservice.config;

import com.resurvepurchase.authservice.entity.Member;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.test.StepVerifier;

@Slf4j
@Configuration
public class R2DBCConfig  extends AbstractR2dbcConfiguration {
    ConnectionFactory connectionFactory = ConnectionFactories.get("r2dbc:mysql:mem:///test?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
    R2dbcEntityTemplate template = new R2dbcEntityTemplate(connectionFactory);

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return connectionFactory;
    }
}
