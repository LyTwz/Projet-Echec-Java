import java.util.ArrayList;
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
        String[] test;
        Piece whiteKing = this.white.getPiece(4);

        Piece[] p = this.black.getPieces();
        for(Piece i : p) {
            test = i.getValidMoves(this.board);
            for(String x : test) { 
                if(x.equals(whiteKing.getPosition())) { return true; }
            }
        }
        return false;
    }

    public Piece[] getWhitesEnemies() { // get the Pieces ready to attack the white King
        ArrayList<Piece> enemies = new ArrayList<Piece>();
        Piece whiteKing = this.white.getPiece(4);
        Piece[] blackPieces = this.black.getPieces();
        String[] test;
        for(Piece black : blackPieces) {
            test = black.getValidMoves(this.board);
            for(String x : test) { 
                if(x.equals(whiteKing.getPosition())) { enemies.add(black); }
            }
        }
        Piece[] enemiesArray = new Piece[enemies.size()];
        int index = 0;
        for(Piece p : enemies) {
            enemiesArray[index] = p;
            index++;
        }
        return enemiesArray;
    }

    public boolean isBlackChecked(){
        String[] test;
        Piece blackKing = this.black.getPiece(4);

        Piece[] p = this.white.getPieces();
        for(Piece i : p ) {
            test = i.getValidMoves(this.board);
            for(String x : test) {
                if(x.equals(blackKing.getPosition())){ return true; }
            }
        }
        return false;
    }

    public Piece[] getBlacksEnemies() { // get the Pieces ready to attack the white King
        ArrayList<Piece> enemies = new ArrayList<Piece>();
        Piece blackKing = this.black.getPiece(4);
        Piece[] whitePieces = this.white.getPieces();
        String[] test;
        for(Piece white : whitePieces) {
            test = white.getValidMoves(this.board);
            for(String x : test) { 
                if(x.equals(blackKing.getPosition())) { enemies.add(white); }
            }
        }
        Piece[] enemiesArray = new Piece[enemies.size()];
        int index = 0;
        for(Piece p : enemies) {
            enemiesArray[index] = p;
            index++;
        }
        return enemiesArray;
    }

    public boolean checkMove(String pos, String dest) { // pos -> current position of piece, dest -> destination 
        if(Board.isCorrectPosition(pos) && Board.isCorrectPosition(dest)) {
            Piece p = this.board.getCell(pos).getPiece();
            if(p == null) { return false; }
            if(!String.join(",", p.getValidMoves(this.board)).contains(dest)) { return false; } // if the move is not valid
            // simulate move to check whether that the impact that would have
            Piece destP = this.board.movePiece(pos, dest);
            // depending on the Piece's color, use the corresponding function to verify the player's not putting its own King in check
            boolean isPlayerChecked = p.getColor() ? isWhiteChecked() : isBlackChecked(); 
            this.board.undoMove(dest, pos, destP);
            if(isPlayerChecked) { return false; } // return false if this move would check the player
            return true;
        }
        return false;
    }

    public void positionPieces() {
        // default
        // start by positioning all the pawns
        int col = 1;
        int line = 1; // white
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
    }

    // play

    public int play() { // returns either -1 --> error, 0 --> don't save, 1 --> save
        positionPieces();
        while(this.gameState == true) {
            this.gameUI.drawUI(this.board);
            // always let the human player start first
            String move = this.white instanceof HumanPlayer ? this.white.chooseMove(this.gameUI, this.board) : this.black.chooseMove(this.gameUI, this.board);
            String playerColor = this.white instanceof HumanPlayer ? "White" : "Black";
            System.out.println(playerColor + "'s move -> " + move);
            String[] positions = move.split("-");
            if(!this.checkMove(positions[0], positions[1])) { this.gameUI.alert("Invalid Move."); }
            else { 
                this.gameUI.alert("Moving Piece to destination ..."); 
                Piece destP = this.board.movePiece(positions[0], positions[1]);
                if(destP != null) {
                    destP.setState(2);
                }
            }
            this.gameUI.drawUI(this.board);
            move = this.white instanceof HumanPlayer ? this.black.chooseMove(this.gameUI, this.board) : this.white.chooseMove(this.gameUI, this.board);
            playerColor = this.white instanceof HumanPlayer ? "Black" : "White";
            System.out.println(playerColor + "'s move -> " + move);
            positions = move.split("-");
            if(!this.checkMove(positions[0], positions[1])) { this.gameUI.alert("Invalid Move."); }
            else { 
                this.gameUI.alert("Moving Piece to destination ..."); 
                Piece destP = this.board.movePiece(positions[0], positions[1]);
                if(destP != null) {
                    destP.setState(2);
                }
            }
            this.gameState = !(isWhiteCheckMate() || isBlackCheckMate());
        }
        this.gameUI.drawUI(this.board);
        this.gameUI.alert("Checkmate !");
        this.winnerColor = !isWhiteCheckMate();
        this.gameUI.alert((this.winnerColor ? "White " : "Black ") + "won !");
        return 0;
    }

    private boolean isBlackCheckMate() { 
        String[] kingsNextMoves = this.black.getPiece(4).getValidMoves(this.board);
        if(kingsNextMoves.length >= 1) {
            if(Board.isCorrectPosition(kingsNextMoves[0])) {
                String kingsPosition = this.black.getPiece(4).getPosition();
                // simulate move to check whether that the impact that would have
                Piece destP = this.board.movePiece(this.black.getPiece(4).getPosition(), kingsNextMoves[0]);
                // depending on the Piece's color, use the corresponding function to verify the player's not putting its own King in check
                boolean isPlayerChecked = isBlackChecked(); 
                this.board.undoMove(kingsNextMoves[0], kingsPosition, destP);
                if(!isPlayerChecked) { return false; } // return false if this move would check the player
            }
        }
        Piece blackKing = this.black.getPiece(4);
        if(isBlackChecked()) {
            Piece[] blackPieces = this.black.getPieces();
            // get the moves of each white Piece
            String blackPossibleMoves = "";
            for(Piece blackPiece : blackPieces) {
                blackPossibleMoves += String.join(",", blackPiece.getValidMoves(this.board));
            }
            Piece[] enemies = getBlacksEnemies(); 
            for(Piece enemy : enemies) { // for each Piece ready to attack the white King
                if(enemy instanceof Knight || enemy instanceof Pawn) { // if the enemy is a Knight
                    if(!blackPossibleMoves.contains(enemy.getPosition())) { // if no one can eat it
                        return true; // check mate
                    }
                } else {
                    String[] path = enemy.getPath(blackKing.getPosition());
                    boolean checkMate = true;
                    for(String pos : path) {
                        if(blackPossibleMoves.contains(pos)) { checkMate = false; }
                    }
                    return checkMate;
                }
            }
        } 
        return false;
    }

    private boolean isWhiteCheckMate() { 
        String[] kingsNextMoves = this.white.getPiece(4).getValidMoves(this.board);
        if(kingsNextMoves.length >= 1) {
            if(Board.isCorrectPosition(kingsNextMoves[0])) {
                return false;
            }
        }
        Piece whiteKing = this.white.getPiece(4);
        if(isWhiteChecked()) {
            Piece[] whitePieces = this.white.getPieces();
            // get the moves of each white Piece
            String whitePossibleMoves = "";
            for(Piece whitePiece : whitePieces) {
                whitePossibleMoves += String.join(",", whitePiece.getValidMoves(this.board));
            }
            Piece[] enemies = getWhitesEnemies(); 
            for(Piece enemy : enemies) { // for each Piece ready to attack the white King
                if(enemy instanceof Knight || enemy instanceof Pawn) { // if the enemy is a Knight
                    if(!whitePossibleMoves.contains(enemy.getPosition())) { // if no one can eat it
                        return true; // check mate
                    }
                } else {
                    String[] path = enemy.getPath(whiteKing.getPosition());
                    boolean checkMate = true;
                    for(String pos : path) {
                        if(whitePossibleMoves.contains(pos)) { checkMate = false; }
                    }
                    return checkMate;
                }
            }
        } 
        return false;
    }

}
