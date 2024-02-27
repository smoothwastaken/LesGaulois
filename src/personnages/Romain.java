package personnages;

public class Romain {
	private String nom;
	private int force;
	
	private int nbEquipement = 0;
	private Equipement[] equipements = new Equipement[2];
	
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
	
	private void ajoutEquipement(Equipement equipement) {
		this.equipements[this.nbEquipement] = equipement;
		this.nbEquipement ++;
		System.out.println("Le soldat " + this.getNom() + " s’équipe avec un " + equipement.toString() + ".");
	}
	
	public void sEquiper(Equipement equipement) {
		switch (this.nbEquipement) {
		case 0:
			this.ajoutEquipement(equipement);
			break;
			
		case 1:
			if (equipements[0] != null && equipements[0].toString().equals(equipement.toString())) {
				System.out.println("Le soldat " + this.getNom() + " possède déjà un " + equipement.toString() + " !");
			} else {
				this.ajoutEquipement(equipement);
			}
			break;
			
		case 2:
			System.out.println("Le soldat " + this.getNom() + " est déjà bien protégé !");
			break;
		}
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
		
		System.out.println(Equipement.CASQUE);
		System.out.println(Equipement.BOUCLIER);
		
		marcus.sEquiper(Equipement.CASQUE);
		marcus.sEquiper(Equipement.CASQUE);
		marcus.sEquiper(Equipement.BOUCLIER);
		marcus.sEquiper(Equipement.CASQUE);
		
	}
}