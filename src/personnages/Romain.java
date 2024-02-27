package personnages;

public class Romain {
	private String nom;
	private int force;
	
	public Romain(String nom, int force) {
		this.nom = nom;
		this.force = force;
		isInvariantSatisfied();
	}
	
	public String getNom() {
		return nom;
	}
	
	public void parler(String texte) {
		System.out.println(prendreParole() + "« " + texte + "»");
	}
	
	private String prendreParole() {
		return "Le romain " + nom + " : ";
	}
	
	public void recevoirCoup(int forceCoup) {
		// Pré-condition
		assert force > 0;
		
		// Première valeur variant
		int forceAvantCoup = force;
		
		force -= forceCoup;
		
		// Invariant
		assert isInvariantSatisfied();
		if (force > 0) {
			parler("Aïe");
		} else {
			parler("J'abandonne...");
		}
		
		// Deuxième valeur variant
		int forceApresCoup = force;
		
		// Post-condition (variant)
		assert forceAvantCoup > forceApresCoup;
	}
	
	private boolean isInvariantSatisfied() {
		return this.force >= 0;
	}
	
	public static void main(String[] args) {
		Romain marcus = new Romain("Marcus", 6);
		System.out.println(marcus.prendreParole());
		marcus.parler("Bonjour!");
		marcus.recevoirCoup(1);
		marcus.recevoirCoup(2);
		
	}
}