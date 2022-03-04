package org.hemit.model;

import java.util.UUID;

public class Participant {
    String name;
    int elo;
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
