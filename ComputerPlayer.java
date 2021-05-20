import java.util.Random;

public class ComputerPlayer extends Player {
    
    public ComputerPlayer(boolean clr) {
        super(clr);
    }

    // implement chooseMove()

    public String chooseMove(GameUI gameUI) { // todo --> current version is actually completely wrong
        Random r = new Random();
        int index = r.nextInt(16); // choose a random a 'Piece' to move 
        String[] nextMoves = this.getPiece(index).getNextMoves().split("\\;"); 
        return nextMoves[r.nextInt(nextMoves.length)]; // choose a random possible move
        
    }
}
