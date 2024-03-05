package personnages;

import java.util.Random;

public class Village {
	private String nom;
	private Chef chef;

	private Gaulois[] villageois;
	private int nbVillageois;

	public Village(String nom, String nomChef, int forceChef, int nbVillageoisMaximum) {
		this.nom = nom;
		this.chef = new Chef(nomChef, forceChef, this);
		this.villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public Chef getChef() {
		return chef;
	}

	public void afficherVillageois() {
		System.out.println("Dans le village du chef " + this.chef.getNom() + " vivent les légendaires gaulois :");
		for (int i = 0; i < this.nbVillageois; i++) {
			System.out.println("- " + this.villageois[i].getNom());
		}
	}

	public void ajouterHabitant(Gaulois nouveauHabitant) {
		this.villageois[this.nbVillageois] = nouveauHabitant;
		this.nbVillageois++;
	}

	public Gaulois trouverHabitant(int indiceHabitant) {
		return this.villageois[indiceHabitant];
	}

	public static void main(String[] args) {
		Village village = new Village("Village des Irréductibles", "Abraracourcix", 6, 30);

		/*
		 * Quand on ajoute la ligne suivante: Gaulois gaulois =
		 * village.trouverHabitant(30); On obtient l'erreur suivante: Exception in
		 * thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 30 out of
		 * bounds for length 30 at personnages.Village.trouverHabitant(Village.java:32)
		 * at personnages.Village.main(Village.java:37)
		 *
		 * Cette erreur survient car on essaye d'accéder au 30e élément alors qu'il n'y
		 * en a que 29.
		 */

		// Création du chef lors de l'instanciation du village (contrainte
		// supplémentaire imposée lors du TP1)

		Gaulois asterix = new Gaulois("Astérix", 8);
		village.ajouterHabitant(asterix);

		/*
		 * Gaulois gaulois = village.trouverHabitant(1); System.out.println(gaulois);
		 * 
		 * 'gaulois' est null, car on accède au deuxième habitant du village alors que
		 * le village n'en dispose que d'un.
		 */

		village.afficherVillageois();

		Gaulois obelix = new Gaulois("Obélix", 25);
		village.ajouterHabitant(obelix);
		village.afficherVillageois();

	}
}