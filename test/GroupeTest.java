import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class GroupeTest {

    private Groupe groupe;
    private Etudiant etudiant1;
    private Etudiant etudiant2;
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

        // Crée un groupe pour la formation 1
        groupe = new Groupe();
        groupe.formation = formation1; // Assigne la formation manuellement pour simplifier

        // Crée deux étudiants, un avec la même formation que le groupe, l'autre avec une formation différente
        etudiant1 = new Etudiant(new Identite("123", "Doe", "John"), formation1, new HashMap<>());
        etudiant2 = new Etudiant(new Identite("456", "Smith", "Anna"), formation2, new HashMap<>());
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
        groupe.ajouterEtudiant(etudiant2);
        assertFalse(groupe.groupeEtu.contains(etudiant2),
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
}
