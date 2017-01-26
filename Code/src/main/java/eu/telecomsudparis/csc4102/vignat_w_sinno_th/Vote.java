// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.vignat_w_sinno_th;


enum TYPE_VOTE{POUR,CONTRE,ABSTENU,ABSENT};

public class Vote 
{
	protected PPOuMandant prop_pres_ou_mandant;
	protected QuestionVotee question_votee;
	protected TYPE_VOTE vote;
	
	
	protected Vote (QuestionVotee q,PPOuMandant p, TYPE_VOTE vv) throws IllegalArgumentException
	{
		if(p==null || q==null)
		{
			throw new IllegalArgumentException("Vote constructor,null param");
		}
		prop_pres_ou_mandant=p;
		question_votee=q;
		vote=vv;
	}

	
	//--------------to string
	@Override
	public String toString() 
	{
		return "Vote [prop_pres_ou_mandant=" + prop_pres_ou_mandant + ", vote=" + vote + "]";
	}
	
	
	//--------------hashcode & equals
	/*the only  important things here are the prop_pres
	 * and question_votee attributes
	 * as the couple is supposed to be unique
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prop_pres_ou_mandant == null) ? 0 : prop_pres_ou_mandant.hashCode());
		result = prime * result + ((question_votee == null) ? 0 : question_votee.hashCode());
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
		Vote other = (Vote) obj;
		if (prop_pres_ou_mandant == null) {
			if (other.prop_pres_ou_mandant != null)
				return false;
		} else if (!prop_pres_ou_mandant.equals(other.prop_pres_ou_mandant))
			return false;
		if (question_votee == null) {
			if (other.question_votee != null)
				return false;
		} else if (!question_votee.equals(other.question_votee))
			return false;
		return true;
	}

	//--------------getters setters
	//NOTE: Votes are read-only, if modification is needed, destroy it then create one again

	public QuestionVotee getQuestion_votee()
	{
		return question_votee;
	}

	public PPOuMandant getProp_pres_ou_mandant() 
	{
		return prop_pres_ou_mandant;
	}

	public TYPE_VOTE getVote()
	{
		return vote;
	}

}
