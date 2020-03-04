package aifinal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

class StateComparator implements Comparator<State>
{
    public int compare(State s1,State s2)
    {
        if((s1.cann+s1.miss)>(s2.cann+s2.miss))
            return 1;
        else if((s1.cann+s1.miss)<(s2.cann+s2.miss))
            return -1;
        
        return 0;
    }
}

class State
{
    State parentState;
    int miss;
    int cann;
    boolean boat;
    
    State(State a,int b,int c,boolean d)
    {
        parentState=a;
        miss=b;
        cann=c;
        boat=d;
    }
    
    public boolean isGoalState(State goal)
    {
        if(boat==goal.boat && cann==goal.cann && miss==goal.miss)
            return true;
        else
            return false;
    }
    
    public State operation(int i,State s)
    {
        int newMiss=miss,newCann=cann;
        boolean newBoat=boat;
        
        switch(i)
        {
            case 1: if(boat==true)
                    {
                        newMiss=miss-2;
                        newBoat=false;
                    }
                    else
                    {
                        newMiss=miss+2;
                        newBoat=true;
                    }
                    break;
            case 2: if(boat==true)
                    {
                        newCann=cann-2;
                        newBoat=false;
                    }
                    else
                    {
                        newCann=cann+2;
                        newBoat=true;
                    }
                    break;
            case 3: if(boat==true)
                    {
                        newCann=cann-1;
                        newMiss=miss-1;
                        newBoat=false;
                    }
                    else
                    {
                        newCann=cann+1;
                        newMiss=miss+1;
                        newBoat=true;
                    }
                    break;
            case 4: if(boat==true)
                    {
                        newMiss=miss-1;
                        newBoat=false;
                    }
                    else
                    {
                        newMiss=miss+1;
                        newBoat=true;
                    }
                    break;
            case 5: if(boat==true)
                    {
                        newCann=cann-1;
                        newBoat=false;
                    }
                    else
                    {
                        newCann=cann+1;
                        newBoat=true;
                    }
                    break;
        }
        return new State(s,newMiss,newCann,newBoat);
    }
    
    public boolean isValid()
    {
        if(miss<=3 && miss>=0 && cann<=3 && cann>=0)
        {
            if(miss==3||miss==0)
                return true;
            
            if(cann==3||cann==0)
            {
                if(miss==3||miss==0)
                    return true;
            }
            
            if(miss==cann)
                return true;
        }
        return false;
    }
    
    public boolean isPresentInFrontier(PriorityQueue<State> frontier)
    {
        Iterator itr1=frontier.iterator();
        while(itr1.hasNext())
        {
            State ss1=(State)itr1.next();
            if(miss==ss1.miss && cann==ss1.cann && boat==ss1.boat)
                return true;
        }
        return false;
    }
}

public class AIfinal {
    public static void main(String[] args) {

        State initialState=new State(null,3,3,true);
        State goalState=new State(null,0,0,false);
        int operation=5;
        ArrayList<State> path=new ArrayList<State>();
        
        path=bestFirstSearch(initialState,goalState,operation);
        
        if(path!=null)
        {
        for(int k=path.size()-1;k>=0;k--)
            System.out.println("("+path.get(k).miss+","+path.get(k).cann+","+path.get(k).boat+")");
        }
    }
    
    public static ArrayList<State> bestFirstSearch(State initialState, State goalState,int operation)
    {
        PriorityQueue<State> frontier=new PriorityQueue<State>(new StateComparator());
        ArrayList<State> explored=new ArrayList<State>();
        ArrayList<State> result =new ArrayList<State>();
        boolean status,out=false;
        State finals=null;
        
        if(initialState.isValid() && goalState.isValid())
        {
        //checking if initial state is a goal state
        if(!initialState.isGoalState(goalState))
            frontier.add(initialState);

        while(!frontier.isEmpty())
        {
            status=false;
            
            State state=frontier.poll();
            explored.add(state);
            
            //no. of operations=no. of states generated
            for(int i=1;i<=operation;i++)
            {
                State stateNew=state.operation(i,state);
                
                if(stateNew.isGoalState(goalState))
                {
                    finals=state;
                    out=true;
                    break;
                }
                
                //if the new state is a valid state
                if(stateNew.isValid())
                {
                    //if it is valid state, checking if new state is in frontier 
                    status=stateNew.isPresentInFrontier(frontier);

                    //if it is valid state, checking if new state is in explored set
                    for(int j=0;j<explored.size();j++)
                    {
                        if((stateNew.cann==explored.get(j).cann && stateNew.miss==explored.get(j).miss && stateNew.boat==explored.get(j).boat))
                        {
                            status=true;
                            break;
                        }
                    }

                    //if it is valid state and not present in explored or frontier, then add into frontier
                    if(status!=true)
                        frontier.add(stateNew);
                }
            }
            
            if(out==true)
                break;
        }
        
        if(!frontier.isEmpty())
        {
            System.out.println("\nPath to reach goal state from initial state:");
            result.add(goalState);
            while(finals.parentState!=goalState.parentState)
            {
                result.add(finals);
                finals=finals.parentState;
            }
            result.add(initialState);
        }
        return result;
    }
    else
        return null;
        
    }
}
