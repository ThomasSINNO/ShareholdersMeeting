// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.vignat_w_sinno_th;


import eu.telecomsudparis.csc4102.vignat_w_sinno_th.Proprietaire;
import java.io.IOException;
public abstract class PPOuMandant 
{
	protected Proprietaire prop;
	protected AssembleeGenerale ag_parent;
	
	public PPOuMandant(AssembleeGenerale ag,Proprietaire p) throws IllegalArgumentException
	{
		if( ag ==null || p==null)
		{
			throw new IllegalArgumentException(""
					+ "PPOuMandant constructor, null param");
		}
		prop=p;
		ag_parent=ag;
	}


	//--------------to string
	@Override
	public String toString() 
	{
		return "PPOuMandant [prop=" + prop + "]";
	}
	//--------------hashcode&equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ag_parent == null) ? 0 : ag_parent.hashCode());
		result = prime * result + ((prop == null) ? 0 : prop.hashCode());
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
		PPOuMandant other = (PPOuMandant) obj;
		if (ag_parent == null) {
			if (other.ag_parent != null)
				return false;
		} else if (!ag_parent.equals(other.ag_parent))
			return false;
		if (prop == null) {
			if (other.prop != null)
				return false;
		} else if (!prop.equals(other.prop))
			return false;
		return true;
	}

	//--------------
	
	public Proprietaire getProp() 
	{
		return prop;
	}

	public void setProp(Proprietaire prop) throws IllegalArgumentException
	{
		try
		{
			this.getAg_parent().findPPouMandant(prop);
			//if it continues without exception there is a pb:
			throw new IllegalArgumentException("proprietaire deja present ou mandant");			
		}
		catch (NotFoundException nfe) //this is something that SHOULD happen
		{
			if(!this.getAg_parent().getCan_m_d_pp())
			{
				throw new IllegalArgumentException("impossible de modifier le pp, il y a deja eu un vote");
			}
			this.prop = prop;			
		}		
	}


	
	


	public AssembleeGenerale getAg_parent() 
	{
		return ag_parent;
	}

	

}
