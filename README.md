# Missionaries-and-Cannibals-AI
Artificial Intelligence problem "Missionaries and Cannibals"  using Greedy Best First Search using Java 

Problem Title: 
Missionaries and Cannibals

Problem description:
On one bank of a river are three missionaries and three cannibals. There is one boat available that can hold up to two people and that they would like to use to cross the river. If the cannibals ever outnumber the missionaries on either of the river’s banks, the missionaries will get eaten.Missionaries and cannibals are to be carried safely across the river without getting anyone eaten.

Language used:
Java 

Algorithm used:
Greedy Best First Search

Heuristic function used:
(number of missionaries + number of cannibals) on left bank of the river

Operations:
1.Two missionaries move to other side of the river
2.Two cannibals move to other side of the river
3.One missionary and one cannibal move to other side of the river
4.One missionary move to other side of the river
5.One cannibal move to other side of the river

Explanation:
Initial state and final state are given. Explored set is taken as an ArrayList and frontier is taken as PriorityQueue.

Current state is represented by (p,m,c,b), where p,m and c represents parent state of the given state,the number of missionaries and cannibals on left bank of the river respectively and b repesents the state of boat i.e if b=true, then boat is present on the left bank and if b=false, then boat is not present on left bank and is present on right bank.

If initial state is valid and and is not a goal state, then it is added into frontier. 
The initial state is explored by applying all the 5 operations ( method used is "operation(int i,State s)"). If the newly generated states are valid ( method used is "isValid()") and are not present in frontier(method used is "isPresentInFrontier(PriorityQueue<State> frontier)") and explored set, then they are added into frontier.

This method is repeated for every state in the frontier and each state is constanly checked whether it is goal state or not. As soon as the goal state is reached, this method is terminated . Once goal state is reached, then parent state i.e "p" is accessed , then parent of this state p is accessed and so on. When we reach the parent state by backtracking each state's parent, then we stop. In this way we get the path through which goal state was reached from initial state.
 

