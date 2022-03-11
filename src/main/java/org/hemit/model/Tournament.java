package org.hemit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.ArrayList;
import java.util.List;

public class Tournament extends PanacheMongoEntity {

    @UniqueElements
    @JsonProperty
    public String name;
    @JsonProperty
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
