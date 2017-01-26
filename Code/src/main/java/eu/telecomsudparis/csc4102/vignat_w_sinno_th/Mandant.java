// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.vignat_w_sinno_th;



import eu.telecomsudparis.csc4102.vignat_w_sinno_th.Proprietaire;
public class Mandant extends PPOuMandant
{
	private ProprietairePresent representant;
	public Mandant(AssembleeGenerale ag, ProprietairePresent rep, Proprietaire p)
	{
		super(ag,p);
		representant=rep;
	}
	
	
	public ProprietairePresent getRepresentant() 
	{
		return representant;
	}
	public void setRepresentant(ProprietairePresent represent) 
	{
		this.representant = represent;
	}
	
}
