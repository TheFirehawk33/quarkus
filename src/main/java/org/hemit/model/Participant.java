package org.hemit.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.UniqueElements;

public class Participant extends PanacheMongoEntity {
    public ObjectId participantId;
    @UniqueElements
    public String name;

    public int elo;

    public Participant(String name, int elo, ObjectId participantId) {
        this.participantId = participantId;
        this.name = name;
        this.elo = elo;
    }

    public Participant() {
    }
}
