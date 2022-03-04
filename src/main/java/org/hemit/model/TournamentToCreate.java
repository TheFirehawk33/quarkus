package org.hemit.model;


import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;

@MongoEntity(collection="tournaments", database = "tournaments")
public class TournamentToCreate extends PanacheMongoEntity {
    @BsonProperty("name")
    public String name;

    public TournamentToCreate() {
    }

    public TournamentToCreate(String name) {
        this.name = name;
    }
}
