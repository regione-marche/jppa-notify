package it.maggioli.pagopa.pagonet.jppanotify.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
public class ApplicationProperties {

    @Value( "${postgres.driver}" )
    private String postgresDriver;
    @Value( "${postgres.url}" )
    private String postgresUrl;
    @Value( "${postgres.username}" )
    private String postgresUsername;
    @Value( "${postgres.password}" )
    private String postgresPassword;

}

