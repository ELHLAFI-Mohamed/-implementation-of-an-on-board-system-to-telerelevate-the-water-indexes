
public class Compteur {
	private int Num_secteur;
	private int Num_compteur;
	private int index_compteur;
	
	public int getNum_secteur() {
		return Num_secteur;
	}
	public void setNum_secteur(int num_secteur) {
		Num_secteur = num_secteur;
	}
	
	public int getNum_compteur() {
		return Num_compteur;
	}
	public void setNum_compteur(int num_compteur) {
		Num_compteur = num_compteur;
	}
	public int getIndex_compteur() {
		return index_compteur;
	}
	public void setIndex_compteur(int index_compteur) {
		this.index_compteur = index_compteur;
	}
	public Compteur() {
		this.index_compteur=0;
		this.Num_compteur=1;
		this.Num_secteur=2;
	}
	
}
