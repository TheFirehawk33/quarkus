package org.hemit.services;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.hemit.model.Participant;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ParticipantRepository implements PanacheMongoRepository<Participant> {

    public Participant findByName(String name) {
        return find("name", name).firstResult();
    }

    public static void cleanLocalBase() {
    }
}
