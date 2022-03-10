package org.hemit.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@MongoEntity(collection="ThePerson")
public class Participant extends PanacheMongoEntity {
    @BsonProperty("name")
    String name;

    @BsonProperty("elo")
    int elo;

    @BsonProperty("_id")
    @NotEmpty
    String id;

    public Participant(String name, int elo, String id) {
        this.name = name;
        this.elo = elo;
        this.id = id;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }
}
