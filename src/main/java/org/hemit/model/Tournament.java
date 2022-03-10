package org.hemit.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.util.ArrayList;
import java.util.List;

public class Tournament extends PanacheMongoEntity {
    public String name;
    public List<Participant> participants = new ArrayList<>();

    public Tournament(String name) {
        this.name = name;
    }

    public Tournament() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }
}
