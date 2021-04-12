package bo;

import java.sql.Date;

public class Article {

        int noArticle;
        String nomArticle;
        String description ;
        Date dateDebutEncheres;
        Date dateFinEncheres;
        int prixInitial;
        int prixVente;
        Utilisateur utilisateur ;
        Categorie categorire ;

    public Article(String nom_article, String description, Date date_debut_encheres, int prix_initial, Utilisateur utilisateur, Categorie categorire) {
        this.nomArticle = nom_article;
        this.description = description;
        this.dateDebutEncheres = date_debut_encheres;
        this.prixInitial = prix_initial;
        this.utilisateur = utilisateur;
        this.categorire = categorire;
    }

    public Article(int no_article, String nom_article, String description, Date date_debut_encheres, Date date_fin_encheres, int prix_initial, int prix_vente, Utilisateur utilisateur, Categorie categorire) {
        this.noArticle = no_article;
        this.nomArticle = nom_article;
        this.description = description;
        this.dateDebutEncheres = date_debut_encheres;
        this.dateFinEncheres = date_fin_encheres;
        this.prixInitial = prix_initial;
        this.prixVente = prix_vente;
        this.utilisateur = utilisateur;
        this.categorire = categorire;
    }

    public Article() {
    }

    public Article(String nom, String ddesc, Date valueOf, Date valueOf1, int i, int i1, Utilisateur i2, Categorie i3) {
        this.nomArticle = nom;
        this.description = ddesc;
        this.dateDebutEncheres = valueOf;
        this.dateFinEncheres = valueOf1;
        this.prixInitial = i;
        this.prixVente = i1;
        this.utilisateur = i2;
        this.categorire = i3;
    }

    @Override
    public String toString() {
        return "Article{" +
                "no_article=" + noArticle +
                ", nom_article='" + nomArticle + '\'' +
                ", description='" + description + '\'' +
                ", date_debut_encheres=" + dateDebutEncheres +
                ", date_fin_encheres=" + dateFinEncheres +
                ", prix_initial=" + prixInitial +
                ", prix_vente=" + prixVente +
                ", no_utilisateur=" + utilisateur +
                ", no_categorire=" + categorire +
                '}';
    }

    public int getNoArticle(){return this.noArticle; }

    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public void setDateDebutEncheres(Date dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }

    public Date getDateFinEncheres() {
        return dateFinEncheres;
    }

    public void setDateFinEncheres(Date dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

    public int getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(int prixInitial) {
        this.prixInitial = prixInitial;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Categorie getCategorire() {
        return categorire;
    }

    public void setCategorire(Categorie categorire) {
        this.categorire = categorire;
    }
}
