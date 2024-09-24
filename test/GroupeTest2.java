import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class GroupeTest2 {

    private Groupe groupe;
    private Etudiant etu1, etu2, etu3,etu4;
    private Formation formation1;
    private Formation formation2;

    /**
     * Initialisation avant chaque test
     */
    @BeforeEach
    public void setUp() {
        Formation formation1 = new Formation("Formation 1");
        groupe = new Groupe(formation1);
        Formation formation2 = new Formation("Formation 2");

        // Crée trois étudiants avec des noms différents
        etu1 = new Etudiant(new Identite("123", "Zidane", "Zinedine"), formation1, new HashMap<>());
        etu2 = new Etudiant(new Identite("124", "Henry", "Thierry"), formation2, new HashMap<>());
        etu4 = new Etudiant(new Identite("126", "Henry", "Thierry"), formation1, new HashMap<>());
        etu3 = new Etudiant(new Identite("125", "Mbappe", "Kylian"), formation1, new HashMap<>());

        // Ajoute les étudiants au groupe
        groupe.ajouterEtudiant(etu1);
        groupe.ajouterEtudiant(etu2);
        groupe.ajouterEtudiant(etu3);
        groupe.ajouterEtudiant(etu4);
    }

    /**
     * Test pour vérifier l'ajout d'un étudiant avec la même formation que le groupe.
     */
    @Test
    public void testAjouterEtudiantAvecMemeFormation() {
        groupe.ajouterEtudiant(etu1);
        assertTrue(groupe.groupeEtu.contains(etu1),
                "L'étudiant ayant la même formation doit être ajouté au groupe.");
    }

    /**
     * Test pour vérifier qu'un étudiant avec une formation différente n'est pas ajouté.
     */
    @Test
    public void testAjouterEtudiantAvecFormationDifferente() {
        groupe.ajouterEtudiant(etu2);
        assertFalse(groupe.groupeEtu.contains(etu2),
                "L'étudiant avec une formation différente ne doit pas être ajouté au groupe.");
    }

    /**
     * Test pour vérifier la suppression d'un étudiant du groupe.
     */
    @Test
    public void testSupprimerEtudiant() {
        groupe.ajouterEtudiant(etu1);
        groupe.suprimmerEtudiant(etu1);
        assertFalse(groupe.groupeEtu.contains(etu1),
                "L'étudiant doit être supprimé du groupe.");
    }

    /**
     * Test pour s'assurer qu'un étudiant qui n'est pas dans le groupe ne cause pas d'erreur lors de la suppression.
     */
    @Test
    public void testSupprimerEtudiantNonPresent() {
        groupe.suprimmerEtudiant(etu1);
        assertFalse(groupe.getEtudiants().contains(etu1),
                "La suppression d'un étudiant non présent ne doit pas causer d'erreur.");
    }

    @Test
    public void triAlpha() {
        // Ajoute des étudiants au groupe
        groupe.ajouterEtudiant(etu1);
        groupe.ajouterEtudiant(etu2);

        // Trie par ordre alphabétique
        groupe.triAlpha();

        // Vérifie l'ordre des étudiants
        assertEquals("Henry", groupe.getEtudiants().get(0).getIdentite().getNom(),
                "Le premier étudiant après tri alphabétique doit être Henry.");
        assertEquals("Zidane", groupe.getEtudiants().get(2).getIdentite().getNom(),
                "Le deuxième étudiant après tri alphabétique doit être Zidane.");
    }

    @Test
    public void triAntiAlpha() {


        // Trie par ordre alphabétique inverse
        groupe.triAntiAlpha();

        // Vérifie l'ordre des étudiants
        assertEquals("Zidane", groupe.getEtudiants().get(0).getIdentite().getNom(),
                "Le premier étudiant après tri anti-alphabétique doit être Zidane.");
        assertEquals("Henry", groupe.getEtudiants().get(2).getIdentite().getNom(),
                "Le deuxième étudiant après tri anti-alphabétique doit être Henry.");

    }
}
