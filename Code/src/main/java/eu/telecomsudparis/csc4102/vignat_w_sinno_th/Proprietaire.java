// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.vignat_w_sinno_th;

public class Proprietaire
{

	private String nom;
	private String prenom;
	private int id;
	static private int base_id;//sert a generer une id unique
	protected Appartement appartement;
		

	public Proprietaire(String n, String p)
	{
		if(n.equals("") || p.equals(""))
		{
			throw new IllegalArgumentException("Proprietaire constructor, null param");
		}
		setId();
		nom=n;
		prenom=p;
		appartement=null;
		base_id++;
	}
	
	//------------to string

	@Override
	public String toString() 
	{
		return "Proprietaire [nom=" + nom + ", prenom=" + prenom + ", appartement=" + appartement + "]";
	}


	//------------equals & hashcode
	/* only thing important for a proprietaire is its id
	 * which is unique 
	 */

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proprietaire other = (Proprietaire) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	//-------------getters setters
	
	public Appartement getAppartement() 
	{
		return appartement;
	}

	public void setAppartement(Appartement appartement)
	{
		this.appartement = appartement;
	}

	private void setId()
	{
		id=base_id+1;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setNom(String n)
	{
		nom=n;
	}
	
	public void setPrenom(String p)
	{
		prenom=p;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}
	
	
	
	
	

}
