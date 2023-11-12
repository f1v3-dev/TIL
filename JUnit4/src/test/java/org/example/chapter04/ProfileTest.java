package org.example.chapter04;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ProfileTest {


    @Test
    public void matches() {
        Profile profile = new Profile("Bull Hockey, Inc.");
        Question question = new BooleanQuestion(1, "Got milk?");

        // must-match 항목이 맞지 않으면 false
        profile.add(new Answer(question, Bool.FALSE));
        Criteria criteria = new Criteria();

        criteria.add(
                new Criterion(new Answer(question, Bool.TRUE),
                        Weight.MustMatch));

        assertFalse(profile.matches(criteria));

        // don't care 항목에 대해서는 true
        profile.add(new Answer(question, Bool.FALSE));
        criteria = new Criteria();
        criteria.add(
                new Criterion(new Answer(question, Bool.TRUE),
                        Weight.DontCare));

        assertTrue(profile.matches(criteria));
    }
}
