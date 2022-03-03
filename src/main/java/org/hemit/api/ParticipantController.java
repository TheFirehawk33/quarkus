package org.hemit.api;


import io.netty.util.internal.StringUtil;
import org.hemit.model.CreateResponse;
import org.hemit.model.Participant;
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

        String id = participantRepository.create(participant);

        return Response.status(Response.Status.CREATED).entity(new CreateResponse(id)).build();
    }

    @GET
    @Path("{id}")
    public Participant getParticipantById(@PathParam("id") String id) {
        return participantRepository.getById(id);
    }

    private boolean validateParticipant(Participant participant) {

        if (Objects.isNull(participant))
            return false;

        if (StringUtil.isNullOrEmpty(participant.getName()))
            return false;

        return Objects.isNull(participantRepository.getByName(participant.getName()));
    }
}
