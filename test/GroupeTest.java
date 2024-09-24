import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class GroupeTest {

    private Groupe groupe;
    private Etudiant etudiant1;
    private Etudiant etudiant2;
    private Etudiant etudiant3;
    private Formation formation1;
    private Formation formation2;

    /**
     * Initialisation avant chaque test
     */
    @BeforeEach
    public void setUp() {
        // Crée deux formations différentes
        formation1 = new Formation("Formation 1");
        formation2 = new Formation("Formation 2");

        // Ajoute des matières avec coefficients pour les tests de moyenne
        formation1.ajouterMatiere("Maths", 2.0);
        formation1.ajouterMatiere("Informatique", 3.0);

        // Crée un groupe pour la formation 1
        groupe = new Groupe();
        groupe.formation = formation1; // Assigne la formation manuellement pour simplifier

        // Crée deux étudiants, un avec la même formation que le groupe, l'autre avec une formation différente
        etudiant1 = new Etudiant(new Identite("123", "Doe", "John"), formation1, new HashMap<>());
        etudiant2 = new Etudiant(new Identite("456", "Smith", "Anna"), formation1, new HashMap<>());
        etudiant3 = new Etudiant(new Identite("457", "Smith", "Anna"), formation2, new HashMap<>());

        // Ajoute des notes aux étudiants pour les tests de moyenne
        try {
            etudiant1.ajouterNote("Maths", 15);
            etudiant1.ajouterNote("Informatique", 18);

            etudiant2.ajouterNote("Maths", 10);
            etudiant2.ajouterNote("Informatique", 12);
        } catch (InvalidNoteFormatException e) {
            fail("Erreur lors de l'ajout des notes: " + e.getMessage());
        }
    }

    /**
     * Test pour vérifier l'ajout d'un étudiant avec la même formation que le groupe.
     */
    @Test
    public void testAjouterEtudiantAvecMemeFormation() {
        groupe.ajouterEtudiant(etudiant1);
        assertTrue(groupe.groupeEtu.contains(etudiant1),
                "L'étudiant ayant la même formation doit être ajouté au groupe.");
    }

    /**
     * Test pour vérifier qu'un étudiant avec une formation différente n'est pas ajouté.
     */
    @Test
    public void testAjouterEtudiantAvecFormationDifferente() {
        groupe.ajouterEtudiant(etudiant3);
        assertFalse(groupe.groupeEtu.contains(etudiant3),
                "L'étudiant avec une formation différente ne doit pas être ajouté au groupe.");
    }

    /**
     * Test pour vérifier la suppression d'un étudiant du groupe.
     */
    @Test
    public void testSupprimerEtudiant() {
        groupe.ajouterEtudiant(etudiant1);
        groupe.suprimmerEtudiant(etudiant1);
        assertFalse(groupe.groupeEtu.contains(etudiant1),
                "L'étudiant doit être supprimé du groupe.");
    }

    /**
     * Test pour s'assurer qu'un étudiant qui n'est pas dans le groupe ne cause pas d'erreur lors de la suppression.
     */
    @Test
    public void testSupprimerEtudiantNonPresent() {
        groupe.suprimmerEtudiant(etudiant1);
        assertFalse(groupe.groupeEtu.contains(etudiant1),
                "La suppression d'un étudiant non présent ne doit pas causer d'erreur.");
    }

    /**
     * Test pour vérifier le calcul de la moyenne pour une matière spécifique dans le groupe.
     */
    @Test
    public void testCalculerMoyennePourUneMatiere() throws InvalidMatiereFormation, NoneNoteException {
        groupe.ajouterEtudiant(etudiant1);
        groupe.ajouterEtudiant(etudiant2);
        // Calcul de la moyenne pour la matière "Maths"
        Double moyenneMaths = groupe.calculerMoyenne("Maths");
        assertEquals(12.5, moyenneMaths, 0.01, "La moyenne pour Maths devrait être 12.5");

        // Calcul de la moyenne pour la matière "Informatique"
        Double moyenneInformatique = groupe.calculerMoyenne("Informatique");
        assertEquals(15.0, moyenneInformatique, 0.01, "La moyenne pour Informatique devrait être 15.0");
    }

    /**
     * Test pour vérifier le calcul de la moyenne générale pondérée pour le groupe.
     */
    @Test
    public void testCalculerMoyenneGenerale() throws NoneNoteException {
        groupe.ajouterEtudiant(etudiant1);
        groupe.ajouterEtudiant(etudiant2);
        // Calcul de la moyenne générale pondérée
        Double moyenneGenerale = groupe.claculerMoyenneG();
        assertEquals(14.1, moyenneGenerale, 0.1, "La moyenne générale devrait être 14.1");
    }

    /**
     * Test pour vérifier le calcul de la moyenne lorsqu'une matière sans notes est présente dans la formation.
     */
    @Test
    public void testCalculerMoyennePourMatiereSansNotes() throws InvalidMatiereFormation {
        formation1.ajouterMatiere("Physique", 1.0);
        groupe.ajouterEtudiant(etudiant1);
        groupe.ajouterEtudiant(etudiant2);

        // Calcul de la moyenne pour la matière "Physique"
        boolean res = false;
        try{
            Double moyennePhysique = groupe.calculerMoyenne("Physique");
        } catch (NoneNoteException e){
            res = true;
        }
        assertTrue(res);
    }
}
