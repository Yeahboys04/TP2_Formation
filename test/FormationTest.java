import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

/**
 * Classe de test unitaire pour la classe Formation.
 */
public class FormationTest {

    private Formation formation;

    /**
     * Initialisation avant chaque test.
     * Une nouvelle instance de Formation est créée avant chaque test pour partir d'un état propre.
     */
    @BeforeEach
    public void setUp() {
        formation = new Formation("1");
    }

    /**
     * Test de la méthode ajouterMatiere() pour s'assurer que les matières sont ajoutées correctement.
     */
    @Test
    public void testAjouterMatiere() {
        formation.ajouterMatiere("Mathématiques", 3.0);
        assertEquals(3.0, formation.getCoef("Mathématiques"),
                "Le coefficient de Mathématiques doit être 3.0 après l'ajout.");

        formation.ajouterMatiere("Informatique", 4.0);
        assertEquals(4.0, formation.getCoef("Informatique"),
                "Le coefficient de Informatique doit être 4.0 après l'ajout.");
    }

    /**
     * Test pour vérifier que ajouterMatiere() n'ajoute pas deux fois la même matière.
     */
    @Test
    public void testAjouterMatiereExistante() {
        formation.ajouterMatiere("Mathématiques", 3.0);
        formation.ajouterMatiere("Mathématiques", 4.0);
        assertEquals(3.0, formation.getCoef("Mathématiques"),
                "Le coefficient de Mathématiques ne doit pas changer si la matière est déjà ajoutée.");
    }

    /**
     * Test de la méthode supprimerMatiere() pour s'assurer que les matières sont supprimées correctement.
     */
    @Test
    public void testSupprimerMatiere() {
        formation.ajouterMatiere("Physique", 2.5);
        formation.suprimerMatiere("Physique");
        assertNull(formation.getCoef("Physique"),
                "Le coefficient de Physique doit être null après la suppression.");
    }

    /**
     * Test pour s'assurer que getCoef() retourne null si la matière n'existe pas.
     */
    @Test
    public void testGetCoefMatiereNonExistante() {
        assertNull(formation.getCoef("Biologie"),
                "Le coefficient de Biologie doit être null car la matière n'a pas été ajoutée.");
    }

}
