package com.isd.game.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.isd.game.converter.MatchHistoryConverter;

@Configuration
public class MatchHistoryConverterConfiguration {

    @Bean
    MatchHistoryConverter matchHistoryConverter() {
        return new MatchHistoryConverter();
    }
}