import jdk.jshell.JShell;

import java.util.*;

/**
 * La classe Etudiant représente un étudiant avec une identité, une formation,
 * et un ensemble de résultats (notes) par matière.
 */
public class Etudiant {

    /**
     * L'identité de l'étudiant.
     */
    private Identite identite;

    /**
     * La formation suivie par l'étudiant.
     */
    private Formation formation;

    /**
     * Les résultats de l'étudiant sous forme de HashMap.
     * La clé est le nom de la matière et la valeur est une liste des notes de cette matière.
     */
    private HashMap<String, ArrayList<Double>> resultat;

    /**
     * Constructeur qui initialise un étudiant avec son identité, sa formation,
     * et un ensemble de résultats (notes par matière).
     *
     * @param i L'identité de l'étudiant.
     * @param f La formation de l'étudiant.
     * @param r Les résultats (notes) par matière.
     */
    Etudiant(Identite i, Formation f, HashMap<String, Double> r) {
        this.identite = i;
        this.formation = f;
        this.resultat = new HashMap<String, ArrayList<Double>>();
    }

    /**
     * Constructeur par défaut qui initialise un étudiant avec une identité et une formation par défaut.
     * Les résultats sont initialisés comme une HashMap vide.
     */
    Etudiant() {
        this.identite = new Identite();
        this.formation = new Formation();
        this.resultat = new HashMap<String, ArrayList<Double>>();
    }

    /**
     * Ajoute une note à une matière spécifique.
     * Si la note est supérieure à 20 ou inférieure à 0, elle est ajustée à 0.
     * Si la matière fait partie de la formation de l'étudiant, la note est ajoutée à la liste de notes pour cette matière.
     *
     * @param matiere Le nom de la matière.
     * @param note    La note à ajouter (comprise entre 0 et 20).
     */
    public void ajouterNote(String matiere, double note) throws InvalidNoteFormatException {
        // Valider la note (elle doit être entre 0 et 20)
        if (note > 20 || note < 0) {
            throw new InvalidNoteFormatException();
        }

        if (!this.resultat.isEmpty()) {
            // Vérifier si la matière fait partie de la formation de l'étudiant
            if (this.estDansFormation(matiere)) {
                // Ajouter la note à la liste des notes pour la matière
                if (this.resultat.containsKey(matiere)) {
                    this.resultat.get(matiere).add(note);
                }else{
                    this.resultat.put(matiere,new ArrayList<Double>());
                    this.resultat.get(matiere).add(note);

                }

            }
        } else {
            resultat.put(matiere, new ArrayList<Double>());
            resultat.get(matiere).add(note);
        }

    }

    /**
     * Calcule la moyenne des notes d'une matière spécifique.
     *
     * @param matiere Le nom de la matière.
     * @return La moyenne des notes de la matière. Si la matière n'existe pas, retourne 0.
     */
    public Double calculerMoyenne(String matiere) {
        Double moyenne = (double) 0;
        if(!resultat.isEmpty()){
            if (estDansFormation(matiere) && resultat.containsKey(matiere)) {
                for (Double note : resultat.get(matiere)) {
                    moyenne += note;
                }
                // Diviser par le nombre de notes si au moins une note existe
                moyenne /= resultat.get(matiere).size();
            } else{
                moyenne = null;
            }
        } else {
            moyenne = null;
        }
        return moyenne;
    }

    public Double calculerMoyenneG(){
        Set<String> keys = formation.getMatieres().keySet();
        Double moyenne =(double)0;
        for(String matiere : keys){
            moyenne += this.calculerMoyenne(matiere);
        }
        return moyenne;
    }

    /**
     * Vérifie si une matière fait partie de la formation de l'étudiant.
     *
     * @param matiere Le nom de la matière.
     * @return {@code true} si la matière est dans la formation, sinon {@code false}.
     */
    public boolean estDansFormation(String matiere) {
        // Obtenir la liste des matières de la formation
        HashMap<String, Double> matieres = formation.getMatieres();
        Set<String> keys = matieres.keySet();

        // Vérifier si la matière est dans la formation
        boolean res = false;
        for (String m : keys) {
            if (m.equals(matiere)) {
                res = true;
                break;
            }
        }
        return res;
    }
}
