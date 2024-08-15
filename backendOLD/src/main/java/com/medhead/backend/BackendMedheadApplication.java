package com.medhead.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BackendMedheadApplication {

    private static final Logger logger = LoggerFactory.getLogger(BackendMedheadApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackendMedheadApplication.class, args);
        logger.info("Application BackendMedhead démarrée avec succès!");
    }
}