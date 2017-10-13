/**
 * This class represents a generic DAO exception. It should wrap any exception of the underlying
 * code, such as SQLExceptions.
 *
 * @author Gilli
 */

package br.com.glsoftware.conting.exceptions;

public class DAOException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DAOException(String message){
		
		super (message);
	}
	
	/**
     * Constructs a DAOException with the given root cause.
     * @param cause The root cause of the DAOException.
     */
    public DAOException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a DAOException with the given detail message and root cause.
     * @param message The detail message of the DAOException.
     * @param cause The root cause of the DAOException.
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
