package org.hemit;

import io.quarkus.test.junit.QuarkusTest;
import org.hemit.model.CreateResponse;
import org.hemit.model.Tournament;
import org.hemit.services.TournamentRepository;
import org.hemit.utils.ParticipantUtils;
import org.hemit.utils.TournamentUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class TournamentResourceTest {

    @BeforeEach
    public void setup() {
        TournamentRepository.cleanLocalBase();
    }

    @Test
    public void create_should_returnAnId() {

        StatusAndContent<CreateResponse> response = TournamentUtils.createTournament("Unreal Tournament");

        assertThat(response.statusCode, is(201));
        assertThat(response.content.id, is(notNullValue()));
    }

    @Test
    public void create_withoutName_should_returnBadRequest() {

        StatusAndContent<CreateResponse> response = TournamentUtils.createTournament(null);

        assertThat(response.statusCode, is(400));
    }

    @Test
    public void create_withEmptyName_should_returnBadRequest() {

        StatusAndContent<CreateResponse> response = TournamentUtils.createTournament("");

        assertThat(response.statusCode, is(400));
    }

    @Test
    public void create_should_returnAlreadyExist() {

        TournamentUtils.createTournament("Unreal Tournament");
        StatusAndContent<CreateResponse> response = TournamentUtils.createTournament("Unreal Tournament");

        assertThat(response.statusCode, is(400));
    }

    @Test
    public void get_should_return_created_tournament() {
        String tournamentName = "New tournament";

        StatusAndContent<CreateResponse> createResponse = TournamentUtils.createTournament(tournamentName);
        StatusAndContent<Tournament> getResponse = TournamentUtils.getTournamentById(createResponse.content.id);

        assertThat(getResponse.statusCode, is(Response.Status.OK.getStatusCode()));
        assertThat(getResponse.content.getName(), is(tournamentName));
    }

    @Test
    public void get_tournament_with_no_id() {
        StatusAndContent<Tournament> getResponse = TournamentUtils.getTournamentById(null);

        assertThat(getResponse.statusCode, is(404));
    }

    @Test
    public void get_tournament_false_id_should_return_404() {
        StatusAndContent<Tournament> getResponse = TournamentUtils.getTournamentById("AAAAA");

        assertThat(getResponse.statusCode, is(404));
    }

    @Test
    public void get_listParticipant_NominalCase_withoutParticipants() {
        StatusAndContent<CreateResponse> creationResponse = TournamentUtils.createTournament("Unreal Tournament");
        StatusAndContent<Tournament> getResponse = TournamentUtils.getTournamentById(creationResponse.content.id);

        assertThat(getResponse.statusCode, is(200));
        assertTrue(getResponse.content.getParticipants().isEmpty());

    }

    @Test
    public void get_listParticipant_withUnknownId_should_returnNotFound() {
        StatusAndContent<Tournament> response = TournamentUtils.getTournamentById("unknownId");

        assertThat(response.statusCode, is(404));
    }

    @Test
    public void get_listParticipant_withoutId_should_returnNotFound() {
        StatusAndContent<Tournament> response = TournamentUtils.getTournamentById(null);

        assertThat(response.statusCode, is(404));
    }

    @Test
    public void get_listParticipant_emptyId_should_returnNotFound() {
        StatusAndContent<Tournament> response = TournamentUtils.getTournamentById("");

        assertThat(response.statusCode, is(405));
    }

    @Test
    public void del_participantInTournament_withoutId_should_returnNotFound() {
       Response.Status response = TournamentUtils.delParticipant(null,null);

       assertThat(response, is(404));
    }

    @Test
    public void del_participantInTournament_withUnknownId_should_returnNotFound(){
        Response.Status response = TournamentUtils.delParticipant("toto",null);

        assertThat(response, is(404));
    }

    @Test
    public void del_participantInTournament_emptyId_should_returnNotFound(){
        Response.Status response = TournamentUtils.delParticipant("",null);

        assertThat(response, is(404));
    }

    @Test
    public void del_participant_withoutId_should_returnNotFound(){
        StatusAndContent<CreateResponse> createResponse = TournamentUtils.createTournament("newName");
        Response.Status response = TournamentUtils.delParticipant(createResponse.content.id,null);

        assertThat(response, is(404));
    }

    @Test
    public void del_participant_withUnknownId_should_returnNotFound(){
        StatusAndContent<CreateResponse> createTournamentResponse = TournamentUtils.createTournament("newName");
        ParticipantUtils.createParticipant("newName",0,"12");

        Response.Status response = TournamentUtils.delParticipant(createTournamentResponse.content.id,"1");

        assertThat(response, is(404));
    }

    @Test
    public void del_participant_emptyId_should_returnNotFound(){
        StatusAndContent<CreateResponse> createTournamentResponse = TournamentUtils.createTournament("newName");

        Response.Status response = TournamentUtils.delParticipant(createTournamentResponse.content.id,null);

        assertThat(response, is(404));
    }

    @Test
    public void del_participant_nominal()
    {
        StatusAndContent<CreateResponse> createTournamentResponse = TournamentUtils.createTournament("newName");
        StatusAndContent<CreateResponse> createParticipantResponse = ParticipantUtils.createParticipant("newName",0,"12");

        Response.Status response = TournamentUtils.delParticipant(createTournamentResponse.content.id,createParticipantResponse.content.id);

        assertThat(response, is(Response.Status.OK));
    }
}



