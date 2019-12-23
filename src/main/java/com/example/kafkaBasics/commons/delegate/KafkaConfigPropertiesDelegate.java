package com.example.kafkaBasics.commons.delegate;

import com.example.kafkaBasics.configuration.beans.Broker;
import com.example.kafkaBasics.configuration.KafkaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Properties;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.defaultString;

@Component
public abstract class KafkaConfigPropertiesDelegate implements ConfigPropertiesDelegate{

    /*
    @Value( "${kafka.broker.host}" )
    private String kafkaBrokerHost;

    @Value( "${kafka.broker.port}" )
    private Integer kafkaBrokerPort;
     */

    @Autowired
    protected KafkaConfig config;

    private Function<Broker,String> getBootStrapServer = (Broker broker) ->
            new StringBuilder()
                    .append(defaultString(broker.getHost(), EMPTY))
                    .append(":")
                    .append(requireNonNull(broker.getPort()))
                    .toString();

    protected String getBootStrapServer(Broker broker) {
        return Optional.ofNullable(broker)
                .map(getBootStrapServer)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Not valid kafka configuration"));
    }

    @Override
    public Properties getProperties() {
        return getProperties(config);
    }

    public abstract Properties getProperties(KafkaConfig config) ;
}
