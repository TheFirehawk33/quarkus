package org.hemit;

import io.quarkus.test.junit.QuarkusTest;
import org.hemit.model.CreateResponse;
import org.hemit.model.Tournament;
import org.hemit.services.TournamentRepository;
import org.hemit.utils.TournamentUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class TournamentResourceTest {

    @BeforeEach
    public void setup()
    {
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
        assertThat(response.content.id, is(null));
    }

    @Test
    public void create_withEmptyName_should_returnBadRequest() {

        StatusAndContent<CreateResponse> response = TournamentUtils.createTournament("");

        assertThat(response.statusCode, is(400));
        assertThat(response.content.id, is(null));
    }

    @Test
    public void create_should_returnAlreadyExist() {

        TournamentUtils.createTournament("Unreal Tournament");
        StatusAndContent<CreateResponse> response = TournamentUtils.createTournament("Unreal Tournament");

        assertThat(response.statusCode, is(400));
        assertThat(response.content.id, is(notNullValue()));
    }

    @Test
    public void get_should_return_created_tournament() {
        String tournamentName = "New tournament";

        StatusAndContent<CreateResponse> createResponse = TournamentUtils.createTournament(tournamentName);
        StatusAndContent<Tournament> getResponse = TournamentUtils.getTournamentById(createResponse.content.id);

        assertThat(getResponse.statusCode, is(200));
        assertThat(getResponse.content.name, is(tournamentName));
    }

    @Test
    public void get_tournament_with_no_id() {
        StatusAndContent<Tournament> getResponse = TournamentUtils.getTournamentById(null);

        assertThat(getResponse.statusCode, is(404));
        assertThat(getResponse.content.name, is(null));
    }

    @Test
    public void get_tournament_false_id_should_return_404(){
        StatusAndContent<Tournament> getResponse = TournamentUtils.getTournamentById("AAAAA");

        assertThat(getResponse.statusCode, is(404));
        assertThat(getResponse.content.name, is(null));
    }
}


