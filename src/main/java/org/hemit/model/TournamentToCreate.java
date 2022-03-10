package org.hemit.model;

import org.bson.Document;

public class TournamentToCreate {
    public String name;

    public TournamentToCreate() {}

    public TournamentToCreate(String name) {
        this.name = name;
    }

    public Document getJsonTournament() {
        return new Document("name", this.name);
    }
}
