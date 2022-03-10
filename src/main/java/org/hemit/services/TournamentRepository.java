package org.hemit.services;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.hemit.model.Tournament;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;

@ApplicationScoped
public class TournamentRepository implements PanacheMongoRepository<Tournament> {

    public Tournament findByName(String name) {
        return find("name", name).firstResult();
    }

    public void cleanDatabase(){
        deleteAll();
    }
}
