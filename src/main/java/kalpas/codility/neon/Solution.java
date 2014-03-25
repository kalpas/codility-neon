package kalpas.codility.neon;

import java.util.Arrays;

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
        int prevDistance = -1;
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

            // if( prevDistance != -1 && distance > prevDistance){
            // boolean hasAdj = true;
            // while(hasAdj){
            //
            // }
            // }
            //

            if (distance > maxDistance) {
                maxDistance = distance;
            }
            boats[i] = prevBoat = boat;
            prevDistance = distance;

        }

        System.out.println(Arrays.toString(distances(bollards, boats, boatHalfW, wharfLength)));

        // move1step(boats.length - 1, true, bollards, boats, boatHalfW,
        // wharfLength);

        checkRules(bollards, boats, boatHalfW, wharfLength);

        print(bollards, boatHalfW, wharfLength, boats);

        return maxDistance;
    }

    public int solution2(int[] bollards, int boatHalfW, int wharfLength) {
        int maxDistance = -1;
        if (bollards.length * boatHalfW * 2 > wharfLength) {
            return maxDistance;
        }

        int[] boats = new int[bollards.length];
        for (int i = 0; i < boats.length; i++) {
            boats[i] = (i * boatHalfW * 2) + boatHalfW;
        }

        int[] forces = forces(bollards, boats, boatHalfW, wharfLength);

        int prevForce = 0;
        int groupStart = 0, groupEnd = 0;
        for (int i = 0; i < forces.length; i++) {
            if (prevForce != 0) {
                if (prevForce > 0 && forces[i] >= 0) {// ->->
                    prevForce = forces[i]>prevForce?forces[i]:prevForce;
                    continue;
                } else if (prevForce <= 0 && forces[i] < 0) {// <-<-

                } else if (prevForce > 0 && forces[i] <= 0) {// -><-
                    int delta = prevForce + forces[i];
                    //prevForce += forces[i];
                    if (delta < -1) {
                        groupEnd = i;
                        if (canMoveLeft(groupStart, boats, boatHalfW)) {
                            for (int j = groupStart; j <= groupEnd; j++) {
                                boats[j] -= 1;
                            }
                        }
                        groupStart = i + 1;
                        continue;
                    } else if (prevForce >= -1 && prevForce <= 0) {
                        groupStart = i + 1;
                        continue;
                    } else {
                        // TODO and what if next is -> ?
                        continue;
                    }

                } else if (prevForce <= 0 && forces[i] > 0) { // <-->

                } else {
                    throw new IllegalStateException("WTF");
                }
            }

        }

        // for (int i = 0; i < boats.length; i++) {
        // int distance = bollards[i] - boats[i];
        // boolean moveRight = distance > 0;
        //
        // }

        print(bollards, boatHalfW, wharfLength, boats);// TODO

        return maxDistance;

    }

    private boolean canMoveLeft(int boat, int[] boats, int boatHalfW) {
        boolean result = false;
        if (boat == 0 && boats[boat] >= boatHalfW + 1) {
            result = true;
        } else if (boat == 0 && boats[boat] < boatHalfW + 1) {
            result = false;
        } else if (boats[boat] >= boats[boat - 1] + boatHalfW + 1) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    private int[] forces(int[] bollards, int[] boats, int boatHalfW, int wharfLength) {
        int[] distances = new int[boats.length];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = bollards[i] - boats[i];
        }
        return distances;
    }

    private boolean move1step(int i, boolean left, int[] bollards, int[] boats, int boatHalfW, int wharfLength) {
        if (left) {
            if (i != 0) {
                if ((boats[i] - boats[i - 1] >= (boatHalfW * 2) + 1)) {
                    boats[i]--;
                    return true;
                } else {
                    return move1step(i - 1, true, bollards, boats, boatHalfW, wharfLength);
                }
            } else {
                if (boats[i] > boatHalfW + 1) {
                    boats[i]--;
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            // TODO
            return false;
        }
    }

    private int[] distances(int[] bollards, int[] boats, int boatHalfW, int wharfLength) {
        int[] distances = new int[boats.length];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Math.abs(boats[i] - bollards[i]);
        }
        return distances;
    }

    private void print(int[] bollards, int boatHalfW, int wharfLength, int[] boats) {

        System.out.println(String.format("boat = %s", boatHalfW * 2));

        int bollardI = 0;
        int bollard = bollards[bollardI];
        for (int i = 0; i <= wharfLength; i++) {
            if (bollard == i) {
                System.out.print(i);
                while (bollards[bollardI] == bollard && bollardI < bollards.length - 1) {
                    bollardI++;
                }

                bollard = bollards[bollardI];
            } else if (i != 0) {
                System.out.print(".");
            }

        }

        System.out.println();

        int boatI = 0;
        int boat = boats[boatI];
        for (int i = 0; i <= wharfLength; i++) {
            if (boat == i) {
                System.out.print(i);
                if (boatI < boats.length - 1) {
                    boat = boats[++boatI];
                }
            } else if (i != 0) {
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
