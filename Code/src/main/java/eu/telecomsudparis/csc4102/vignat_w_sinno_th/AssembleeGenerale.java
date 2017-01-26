package eu.telecomsudparis.csc4102.vignat_w_sinno_th;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;




public class AssembleeGenerale 
{ 
	/**
	 * La date de l'assemblée
	 */
	protected LocalDate date;
	/**
	 * Peut on modifier ou supprimer un ProprietairePresent de l'ArrayList prop_presents
	 * <br> Ce sera vrai au début, puis dès que l'on commence à voter on ne veut plus
	 * pouvoir modifier ceci
	 */
	protected boolean can_m_d_pp;
	/**
	 * Peut on ajouter une question VOTEE à l'ArrayList questions	 
	 * <br>Vrai au debut, puis faux dès que l'assemblée débute
	 */
	protected boolean can_a_vq;
	/**
	 * Le conteneur de toutes les questions (votées ou non) de cette assemblée,
	 * c'est l'équivaent de l'ordre du jour
	 */
	protected ArrayList<Question> questions;
	/**
	 * Contient les propriétaires vraiment présents à cette assemblée générale, leurs
	 * possibles mandants sont stockés dans une sous-liste de la classe ProprietairePresent
	 * avoir les deux permets ainsi de générer la feuille de présence
	 */
	protected ArrayList<ProprietairePresent> prop_presents;
	/**
	 * La résidence correspondant à cette assemblée générale
	 */
	protected Residence residence;
	private int id;
	private static int id_base;
	
	static
	{
		id_base=0;
	}
	
	/**
	 *  Vérifie si la date entrée est valide ou non
	 *  
	 * @param j le jour de la date à vérifier
	 * @param m le mois de la date à vérifier
	 * @param a l'année de la date à vérifier
	 * @return true si la date est INvalide
	 * cad si (j<=0) ||(a<=0)||(m<=0)|| (j>31) || ( m>12)
	 * ,false autrement
	 */
	public static boolean isInvalidDate(int j, int m, int a)
	{
		return ( 
				
				(j<=0) ||(a<=0)||(m<=0) 			
				|| (j>31) || ( m>12) 
				
				);
	}
	
	/**
	 * Construit  l'objet représentant notre assemblée
	 * 
	 * @param j le jour de la date de l'assemblée
	 * @param m le mois de la date de l'assemblée
	 * @param a l'année de la date de l'assemblée
	 * @param r la résidence de cette assemblée
	 * @throws IllegalArgumentException
	 */
	public AssembleeGenerale(int j, int m, int a, Residence r) throws IllegalArgumentException
	{    
		if(isInvalidDate(j,m,a) || r==null)
		{
			throw new IllegalArgumentException("AssembleeGenerale constructor, pb param");
		}
		id=id_base;
		id_base++;
		date= LocalDate.of(a,m,j);
		can_m_d_pp=true;
		can_a_vq=true;
		residence= r;
		questions= new ArrayList<Question>();
 		prop_presents= new ArrayList<ProprietairePresent>();
	}

	/**
	 * L'invariant de notre classe
	 * @return false si il y a un gros problème très visible dans notre classe, true
	 * si à première vue c'est ok
	 */
	public boolean invariant()
	{
		//invariant : (can_a_vq =>  can_m_d_pp)
		return ( (!this.can_a_vq || this.can_m_d_pp) );
	}
	
	//question
	/**
	 * Ajoute une question non votee à l'ordre du jour
	 * @param q le titre de la question
	 * @throws UnsupportedOperationException
	 */
	public void ajQuestion(String q) throws UnsupportedOperationException
	{
		//TODO try catch
		QuestionNonVotee qnv= new QuestionNonVotee (this,q);
		questions.add(qnv);
	}
	public void ajQuestion(String q,AlgorithmeVote t) throws UnsupportedOperationException
	{
		if(!this.can_a_vq)
		{
			throw new UnsupportedOperationException("Can't add a voted question since the assembly has already begun");
		}
		else
		{
			//TODO try catch
			QuestionVotee qv= new QuestionVotee (this,q,t);
			questions.add(qv);
		}
	}
	/**
	 * Enlève une question de l'ordre du jour
	 * @param q la question devant être enlevée
	 * @return true si elle a été enlevée avec succès
	 * @throws IllegalArgumentException
	 */
	public boolean enlQuestion(Question q) throws IllegalArgumentException
	{
		if( q==null)
		{
			throw new IllegalArgumentException(""
					+ "enlQuestion, null param");
		}
		if(q.getAg_parent()!=this)
		{
			//TODO throw essai enlever question d'une autre ag
			return false;
		}
		return questions.remove(q);
	}

	/**
	 * Cherche une question à partir de son id
	 * @param id l'id de la question
	 * @return
	 */
	public Question findQuestion(int id)throws NotFoundException
	{
		for( Question qq: questions)				
		{
			if(qq.getId()==id)
				return qq;
		}
		return null;
	}
	
	//Proprietaire present & mandant
	/**
	 * Enlève un propriétaire présent de la feuille d'appel
	 * @param pp le proprietaire PRESENT à enlever
	 * @return
	 */
	public boolean enlPropPresent(ProprietairePresent pp)
	{
		if( pp==null)
		{
			throw new IllegalArgumentException(""
					+ "enlPropPresent, null param");
		}
		if(pp.getAg_parent()!=this)
		{
			//TODO throw essai enlever pp d'une autre ag
			return false;
		}
		if(! this.can_m_d_pp)
		{
			//TODO throw essai enlever pp alors qu'il y a deja eu un vote
			return false;
		}
		//TODO remove corresponding mandants as well
		//TODO enlever tantiems du pp + de ses mandants
		return prop_presents.remove(pp);
	}
	/**
	 * Ajoute un propriétaire présent à la feuille d'appel
	 * @param p le proprietaire a ajouter
	 * @param retard
	 */
	public void ajPropPresent(Proprietaire p, boolean retard) throws IllegalArgumentException
	{
		if(this.can_a_vq)//change its state if need be
			this.can_a_vq=false;
		try
		{
			this.findPPouMandant(p);
			throw new IllegalArgumentException("Ce propriétaire est deja sur la feuille de présence, impossible "
					+ "de le rajouter une seconde fois");			
		}
		catch (NotFoundException nfe)
		{
			//TODO try catch
			ProprietairePresent pp= new ProprietairePresent(this,p,retard);
			//TODO ajouter sum tantiem
			prop_presents.add(pp);
		}		
	}
	

	/**
	 * Cherche un proprietaire parmis les proprietaire 
	 * presents (pas parmis les mandants donc)référencés à cette assemblée
	 * @param p le proprietaire cherché
	 * @return la reference au proprietaire present trouvé, null si rien n'a été trouvé
	 */
	public ProprietairePresent findPP(Proprietaire p)throws NotFoundException
	{
		for( ProprietairePresent pp: prop_presents)				
		{
			if(pp.getProp().equals(p))
				return pp;
		}
		throw new NotFoundException("ProprietairePresent");
	}
	/**
	 * Cherche un proprietaire parmis tous les proprietaires présents et les mandants
	 *  référencés à cette assemblée
	 * @param p le proprietaire cherché
	 * @return
	 */
	public Mandant findMandantTousPP(Proprietaire p)throws NotFoundException
	{
		Mandant m;
		for(ProprietairePresent pp : prop_presents)				
		{
			if( (m=pp.findMandant(p)).equals(p))
				return m;
		}
		throw new NotFoundException("Mandant");
	}
	/**
	 * Cherche un proprietaire 
	 * parmis les mandants (pas parmis les proprietaires presents donc) référencés par les proprietaires présent à cette assemblée
	 * @param p le proprietaire cherché
	 * @return
	 */
	public PPOuMandant findPPouMandant(Proprietaire p)throws NotFoundException
	{
		Mandant m;
		//On pourrait utiliser les 2 fonctions au dessus mais ce serait beaucoup
		//moins efficaces, on parcourerais bien trop de fois les mêmes listes
		for(ProprietairePresent pp : prop_presents)				
		{
			if(pp.getProp().equals(p))
				return pp;
			if( (m=pp.findMandant(p)).equals(p))
				return m;
		}
		throw new NotFoundException("PPOuMandant");	
	}
	
	
	//------------to string
	@Override
	public String toString() 
	{
		return "AssembleeGenerale["+id+"] [residence=" + residence + ", date=" + date + ", prop_presents=" + prop_presents
				+ ", questions=" + questions + "]";
	}
	
	
	//------------equals&hashcode
	/* a date and a residence uniquelly identify an 
	 * assemblee
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((residence == null) ? 0 : residence.hashCode());
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
		AssembleeGenerale other = (AssembleeGenerale) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (residence == null) {
			if (other.residence != null)
				return false;
		} else if (!residence.equals(other.residence))
			return false;
		return true;
	}
	
	//-------------getters&setters
	
	public void setCan_m_d_pp(boolean can_m_d_pp) 
	{
		this.can_m_d_pp = can_m_d_pp;
	}
	public void setCan_a_vq(boolean can_a_vq) 
	{
		this.can_a_vq = can_a_vq;
	}
	public void setDate(LocalDate date) 
	{
		this.date = date;
	}
	public void setDate(int j, int m, int a) 
	{
		this.setDate(LocalDate.of(a, m, j));;
	}

	
	public int getId() {
		return id;
	}

	public Residence getResidence() 
	{
		return residence;
	}
	public Boolean getCan_m_d_pp() 
	{
		return can_m_d_pp;
	}
	public Boolean getCan_a_vq() 
	{
		return can_a_vq;
	}
	public LocalDate getDate() 
	{
		return date;
	}
	public ArrayList<Question> getQuestions() 
	{
		return questions;
	}

	public ArrayList<ProprietairePresent> getProp_presents() 
	{
		return prop_presents;
	}
	
}
