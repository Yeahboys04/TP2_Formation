import java.util.ArrayList;

public class Group {
    ArrayList<Etudiant> groupEtu;
    Formation formation;

    public void ajouterEtudiant(Etudiant etu){
        if(etu.getFormation().getIdentifiant().equals(formation.getIdentifiant())){
            groupEtu.add(etu);
        }
    }

    public void suprimmerEtudiant(Etudiant etu){
        groupEtu.remove(etu);
    }
}
