package bll;

import bo.Article;
import bo.Enchere;
import bo.Utilisateur;
import dal.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnchereManagerImpl implements EnchereManager {

    private final EnchereDAO enchereDAO ;


    public EnchereManagerImpl() throws BLLException, DALException { enchereDAO= DAOFactory.getEnchereDAO(); }

    @Override
    public Enchere insert(Enchere enchere) throws BLLException {
        try {
            return enchereDAO.insert(enchere);

        } catch (DALException e) {
            throw new BLLException("Erreur lors du ajouter NouveauRetrait dans BLL");
        }
    }

    @Override
    public List<Enchere> selectAll() throws Exception {
        List<Enchere> lesEncheres = null;
        try {
            lesEncheres = enchereDAO.selectAll();
        } catch (DALException e) {
            throw new BLLException("Erreur lors du selectAll enchere dans BLL");
        }
        return lesEncheres;
    }

    @Override
    public Enchere selectById(int idUser, int idArticle) throws Exception {

        try {

            return enchereDAO.selectById(idUser, idArticle);
        } catch (DALException e) {
            throw new BLLException("Erreur lors du selectById enchere dans BLL :" +idUser +" et " + idArticle);
        }

    }

    @Override
    public List<Enchere> selectByUtilisateur(int id) throws Exception {
        List<Enchere> lesEncheres = null;
        lesEncheres = enchereDAO.selectByUtilisateur(id);
        return lesEncheres;
    }

    @Override
    public void delete(int idUser, int idArticle) throws BLLException {
        try {
            enchereDAO.delete(idUser, idArticle);
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException("Erreur lors du delete retrait dans BLL:" +idUser +" et " + idArticle);
        }
    }

    @Override
    public void update(Enchere enchere) throws BLLException {
        try {
            enchereDAO.update(enchere);
        } catch (DALException e) {
            throw new BLLException("Erreur lors de la modif d'un retrait dans BLL");
        }
    }

    @Override
    public Enchere dernierUtilisateur(int art) throws Exception {
        List<Enchere> list = enchereDAO.selectByArticle(art);
       if (list.size() != 0)
       {
           return list.get(0);
       }
       else
       {
           return null;
       }

    }

    @Override
    public List<Enchere> selectByArticle(int id) throws Exception {
        return null;
    }

    @Override
    public boolean gagne(Utilisateur uti, Article art) throws Exception {
        Enchere derniere = dernierUtilisateur(art.getNoArticle());
        if (derniere != null) {
            if (uti.getNoUtilisateur().equals(derniere.getUtilisateur().getNoUtilisateur()))
                return true;
            else
                return false;
        }
        else
        {
            return false;
        }
    }

    @Override
    public List<Article> selectByUtilisateurWithList(Utilisateur uti, List<Article> list) throws Exception {

        List<Article> laListeAtt = new ArrayList<>();
        Date date = Date.valueOf(LocalDate.now());
        for (Article art : list){

            if(gagne(uti,art))
            {

                if (art.getDateFinEncheres().after(date))
                {
                    laListeAtt.add(art);
                }

            }

        }
        return laListeAtt;
    }



    @Override
    public boolean enchereRemporte(Utilisateur uti, Article art) throws Exception {
        Date date = Date.valueOf(LocalDate.now());
        if (gagne(uti, art) && art.getDateFinEncheres().before(date))
            return true;
        else
            return false;
    }

    public List<Article> enchereRemporteWithList(Utilisateur uti, List<Article> list) throws Exception {
        List<Article> laListeAtt = new ArrayList<>();
        for (Article art : list){

            if (enchereRemporte(uti,art)){
                laListeAtt.add(art);
            }
        }
        return laListeAtt;
    }
}
