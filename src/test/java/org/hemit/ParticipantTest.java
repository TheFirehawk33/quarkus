package org.hemit;

import org.hemit.model.Participant;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParticipantTest {

        @Test
        public void create_should_return_id(){
                Participant bob = new Participant("bob",120,"12");
                assertThat(bob.getId(), is(12));
        }

        @Test
        public void create_should_return_() {

        }
}
