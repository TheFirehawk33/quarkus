package org.hemit.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.ArrayList;
import java.util.List;

public class Tournament extends PanacheMongoEntity {

    @UniqueElements
    public String name;
    public List<Participant> participants = new ArrayList<>();

    public Tournament(String name) {
        this.name = name;
    }

    public Tournament() {
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }
}
