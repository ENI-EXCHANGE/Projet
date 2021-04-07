package dal;

public class DALException extends Exception{


    public DALException(String message) {
        super(message);
    }

    //Méthodes
    @Override
    public String getMessage() {
        StringBuffer sb = new StringBuffer("Couche DAL - ");
        sb.append(super.getMessage());

        return sb.toString() ;
    }

}
