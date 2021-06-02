public class HumanPlayer extends Player {
    
    public HumanPlayer(boolean clr) {
        super(clr);
    }

    // implement chooseMove()



    public String chooseMove(GameUI gameUI) { // returns a string formatted like so -> <startPos>-<nextPos> -> ex : a8-b5
        if(gameUI != null) {
            gameUI.alert("Here is your current game : \n");
            gameUI.alert(this.gameStatus());
            String startPos = gameUI.askUser("Which cell do you want to start from ?");
            if(!Board.isCorrectPosition(startPos)) {
                while(!Board.isCorrectPosition(startPos)) {
                    gameUI.alert("'" + startPos + "' is not a correctly formatted cell identifier.");
                    gameUI.alert("Please use the correct format (ex: a8 or b5)");
                    startPos = gameUI.askUser("So, which cell do you want to start from ?");
                }
            }
            String nextPos = gameUI.askUser("Which cell do you want to go to ?");
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
