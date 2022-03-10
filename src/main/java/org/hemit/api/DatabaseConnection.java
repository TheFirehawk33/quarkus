package org.hemit.api;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DatabaseConnection {

    private MongoDatabase database;

    public DatabaseConnection() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://tournaments:prout@cluster0.ytl7e.mongodb.net/tournaments?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("tournaments");
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
