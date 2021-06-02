import java.util.Arrays;

public class ChessGame {
    
    private final boolean mode; // false = 2 HumanPlayers, true = 1 HumanPlayer vs 1 ComputerPlayer
    private boolean gameState; // true = ongoing, false = finished
    private boolean winnerColor; // no need to explain

    // the two players, each either human or computer
    private Player black; 
    private Player white;

    private Board board;
    private Log log;

    private GameUI gameUI; // can be either text or graphic

    public ChessGame(boolean md) { // default constructor
        this.mode = md; 
        this.gameState = true;
        this.board = new Board();
        this.log = new Log();
        this.gameUI = new TextGameUI();
        if(!this.mode) { // 2 HumanPlayers
            this.black = new HumanPlayer(false); // false is black
            this.white = new HumanPlayer(true); // and true is white
        } else { // Human vs Computer
            String color = this.gameUI.askUser("Which color do you want your pieces to be ?").trim().toLowerCase();
            while(!color.equals("black") && !color.equals("white")) { // make sure the user enters a valid color
                System.out.println("'" + color + "' is not a valid color.'");
                System.out.println("Please choose either black or white.");
                color = this.gameUI.askUser("So, which color do you want your pieces to be ?").trim().toLowerCase();
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

    // getters 

    public boolean getGameState() {
        return this.gameState;
    }

    public boolean getWinnerColor() {
        return this.winnerColor;
    }

    public boolean isWhiteChecked(){
        String pos = "";
        String[] test;
        Piece[] p1 = this.white.getPieces();
        for(Piece z : p1){
            if(z.getType() == "King"){
                pos = z.getPosition();
            }
        }

        Piece[] p = this.black.getPieces();
        for(Piece i : p ){
            test = i.getNextMoves();
            for(int w = 0; w<test.length;w++){
                if(test[w] == pos){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isBlackChecked(){
        String pos = "";
        String[] test;
        Piece[] p1 = this.black.getPieces();
        for(Piece z : p1){
            if(z.getType() == "King"){
                pos = z.getPosition();
            }
        }

        Piece[] p = this.white.getPieces();
        for(Piece i : p ){
            test = i.getNextMoves();
            for(int w = 0; w<test.length;w++){
                if(test[w] == pos){
                    return true;
                }
            }
        }
        return false;
    }

    public void positionPieces() {
        // default
        // start by positioning all the pawns
        int col = 1;
        int line = 1; // white
        Piece[] p = this.white.getPieces();
        for(Piece i : p) {
            i.getNextMoves();
        }
        for(int i = 0; i < Player.NB_PIECES - 8; i++) {
            this.white.getPiece(i).setState(1);
            this.white.getPiece(i+8).setState(1);
            this.board.addPiece(this.white.getPiece(i), Board.intToPosition(col, line));
            this.board.addPiece(this.white.getPiece(i+8), Board.intToPosition(col, line+1));
            col++;
        }
        col = 1;
        line = 8; // black
        for(int i = 0; i < Player.NB_PIECES - 8; i++) {
            this.black.getPiece(i).setState(1);
            this.black.getPiece(i+8).setState(1);
            this.board.addPiece(this.black.getPiece(i), Board.intToPosition(col, line));
            this.board.addPiece(this.black.getPiece(i+8), Board.intToPosition(col, line-1));
            col++;
        }
        // System.out.println(Arrays.toString(this.board.getCells()));
    }

    // play

    public int play() { // returns either -1 --> error, 0 --> don't save, 1 --> save
        positionPieces();
        while(this.gameState == true) {
            this.gameUI.drawUI(this.board);
            String move = this.white.chooseMove(this.gameUI);
        }
        return 0;
    }

    // todo --> write the other constructors + needed getters / setters
}
