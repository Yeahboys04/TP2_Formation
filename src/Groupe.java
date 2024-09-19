import java.util.ArrayList;

public class Groupe {
    ArrayList<Etudiant> groupeEtu;
    Formation formation;

    public Groupe(){
        groupeEtu = new ArrayList<Etudiant>();
        formation = new Formation();
    }
    public void ajouterEtudiant(Etudiant etu){
        if(etu.getFormation().getIdentifiant().equals(formation.getIdentifiant())){
            groupeEtu.add(etu);
        }
    }

    public void suprimmerEtudiant(Etudiant etu){
        groupeEtu.remove(etu);
    }
}
