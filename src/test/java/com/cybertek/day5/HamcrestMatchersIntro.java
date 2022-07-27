package com.cybertek.day5;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HamcrestMatchersIntro {

    @DisplayName("")
    @Test
    public void simpleTest1(){

        assertThat(5+5, is(10));
        assertThat(5+5, equalTo(10));

        //matchers has 2 overloaded version
        //first that accept actual value
        // second that accept another matchers
       // below example is method is accepting another matchers equal to make it readable
        assertThat(5+5, is(equalTo(10)));
        assertThat(5+5, (not(9)));
        assertThat(5+5, is(not(equalTo(9))));

        //number comparison
        assertThat(5+5, is(greaterThan(9)));





    }

    @DisplayName("Assertion with string")
    @Test
    public void stringHmcrest(){
        String text = "B22 is learning Hamcrest";

        //checking for equality is same as numbers
        assertThat(text, is("B22 is learning Hamcrest"));
        assertThat(text, equalTo("B22 is learning Hamcrest"));
        assertThat(text, is(equalTo("B22 is learning Hamcrest")));

        //check if this text starts with B22
        assertThat(text, startsWith("B22"));

        assertThat(text, startsWithIgnoringCase("b22"));

        assertThat(text, endsWith("rest"));

        //checj if text contains learning
        assertThat(text, containsString("learning"));
        assertThat(text, containsStringIgnoringCase("learning"));

        String str = " ";

        // check if above str is blank
        assertThat(str, blankString());

        assertThat(str.trim(), emptyString());



    }



}
