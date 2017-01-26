// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.vignat_w_sinno_th;

import java.util.ArrayList;

enum TYPE_MAJORITE
{
	SIMPLE,DOUBLE,ABSOLUE
};

public class QuestionVotee extends Question
{
	protected int sum_pour,sum_contre,sum_total;
	private AlgorithmeVote fonction_vote_accepte;
	private ArrayList<Vote> votes;
	
	public QuestionVotee(AssembleeGenerale ag,String q,AlgorithmeVote t)
	{
		super(ag,q);
		sum_pour=0;
		sum_contre=0;
		votes= new ArrayList<Vote>();
		fonction_vote_accepte=t;

	}

	//verifier qu'il n'y ait pas un prop present/mandant
	// qui ait voté deux fois 
	public boolean invariant()
	{
		for(int i=0;i<this.votes.size();i++)
		{
			if(this.findVote(this.votes.get(i).getProp_pres_ou_mandant().getProp() , i) != null)
				return false;
		}
		return true;
	}
	
	//NOTE: Votes are read-only, if modification is needed, destroy it then create one again
	public void ajVote (PPOuMandant pp,TYPE_VOTE vv) throws IllegalArgumentException
	{
		if(this.findVote(pp.getProp(),0)!=null)
		{
			throw new IllegalArgumentException("Un proprietaire essai de voter 2 fois");
		}
		
		//TODO try catch
		Vote v= new Vote(this,pp,vv);
		
		
		if(this.getAg_parent().getCan_m_d_pp())//change state if needed
			this.getAg_parent().setCan_m_d_pp(false);

		votes.add(v);
		updateSums(v);		
	}

	public boolean enlVote(Vote v) throws IllegalArgumentException
	{
		if( v==null)
		{
			throw new IllegalArgumentException(""
					+ "enlVote, null param");
		}
		if(v.getQuestion_votee()!=this)
		{
			throw new IllegalArgumentException(""+"enlVote, wrong question");
	
		}
		return votes.remove(v);
	}
	
	public Vote findVote(Proprietaire p, int offset)
	{
		Vote v;
		for(int i=offset;i<votes.size();i++)				
		{
			v=votes.get(i);
			if(v.getProp_pres_ou_mandant().getProp().equals(p))
				return v;
		}
		return null;
	}
		
	public void updateSums(Vote v)
	{
		if(v.getVote()==TYPE_VOTE.POUR)
		{
			sum_pour+= v.getProp_pres_ou_mandant().getProp().getAppartement().getTantiemes();
		}
		else if(v.getVote()==TYPE_VOTE.CONTRE)
		{
			sum_contre+= v.getProp_pres_ou_mandant().getProp().getAppartement().getTantiemes();
		}					
	}
	
	//--------------to string
	@Override
	public String toString() {
		String ret= "[sum_pour=" + sum_pour + ", sum_contre=" + sum_contre ;
		if(sum_total<=0)
			ret+="=>pas encore votée";
		else if(fonction_vote_accepte.isVoteAccepted(sum_pour,sum_contre, sum_total))
			ret+="=>accepté";
		else
			ret+="=>refusé";
		ret += ", votes=" + votes + "]";
		return super.toString()+ret;
	}

	public int getNbr_votes() 
	{
		return votes.size();
	}

	public ArrayList<Vote> getVotes() 
	{
		return votes;
	}
	

}
