import java.util.Comparator;

public class EtudiantComparatorMoyenne implements Comparator<Etudiant> {

    @Override
    public int compare(Etudiant o1, Etudiant o2) {
        Double moyG1 = o1.calculerMoyenneG();
        Double moyG2 = o2.calculerMoyenneG();
        return moyG1.compareTo(moyG2);
    }

    @Override
    public Comparator reversed() {
        return Comparator.super.reversed();
    }
}
