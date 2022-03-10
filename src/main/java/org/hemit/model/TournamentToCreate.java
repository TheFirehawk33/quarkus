package org.hemit.model;

import javax.validation.constraints.NotEmpty;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.Document;

@MongoEntity(collection="tournaments", database = "tournaments")
public class TournamentToCreate extends PanacheMongoEntity {

    @NotEmpty
    public String name;

    public TournamentToCreate() {}

    public TournamentToCreate(String name) {
        this.name = name;
    }

    public Document getJsonTournament() {
        return new Document("name", this.name);
    }
}
