package org.hemit.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.hibernate.validator.constraints.UniqueElements;

public class Participant extends PanacheMongoEntity {
    @UniqueElements
    public String name;

    public int elo;

    public Participant(String name, int elo) {
        this.name = name;
        this.elo = elo;
    }
}
