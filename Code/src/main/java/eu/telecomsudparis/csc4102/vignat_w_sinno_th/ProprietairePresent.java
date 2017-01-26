// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.vignat_w_sinno_th;


import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import eu.telecomsudparis.csc4102.vignat_w_sinno_th.Proprietaire;
public class ProprietairePresent extends PPOuMandant
{
	protected ArrayList<Mandant> mandants;
	protected LocalTime arrivee_retard;
	protected LocalTime depart_avance;
		
	public ProprietairePresent(AssembleeGenerale ag,Proprietaire p, boolean r)
	{
		super(ag,p);
		if( p==null)
		{
			throw new IllegalArgumentException(""
					+ "ProprietairePresent constructor, null param");
		}
		if(! r)		
			arrivee_retard=null;		
		else
			arrivee_retard=LocalTime.now();
		mandants= new ArrayList<Mandant>();
		depart_avance=null;
	}

	//-------------To string
	@Override
	public String toString() 
	{
		String ret=super.toString();
		return ret+"ProprietairePresent [mandants=" + mandants + ", arrivee_retard=" + arrivee_retard + ", depart_avance="
				+ depart_avance + "]";
	}
	
	//--------------Mandants
	
	public Mandant findMandant(Proprietaire p)
	{
		for(Mandant m: this.mandants)				
		{
			if(m.getProp().equals(p))
				return m;
		}
		return null;
	}
	


	public void  ajMandant(Proprietaire p) throws IllegalArgumentException
	{
		try
		{
			this.getAg_parent().findPPouMandant(p);
			//if it continues without exception there is a pb:
			throw new IllegalArgumentException("Ce propriétaire est deja sur la feuille de présence, impossible "
					+ "d'en faire un mandant");		
		}
		catch (NotFoundException nfe) //this is something that SHOULD happen
		{
			//TODO try catch
			Mandant m=new Mandant(this.getAg_parent(), this,p);
			mandants.add(m);
		}
			
		
	}

	public boolean  enlMandant(Mandant m) throws IOException,IllegalArgumentException
	{
		if( m==null)
		{
			throw new IllegalArgumentException(""
					+ "enlMandant, null param");
		}
		if(m.getRepresentant()!=this)
		{
			throw new IOException("essaie enlever mandant d'un autre pp");
		}		
		//TODO enlever tantiemes
		return mandants.remove(m);
	}
	
	//---------------getters setters

	public LocalTime getArrivee_retard() 
	{
		return arrivee_retard;
	}


	public void setArrivee_retard(LocalTime time) 
	{
		this.arrivee_retard = time;
	}
	public void setArrivee_retard(int h, int m) 
	{
		this.setArrivee_retard(LocalTime.of(h, m));
	}

	public LocalTime getDepart_avance() 
	{
		return depart_avance;
	}

	public void setDepart_avance(LocalTime depart_avance) 
	{
		this.depart_avance = depart_avance;
	}
	public void setDepart_avance(int h, int m) 
	{
		this.setDepart_avance(LocalTime.of(h, m));
	}

	public ArrayList<Mandant> getMandants() 
	{
		return mandants;
	}

	
	

	

}
