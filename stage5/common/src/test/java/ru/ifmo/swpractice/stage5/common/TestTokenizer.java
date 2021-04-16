package ru.ifmo.swpractice.stage5.common;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestTokenizer {
    @Test
    public void test() {
        runTest("Привет Привет Hello", Map.of("привет", 2, "hello", 1));
        runTest("1904 a   90     2390-=--1134=a=a==/.,.a,, b", Map.of("a", 4, "b", 1));
        runTest("", Map.of());
        runTest("ジョジョの奇妙な冒険 ora ora", Map.of("ジョジョの奇妙な冒険", 1, "ora", 2));
        runTest("   bacaaa<.--.12хдх!д?х12", Map.of("bacaaa", 1, "хдх", 1, "д", 1, "х", 1));
    }

    private void runTest(final String str, final Map<String, Integer> expected) {
        Map<String, Integer> mp = new HashMap<>();
        TextUtilities.tokenizeAndAddWords(str, mp);
        assertEquals(expected, mp);
    }
}
