// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.vignat_w_sinno_th;

public class AlgorithmeVoteSimple extends AlgorithmeVote 
{
	public boolean isVoteAccepted(int sum_pour,int sum_contre, int sum_total)
	{
		return sum_pour>sum_contre;			
	}
}
