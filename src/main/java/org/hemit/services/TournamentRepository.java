package org.hemit.services;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.hemit.model.Tournament;
import org.hemit.model.TournamentToCreate;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.UUID;



@ApplicationScoped
@MongoEntity(collection="tournaments")
public class TournamentRepository extends PanacheMongoEntity {

    private static final HashMap<String, Tournament> tournaments = new HashMap<>();

    public static String create(TournamentToCreate tournament) {
        String id = UUID.randomUUID().toString();
        tournaments.put(id, new Tournament(tournament.name));
        tournament.persist();
        tournament.update();

        return id;
    }

    public static Tournament getById(String id) {
        return tournaments.get(id);
    }

    public static Tournament getByName(String name)
    {
        for(Tournament tournament : tournaments.values())
        {
            if(tournament.name.equals(name))
                return tournament;
        }
        return null;
    }
    public static void cleanLocalBase(){
        tournaments.clear();
    }
}
