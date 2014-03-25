package kalpas.codility.neon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple Solution.
 */
public class SolutionTest {
    @Test
    public void testSolution() {
        int R[] = { 1, 3, 14 };
        int M = 16;
        int X = 2;

        Solution solution = new Solution();
        int maxDistance = solution.solution(R, X, M);

        assertEquals(3, maxDistance);

    }
}
