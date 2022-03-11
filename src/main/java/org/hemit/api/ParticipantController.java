package org.hemit.api;


import io.netty.util.internal.StringUtil;
import org.bson.types.ObjectId;
import org.hemit.model.Participant;
import org.hemit.services.TournamentRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("participants")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ParticipantController {

    @Inject
    TournamentRepository tournamentRepository;

    @POST
    @Path("{tournamentId}")
    public Response createParticipant(@PathParam("tournamentId") String tournamentId, Participant participant) {

        if (!validateParticipant(participant))
            return Response.status(Response.Status.BAD_REQUEST).build();

        //participantRepository.persist(participant);
        participant.participantId = new ObjectId();
        tournamentRepository.addParticipant(tournamentId, participant);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("{tournamentId}/{participantId}")
    public Response getParticipantById(@PathParam("participantId") String participantId, @PathParam("tournamentId") String tournamentId) {
        //Participant result = participantRepository.findById(new ObjectId(id));
        if(null == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.OK).entity(null).build();
    }

    private boolean validateParticipant(Participant participant) {

        if (Objects.isNull(participant))
            return false;

        if (StringUtil.isNullOrEmpty(participant.name))
            return false;

        return Objects.isNull(tournamentRepository.findByName(participant.name));
    }
}
