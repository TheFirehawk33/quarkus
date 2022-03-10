package org.hemit;

import org.hemit.model.CreateResponse;
import org.hemit.model.Participant;
import org.hemit.utils.ParticipantUtils;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParticipantResourceTest {

        @Test
        public void create_should_return_id(){
                Participant bob = new Participant("bob",120);
                //assertThat(bob.getId(),is("12"));
        }

        @Test
        public void create_null_should_return_bad_request() {
                StatusAndContent<CreateResponse> response = ParticipantUtils.createParticipant(null,0);

                assertThat(response.statusCode, is(400));
        }

        @Test
        public void create_empty_should_return_bad_request() {
                StatusAndContent<CreateResponse> response = ParticipantUtils.createParticipant("",0);

                assertThat(response.statusCode, is(400));
        }

        @Test
        public void create_elo_null_should_return_bad_request() {
                StatusAndContent<CreateResponse> response = ParticipantUtils.createParticipant("bob",-1);

                assertThat(response.statusCode,is(400));
        }


}
