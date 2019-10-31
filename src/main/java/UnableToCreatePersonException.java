public class UnableToCreatePersonException extends Exception {
    public UnableToCreatePersonException(String[] messages){
        super(String.join("\n", messages));
    }
}