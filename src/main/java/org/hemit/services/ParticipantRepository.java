package org.hemit.services;

import org.hemit.model.Participant;
import org.hemit.model.Tournament;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.UUID;

@ApplicationScoped
public class ParticipantRepository {

    private static final HashMap<String, Participant> participants = new HashMap<>();

    public static String create(Participant participant) {
        String id = UUID.randomUUID().toString();
        participants.put(id, new Participant(participant.getName(), participant.getElo(), participant.getId()));
        return id;
    }

    public static Participant getById(String id) {
        return participants.get(id);
    }

    public static Participant getByName(String name) {
        for (Participant participant : participants.values()) {
            if (participant.getName().equals(name))
                return participant;
        }
        return null;
    }

    public static void cleanLocalBase() {
        participants.clear();
    }
}
