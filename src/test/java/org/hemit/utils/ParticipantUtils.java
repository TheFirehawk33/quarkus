package org.hemit.utils;

import io.restassured.response.ValidatableResponse;
import org.bson.types.ObjectId;
import org.hemit.StatusAndContent;
import org.hemit.model.CreateResponse;
import org.hemit.model.Participant;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ParticipantUtils {

    public static StatusAndContent<CreateResponse> createParticipant(String participantName,int participantElo) {
        ValidatableResponse response = given()
                .contentType("application/json")
                .body(new Participant(participantName,participantElo,new ObjectId()))
                .when()
                .post("/participants")
                .then();

        int statusCode = response.extract().statusCode();
        CreateResponse content = null;
        if (statusCode == 201) {
            content = response.extract().as(CreateResponse.class);
        }

        return new StatusAndContent<>(statusCode, content);
    }

    public static StatusAndContent<Participant> getParticipantById(String id) {
        ValidatableResponse response = when().get("/participants/"+id).then();

        int statusCode = response.extract().statusCode();
        Participant content = null;
        if (statusCode == 200) {
            content = response.extract().as(Participant.class);
        }

        return new StatusAndContent<>(statusCode, content);
    }

}
