package org.hemit.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

public class Tournament extends PanacheMongoEntity {
    public String name;

    public Tournament(String name) {
        this.name = name;
    }

    public Tournament() { }
}
