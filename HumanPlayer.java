public class HumanPlayer extends Player {
    
    public HumanPlayer(boolean clr) {
        super(clr);
    }

    // implement chooseMove()



    public String chooseMove(GameUI gameUI, Board b) { // returns a string formatted like so -> <startPos>-<nextPos> -> ex : a8-b5
        if(gameUI != null) {
            gameUI.alert("Here is your current game : \n");
            gameUI.alert(this.gameStatus());
            int choice = Integer.parseInt(gameUI.askUser("Which piece do you want to move (choose a number between 1 and " + this.getInUsePiecesCount() + ") ?"));
            if(!(choice >= 1 && choice <= this.getInUsePiecesCount())) {
                while(!(choice >= 1 && choice <= this.getInUsePiecesCount())) {
                    gameUI.alert("'" + choice + "' is not a correct choice");
                    gameUI.alert("Please choose a number between 1 and " + this.getInUsePiecesCount());
                    choice = Integer.parseInt(gameUI.askUser("So, which piece do you want to move ?"));
                }
            }
            Piece[] inUsePieces = this.getInUsePieces();
            String startPos = inUsePieces[choice - 1].getPosition();
            String nextPos = gameUI.askUser("Where do you want to move it to ?");
            if(!Board.isCorrectPosition(nextPos)) {
                while(!Board.isCorrectPosition(nextPos)) {
                    gameUI.alert("'" + nextPos + "' is not a correctly formatted cell identifier.");
                    gameUI.alert("Please use the correct format (ex: a8 or b5)");
                    nextPos = gameUI.askUser("So, which cell do you want to go to ?");
                }
            }
            return startPos + "-" + nextPos;
        }
        return null;
    }
}
