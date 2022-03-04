package org.hemit.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;

@MongoEntity(collection="tournaments", database = "tournaments")
public class Tournament extends PanacheMongoEntity {
    @BsonProperty("name")
    public String name;

    public Tournament(String name) {
        this.name = name;
    }

    public Tournament() {}
}
