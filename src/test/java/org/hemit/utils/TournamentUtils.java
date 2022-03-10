package org.hemit.utils;

import io.restassured.response.ValidatableResponse;
import org.hemit.StatusAndContent;
import org.hemit.model.CreateResponse;
import org.hemit.model.Tournament;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class TournamentUtils {
    public static StatusAndContent<CreateResponse> createTournament(String tournamentName) {
        ValidatableResponse response = given()
                .contentType("application/json")
                .body(new Tournament(tournamentName))
                .when()
                .post("/tournaments")
                .then();

        int statusCode = response.extract().statusCode();
        CreateResponse content = null;
        if (statusCode == 201) {
            content = response.extract().as(CreateResponse.class);
        }

        return new StatusAndContent<>(statusCode, content);
    }

    public static StatusAndContent<Tournament> getTournamentById(String id) {
        ValidatableResponse response = when().get("/tournaments/"+id).then();

        int statusCode = response.extract().statusCode();
        Tournament content = null;

        if(statusCode == Response.Status.OK.getStatusCode()) {
            content = response.extract().as(Tournament.class);
        }

        return new StatusAndContent<>(statusCode, content);
    }

    public static StatusAndContent<Tournament> getTournamentByS(String id) {
        return null;
    }
}
