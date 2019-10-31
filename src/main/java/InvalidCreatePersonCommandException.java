public class InvalidCreatePersonCommandException extends Exception {
    public InvalidCreatePersonCommandException(String[] messages){
        super(String.join("\n", messages));
    }
}