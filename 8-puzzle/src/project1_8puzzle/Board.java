/* 
 * Project: Project 1
 * Class:	CS 4200
 * Name:	Fengyi Guo
 * Date:	9/23/2018
 * Description:	Board class.
 */
package project1_8puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
	
	// given an initial array
    private int array[] = {0,1,2,3,4,5,6,7,8};  

    private int zeroIndex;
    
    public Board() {
        zeroIndex = findZeroIndex();
    }
    
    public Board(int[] array) {
    	this.array = array;
        zeroIndex = findZeroIndex();
    }

	public void setGrids(int[] arr) {
        array = arr;
        zeroIndex = findZeroIndex();
    }
    public int[] getGrids() {
        return array;
    }
    
    private int findZeroIndex() {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
            	return i;
            }             
        }
        return 0;
    }
    
    public List<Integer> getPossibleMoves() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (1 == getEuclidDist(i / 3, i % 3, zeroIndex / 3, zeroIndex % 3)) {
                list.add(i);
            }
        }
        return list;
    }

    private int getEuclidDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public void moveZeroToNewPosition(int newZeroIndex) {
        int temp = array[zeroIndex];
        array[zeroIndex] = array[newZeroIndex];
        array[newZeroIndex] = temp;
        zeroIndex = newZeroIndex;
    }
    
    
    public void print() {
        for (int i = 0; i < array.length; i++) {
            if (i % 3 == 0 && i != 0) {
            	 System.out.println();
            }               
            System.out.print(array[i] + "  ");
        }
        System.out.println();
    }


}
