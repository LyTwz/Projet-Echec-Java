import java.util.Random;

public class ComputerPlayer extends Player {
    
    public ComputerPlayer(boolean clr) {
        super(clr);
    }

    // implement chooseMove()

    public String chooseMove(GameUI gameUI, Board b) { 
        Piece[] inUsePieces = this.getInUsePieces();
        // choose a random in-use Piece
        Random rand = new Random();
        int index = rand.nextInt(inUsePieces.length); 
        String[] nextValidMoves = inUsePieces[index].getValidMoves(b);
        if(nextValidMoves.length == 0) {
            while(nextValidMoves.length == 0) {
                index = rand.nextInt(inUsePieces.length); 
                nextValidMoves = inUsePieces[index].getValidMoves(b);
            }
        }
        return inUsePieces[index].getPosition() + "-" + nextValidMoves[rand.nextInt(nextValidMoves.length)];
    }
}
