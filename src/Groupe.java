import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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

    public Double calculerMoyenne(String matiere) throws InvalidMatiereFormation, NoneNoteException {
        Double moyenne = (double) 0 ;
        int somme = groupeEtu.size();
        int nbNotes =0;
        if (this.formation.estDansFormation(matiere)) {
            for (Etudiant etu : groupeEtu) {
                try{
                    moyenne += (etu.calculerMoyenne(matiere));
                    nbNotes++;
                } catch (NoneNoteException | InvalidMatiereFormation e){
                    moyenne +=0;
                    somme--;
                }

            }
            moyenne = moyenne / somme;
        }
         else {
            throw new InvalidMatiereFormation("La matiere +" + matiere + "n'est pas dans la formation ");
        }

         if(nbNotes==0){
             throw new NoneNoteException("Il n'y a aucunes notes");
         }
        return moyenne;
    }

    public Double claculerMoyenneG() throws NoneNoteException {
        HashMap<String, Double> matieres = formation.getMatieres();
        Set<String> matiereFormation = matieres.keySet();
        Double moyenne = 0.0;
        Double sommeCoef = 0.0;
        int nbMatieres =0;
        for(String matiere :  matiereFormation){
            Double coef = matieres.get(matiere);
            try{
                moyenne += this.calculerMoyenne(matiere) * coef;
                sommeCoef += coef;
                nbMatieres ++;
            } catch (InvalidMatiereFormation e){
                moyenne +=0;
            }
        }
        if(nbMatieres == 0){
            throw new NoneNoteException("Aucunes notes pour calculer une moyenne général");
        }
        return moyenne/sommeCoef;
    }
}