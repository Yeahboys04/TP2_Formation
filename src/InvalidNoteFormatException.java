public class InvalidNoteFormatException extends Exception {
    public InvalidNoteFormatException() {
        super("La note est invalide.");
    }


    public InvalidNoteFormatException(double note) {
            super("La note " + note + " est invalide. Elle doit être comprise entre 0 et 20.");
        }


}
