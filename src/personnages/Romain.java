package personnages;

public class Romain {
	private String nom;
	private int force;

	private int nbEquipement = 0;
	private Equipement[] equipements = new Equipement[2];
	
	private boolean vainqueur = false;

	public Romain(String nom, int force) {
		this.nom = nom;
		this.force = force;
		isInvariantSatisfied();
	}

	public String getNom() {
		return nom;
	}

	public int getForce() {
		return force;
	}
	
	public boolean isVainqueur() {
		return vainqueur;
	}

	public void parler(String texte) {
		System.out.println(prendreParole() + "« " + texte + "»");
	}

	private String prendreParole() {
		return "Le romain " + nom + " : ";
	}

	/*
	 * public void recevoirCoup(int forceCoup) { // Pré-condition assert
	 * isInvariantSatisfied();
	 * 
	 * // Première valeur variant int forceAvantCoup = force;
	 * 
	 * force -= forceCoup;
	 * 
	 * // Invariant assert isInvariantSatisfied(); if (force > 0) { parler("Aïe"); }
	 * else { parler("J'abandonne..."); }
	 * 
	 * // Deuxième valeur variant int forceApresCoup = force;
	 * 
	 * // Post-condition (variant) assert forceAvantCoup > forceApresCoup; }
	 */

	public Equipement[] recevoirCoup(int forceCoup) {
		Equipement[] equipementEjecte = null;
		// précondition
		assert force > 0;
		int oldForce = force;
		forceCoup = calculResistanceEquipement(forceCoup);
		force -= forceCoup;
		if (force > 0) {
			parler("Aïe");
			this.vainqueur = true;
		} else {
			equipementEjecte = ejecterEquipement();
			this.vainqueur = false;
			parler("J'abandonne...");
		}
		// post condition la force a diminuée ou reste inchangée (resistance
		// équipement).
		if (force == oldForce) {			
			this.parler("Je n'ai rien senti !");
		}
		assert force <= oldForce;
		return equipementEjecte;
	}

	private int calculResistanceEquipement(int forceCoup) {
		String texte;
		texte = "Ma force est de " + this.force + ", et la force du coup est de " + forceCoup;
		int resistanceEquipement = 0;
		if (nbEquipement != 0) {
			texte += "\nMais heureusement, grace à mon équipement sa force est diminué de ";
			for (int i = 0; i < nbEquipement; i++) {
				if (equipements[i] != null && equipements[i].equals(Equipement.BOUCLIER)) {
					resistanceEquipement += Equipement.BOUCLIER.getForce();
				} else {
					System.out.println("Equipement casque");
					resistanceEquipement += Equipement.CASQUE.getForce();
				}
			}
			texte += resistanceEquipement + "!";
		}
		parler(texte);
		forceCoup -= resistanceEquipement;
		if (forceCoup < 0) {
			return 0;
		} else {
			return forceCoup;
		}
	}

	private Equipement[] ejecterEquipement() {
		Equipement[] equipementEjecte = new Equipement[nbEquipement];
		System.out.println("L'équipement de " + nom + " s'envole sous la force du coup.");
		int nbEquipementEjecte = 0;
		for (int i = 0; i < nbEquipement; i++) {

			if (equipements[i] != null) {
				equipementEjecte[nbEquipementEjecte] = equipements[i];
				nbEquipementEjecte++;
				equipements[i] = null;
			}
		}
		return equipementEjecte;
	}

	private void ajoutEquipement(Equipement equipement) {
		this.equipements[this.nbEquipement] = equipement;
		this.nbEquipement++;
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

		default :
			System.out.println("nombre d'equipementnon reconnu");
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