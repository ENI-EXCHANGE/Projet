package bll;

public class UtilisateurManagerSingl {
    public static UtilisateurManager instance;

    public static UtilisateurManager getInstance() throws BLLException{
        if(instance==null){
            initInstance();
        }return instance;
  }
    private static void initInstance() throws BLLException {
        instance = new UtilisateurManagerImpl();
    }
}
