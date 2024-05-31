package fr.diginamic.jdbc.entites;

import java.util.Objects;

public class Fournisseur {
    private int id;
    private String nom;

    public Fournisseur(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

	@Override
	public int hashCode() {
		return Objects.hash(id, nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Fournisseur)) {
			return false;
		}
		Fournisseur other = (Fournisseur) obj;
		return id == other.id && Objects.equals(nom, other.nom);
	}

	@Override
	public String toString() {
		return "Fournisseur [id=" + id + ", nom=" + nom + "]";
	}
	
	
    
    
}
