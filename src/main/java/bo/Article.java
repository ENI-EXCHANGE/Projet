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
        Categorie categorie;

    public Article(String nom, String description, Date dateDebutEncheres, int prixInitial, Utilisateur utilisateur, Categorie categorie) {
        this.nomArticle = nom;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.prixInitial = prixInitial;
        this.utilisateur = utilisateur;
        this.categorie = categorie;
    }

    public Article(int no_article, String nom, String description, Date dateDebutEncheres, Date dateFinEncheres, int prixInitial, int prixVente, Utilisateur utilisateur, Categorie categorie) {
        this.noArticle = no_article;
        this.nomArticle = nom;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.utilisateur = utilisateur;
        this.categorie = categorie;
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
        this.categorie = i3;
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
                ", Utilisateur=" + utilisateur +
                ", Categorie=" + categorie +
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
