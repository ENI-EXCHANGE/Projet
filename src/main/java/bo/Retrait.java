package bo;

public class Retrait {

    int noArticle;
    String rue;
    int cp;
    String ville;

    public Retrait(int noArticle, String rue, int cp, String ville) {
        this.noArticle = noArticle;
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
    }

    public Retrait() {
    }

    public int getNoArticle() {
        return noArticle;
    }

    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
