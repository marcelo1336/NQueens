package Projeto_IA;

public class Nqueens {
    int[] board;

    public Nqueens(int n){

        board = new int[n];
        for (int i = 0; i < n; i++){
            board[i] = 0;
        }

    }

    public void solve(String serachType){
        switch (serachType){
            case "DFS":
                Search.depthFirstSearch(board);
                break;
            case "BFS":
                Search.breadthFirstSearch(board);
                break;
            default:
                System.out.println("Tipo de busca não encontrada");
        }
    }
}