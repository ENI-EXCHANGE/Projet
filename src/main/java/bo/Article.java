package bo;

import java.sql.Date;

public class Article {

        int no_article;
        String nom_article;
        String description ;
        Date date_debut_encheres ;
        Date date_fin_encheres ;
        int prix_initial ;
        int prix_vente ;
        Utilisateur utilisateur ;
        Categorie categorire ;

    public Article(String nom_article, String description, Date date_debut_encheres, int prix_initial, Utilisateur utilisateur, Categorie categorire) {
        this.nom_article = nom_article;
        this.description = description;
        this.date_debut_encheres = date_debut_encheres;
        this.prix_initial = prix_initial;
        this.utilisateur = utilisateur;
        this.categorire = categorire;
    }

    public Article(int no_article, String nom_article, String description, Date date_debut_encheres, Date date_fin_encheres, int prix_initial, int prix_vente, Utilisateur utilisateur, Categorie categorire) {
        this.no_article = no_article;
        this.nom_article = nom_article;
        this.description = description;
        this.date_debut_encheres = date_debut_encheres;
        this.date_fin_encheres = date_fin_encheres;
        this.prix_initial = prix_initial;
        this.prix_vente = prix_vente;
        this.utilisateur = utilisateur;
        this.categorire = categorire;
    }

    public Article() {
    }

    public Article(String nom, String ddesc, Date valueOf, Date valueOf1, int i, int i1, Utilisateur i2, Categorie i3) {
        this.nom_article = nom;
        this.description = ddesc;
        this.date_debut_encheres = valueOf;
        this.date_fin_encheres = valueOf1;
        this.prix_initial = i;
        this.prix_vente = i1;
        this.utilisateur = i2;
        this.categorire = i3;
    }

    @Override
    public String toString() {
        return "Article{" +
                "no_article=" + no_article +
                ", nom_article='" + nom_article + '\'' +
                ", description='" + description + '\'' +
                ", date_debut_encheres=" + date_debut_encheres +
                ", date_fin_encheres=" + date_fin_encheres +
                ", prix_initial=" + prix_initial +
                ", prix_vente=" + prix_vente +
                ", no_utilisateur=" + utilisateur +
                ", no_categorire=" + categorire +
                '}';
    }

    public int getNo_article(){return this.no_article; }

    public void setNo_article(int no_article) {
        this.no_article = no_article;
    }

    public String getNom_article() {
        return nom_article;
    }

    public void setNom_article(String nom_article) {
        this.nom_article = nom_article;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_debut_encheres() {
        return date_debut_encheres;
    }

    public void setDate_debut_encheres(Date date_debut_encheres) {
        this.date_debut_encheres = date_debut_encheres;
    }

    public Date getDate_fin_encheres() {
        return date_fin_encheres;
    }

    public void setDate_fin_encheres(Date date_fin_encheres) {
        this.date_fin_encheres = date_fin_encheres;
    }

    public int getPrix_initial() {
        return prix_initial;
    }

    public void setPrix_initial(int prix_initial) {
        this.prix_initial = prix_initial;
    }

    public int getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(int prix_vente) {
        this.prix_vente = prix_vente;
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
