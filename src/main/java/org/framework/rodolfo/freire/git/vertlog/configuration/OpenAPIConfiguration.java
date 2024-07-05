package org.framework.rodolfo.freire.git.vertlog.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * This class configures and returns a custom OpenAPI instance for integration with Swagger.
 * It uses the settings defined in {@link PropertiesVertLog} to fill in the information
 * necessary documentation and associated server.
 */

@Configuration
@OpenAPIDefinition
public class OpenAPIConfiguration {

    private final PropertiesVertLog propertiesVertlog;

    /**
     * Constructor that injects the configuration properties defined in {@link PropertiesVertLog}.
     *
     * @param propertiesVertlog Configuration properties for integration with Swagger.
     */
    public OpenAPIConfiguration(PropertiesVertLog propertiesVertlog) {
        this.propertiesVertlog = propertiesVertlog;
    }

    /**
     * Method responsible for creating and configuring a custom OpenAPI instance.
     *
     * @return OpenAPI instance configured with the information defined in {@link PropertiesVertLog}.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(propertiesVertlog.getDocInfoTitle())
                        .description(propertiesVertlog.getDocInfoDescription())
                        .version(propertiesVertlog.getDocInfoVersion())
                        .contact(new Contact()
                                .name(propertiesVertlog.getDocInfoContactName())
                                .email(propertiesVertlog.getDocInfoContactEmail())
                                .url(propertiesVertlog.getDocInfoContactUrl())
                        )
                        .license(new License()
                                .name(propertiesVertlog.getDocInfoLicense())
                                .url(propertiesVertlog.getDocInfoLicenseUrl())
                        )
                        .termsOfService(propertiesVertlog.getDocInfoTermsOfServiceUrl())
                )
                .servers(Collections.singletonList(
                        new Server()
                                .url(propertiesVertlog.getDocServerUrl())
                                .description(propertiesVertlog.getDocServerDescription())
                ));
    }
}


