package Projeto_IA;

public class Nqueens {
    int[] board;

    public Nqueens(){
        board = new int[]{-1,-1,-1,-1,-1,-1,-1,-1};
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