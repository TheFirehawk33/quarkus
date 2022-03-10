package org.hemit.api;

import io.netty.util.internal.StringUtil;
import io.quarkus.panache.common.Sort;
import org.bson.types.ObjectId;
import org.hemit.model.CreateResponse;
import org.hemit.model.Tournament;
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
    public Response createTournament(Tournament tournament) {

        if (!validateTournament(tournament))
            return Response.status(Response.Status.BAD_REQUEST).build();

        tournamentRepository.persist(tournament);
        String id = tournamentRepository.findAll(
            Sort.by("_id", Sort.Direction.Descending))
                .firstResult()
                .id
                .toString();
        return Response.status(Response.Status.CREATED).entity(new CreateResponse(id)).build();
    }

    @GET
    @Path("{id}")
    public Response getTournamentById(@PathParam("id") String id) {
        Tournament result = null;
        if (ObjectId.isValid(id)) {
            result = tournamentRepository.findById(new ObjectId(id));
        }

        if (result == null || id.equals("null"))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    private boolean validateTournament(Tournament tournament) {

        if (Objects.isNull(tournament))
            return false;

        if (StringUtil.isNullOrEmpty(tournament.name))
            return false;

        return Objects.isNull(tournamentRepository.findByName(tournament.name));
    }

}
