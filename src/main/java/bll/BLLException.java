package bll;

public class BLLException extends Exception  {
    /**
     * Permet de gérer les exception de la BLL
     * @author fannie
     */
    private static final long serialVersionUID = 1L;

    public BLLException(String message) {
        super(message);
    }
}
