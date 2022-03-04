package org.hemit.api;

import io.netty.util.internal.StringUtil;
import org.hemit.model.CreateResponse;
import org.hemit.model.Tournament;
import org.hemit.model.TournamentToCreate;
import org.hemit.services.TournamentRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("tournaments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TournamentController {

    @Inject
    TournamentRepository tournamentRepository;

    @POST
    public Response createTournament(TournamentToCreate tournament) {

        if (!validateTournament(tournament))
            return Response.status(Response.Status.BAD_REQUEST).build();

        String id = tournamentRepository.create(tournament);

        return Response.status(Response.Status.CREATED).entity(new CreateResponse(id)).build();
    }

    @GET
    @Path("{id}")
    public Response getTournamentById(@PathParam("id") String id) {
        Tournament result = tournamentRepository.getById(id);
        if(result == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.FOUND).entity(result).build();

    }

    private boolean validateTournament(TournamentToCreate tournament) {

        if (Objects.isNull(tournament))
            return false;

        if (StringUtil.isNullOrEmpty(tournament.name))
            return false;

        return Objects.isNull(tournamentRepository.getByName(tournament.name));
    }

}
