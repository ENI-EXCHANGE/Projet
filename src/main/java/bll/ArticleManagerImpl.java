package bll;

import bo.Article;
import dal.ArticleDAO;
import dal.DALException;
import dal.DAOFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArticleManagerImpl implements ArticleManager{

    private ArticleManager articleManager ;
    //public ArticleManagerImpl() { articleManager = BLLFactory.getArticleBLL(); }

    private final ArticleDAO articleDAO;

    public ArticleManagerImpl() throws BLLException, DALException {
        articleDAO = DAOFactory.getArticleDAO();
    }

    @Override
    public void ajouterArticle(Article nouvelArticle) throws DALException {
        articleDAO.insert(nouvelArticle);
    }

    @Override
    public Article modifierArticle(Article art) throws Exception {
        return articleDAO.update(art);
    }

    @Override
    public List<Article> selectAll() throws Exception {
        return articleDAO.selectAll();
    }

    @Override
    public List<Article> selectAllDate() throws Exception {
        return articleDAO.selectAllDate();
    }

    @Override
    public Article selectById(int id) throws Exception {
        return  articleDAO.selectById(id);
    }

    @Override
    public List<Article> selectByName(String name) throws Exception {
        return articleDAO.selectByName(name);
    }

    @Override
    public List<Article> selectByCategorie(int noCategorie) throws Exception {
        return articleDAO.selectByCategorie(noCategorie);
    }


    public List<Article> selectByMesVentes(Integer noUtilisateur,List<Article> list) throws Exception {
        List<Article> laListeAtt = new ArrayList<>();
        for (Article article : list){
            if (article.getUtilisateur().getNoUtilisateur() == noUtilisateur){
                laListeAtt.add(article);
            }
        }
        return laListeAtt;
    }



    @Override
    public List<Article> selectByNameCategorie(String name,int noCategorie) throws Exception {
        return articleDAO.selectByNameCategorie(name, noCategorie);
    }



    @Override
    public void supprimerArticle(int id) throws Exception {
        articleDAO.delete(id);
    }

    @Override
    public List<Article> selectByUtilisateur(int id) throws Exception {
        return articleDAO.selectByUtilisateur(id);
    }

    @Override
    public List<Article> selectByUtilisateurAtt(Integer noUtilisateur) throws Exception {
        List<Article> laListeAtt = new ArrayList<>();
        List<Article> laListe = articleDAO.selectByUtilisateur(noUtilisateur);
        Date date = Date.valueOf(LocalDate.now());
        for (Article art : laListe){
            if (art.getDateDebutEncheres().after(date)){
                laListeAtt.add(art);
            }
        }
        return laListeAtt;
    }

    @Override
    public List<Article> selectByUtilisateurCours(Integer noUtilisateur) throws Exception {
        List<Article> laListeAtt = new ArrayList<>();
        List<Article> laListe = articleDAO.selectByUtilisateur(noUtilisateur);
        Date date = Date.valueOf(LocalDate.now());
        for (Article art : laListe){
            if (art.getDateDebutEncheres().before(date) && art.getDateFinEncheres().after(date)){
                laListeAtt.add(art);
            }
        }
        return laListeAtt;
    }

    @Override
    public List<Article> selectByUtilisateurTermine(Integer noUtilisateur) throws Exception {
        List<Article> laListeAtt = new ArrayList<>();
        List<Article> laListe = articleDAO.selectByUtilisateur(noUtilisateur);
        Date date = Date.valueOf(LocalDate.now());
        for (Article art : laListe){
            if (art.getDateFinEncheres().before(date)){
                laListeAtt.add(art);
            }
        }
        return laListeAtt;
    }


    public List<Article> selectByCategorieWithListe(Integer noCategorie,List<Article> list) throws Exception {
        List<Article> laListeAtt = new ArrayList<>();
        for (Article article : list){
            if (article.getCategorie().getNoCategorie() == noCategorie){
                laListeAtt.add(article);
            }
        }
        return laListeAtt;
    }

    public List<Article> selectByUtilisateurCoursWhithListe(Integer noUtilisateur, List<Article> list) throws Exception {
        List<Article> laListeAtt =  new ArrayList<>();
        Date date = Date.valueOf(LocalDate.now());
        for (Article art : list){
            if (art.getDateDebutEncheres().before(date) && art.getDateFinEncheres().after(date)){
                laListeAtt.add(art);
            }
        }

        return laListeAtt;
    }

    public List<Article> selectByUtilisateurTermineWhithListe(Integer noUtilisateur, List<Article> list) throws Exception
    {
        List<Article> laListeAtt = new ArrayList<>();
        Date date = Date.valueOf(LocalDate.now());

        for (Article art : list){
            if (art.getDateFinEncheres().before(date)){
                laListeAtt.add(art);
            }
        }
        System.out.println(laListeAtt);
        return  laListeAtt;
    }


    public List<Article> selectByUtilisateurWithList(Integer noUtilisateur, List<Article> list) throws Exception {

        List<Article> laListeAtt = new ArrayList<>();
        for (Article art : list){
            if (art.getUtilisateur().getNoUtilisateur() == noUtilisateur){
                laListeAtt.add(art);
            }
        }

        return  laListeAtt;
    }


    public List<Article> selectByCours(List<Article> list) throws Exception {
        List<Article> laListeAtt = new ArrayList<>();
        Date date = Date.valueOf(LocalDate.now());
        for (Article art : list){
            if (art.getDateDebutEncheres().before(date) && art.getDateFinEncheres().after(date)){
                laListeAtt.add(art);
            }
        }

        return laListeAtt;
    }

    @Override
    public List<Article> selectByUtilisateurAttWithList(Integer noUtilisateur, List<Article> list) throws Exception {
        List<Article> laListeAtt = new ArrayList<>();

        Date date = Date.valueOf(LocalDate.now());
        for (Article art : list){
            if (art.getDateDebutEncheres().after(date)){
                laListeAtt.add(art);
            }
        }
        return laListeAtt;
    }

    public List<Article> concat(List<Article> list1,List<Article> list2,List<Article> list3) throws Exception {

        List<Article> laListeAtt = new ArrayList<>();
        for (Article art : list1){
            laListeAtt.add(art);
        }
        for (Article art : list2){
            laListeAtt.add(art);
        }
        for (Article art : list3){
            laListeAtt.add(art);
        }

        return laListeAtt;
    }

    public List<Article> doublon(List<Article> list) throws Exception {

        Set set = new HashSet() ;
        set.addAll(list) ;
        ArrayList distinctList = new ArrayList(set) ;

        return distinctList;
    }
/*
    @Override
    public void modifierArticle(Article article) throws Exception {
        articleDAO.update(article);
    } */

}
