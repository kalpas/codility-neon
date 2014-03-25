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

    @Test
    public void testSolution2() {
        int R[] = { 6, 6, 6 };
        int M = 16;
        int X = 2;

        Solution solution = new Solution();
        int maxDistance = solution.solution(R, X, M);

        assertEquals(4, maxDistance);

    }

    @Test
    public void testSolution3() {
        int R[] = { 4, 7, 9, 11, 14, 21, 21, 22, 22, 23 };
        int M = 37;
        int X = 1;

        Solution solution = new Solution();
        int maxDistance = solution.solution(R, X, M);

        assertEquals(3, maxDistance);

    }
}
