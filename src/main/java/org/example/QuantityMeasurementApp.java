package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class QuantityMeasurementApp {
    private static final Logger logger = Logger.getLogger(QuantityMeasurementApp.class.getName());

    public static void main(String[] args) {
        logger.info("Quantity Measurement App Started.....................");
        SpringApplication.run(QuantityMeasurementApp.class, args);
    }
}