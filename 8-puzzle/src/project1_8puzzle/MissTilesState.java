/* 
 * Project: Project 1
 * Class:	CS 4200
 * Name:	Fengyi Guo
 * Date:	9/23/2018
 * Description:	MissTilesState class extends State class
 */
package project1_8puzzle;
import java.util.ArrayList;
import java.util.List;

public class MissTilesState extends State {

    public MissTilesState(int[] array) {
        super(array);
    }

    public MissTilesState(State parent, int newZeroIndex) {
        super(parent, newZeroIndex);
    }

    public List<State> getFrontier() {
        List<State> frontiers = new ArrayList<>();
        List<Integer> possibleMoves = board.getPossibleMoves();

        for (Integer possibleMove : possibleMoves) {
            State state = new MissTilesState(this, possibleMove);
            frontiers.add(state);
        }

        return frontiers;
    }

    public int calculateHeuristics() {
        int[] arr = board.getGrids();
        int total = 0;
        for (int i = 0; i < goalState.length; i++) {
            if (arr[i] != i && arr[i] != 0)
                total++;
        }
        return total;
    }

}

