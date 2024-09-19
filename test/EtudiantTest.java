import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe de test unitaire pour la classe Etudiant.
 */
public class EtudiantTest {

    private Etudiant etudiant;
    private Formation formation;

    /**
     * Initialisation avant chaque test.
     * Une nouvelle instance d'Etudiant et de Formation est créée avant chaque test.
     */
    @BeforeEach
    public void setUp() {
        // Crée une formation avec des matières
        formation = new Formation();
        formation.ajouterMatiere("Mathématiques", 3.0);
        formation.ajouterMatiere("Informatique", 4.0);

        // Initialise l'étudiant avec une formation et des résultats vides
        etudiant = new Etudiant(new Identite(), formation, new HashMap<>());
    }

    /**
     * Test de la méthode ajouterNote() pour s'assurer que les notes sont ajoutées correctement.
     */
    @Test
    public void testAjouterNote() throws Exception{
        // Ajoute une note valide
        etudiant.ajouterNote("Mathématiques", 15.0);
        boolean res = false;
        assertEquals(15.0, etudiant.calculerMoyenne("Mathématiques"),
                "La moyenne pour Mathématiques doit être 15.0 après avoir ajouté une note.");

        // Ajoute une note hors limites (>20), qui devrait être réinitialisée à 0
        try{
            etudiant.ajouterNote("Informatique", 25.0);
        } catch (InvalidNoteFormatException e){
            res = true;
        }
        assertEquals(true,res);
        assertEquals(null, etudiant.calculerMoyenne("Informatique"),
                "La note pour Informatique doit être null car elle dépassait 20.");
    }

    /**
     * Test de la méthode calculerMoyenne() pour vérifier que la moyenne est calculée correctement.
     */
    @Test
    public void testCalculerMoyenne() throws Exception{
        // Ajoute plusieurs notes pour une matière
        etudiant.ajouterNote("Mathématiques", 10.0);
        etudiant.ajouterNote("Mathématiques", 20.0);
        etudiant.ajouterNote("Mathématiques", 15.0);

        // La moyenne doit être (10 + 20 + 15) / 3 = 15.0
        assertEquals(15.0, etudiant.calculerMoyenne("Mathématiques"),
                "La moyenne pour Mathématiques doit être 15.0 après avoir ajouté 3 notes.");
    }

    /**
     * Test pour vérifier que la méthode estDansFormation() fonctionne correctement.
     */
    @Test
    public void testEstDansFormation() {
        assertTrue(etudiant.estDansFormation("Mathématiques"),
                "Mathématiques devrait être dans la formation.");
        assertTrue(etudiant.estDansFormation("Informatique"),
                "Informatique devrait être dans la formation.");
        assertFalse(etudiant.estDansFormation("Physique"),
                "Physique ne devrait pas être dans la formation.");
    }

    /**
     * Test pour s'assurer qu'une note n'est pas ajoutée si la matière n'est pas dans la formation.
     */
    @Test
    public void testAjouterNoteMatiereNonExistante() throws Exception {
        // Essaye d'ajouter une note à une matière qui n'est pas dans la formation

        etudiant.ajouterNote("Physique", 12.0);



        // La matière Physique ne devrait pas exister, donc aucune note ne devrait être ajoutée
        assertNull(etudiant.calculerMoyenne("Physique"),
                "La moyenne pour Physique doit être null car la matière n'est pas dans la formation.");
    }

    /**
     * Test pour vérifier que calculerMoyenne() retourne null pour une matière sans notes.
     */
    @Test
    public void testCalculerMoyenneSansNotes() {
        assertNull(etudiant.calculerMoyenne("Informatique"),
                "La moyenne pour Informatique doit être null si aucune note n'a été ajoutée.");
    }


    /**
     * Test de la méthode calculerMoyenneG() pour s'assurer que la moyenne générale est calculée correctement.
     */
    @Test
    public void testCalculerMoyenneG() throws Exception {
        // Ajout des notes pour chaque matière
        etudiant.ajouterNote("Mathématiques", 10.0);
        etudiant.ajouterNote("Mathématiques", 20.0); // Moyenne Mathématiques = 15.0
        etudiant.ajouterNote("Informatique", 15.0);
        etudiant.ajouterNote("Informatique", 10.0);  // Moyenne Informatique = 12.5

        // Calcul de la moyenne générale = (15.0 + 12.5) = 27.5
        assertEquals(27.5, etudiant.calculerMoyenneG(),
                "La moyenne générale doit être 27.5.");
    }
}
