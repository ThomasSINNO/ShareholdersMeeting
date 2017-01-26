
// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.vignat_w_sinno_th;

import java.util.ArrayList;

public class Residence 
{

	private String nom;
	private ArrayList<Appartement> appartements;
	private ArrayList<Proprietaire> proprietaires ;
	private ArrayList<AssembleeGenerale> ags ;
	private int tantiemes;
	
	public Residence(String n, int t) throws IllegalArgumentException
	{
		if( n.equals(""))
		{
			throw new IllegalArgumentException("Residence constructor, pb param");
		}
		this.nom=n;
		this.appartements=new ArrayList<Appartement>();
		this.proprietaires = new ArrayList<Proprietaire>();
		this.tantiemes=t;
		this.ags= new ArrayList<AssembleeGenerale>();
	}
	
	public void ajProp(String n, String p)
	{
		Proprietaire prop = new Proprietaire(n,p);
		this.proprietaires.add(prop);
	}
	
	public void ajAppart(int num, int t, Proprietaire p)
	{
		Appartement app = new Appartement(this,num,t,p);
		appartements.add(app);
		p.setAppartement(app);
	}
	
	public boolean verifTantiem()
	{
		int sum=0;
		for(Appartement a : this.appartements)
		{
			sum +=a.getTantiemes();
		}
		return this.tantiemes==sum;
	}
	
	public void ajAG(int j, int m, int a) throws IllegalArgumentException
	{
		//TODO try catch
		AssembleeGenerale ag = new AssembleeGenerale(j,m,a,this);
		ags.add(ag);
	}
	//---------finders
	public Appartement findAppartement(int id)throws NotFoundException
	{
		for( Appartement ap: appartements)				
		{
			if(ap.getId()==id)
				return ap;
		}
		throw new NotFoundException("Appartement");
	}
	public Proprietaire findProprietaire(String nom, String prenom)throws NotFoundException
	{
		for( Proprietaire p: proprietaires)				
		{
			if(p.getNom().equals(nom) && p.getPrenom().equals(prenom))
				return p;
		}
		throw new NotFoundException("Proprietaire");
	}
	public AssembleeGenerale findAssembleeGenerale(int id)throws NotFoundException
	{
		for(AssembleeGenerale AG : ags)
		{
			if(AG.getId()==id)
				return AG;
		}
		throw new NotFoundException("AssembleeGenerale");
	}
	
	

	//----------to string
	@Override
	public String toString() 
	{
		return "Residence [nom=" + nom + "]";
	}
	
	//----------equals&hashcode
	/*only thing important to ID a Residence is its 
	 * nom attribute as it is unique
	 */
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		Residence other = (Residence) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

	//----------getters setters
	
	public void setNom(String n)
	{
		nom=n;
	}

	public String getNom() 
	{
		return nom;
	}
	
	
	
	public ArrayList<Appartement> getAppartements() {
		return appartements;
	}

	public ArrayList<Proprietaire> getProprietaires() {
		return proprietaires;
	}

	public ArrayList<AssembleeGenerale> getAgs() {
		return ags;
	}

	public int getTantiemes() 
	{
		return tantiemes;
	}

	public void setTantiemes(int tantiemes) 
	{
		this.tantiemes = tantiemes;
	}	
	
}
