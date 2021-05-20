public class ChessGame {
    
    private final int mode; // 0 = 2 HumanPlayers, 1 = 1 HumanPlayer vs 1 ComputerPlayer
    private boolean gameState; // true = ongoing, false = finished
    private boolean winnerColor; // no need to explain

    // the two players, each either human or computer
    private Player black; 
    private Player white;

    private Board board;
    private Log log;

    private GameUI gameUI; // text or graphic

    public ChessGame(int md) { // default constructor
        this.mode = md >= 0 && md <= 1 ? md : 1; // check whether 'md' has a correct value. If not, default is 1 (human vs computer)
        this.gameState = true;
        this.board = new Board();
        this.log = new Log();
        this.gameUI = new TextGameUI();
        if(this.mode == 0) { // 2 HumanPlayers
            this.black = new HumanPlayer(false); // false is black
            this.white = new HumanPlayer(true); // and true is white
        } else { // Human vs Computer
            String color = this.gameUI.askUser("Which color do you want your pieces to be ?").trim().toLowerCase();
            while(!color.equals("black") || !color.equals("white")) { // make sure the user enters a valid color
                System.out.println("'" + color + "' is not a valid color.'");
                System.out.println("Please choose either black or white.");
                String color = this.gameUI.askUser("So, which color do you want your pieces to be ?").trim().toLowerCase();
            }
            if(color.equals("black")) {
                this.black = new HumanPlayer(false);
                this.white = new ComputerPlayer(true);
            } else if(color.equals("white")) {
                this.black = new ComputerPlayer(false);
                this.white = new HumanPlayer(true);
            }
        }
    }

    // todo --> write the other constructors + needed getters / setters
}
