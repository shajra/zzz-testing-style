package org.atxsm.num2words;


import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class NumberWordsByBehaviorTest {

    @Test
    public void making_words_for_zero() {
        assertNumberGivesWord(0, "zero");
    }

    @Test
    public void making_words_for_single_digit_positive_numbers() {
        assertNumberGivesWord(1, "one");
        assertNumberGivesWord(2, "two");
        assertNumberGivesWord(3, "three");
        assertNumberGivesWord(4, "four");
        assertNumberGivesWord(5, "five");
        assertNumberGivesWord(6, "six");
        assertNumberGivesWord(7, "seven");
        assertNumberGivesWord(8, "eight");
        assertNumberGivesWord(9, "nine");
    }

    @Test
    public void making_words_for_numbers_with_irregular_terms() {
        assertNumberGivesWord(11, "eleven");
        assertNumberGivesWord(12, "twelve");
        assertNumberGivesWord(13, "thirteen");
        assertNumberGivesWord(14, "fourteen");
        assertNumberGivesWord(15, "fifteen");
        assertNumberGivesWord(16, "sixteen");
        assertNumberGivesWord(17, "seventeen");
        assertNumberGivesWord(18, "eighteen");
        assertNumberGivesWord(19, "nineteen");
    }

    @Test
    public void making_words_for_multiples_of_ten() {
        assertNumberGivesWord(10, "ten");
        assertNumberGivesWord(20, "twenty");
        assertNumberGivesWord(30, "thirty");
        assertNumberGivesWord(40, "forty");
        assertNumberGivesWord(50, "fifty");
        assertNumberGivesWord(60, "sixty");
        assertNumberGivesWord(70, "seventy");
        assertNumberGivesWord(80, "eighty");
        assertNumberGivesWord(90, "ninety");
    }

    @Test
    public void making_a_word_with_a_hundred_followed_by_nothing() {
        assertNumberGivesWord(100, "one hundred");
    }

    @Test
    public void making_a_word_with_a_hundred_followed_by_something() {
        assertNumberGivesWord(101, "one hundred one");
    }

    @Test
    public void making_words_for_powers_of_one_thousand() {
        assertNumberGivesWord(1000, "one thousand");
        assertNumberGivesWord(1000000, "one million");
        assertNumberGivesWord(1000000000, "one billion");
        assertNumberGivesWord(1000000000000L, "one trillion");
        assertNumberGivesWord(1000000000000000L, "one quadrillion");
        assertNumberGivesWord(1000000000000000000L, "one quintillion");
    }

    @Test
    public void making_a_word_with_a_hyphen() {
        assertNumberGivesWord(21, "twenty-one");
    }

    @Test
    public void making_a_word_with_some_skipped_powers_of_thousand() {
        assertNumberGivesWord(1000011, "one million eleven");
    }

    @Test
    public void making_a_word_for_greatest_negative_number() {
        assertNumberGivesWord(-1, "negative one");
    }

    @Test
    public void making_a_word_for_least_negative_number() {
        assertEquals(Long.MIN_VALUE, -9223372036854775808L);
        assertNumberGivesWord(Long.MIN_VALUE,
                "negative nine quintillion" +
                        " two hundred twenty-three quadrillion" +
                        " three hundred seventy-two trillion" +
                        " thirty-six billion" +
                        " eight hundred fifty-four million" +
                        " seven hundred seventy-five thousand" +
                        " eight hundred eight");
    }

    @Test
    public void making_a_word_for_greatest_positive_number() {
        assertEquals(Long.MAX_VALUE, 9223372036854775807L);
        assertNumberGivesWord(Long.MAX_VALUE, "nine quintillion" +
                " two hundred twenty-three quadrillion" +
                " three hundred seventy-two trillion" +
                " thirty-six billion" +
                " eight hundred fifty-four million" +
                " seven hundred seventy-five thousand" +
                " eight hundred seven");
    }

    private void assertNumberGivesWord(long value, String expectedWord) {
        String actualWord = NumberWords.get(value);
        assertEquals("when making a word from: " + value, expectedWord, actualWord);
    }

}