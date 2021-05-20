public class HumanPlayer extends Player {
    
    public HumanPlayer(boolean clr) {
        super(clr);
    }

    // implement chooseMove()

    public String chooseMove(GameUI gameUI) { // returns a string formatted like so -> <startPos>-<nextPos> -> ex : a8-b5
        if(gameUI != null) {
            System.out.println("What's your next move ? :-D");
            String startPos = gameUI.askUser("Which cell do you want to start from ?").trim().toLowerCase();
            if(!Board.isCorrectPosition(startPos)) {
                while(!Board.isCorrectPosition(startPos)) {
                    System.out.println("'" + startPos + "' is not a correctly formatted cell identifier.");
                    System.out.println("Please use the correct format (ex: a8 or b5)");
                    startPos = gameUI.askUser("So, which cell do you want to start from ?").trim().toLowerCase();
                }
            }
            String nextPos = gameUI.askUser("Which cell do you want to go to ?").trim().toLowerCase();
            if(!Board.isCorrectPosition(nextPos)) {
                while(!Board.isCorrectPosition(nextPos)) {
                    System.out.println("'" + nextPos + "' is not a correctly formatted cell identifier.");
                    System.out.println("Please use the correct format (ex: a8 or b5)");
                    nextPos = gameUI.askUser("So, which cell do you want to go to ?").trim().toLowerCase();
                }
            }
            return startPos + "-" + nextPos;
        }
        return null;
    }
}
