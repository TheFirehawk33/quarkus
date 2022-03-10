package org.hemit.services;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.hemit.api.DatabaseConnection;
import org.hemit.model.Tournament;
import org.hemit.model.TournamentToCreate;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.UUID;

@ApplicationScoped
public class TournamentRepository {

    private static final HashMap<String, Tournament> tournaments = new HashMap<>();
    private static final MongoCollection<org.bson.Document> db = new DatabaseConnection()
            .getDatabase()
            .getCollection("tournaments");

    public static String create(TournamentToCreate tournament) {
        String id = UUID.randomUUID().toString();
        tournaments.put(id, new Tournament(tournament.name));
        db.insertOne(tournament.getJsonTournament());

        return id;
    }

    public static Tournament getById(String id) {
        Document doc = db.find(new Document("_id",new ObjectId(id))).first();

        return doc != null ? new Tournament(doc.getString("name")) : null;
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
