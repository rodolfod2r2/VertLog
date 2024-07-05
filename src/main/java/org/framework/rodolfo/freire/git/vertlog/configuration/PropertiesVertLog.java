package org.framework.rodolfo.freire.git.vertlog.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * This class represents property settings for integration with Swagger
 * in the specific context of the VertLog system.
 * <p>
 * It uses values injected via @Value annotations to define information about the documentation
 * and the server associated with Swagger.
 */

@Primary
@Configuration
@Getter
@NoArgsConstructor
public class PropertiesVertLog {

    @Value("${vertlog-swagger.info.title}")
    private String docInfoTitle;
    @Value("${vertlog-swagger.info.description}")
    private String docInfoDescription;
    @Value("${vertlog-swagger.info.version}")
    private String docInfoVersion;
    @Value("${vertlog-swagger.info.termsOfServiceUrl}")
    private String docInfoTermsOfServiceUrl;
    @Value("${vertlog-swagger.info.contact.name}")
    private String docInfoContactName;
    @Value("${vertlog-swagger.info.contact.url}")
    private String docInfoContactUrl;
    @Value("${vertlogs-wagger.info.contact.email}")
    private String docInfoContactEmail;
    @Value("${vertlog-swagger.info.license}")
    private String docInfoLicense;
    @Value("${vertlog-swagger.info.licenseUrl}")
    private String docInfoLicenseUrl;
    @Value("${vertlog-swagger.server.url}")
    private String docServerUrl;
    @Value("${vertlog-swagger.server.description}")
    private String docServerDescription;

}