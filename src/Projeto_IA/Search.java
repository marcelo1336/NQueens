package Projeto_IA;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Search {

    private static Queue<int[]> solution;
    private static Queue<int[]> generated;
    private static HashSet redundantState;

    public static void printState(int[] state) {
        for (int i = 1; i <= 8; i++) {
            for (int j = 0; j < 8; j++) {
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
                    } while ((state[col] <= 8) && !issafe(state, col));
                    if (state[col] < 8 && col == atual && redundantState.add(state.clone())) {
                        generated.add(state);
                        return true;
                    } else if(state[col] > 8){
                        state[col] = 0;
                        col--;
                    }else{
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
//                        solution.pop();
//                        generated.add(solution.peek());
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
            solution.add(generated.element().clone());
            state = generated.remove();
        }

    }

    public static void breadthFirstSearch(int[] initialState) {
    }

}
