package Projeto_IA;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Search {

    private static Queue<int[]> solution;
    private static Queue<int[]> solutionRemoved;
    private static Queue<int[]> generated;

    public static boolean isObjective(int[] state){
        for(int i = 0; i < state.length; i++){
            if(state[i] == -1){
                return false;
            }
        }

        for(int i = 0; i < state.length - 1; i++){
            for(int j = 0; j < state.length; j++){
                if(state[j] == state[i] && i != j){
                    return false;
                }
            }
            int count = 1;
            for(int j = i + 1; j < i + Math.min(7 - i, 8 - state[i]); j++){
                if(state[j] == state[i] + count){
                    return false;
                }
                count++;
            }
            count = 1;
            for(int j = i + 1; j < i + Math.min(7 - i, 8 - state[i]); j++){
                if(state[j] == state[i] - count){
                    return false;
                }
                count++;
            }
            count = 1;
            for(int j = i - 1; j >= Math.min(i, Math.abs(i - (8 - state[i]))) ; j--){
                if(state[j] == state[i] + count){
                    return false;
                }
                count++;
            }
            count = 1;
            for(int j = i - 1; j >= Math.min(i,Math.abs(i - (8 - state[i]))); j--){
                if(state[j] == state[i] - count){
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    public static boolean generateState(int[] currentState, int type){
        int[] state = currentState;
        switch (type){
            case 1:
                for(int i = 0; i < currentState.length; i++){
                    if(state[i] == -1){
                        state[i] = 1;
                        generated.add(state);
                        return true;
                    }
                }
                for(int i = 0; i < currentState.length; i++){
                    for(int j = 0; j < currentState.length; j++){
                        state[i]= 1;
                        if(state[i] == 9){
                            state[i] = 1;
                        }
                        if(!(solution.contains(state) || solutionRemoved.contains(state))){
                            generated.add(state);
                            return true;
                        }
                    }
                }
                return  false;
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
            System.out.println("teste");
            if(isObjective(state)){
                for (int i = 0; i < solution.size();i++) {
                    System.out.println(solution.remove().toString());
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

//                    0 0 0 1 0 0 0 0
//                    0 0 0 0 0 0 1 0
//                    0 0 1 0 0 0 0 0
//                    0 0 0 0 0 0 0 1
//                    0 1 0 0 0 0 0 0
//                    0 0 0 0 1 0 0 0
//                    1 0 0 0 0 0 0 0
//                    0 0 0 0 0 1 0 0