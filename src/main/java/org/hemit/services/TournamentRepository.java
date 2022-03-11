package org.hemit.services;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.bson.types.ObjectId;
import org.hemit.model.Participant;
import org.hemit.model.Tournament;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TournamentRepository implements PanacheMongoRepository<Tournament> {

    public Tournament findByName(String name) {
        return find("name", name).firstResult();
    }

    public void cleanDatabase(){
        deleteAll();
    }

    public void addParticipant(String tournamentId,Participant participant) {
        Tournament t = findById(new ObjectId(tournamentId));
        t.participants.add(participant);
        update(t);
    }
}
