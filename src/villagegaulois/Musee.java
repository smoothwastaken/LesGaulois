package villagegaulois;

import personnages.Equipement;
import personnages.Gaulois;

public class Musee {

	private static final int MAX_TROPHEES = 200;

	private Trophee[] trophees = new Trophee[MAX_TROPHEES];
	private int nbTrophee = 0;

	public void donnerTrophees(Gaulois gaulois, Equipement equipement) {
		trophees[nbTrophee] = new Trophee(gaulois, equipement);
		nbTrophee++;
	}

	public String extraireInstructionsOCaml() {
		StringBuilder textDeclaration = new StringBuilder("let musee = [\n");
		for (int i = 0; i < nbTrophee; i++) {
			textDeclaration.append("\t\"" + trophees[i].donnerNom() + "\", \"" + trophees[i].getEquipement().toString()
					+ "\";\n");
		}
		textDeclaration.append("]");
		return textDeclaration.toString();
	}

}
