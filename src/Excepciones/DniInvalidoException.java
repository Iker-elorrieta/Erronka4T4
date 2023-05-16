package Excepciones;

public class DniInvalidoException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DniInvalidoException() {
        super();
    }

	public DniInvalidoException(String message) {
		super(message);
	}

	public DniInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DniInvalidoException(Throwable cause) {
		super(cause);
	}
}