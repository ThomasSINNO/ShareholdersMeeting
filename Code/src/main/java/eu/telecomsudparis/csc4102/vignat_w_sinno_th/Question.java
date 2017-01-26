// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.vignat_w_sinno_th;

public abstract class Question 
{

	private String question;
	private AssembleeGenerale ag_parent;
	private int id;
	private static int id_base;
	
	static
	{
		id_base=0;
	}
	
	public void setQuestion(String q)
	{
		question=q;
	}
	
	protected Question(AssembleeGenerale ag, String q) throws IllegalArgumentException
	{
		if(ag==null || q.equals(""))
		{
			throw new IllegalArgumentException("Question constructor, pb param");
		}
		id=id_base;
		id_base++;
		ag_parent=ag;
		question=q;
	}
	
	
	//------------to string
	@Override
	public String toString() 
	{
		return "Question["+id+"]"+ question + "\n";
	}
	
	//-------------hashcode& equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ag_parent == null) ? 0 : ag_parent.hashCode());
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
		Question other = (Question) obj;
		if (ag_parent == null) {
			if (other.ag_parent != null)
				return false;
		} else if (!ag_parent.equals(other.ag_parent))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	//-------------getters&setters
	

	public AssembleeGenerale getAg_parent() 
	{
		return ag_parent;
	}


	public String getQuestion() 
	{
		return question;
	}

	
	public int getId() 
	{
		return id;
	}


}
