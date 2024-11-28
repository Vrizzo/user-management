package com.mybank.user.core.configurations;

import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.WildcardTypePermission;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.MongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoFactory;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoSettingsFactory;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.axonframework.spring.config.AxonConfiguration;
import org.axonframework.spring.config.SpringConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class AxonConfig {
    @Value("${spring.data.mongodb.host}")
    private String mongoHost;
    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;
    @Value("${spring.data.mongodb.port}")
    private int mongoPort;

    @Bean
    public MongoClient mongo() {
        MongoFactory mongoFactory = new MongoFactory();
        MongoSettingsFactory mongoSettingsFactory = new MongoSettingsFactory();
        mongoSettingsFactory.setMongoAddresses(Collections.singletonList(new ServerAddress(mongoHost, mongoPort)));
        mongoFactory.setMongoClientSettings(mongoSettingsFactory.createMongoClientSettings());

        return mongoFactory.createMongo();
    }

    @Bean
    public MongoTemplate axonMongoTemplate() {

        return DefaultMongoTemplate.builder()
                .mongoDatabase(mongo(), mongoDatabase)
                .build();
    }

    //    @Bean
//    @Qualifier("messageSerializer")
//    public Serializer serializer() {
//        return JacksonSerializer.defaultSerializer();
//    }
    @Bean
    @Qualifier("defaultAxonXStream")
    public XStream xStream() {
        XStream xStream = new XStream();
        String[] patterns = {"com.mybank.**"};
        xStream.addPermission(new WildcardTypePermission(patterns));

        return xStream;
    }

//    @Bean
//    public Serializer defaultSerializer() {
//        // Set the secure types on the xStream instance
//        XStream xStream = new XStream();
//        return XStreamSerializer.builder()
//                .xStream(xStream)
//                .build();
//    }

    // But for all our messages we'd prefer the JacksonSerializer due to JSON's smaller format
//    @Bean
//    @Qualifier("messageSerializer")
//    public Serializer messageSerializer() {
//        return JacksonSerializer.defaultSerializer();
//    }

    @Bean
    public TokenStore tokenStore(Serializer serializer) {
        System.out.println("serializer: " + serializer.getClass().getName());
        return MongoTokenStore
                .builder()
                .mongoTemplate(axonMongoTemplate())
                .serializer(serializer)
                .build();
    }

    @Bean
    public EventStorageEngine eventStorageEngine() {
        return MongoEventStorageEngine
                .builder()
                .mongoTemplate(axonMongoTemplate())
                .build();
    }

    @Bean
    public EmbeddedEventStore embeddedEventStore(EventStorageEngine eventStorageEngine) {
        return EmbeddedEventStore.builder()
//                .messageMonitor(pringAxonConfiguration.messageMonitor(EventStore.class, "eventStore"))
                .storageEngine(eventStorageEngine)
                .build();
    }
}
