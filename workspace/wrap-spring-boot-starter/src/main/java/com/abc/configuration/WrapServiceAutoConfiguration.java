package com.abc.configuration;

import com.abc.service.WrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(WrapService.class)
@EnableConfigurationProperties(WrapServiceProperties.class)
public class WrapServiceAutoConfiguration {
    @Autowired
    private WrapServiceProperties properties;

    @Bean
    @ConditionalOnProperty(name = "wrap.service.enabled", matchIfMissing = true)
    public WrapService wrapService() {
        return new WrapService(properties.getPrefix(), properties.getSuffix());
    }

    @Bean
    @ConditionalOnMissingBean
    public WrapService wrapService2() {
        return new WrapService("", "");
    }

}
