package personnages;

import java.util.Random;

public class Village {
	private String nom;
	private Chef chef;
	
	public Village(String nom, String nomChef) {
		this.nom = nom;
		this.chef = new Chef(nomChef, new Random().nextInt(10), this);
	}
	
	public String getNom() {
		return nom;
	}
	
	public Chef getChef() {
		return chef;
	}
	
	public static void main(String[] args) {
		Village village = new Village("Sarrant", "Okawa");
		System.out.println(village.getChef());
		System.out.println(village.chef.village.chef);
		System.out.println(village);
		System.out.println(village.chef.village);
	}
}