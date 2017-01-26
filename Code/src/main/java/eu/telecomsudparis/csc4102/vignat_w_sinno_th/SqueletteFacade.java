// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.vignat_w_sinno_th;

import eu.telecomsudparis.csc4102.util.Console;
import java.util.ArrayList;
import java.io.IOException;


public class SqueletteFacade {
	private ArrayList<Residence> residences;
	
	public SqueletteFacade() 
	{
		residences= new ArrayList<Residence>();
	}

	private Residence findResidence(String nom) throws NotFoundException
	{
		for(Residence r: residences)
		{
			if(r.getNom().equals(nom))
				return r;
		}
		throw new NotFoundException("Residence");
	}
	//
	public String casUtilisation1(String nom, int t) throws NotFoundException
	{
		//throw new UnsupportedOperationException();
		//ajouter une résidence
		Residence r = new Residence(nom,t);
		residences.add(r);
		return "Residence ajoutée:\n"+r.toString();
	}
	public String casUtilisation2(String nomRes,String n, String p)throws NotFoundException
	{
		//ajouter un copropriétaire dans la residence
		Residence res=findResidence(nomRes);
		res.ajProp(n, p);
		return "Copropriétaire ajouté:\n"+res.getProprietaires().get(res.getProprietaires().size()-1).toString();
	}
	public String casUtilisation3(String nomRes,int num,int t, String nom,String prenom)throws NotFoundException
	{
		Residence res=findResidence(nomRes);		
		Proprietaire p=res.findProprietaire(nom, prenom);
		res.ajAppart(num, t, p);
		return "Lot de copropriété ajouté:\n"+res.getAppartements().get(res.getAppartements().size()-1).toString();
	}
	public String casUtilisation4(String nomRes,int j,int m, int a) throws NotFoundException
	{
		//throw new UnsupportedOperationException();
		// ajouter une assemblee generale pour cette residence 
		Residence res=findResidence(nomRes);
		//TODO try catch
		res.ajAG(j, m, a);
		return "AG ajoutée:\n"+res.getAgs().get(res.getAgs().size()-1).toString();
	}
	public String casUtilisation5a(String nomRes,int id_ag,String q) throws NotFoundException
	{
		//ajouter une question NON VOTEE à l'ordre du jour d'une ag
		Residence res=findResidence(nomRes);
		AssembleeGenerale ag=res.findAssembleeGenerale(id_ag);
		ag.ajQuestion(q);
		return "Question ajoutée:\n"+ag.getQuestions().get(ag.getQuestions().size()-1).toString();
	}
	
	public String casUtilisation5b(String nomRes,int id_ag,String q, TYPE_MAJORITE vv) throws NotFoundException,UnsupportedOperationException
	{
		//throw new UnsupportedOperationException();
		//ajouter une question VOTEE à l'ordre du jour d'une ag
		Residence res=findResidence(nomRes);
		AssembleeGenerale ag=res.findAssembleeGenerale(id_ag);		
		// recupere la liste de tous les types de majorite possibles
		AlgorithmeVote t;
		switch (vv)
		{
			case SIMPLE: t=new AlgorithmeVoteSimple();
				break;
			case DOUBLE:t=new AlgorithmeVoteDouble();
				break;
			case ABSOLUE:t=new AlgorithmeVoteAbsolu();
				break;
			default:t=new AlgorithmeVoteSimple();
				break;
		}
		ag.ajQuestion(q, t);
		return "Question ajoutée:\n"+ag.getQuestions().get(ag.getQuestions().size()-1).toString();
	}
	
	public String casUtilisation6a(String nomRes,int id_ag,String nom_prop,String prenom_prop, boolean retard) throws NotFoundException,IllegalArgumentException
	{
		//remplir la feuille de presence pour un proprietaire present
		Residence res=findResidence(nomRes);
		AssembleeGenerale ag=res.findAssembleeGenerale(id_ag);
		Proprietaire p=res.findProprietaire(nom_prop, prenom_prop);		
		ag.ajPropPresent(p, retard);		
		return"Ajouté à la feuille de présence:\n"+ag.getProp_presents().get(ag.getProp_presents().size()-1).toString();
	}
	public String casUtilisation6b(String nomRes,int id_ag,String nom_pp,String prenom_pp,String nom_m, String prenom_m) throws NotFoundException,IllegalArgumentException
	{
		//remplir la feuille de presence pour un mandant
		Residence res=findResidence(nomRes);
		AssembleeGenerale ag=res.findAssembleeGenerale(id_ag);
		Console.printPrompt("Commencez par choisir son représentant:\n");
		ProprietairePresent pp=ag.findPP(res.findProprietaire(nom_pp, prenom_pp));
		Proprietaire p=res.findProprietaire(nom_m, prenom_m);
		pp.ajMandant(p);
		return "Ajouté à la feuille de présence:\n"+ag.getQuestions().get(ag.getQuestions().size()-1).toString();
	}	
	
	
	public String casUtilisation7a(String nomRes,int id_ag,int id_question,String nom_pp,String prenom_pp, TYPE_VOTE vv) throws NotFoundException, IllegalArgumentException
	{
		//ajouter le choix d'un vote d'un proprietaire present
		Residence res=findResidence(nomRes);
		AssembleeGenerale ag=res.findAssembleeGenerale(id_ag);
		//on choisit la question pour laquelle on ajoute le vote
		Question q =ag.findQuestion(id_question);
		if(q.getClass()!=QuestionVotee.class)
		{
			throw new IllegalArgumentException("La question entrée n'est pas une question à voter, veuillez en choisir une compatible");
		}
		QuestionVotee qv =(QuestionVotee) q;
		ProprietairePresent pp=ag.findPP(res.findProprietaire(nom_pp, prenom_pp));
		qv.ajVote(pp, vv);
		return "Vote ajouté:\n"+qv.getVotes().get(qv.getVotes().size()-1).toString();		
	}
	public String casUtilisation7b(String nomRes,int id_ag,int id_question,String nom_pp,String prenom_pp,String nom_m,String prenom_m,TYPE_VOTE vv) throws NotFoundException, IllegalArgumentException
	{
		//throw new UnsupportedOperationException();
		//ajouter le choix d'un vote d'un mandataire
		Residence res=findResidence(nomRes);
		AssembleeGenerale ag=res.findAssembleeGenerale(id_ag);
		//on choisit la question pour laquelle on ajoute le vote
		Question q =ag.findQuestion(id_question);
		if(q.getClass()!=QuestionVotee.class)
		{
			throw new IllegalArgumentException("La question entrée n'est pas une question à voter, veuillez en choisir une compatible");
		}
		QuestionVotee qv =(QuestionVotee) q;
		ProprietairePresent pp=ag.findPP(res.findProprietaire(nom_pp, prenom_pp));
		Mandant m=pp.findMandant(res.findProprietaire(nom_m, prenom_m));
		qv.ajVote(m, vv);
		return "Vote ajouté:\n"+qv.getVotes().get(qv.getVotes().size()-1).toString();		
	}

}
