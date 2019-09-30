package Projeto_IA;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BFS {
    static class State {
        int[] state;
        Deque<State> childs;

        // constructor
        public State(int[] rootState) {
            state = rootState;
            childs = new LinkedList<>();
        }
    }

    public Deque<int[]> findPartialPath(State root, int[] targetState) {
        Deque<int[]> result = result = new LinkedList<>();
        result.add(root.state.clone());
        if (!(toString(root.state).equals(toString(targetState)))) {
            for (State aux : root.childs) {
                Deque<int[]> path = findPartialPath(aux, targetState);
                if(!path.isEmpty()){
                    for (int[] p : path) {
                        result.add(p.clone());
                    }
                    return result;
                }
            }
        }
        if(toString(root.state).equals(toString(targetState))) {
            return result;
        }

        return new LinkedList<>();
    }

    public void add(int[] key, State temp, int[] targetState) {
        Queue<State> q = new LinkedList<State>();
        q.add(temp);

        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();
            if (toString(temp.state).equals(toString(targetState))) {
                temp.childs.add(new State(key));
                break;
            } else {
                for (State aux : temp.childs) {
                    q.add(aux);
                }
            }
        }
    }

    public void printBfs(State initial) {
        for (int i = 0; i < initial.state.length; i++) {
            System.out.print(initial.state[i]);
        }
        System.out.println();
        for (State node : initial.childs) {
            printBfs(node);
        }
    }

    public String toString(int[] state) {
        String result = "";
        for (int i = 0; i < state.length; i++) {
            result += state[i];
        }
        return result;
    }

}
