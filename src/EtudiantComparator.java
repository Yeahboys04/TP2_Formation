import java.util.Comparator;

public class EtudiantComparator implements Comparator<Etudiant> {
    @Override
    public int compare(Etudiant o1, Etudiant o2) {
        String nom = o1.getIdentite().getNom();
        String nom2 = o2.getIdentite().getNom();
        String prenom = o1.getIdentite().getPrenom();
        String prenom2 = o2.getIdentite().getPrenom();
        int res = nom.compareTo(nom2);
        if(res ==0){
            res = prenom.compareTo(prenom2);
        }
        return res;
    }

    @Override
    public Comparator<Etudiant> reversed() {
        return Comparator.super.reversed();
    }
}
