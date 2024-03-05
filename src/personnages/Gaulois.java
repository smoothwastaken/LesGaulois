package personnages;

import villagegaulois.Musee;

public class Gaulois {
	private String nom;
	private int force;
	private int nbTrophees;
	private int effetPotion = 1;
	private Equipement[] trophees = new Equipement[100];

	public Gaulois(String nom, int force) {
		this.nom = nom;
		this.force = force;
	}

	public String getNom() {
		return nom;
	}

	public void parler(String texte) {
		System.out.println(prendreParole() + "« " + texte + "»");
	}

	/*
	 * private String prendreParole() { return "Le gaulois " + nom + " : "; }
	 */

	private String prendreParole() {
		return "Le gaulois " + nom + " : ";
	}

	/*
	 * public void frapper(Romain romain) { System.out.println(nom +
	 * " envoie un grand coup dans la mâchoire de " + romain.getNom());
	 * romain.recevoirCoup((force / 3) * this.effetPotion); }
	 */

	public void frapper(Romain romain) {
		System.out.println(nom + " envoie un grand coup dans la mâchoire de " + romain.getNom());
		Equipement[] nouveauTrophees = romain.recevoirCoup((force / 3) * effetPotion);
		for (int i = 0; nouveauTrophees != null && i < nouveauTrophees.length; i++, nbTrophees++) {
			this.trophees[nbTrophees] = nouveauTrophees[i];
		}
	}

	public void boirePotion(int forcePotion) {
		this.effetPotion = forcePotion;
		parler("Merci Druide, je sens que ma force est " + this.effetPotion + " fois décuplée!");
	}

	public void faireUneDonnation(Musee musee) {
		if (this.trophees.length > 0) {
			StringBuilder texte = new StringBuilder("Je donne au musee tous mes trophees :\n");
			for (int i = 0; i < nbTrophees; i++) {
				musee.donnerTrophees(this, trophees[i]);
				texte.append("- " + trophees[i] + "\n");
			}
			parler(texte.toString());
		}
	}

	@Override
	public String toString() {
		return "Gaulois [nom=" + nom + ", force=" + force + ", effetPotion=" + effetPotion + "]";
	}

	public static void main(String[] args) {
		Gaulois asterix = new Gaulois("Asterix", 8);
		System.out.println(asterix.getNom());
		System.out.println(asterix);
		System.out.println(asterix.prendreParole());
		asterix.parler("Bonjour !");
		Romain minus = new Romain("Minus", 5);
		asterix.frapper(minus);
		System.out.println("effetPotion = " + asterix.effetPotion);
		asterix.boirePotion(2);
		System.out.println("effetPotion = " + asterix.effetPotion);

		asterix.faireUneDonnation(new Musee());
	}
}