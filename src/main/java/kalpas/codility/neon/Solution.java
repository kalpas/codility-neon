package kalpas.codility.neon;

public class Solution {
    /**
     * 
     * @param R
     * @param X
     * @param M
     * @return
     */
    public int solution(int[] bollards, int boatHalfW, int wharfLength) {
        int maxDistance = -1;
        int boats[] = new int[bollards.length];

        int prevBoat = -1;
        for (int i = 0; i < bollards.length; i++) {
            int bollard = bollards[i];
            int boat;
            int distance;

            if (bollard >= boatHalfW) {
                if (bollard >= prevBoat + boatHalfW * 2) {
                    boat = bollard;
                } else {
                    boat = prevBoat + boatHalfW * 2;
                }
            } else {
                boat = boatHalfW;
            }
            distance = Math.abs(bollard - boat);
            if (distance > maxDistance) {
                maxDistance = distance;
            }
            boats[i] = prevBoat = boat;

        }

        checkRules(bollards, boats, boatHalfW, wharfLength);

        print(bollards, boatHalfW, wharfLength, boats);

        return maxDistance;
    }

    private void print(int[] bollards, int boatHalfW, int wharfLength, int[] boats) {

        System.out.println(String.format("boat = %s", boatHalfW * 2));

        int bollardI = 0;
        int bollard = bollards[bollardI];
        for (int i = 0; i < wharfLength; i++) {
            if (bollard == i) {
                System.out.print(i);
                if (bollardI < bollards.length - 1) {
                    bollard = bollards[++bollardI];
                }
            } else {
                System.out.print(".");
            }

        }

        System.out.println();

        int boatI = 0;
        int boat = boats[boatI];
        for (int i = 0; i < wharfLength; i++) {
            if (boat == i) {
                System.out.print(i);
                if (boatI < boats.length - 1) {
                    boat = boats[++boatI];
                }
            } else {
                System.out.print(".");
            }

        }
        System.out.println();
    }

    private boolean checkRules(int[] bollards, int[] boats, int boatHalfW, int wharfLength) {
        if (boats.length != bollards.length) {
            System.err.println("boats different from bollards");
        }

        int prevBoat = -1;
        for (int i = 0; i < boats.length; i++) {
            int boat = boats[i];
            if (boat < boatHalfW) {
                System.err.println(String.format("boat %s with position %s is too close to the start", i, boat));
            } else if (boat > wharfLength - boatHalfW) {
                System.err.println(String.format("boat %s with position %s is too far", i, boat));
            } else if ((prevBoat != -1 && boat < prevBoat + boatHalfW * 2)) {
                System.err.println(String.format("boats %s (%s) and %s(%s) overlap!!!", boat, i, prevBoat, i - 1));
            }
        }
        return true;
    }

}
