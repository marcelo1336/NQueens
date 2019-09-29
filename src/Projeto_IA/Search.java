package Projeto_IA;
import java.util.*;

public class Search {

    private static Deque<int[]> solution;
    private static Deque<int[]> generated;
    private static HashSet redundantState;

    public static void printState(int[] state) {
        for (int i = 1; i <= state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                System.out.print((state[j] == i) ? "|Q" : "|_");
            }
            System.out.println("|");
        }
    }

    public static boolean isGoal(int[] state){
        for(int i = 0; i < state.length; i++){
            if(state[i] == 0){
                return false;
            }
        }
        return true;
    }

    static boolean issafe(int[] state, int column) {
        int x = state[column];
        for (int i = 1; i <= column; i++) {
            int t = state[column - i];
            if (t == x || t == x - i || t == x + i) {
                return false;
            }
        }

        return true;
    }


    public static boolean generateState(int[] currentState, int type){
        int[] state = currentState;
        switch (type){
            case 1:
                int col = 0;
                int atual = 0;
                for(int i = 0; i < 8; i++){
                    if(state[i] == 0){
                        col = i;
                        atual = i;
                        break;
                    }
                }
                while (col >= 0) {
                    do {
                        state[col]++;
                    } while ((state[col] <= state.length) && !issafe(state, col));
                    if (state[col] < state.length && col == atual && redundantState.add(state.clone())) {
                        generated.addFirst(state.clone());
                        return true;
                    } else if(state[col] > 8){
                        state[col] = 0;
                        col--;
                    }else if(col < state.length-1){
                        col++;
                    }
                }
                return false;
            case 2:
                for(int i = 0; i < currentState.length; i++){
                    if(state[i] == -1){
                        state[i] = 1;
                    }
                }
                return false;
            default:
                System.out.println("Erro");
                return false;

        }
    }

        public static void depthFirstSearch(int[] initialState) {
        int[] state = initialState;
        generated = new LinkedList<>();
        solution = new LinkedList<>();
        redundantState = new HashSet();
        solution.add(state.clone());
        while(true){
            if(isGoal(state)){
                System.out.println("Solução:");
                while(!solution.isEmpty()){
                    printState(solution.remove());
                    System.out.println();
                }
                break;
            }else{
                if(!generateState(state, 1)){
                    try {
                        solution.removeLast();
                    }catch(Exception e){
                        System.out.println("Não existe solução");
                        return;
                    }
                }
            }
            if(generated.isEmpty()){
                System.out.println("Erro");
                break;
            }
            state = generated.removeFirst();
            solution.addLast(state.clone());
        }

    }

    public static void breadthFirstSearch(int[] initialState) {
    }

}
