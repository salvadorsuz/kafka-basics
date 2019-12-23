package com.example.kafkaBasics.commons.delegate;


import java.util.Properties;

@FunctionalInterface
public interface ConfigPropertiesDelegate {
    Properties getProperties() ;
}
