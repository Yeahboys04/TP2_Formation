import java.util.ArrayList;

public class Groupe {
    ArrayList<Etudiant> groupeEtu;
    Formation formation;

    /**
     * Constructeur par défaut qui initialise un groupe vide.
     */
    public Groupe(){
        groupeEtu = new ArrayList<Etudiant>();
        formation = new Formation();
    }

    /**
     * Constructeur qui initialise un groupe avec une formation.
     * @param formation
     */
    public Groupe(Formation formation){
        groupeEtu = new ArrayList<Etudiant>();
        this.formation = formation;
    }


    /**
     * Constructeur qui initialise un groupe avec une formation.
     * @param etu
     */
    public void ajouterEtudiant(Etudiant etu){
        if(etu.getFormation().getIdentifiant().equals(formation.getIdentifiant())){
            groupeEtu.add(etu);
        }
    }

    /**
     * Supprime un étudiant du groupe.
     * @param etu
     */
    public void suprimmerEtudiant(Etudiant etu){
        groupeEtu.remove(etu);
    }

    /**
     * Méthode triAlpha qui trie les étudiants par ordre alphabétique.
     */

    public void triAlpha() {
        groupeEtu.sort((Etudiant e1, Etudiant e2) -> e1.getIdentite().getNom().compareTo(e2.getIdentite().getNom()));
    }

    /**
     * Méthode triAntiAlpha qui trie les étudiants par ordre alphabétique inverse.
     */
    public void triAntiAlpha() {
        groupeEtu.sort((Etudiant e1, Etudiant e2) -> e2.getIdentite().getNom().compareTo(e1.getIdentite().getNom()));
    }

    /**
     * Méthode getEtudiants qui retourne la liste des étudiants du groupe.
     * @return
     */
    public ArrayList<Etudiant> getEtudiants(){
        return groupeEtu;
    }
}
