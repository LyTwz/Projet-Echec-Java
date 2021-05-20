public abstract class Player {
    
    private final boolean color; // cannot change the 'Player's color after it's been instantiated
    private Piece[] pieces;

    public static final int NB_PIECES = 16;
    public static final int NB_PAWNS = 8;

    // todo -> add interface to let the player choose their next move

    public Player(boolean clr) {
        this.color = clr;
        this.pieces = new Piece[Player.NB_PIECES];
        // init all the player's 'Piece's
        this.pieces[0] = new King(clr, 0);
        this.pieces[1] = new Queen(clr, 0);
        this.pieces[2] = new Knight(clr, 0);
        this.pieces[3] = new Knight(clr, 0);
        this.pieces[4] = new Bishop(clr, 0);
        this.pieces[5] = new Bishop(clr, 0);
        this.pieces[6] = new Rook(clr, 0);
        this.pieces[7] = new Rook(clr, 0);
        // initialise each 'Piece' that is supposed to be a 'Pawn' (the last 8 ones)
        for(int i = Player.NB_PIECES - Player.NB_PAWNS - 1; i < Player.NB_PAWNS; i++) {
            this.pieces[i] = new Pawn(clr, 0);
        }
    }

    // getter

    public Piece getPiece(int index) {
        return index >= 0 && index < 16 ? this.pieces[index] : null;
    }
    
    /*
    public Piece getPiece(int stt, String pos) {
        if(stt >= 0 && stt <= 2 && Board.isCorrectPosition(pos)) {
            for(int i = 0; i < Player.NB_PIECES; i++) {
                if(this.pieces[i].getState() == stt && this.pieces[i].getPosition().equals(pos)) { // if the current 'Piece's state & position match the provided values
                    return this.pieces[i];
                }
            }
        }
        return null;
    }*/

    public boolean getColor() {
        return this.color;
    }

    // other functions -->

    public abstract String chooseMove(GameUI gameUI); // returns a string formatted like so -> <startPos>-<nextPos> -> ex : a8-b5
}
