package org.framework.rodolfo.freire.git.vertlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class represents the main entry point for the Vertlog application.
 * It is annotated with {@link SpringBootApplication}, which indicates that it is a configuration class
 * Spring Boot and which also automatically activates default settings for the Spring application.
 */

@SpringBootApplication
public class VertLogApplication {

    /**
     * Main method that initializes and runs the VertLog application.
     *
     * @param args Command line arguments passed during application execution.
     */

    public static void main(String[] args) {
        SpringApplication.run(VertLogApplication.class, args);
    }

}
