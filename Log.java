public class Log {
    
    private String log;

    public Log() {
        this.log = "";
    }

    public boolean check(String move) { // check the validity of the log entry
        if(move == null) { return false; }
        if(move.length() != 5) { return false; }
        return Board.isCorrectPosition(move.substring(0, 2)) && move.charAt(2) == '-' && Board.isCorrectPosition(move.substring(3));
    }

    public String update(String entry) {
        if(entry != null) {
            // check whether 'move' is correctly formatted --> <+ or -><move> 
            System.out.println(entry + " is correct ? " + check(entry));
            this.log += check(entry) ? entry + "," : "";
            return entry;
        }
        return null;
    }

    // getter

    public String getLog() {
        return this.log;
    }

}
