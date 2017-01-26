// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.vignat_w_sinno_th;



public class Appartement 
{

	private int tantiemes;
	private int id;// le numero de l'appart 
	private Residence residence;
	private Proprietaire proprietaire;
	
	public Appartement(Residence r,int num, int t, Proprietaire p) throws IllegalArgumentException
	{
		if(r==null || p==null || num < 0 || t <=0)
		{
			throw new IllegalArgumentException("Appartement constructor, pb param");
		}
		id=num;
		setTantiemes(t);
		setResidence(r);
		setProprietaire(p);
	}

	
	
	//--------------to string
	@Override
	public String toString() 
	{
		return "Appartement [tantiemes=" + tantiemes + ", id=" + id + ", residence=" + residence + "]";
	}
	
	//----------------hashcode & equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((residence == null) ? 0 : residence.hashCode());
		return result;
	}

	



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appartement other = (Appartement) obj;
		if (id != other.id)
			return false;
		if (residence == null) {
			if (other.residence != null)
				return false;
		} else if (!residence.equals(other.residence))
			return false;
		return true;
	}
	
	//----------------getters&setters	

	public void setTantiemes(int t)
	{
		tantiemes=t;
	}
	
	public void setResidence(Residence r)
	{
		residence=r;
	}
	
	public void setProprietaire(Proprietaire p)
	{
		proprietaire=p;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTantiemes() {
		return tantiemes;
	}

	public Residence getResidence() {
		return residence;
	}

	public Proprietaire getProprietaire() {
		return proprietaire;
	}
		
}
