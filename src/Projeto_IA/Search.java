package Projeto_IA;

import java.util.LinkedList;
import java.util.Queue;

public class Search {

    private static Queue<int[]> solution;
    private static Queue<int[]> solutionRemoved;
    private static Queue<int[]> generated;

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
                    if (state[col] < 8 && col == atual) {
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
        solutionRemoved = new LinkedList<>();
        solution.add(state);
        while(true){
//            generated.add(initialState);
            if(isGoal(state)){
                System.out.println("Estado objetivo alcançado");
                for(int i = 0; i < 8; i++ ){
                    System.out.print(state[i] + ";");
                }
                break;
            }else{
                for(int i = 0; i < 8; i++ ){
                    System.out.print(state[i] + ";");
                }
                System.out.println();
                if(!generateState(state, 1)){
//                    solutionRemoved.add(solution.remove());
//                    generated.add(solution.element());

                }
            }
            if(generated.isEmpty()){
                System.out.println("Erro");
                break;
            }
            state = generated.remove();
            solution.add(state);
        }

    }

    public static void breadthFirstSearch(int[] initialState) {
    }

}
