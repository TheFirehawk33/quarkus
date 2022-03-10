package org.hemit.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.util.ArrayList;
import java.util.List;

public class Tournament {
    public String name;
    public List<Participant> participants = new ArrayList<>();

    public Tournament(String name) {
        this.name = name;
    }

    public Tournament() {
    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

}
