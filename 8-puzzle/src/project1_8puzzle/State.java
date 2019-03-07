/* 
 * Project: Project 1
 * Class:	CS 4200
 * Name:	Fengyi Guo
 * Date:	9/23/2018
 * Description:	State class
 */
package project1_8puzzle;

import java.util.Arrays;
import java.util.List;

public abstract class State {

    int[] goalState = {0,1,2,3,4,5,6,7,8};
    Board board;
    private State parent = null;
    private int gValue;
    private int hValue;
    private int fValue;

    public State(int[] array) {
    	board = new Board(array);
    }

    public State(State parent, int newZeroIndex) {
        board = new Board();
        int[] arr = parent.getBoard().getGrids();
        int[] arrCopy = Arrays.copyOf(arr, arr.length);
        board.setGrids(arrCopy);
        board.moveZeroToNewPosition(newZeroIndex);
        this.parent = parent;

        gValue = parent.getgValue() + 1;
        hValue = calculateHeuristics();
        fValue = gValue + hValue;
    }
    
    public Board getBoard() {
        return board;
    }

    public void print() {
        board.print();
        System.out.println();
    }

    public boolean isGoal() {
        int[] arr = board.getGrids();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != goalState[i]) {
            	return false;
            }          
        }
        return true;
    }

    public int getgValue() {
        return gValue;
    }

    public int gethValue() {
        return hValue;
    }

    public int getfValue() {
        return fValue;
    }

    public abstract List<State> getFrontier();
    public abstract int calculateHeuristics();

    public State getParent() {
        return this.parent;
    }
}

