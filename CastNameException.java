public class CastNameException extends RuntimeException{
    
    public CastNameException() {
        super("при вводе ФИО используйте только буквы");
    }
}
