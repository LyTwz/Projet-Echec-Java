public abstract class Player {
    
    private int score;
    private final boolean color; // cannot change the 'Player's color after it's been instantiated
    private Piece[] pieces; // pawns start at index 7

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
        for(int i = Player.NB_PIECES - Player.NB_PAWNS; i < Player.NB_PIECES; i++) {
            // System.out.println("index " + i);
            this.pieces[i] = new Pawn(clr, 0);
        }
    }

    // getter

    public Piece getPiece(int index) {
        return index >= 0 && index < Player.NB_PIECES ? this.pieces[index] : null;
    }

    public boolean getColor() {
        return this.color;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int s) {
        this.score = s;
    }

    // other functions -->

    public abstract String chooseMove(GameUI gameUI); // returns a string formatted like so -> <startPos>-<nextPos> -> ex : a8-b5
}
