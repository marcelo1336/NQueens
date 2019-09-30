package Projeto_IA;

import java.util.*;

public class Search {

    private static Deque<int[]> solution;
    private static Deque<int[]> generated;
    private static Set<String> redundantState;
    private static BFS bfs = new BFS();
    private static BFS.State bfsManager;

    public static int countQueen(int[] state) {
        int count = 0;
        for (int i = 0; i < state.length; i++) {
            if (state[i] > 0) {
                count++;
            }
        }
        return count;
    }

    public static void printState(int[] state) {
        for (int i = 1; i <= state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                System.out.print((state[j] == i) ? "|Q" : "|_");
            }
            System.out.println("|");
        }
    }

    public static boolean isGoal(int[] state) {
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 0) {
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

    public static String toString(int[] state) {
        String result = "";
        for (int i = 0; i < state.length; i++) {
            result += state[i];
        }
        return result;
    }


    public static boolean generateState(int[] currentState, int type) {
        int[] state = currentState.clone();
        int col = 0;
        int atual = 0;
        int inserted = 0;
        int count = 0;
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 0) {
                col = i;
                atual = i;
                break;
            }
        }
        System.out.println("Estado atual");
        printState(state);
        System.out.println("\nEstados gerados a partir do atual");
        Stack<int[]> aux = new Stack<>();
        while (count < state.length) {
            do {
                state[col]++;
            } while ((state[col] <= state.length) && !issafe(state, col));
            if (state[col] <= state.length && col == atual && redundantState.add(toString(state))) {
                printState(state);
                System.out.println();
                if (type == 1) {
                    aux.push(state.clone());
                } else {
                    generated.addLast(state.clone());
                    bfs.add(state.clone(), bfsManager, currentState.clone());
                }
                inserted++;
            }
            count++;
        }
        if (inserted > 0) {
            if(type == 1){
                while(!aux.empty()){
                    generated.addFirst(aux.pop());
                }
            }
            return true;
        }
        System.out.println("Nenhum estado gerado!!");
        return false;
    }


    public static void depthFirstSearch(int[] initialState) {
        int[] state = initialState;
        generated = new LinkedList<>();
        solution = new LinkedList<>();
        redundantState = new HashSet<>();
        solution.add(state.clone());
        redundantState.add(state.toString());
        while (true) {
            Queue<int[]> path = new LinkedList<>(solution);
            System.out.println("Caminho parcial atual");
            for (int[] node: path) {
                printState(node);
                System.out.println();
            }
            if (isGoal(state)) {
                System.out.println("Solução encontrada!!");
                break;
            } else {
                if (!generateState(state, 1)) {
                    try {
                        solution.removeLast();
                    } catch (Exception e) {
                        System.out.println("Falha do sistema");
                        return;
                    }
                }
            }
            if (generated.isEmpty()) {
                System.out.println("Erro: não possui solução!!\n");
                break;
            }
            state = generated.removeFirst();
            while (countQueen(state) <= countQueen(solution.getLast()))
                solution.removeLast();
            solution.addLast(state.clone());
        }

    }

    public static void breadthFirstSearch(int[] initialState) {
        int[] state = initialState.clone();
        generated = new LinkedList<>();
        redundantState = new HashSet<>();
        redundantState.add(state.toString());
        bfsManager = new BFS.State(state.clone());
        while (true) {
            Deque<int[]> res = bfs.findPartialPath(bfsManager, state.clone());
            System.out.println("Caminho parcial atual");
            for (int[] aux : res) {
                printState(aux);
                System.out.println();
            }
            if (isGoal(state)) {
                System.out.println("Solução encontrada!!");
                break;
            } else {
                generateState(state, 2);
            }
            if (generated.isEmpty()) {
                System.out.println("BFS: Não possui solução");
                break;
            }
            state = generated.removeFirst();
        }
    }

}
