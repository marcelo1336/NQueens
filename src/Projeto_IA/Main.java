package Projeto_IA;

import Projeto_IA.BFS.State;

import java.util.Deque;

public class Main {

    public static  void main(String[] args){
        Nqueens nqueens = new Nqueens(4);
        nqueens.solve("DFS");
    }

}
