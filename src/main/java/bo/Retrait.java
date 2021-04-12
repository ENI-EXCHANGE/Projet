package bo;

public class Retrait {

    Article article;
    String rue;
    String cp;
    String ville;

    public Retrait(Article article, String rue, String cp, String ville) {
        this.article = article;
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
    }

    public Retrait() {
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Retrait{" +
                "noArticle=" + article +
                ", rue='" + rue + '\'' +
                ", cp='" + cp + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
