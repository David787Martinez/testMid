package com.chakray.testmid.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author luis-barrera
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
           title = "Web Service TestMid",
           version = "1.0.0",
           description = "Web service para prueba técnica desarrollador Luis David Barrera."
        ),
        servers = {@Server(url = "/testmid", description = "Url del web service")}
)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "BearerToken", scheme = "bearer", in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {
    
}
