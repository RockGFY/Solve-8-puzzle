/* 
 * Project: Project 1
 * Class:	CS 4200
 * Name:	Fengyi Guo
 * Date:	9/23/2018
 * Description:	AStar class
 */
package project1_8puzzle;
import java.util.*;

public class AStar {

    private State start;
    private HashSet<State> closedList;
    private PriorityQueue<State> pq;
    public int nodeGenerated = 0;
    public AStar(State state) {
        start = state;
    }

    public State run() {
        closedList = new HashSet<>();
        pq = new PriorityQueue<>(stateComparator);
        pq.add(start);
        
        while (!pq.isEmpty()) {
            State curState = pq.poll();

            if (!closedList.contains(curState)) {
                List<State> frontiersList = curState.getFrontier();
                for (State state : frontiersList) {
                    if (!closedList.contains(state)) {
                        //state.print();
                        if (state.isGoal()) {
                        	return state;
                        } 
                        //check each state's g, h, f value
                        //System.out.println(state.getgValue() + "," + state.gethValue() + "," + state.getfValue());                        
                        pq.add(state);
                        nodeGenerated++;
                    }
                }
                closedList.add(curState);
                //System.out.println();
            }
        }    
        return null;
    }

    public List<State> traceBack(State state) {
        List<State> list = new LinkedList<>();
        list.add(state);
        while ((state = state.getParent()) != null) {
            list.add(state);
        }
        return list;
    }
    
    public boolean isSolvable(State state) {
        int counter = 0;
        int arr[] = state.getBoard().getGrids();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] == 0 || arr[j] == 0) {
                	continue;
                }
                   
                if (arr[i] > arr[j]) {
                	counter++;
                }                  
            }
        }
        return counter % 2 == 0;
    }
    
    public int getNodeGenerated() {
    	return nodeGenerated;
    }

    private Comparator<State> stateComparator = (s1, s2) -> s1.getfValue() - s2.getfValue();
}

