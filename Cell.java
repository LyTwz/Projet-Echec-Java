public class Cell {

    private final String position; 
    // this attribute really shouldn't be changed once the object has been instantiated

    private Piece piece; 

    public Cell(String pos) {
        this.position = ((int) pos.charAt(0) >= 97 && (int) pos.charAt(0) <= 104) && (Integer.parseInt(pos.substring(1)) >= 1 && Integer.parseInt(pos.substring(1)) <= 8) ? pos : null;
    }

    public Cell(String pos, Piece p) {
        this(pos);
        this.piece = p;
    }

    // getters

    public String getPosition() {
        return this.position;
    }

    public Piece getPiece() {
        return this.piece;
    }

    // setters 

    // no setter for position, as it's a final attribute

    public void setPiece(Piece p) {
        // make sure we're not affecting an uninstantiated object to this.piece
        this.piece = p != null ? p : this.piece;
    }

}
