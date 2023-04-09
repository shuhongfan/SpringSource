package com.abc.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("wrap.service")
public class WrapServiceProperties {
    private String prefix;
    private String suffix;
}
