// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.vignat_w_sinno_th;

public class QuestionNonVotee extends Question
{

	private String compte_rendu;
	
	
	public QuestionNonVotee(AssembleeGenerale ag, String q)
	{
		super(ag,q);
		compte_rendu="";
	}
	
	//------------to string
	
	@Override
	public String toString() 
	{
		String ret=super.toString();
		return ret+"[compte_rendu=" + compte_rendu + "]";
	}
	public String getCompte_rendu() {
		return compte_rendu;
	}

	public void setCompte_rendu(String compte_rendu) {
		this.compte_rendu = compte_rendu;
	}
	
}
