package org.atxsm.num2words;


import java.util.ArrayDeque;
import java.util.Deque;


public class NumberWords {

    private static final String PRECOMPUTED_MIN_VALUE =
            "negative nine quintillion" +
                    " two hundred twenty-three quadrillion" +
                    " three hundred seventy-two trillion" +
                    " thirty-six billion" +
                    " eight hundred fifty-four million" +
                    " seven hundred seventy-five thousand" +
                    " eight hundred eight";

    private final static String[] WORDS_UNDER_20 = {
            "zero", "one", "two", "three", "four", "five", "six", "seven",
            "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private final static String[] TENS = {
            null, "ten", "twenty", "thirty", "forty", "fifty", "sixty",
            "seventy", "eighty", "ninety"
    };

    private final static String[] THOUSANDS = {
            null, "thousand", "million", "billion", "trillion", "quadrillion",
            "quintillion"
    };

    public static String get(long value) {
        // 0 and MIN_VALUE are algorithmic corner cases
        if (value == 0) {
            return WORDS_UNDER_20[0];
        }
        if (value == Long.MIN_VALUE) {
            return PRECOMPUTED_MIN_VALUE;
        }
        return getWordRepresentationNormally(value);
    }

    private static String getWordRepresentationNormally(long value) {
        Deque<String> wordsInOrder = new ArrayDeque<String>();
        long valueRemaining = Math.abs(value);
        for (int numThousands = 0; valueRemaining != 0; numThousands++) {
            int nextThousand = (int) (valueRemaining % 1000);
            valueRemaining = valueRemaining / 1000;
            addWordsForNextThousand(wordsInOrder, numThousands, nextThousand);
        }
        if (value < 0) {
            wordsInOrder.addFirst("negative");
        }
        return spacedWords(wordsInOrder);
    }

    private static void addWordsForNextThousand(Deque<String> words,
                                                int numThousands,
                                                int nextThousand) {
        if (nextThousand == 0) {
            return;
        }
        if (numThousands > 0) {
            words.addFirst(THOUSANDS[numThousands]);
        }
        addWordsFor3DigitValue(words, nextThousand);
    }

    private static String spacedWords(Deque<String> wordsInOrder) {
        StringBuilder builder = new StringBuilder();
        boolean spaceNeeded = false;
        for (String word : wordsInOrder) {
            if (spaceNeeded) {
                builder.append(" ");
            }
            builder.append(word);
            spaceNeeded = true;
        }
        return builder.toString();
    }

    private static void addWordsFor3DigitValue(Deque<String> words,
                                               int value) {
        int hundredsValue = value / 100;
        int underHundredsValue = value % 100;
        if (underHundredsValue > 0 || hundredsValue == 0) {
            words.addFirst(get2DigitWords(underHundredsValue));
        }
        if (hundredsValue > 0) {
            words.addFirst("hundred");
            words.addFirst(WORDS_UNDER_20[hundredsValue]);
        }
    }

    private static String get2DigitWords(int value) {
        int tensValue = value / 10;
        if (tensValue < 2) {
            return WORDS_UNDER_20[value];
        }
        int onesValue = value % 10;
        if (onesValue == 0) {
            return TENS[tensValue];
        }
        return TENS[tensValue] + "-" + WORDS_UNDER_20[onesValue];
    }

}
