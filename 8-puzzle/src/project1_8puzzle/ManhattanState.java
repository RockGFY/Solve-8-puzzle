/* 
 * Project: Project 1
 * Class:	CS 4200
 * Name:	Fengyi Guo
 * Date:	9/23/2018
 * Description:	ManhattanState class extends State class
 */
package project1_8puzzle;

import java.util.ArrayList;
import java.util.List;

public class ManhattanState extends State {

    public ManhattanState(int[] array) {
        super(array);
    }
    
    public ManhattanState(State parent, int newZeroIndex) {
        super(parent, newZeroIndex);
    }

    public List<State> getFrontier() {
        List<State> frontiers = new ArrayList<>();
        List<Integer> possibleMoves = board.getPossibleMoves();

        for (Integer possibleMove : possibleMoves) {
            State state = new ManhattanState(this, possibleMove);
            frontiers.add(state);
        }

        return frontiers;
    }

    public int calculateHeuristics() {
        int[] arr = board.getGrids();
        int distance = 0;
        for (int i = 0; i < goalState.length; i++) {
            int diffInX = Math.abs(arr[i] % 3 - i % 3);
            int diffInY = Math.abs(arr[i] / 3 - i / 3);
            distance += diffInX + diffInY;
        }
        return distance;
    }
}

