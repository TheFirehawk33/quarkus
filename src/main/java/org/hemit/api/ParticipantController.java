package org.hemit.api;


import io.netty.util.internal.StringUtil;
import org.bson.types.ObjectId;
import org.hemit.model.CreateResponse;
import org.hemit.model.Participant;
import org.hemit.model.Tournament;
import org.hemit.services.ParticipantRepository;

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
    ParticipantRepository participantRepository;

    @POST
    public Response createParticipant(Participant participant) {

        if (!validateParticipant(participant))
            return Response.status(Response.Status.BAD_REQUEST).build();

        participantRepository.persist(participant);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("{id}")
    public Response getParticipantById(@PathParam("id") String id) {
        Participant result = participantRepository.findById(new ObjectId(id));
        if(result == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    private boolean validateParticipant(Participant participant) {

        if (Objects.isNull(participant))
            return false;

        if (StringUtil.isNullOrEmpty(participant.name))
            return false;

        return Objects.isNull(participantRepository.findByName(participant.name));
    }
}
