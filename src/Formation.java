import java.util.HashMap;

/**
 * La classe Formation représente une formation avec un identifiant et une liste de matières
 * associées à leurs coefficients.
 */
public class Formation {

    /**
     * L'identifiant de la formation.
     * Cet identifiant est unique pour chaque formation.
     */

    private String identifiant;
    /**
     * Les matières de la formation et leurs coefficients.
     * Utilise une HashMap où la clé est le nom de la matière et la valeur est le coefficient associé.
     */
     private HashMap<String, Double> matieres;

    public Formation(String identifiant) {
        this.identifiant = identifiant;
        this.matieres = new HashMap<String, Double>();
    }

    public Formation() {
        this.identifiant = null;
        this.matieres = new HashMap<String, Double>();
    }

    /**
     * Ajoute une matière avec son coefficient à la formation.
     * Si la matière existe déjà, elle ne sera pas ajoutée de nouveau.*
     *
     * @param matiere Le nom de la matière à ajouter.
     * @param coef    Le coefficient associé à la matière.
     ***/
    public void ajouterMatiere(String matiere, Double coef) {
        if (matieres.isEmpty()) {
            matieres.put(matiere, coef);
        } else {
            if (!matieres.containsKey(matiere)) {
                matieres.put(matiere, coef);
            }
        }

    }

    /**
     * Supprime une matière de la formation si elle est présente.*
     *
     * @param matiere Le nom de la matière à supprimer.
     */
    public void suprimerMatiere(String matiere) {
        if (matieres.containsKey(matiere)) {
            matieres.remove(matiere);
        }
    }

    /**
     * Récupère le coefficient associé à une matière donnée.*
     *
     * @param matiere Le nom de la matière.
     * @return Le coefficient de la matière, ou {@code null} si la matière n'existe pas.
     */
    public Double getCoef(String matiere) {
        return matieres.get(matiere);
    }

    /**
     * Récupère toutes les matières et leurs coefficients.*
     *
     * @return Une HashMap contenant les matières et leurs coefficients.
     */
    public HashMap<String, Double> getMatieres() {
        return matieres;
    }

}
