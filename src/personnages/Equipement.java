package personnages;

public enum Equipement {
	CASQUE("casque", 5), BOUCLIER("bouclier", 8);
	
	private String nom;
	private int force;
	
	private Equipement(String nom, int force) {
		this.nom = nom;
		this.force = force;
	}
	
	@Override
	public String toString() {
		return this.nom;
	}
	
	public int getForce() {
		return force;
	}
}
