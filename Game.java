public class Game {

    public Game() {
        // nothing special here yet
    }

    // public state

    // utility

    public static boolean isCorrectMove(String move) { // verifies the format of a 'move' string
        if(move != null) {
            String[] positions = move.split("\\-");
            if(positions.length == 2) {
                return Board.isCorrectPosition(positions[0]) && Board.isCorrectPosition(positions[1]);
            } 
        }
        return false;
    } 

}