public class Log {
    
    private String log;

    public Log() {
        this.log = "";
    }

    public boolean check(String move) { // check the validity of the log entry
        return move.charAt(0) == '+' || move.charAt(0) == '-' && Game.isCorrectMove(move.substring(1));
    }

    public String update(String entry) {
        if(entry != null) {
            // check whether 'move' is correctly formatted --> <+ or -><move> 
            this.log += check(entry) ? entry : "";
            return entry;
        }
        return null;
    }

    // getter

    public String getLog() {
        return this.log;
    }

}
