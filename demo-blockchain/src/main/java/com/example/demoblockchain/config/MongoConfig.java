package com.example.demoblockchain.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration
@EnableReactiveMongoRepositories(
    basePackages = "com.example.demoblockchain.repository")
public class MongoConfig extends AbstractReactiveMongoConfiguration {

  @Override
  protected String getDatabaseName() {
    return "testblockchain";
  }

  @Primary
  @Override
  public MongoClient reactiveMongoClient() {
    return MongoClients.create(); // connect to localhost:27017 by default
  }

}
