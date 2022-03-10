package org.hemit.services;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.hemit.api.DatabaseConnection;
import org.hemit.model.Tournament;
import org.hemit.model.TournamentToCreate;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;

@ApplicationScoped
public class TournamentRepository {

    private static final HashMap<String, Tournament> tournaments = new HashMap<>();
    private static final MongoCollection<org.bson.Document> db = new DatabaseConnection()
            .getDatabase()
            .getCollection("tournaments");

    public static String create(TournamentToCreate tournament) {
        if(db.find(new Document("name", tournament.name)).first() != null) {
            return null;
        }

        InsertOneResult insertion = db.insertOne(tournament.getJsonTournament());

        return insertion.getInsertedId().asObjectId().getValue().toString();
    }

    public static Tournament getById(String id) {
        if(!ObjectId.isValid(id)) {
            return null;
        }

        System.out.println(id);
        Document doc = db.find(new Document("_id", new ObjectId(id))).first();

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
    public static void cleanDatabase(){
        db.drop();
    }
}
