public class UgyldigInput extends NumberFormatException {
    UgyldigInput (Integer input) {
        super("Ugyldig input: " + input);
    }
}
