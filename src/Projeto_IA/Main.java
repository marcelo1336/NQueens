package Projeto_IA;

import Projeto_IA.BFS.State;

import java.util.Deque;

public class Main {

    public static  void main(String[] args){
        Nqueens nqueens = new Nqueens();
        nqueens.solve("BFS");
//        BFS bfs = new BFS();
//        State teste = new State(new int[]{1,2});
//        bfs.printBfs(teste);
//        bfs.add(new int[]{1,3}, teste, new int[]{1,2});
//        bfs.printBfs(teste);
//        bfs.add(new int[]{2,3}, teste, new int[]{1,2});
//        bfs.printBfs(teste);
//        bfs.add(new int[]{4,3}, teste, new int[]{1,3});
//        bfs.printBfs(teste);
//        Deque<int[]> res = bfs.findPartialPath(teste, new int[]{4,3});
//        for (int[] aux: res) {
//            for(int i = 0; i < aux.length;i++){
//                System.out.print(aux[i]);
//            }
//            System.out.println();
//        }
    }

}
