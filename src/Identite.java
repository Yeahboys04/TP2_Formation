public class Identite {
    private String NIP, nom, prenom;

    /**
     * Constructeur de la classe Identite
     * @param NIP
     * @param nom
     * @param prenom
     */
    public Identite(String NIP, String nom, String prenom) {
        this.NIP = NIP;
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Accesseur pour le NIP
     * @return NIP
     */
    public String getNIP() {
        return NIP;

    }

    /**
     * Accesseur pour le nom
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Accesseur pour le prenom
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

}

