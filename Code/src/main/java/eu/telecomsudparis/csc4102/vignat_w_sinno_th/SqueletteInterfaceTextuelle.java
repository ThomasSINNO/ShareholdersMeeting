// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.vignat_w_sinno_th;

import java.util.ArrayList;

import eu.telecomsudparis.csc4102.util.Console;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Menu d'utilisation de l'application.
 */
public class SqueletteInterfaceTextuelle {

	/**
	 * Reference sur l'instance.
	 */
	private static SqueletteFacade facade = null;
	
	private static int choixInt(int min, int max,String message) throws CancelOperationBackToMenu
	{
		String choice_s ;
		int choice=-1;
		while(choice>max || choice<min)
		{
			choice_s=Console.readLine(message+"\n #exit# pour annuler l'opération et revenir au menu\n");
			try
			{
				choice=Integer.parseInt(choice_s.trim());				
			}
			catch(NumberFormatException a)
			{
				if(choice_s.equals("#exit#"))
					throw new CancelOperationBackToMenu();
				choice=min-1;
			}					
		}
		return choice;
	}
	private static <E> E choixTableauObjet(ArrayList<E> a, String name_object) throws IllegalArgumentException,CancelOperationBackToMenu
	{
		if(a.size()<=0)
			throw new IllegalArgumentException("On veut choisir un "+name_object+" or le tableau"
					+ " conteneur n'en contient aucun");
		//affichage des options:
		for(int i=0;i<a.size();i++)
		{
			Console.printPrompt(i+":"+a.get(i).toString());
		}
		Console.printPrompt("\n");
		//demande d'un bon numero
		int choice =choixInt(0,a.size()-1,"Choisissez le numéro de "+ name_object+" parmi ceux proposés");
		E res=a.get(choice);
		Console.printPrompt(name_object+" choisi:"+a.get(choice).toString());
		Console.printPrompt("\n");
		return res;
	}

	private static void casUtilisation1() throws CancelOperationBackToMenu
	{
		while(true)
		{
			try
			{
				//throw new UnsupportedOperationException();
				//ajouter une résidence
				String nom = Console.readLine("entrez le nom de la résidence :");
				int t= choixInt(0,Integer.MAX_VALUE,"entrez le nombre total de tantiemes de la résidence :");
				Console.printPrompt(facade.casUtilisation1(nom, t));
				return;
			}
			catch(NotFoundException nfe)
			{
				//we don't do anything and we'll just loop over in the while
			}
		}
	}
	private static void casUtilisation2() throws CancelOperationBackToMenu
	{
		while(true)
		{
			try
			{
				String nomRes = Console.readLine("entrez le nom de la résidence à chercher:");			
				String n=Console.readLine("choisissez le nom du copropriétaire");
				String p=Console.readLine("choisissez le prénom du copropriétaire");
				Console.printPrompt(facade.casUtilisation2(nomRes,n, p));
				return;	
			}
			catch(NotFoundException nfe)
			{
				//we don't do anything and we'll just loop over in the while
				Console.printPrompt("ERREUR:"+nfe.getMessage()+"\n");
			}
		}
	}
	private static void casUtilisation3() throws CancelOperationBackToMenu
	{
		while(true)
		{
			try
			{
				String nomRes = Console.readLine("entrez le nom de la résidence à chercher:");
				int num = choixInt(0,Integer.MAX_VALUE,"entrez le numéro du lot de copropriété");
				int t = choixInt(0,Integer.MAX_VALUE,"entrez le nombre de tantiemes du lot de copropriété :");
				String n=Console.readLine("choisissez le nom du copropriétaire à chercher");
				String p=Console.readLine("choisissez le prénom du copropriétaire à chercher");
				Console.printPrompt(facade.casUtilisation3(nomRes,num,t,n, p));
				return;	
			}
			catch(NotFoundException nfe)
			{
				//we don't do anything and we'll just loop over in the while
				Console.printPrompt("ERREUR:"+nfe.getMessage()+"\n");
			}
		}
	}
	private static void casUtilisation4() throws CancelOperationBackToMenu
	{
		while(true)
		{
			try
			{
				String nomRes = Console.readLine("entrez le nom de la résidence à chercher:");
				int j = choixInt(1,31,"entrez le jour de l'AG");
				int m = choixInt(1,12,"entrez le mois de l'AG");
				int a = choixInt(1,Integer.MAX_VALUE,"entrez l'année de l'AG");
				Console.printPrompt(facade.casUtilisation4(nomRes, j, m, a));
				return;
			}
			catch(NotFoundException nfe)
			{
				//we don't do anything and we'll just loop over in the while
				Console.printPrompt("ERREUR:"+nfe.getMessage()+"\n");
			}
		}
	}
	private static void casUtilisation5() throws CancelOperationBackToMenu
	{
		while(true)
		{
			try
			{
				String nomRes = Console.readLine("entrez le nom de la résidence à chercher:");
				int id_ag=choixInt(0,Integer.MAX_VALUE,"entrez l'id de l'assemblée générale à chercher");
				String q=Console.readLine("choisissez le titre de la question");
				int votee = choixInt(0,1,"entrez 0 pou	r une question non votée, 1 pour une question votee");
				if(votee==0)
				{
					Console.printPrompt(facade.casUtilisation5a(nomRes, id_ag, q));
					return;
				}
				else
				{
					// recupere la liste de tous les types de majorite possibles
					ArrayList<TYPE_MAJORITE> tab_type_vote= new ArrayList<TYPE_MAJORITE>();
					for (TYPE_MAJORITE type : TYPE_MAJORITE.values()) 
					{
						tab_type_vote.add(type);
			        }
					//on choisit dedans
					TYPE_MAJORITE vv=choixTableauObjet(tab_type_vote,"Vote");				
					try
					{
						Console.printPrompt(facade.casUtilisation5b(nomRes,id_ag,q,vv));
						return;
					}
					catch(UnsupportedOperationException pb)// on essaye d'ajouter une question votee alors que l'assemble a deja commencé
					{
						Console.printPrompt(pb.getMessage());
						int c = choixInt(0,1,"entrez 0 pour quitter, 1 pour l'ajouter en question non votee");
						if(c==1)
							Console.printPrompt(facade.casUtilisation5a(nomRes, id_ag, q));
						return;
					}
				}
			}
			catch(NotFoundException nfe)
			{
				//we don't do anything and we'll just loop over in the while
				Console.printPrompt("ERREUR:"+nfe.getMessage()+"\n");
			}
		}
	}
	
	private static void casUtilisation6() throws CancelOperationBackToMenu
	{
		while(true)
		{
			try
			{
				String nomRes = Console.readLine("entrez le nom de la résidence à chercher:");
				int id_ag=choixInt(0,Integer.MAX_VALUE,"entrez l'id de l'assemblée générale à chercher");
				int c = choixInt(0,1,"entrez 0 si c'est un proprietaire present, 1 si c'est un mandataire");
				if(c==0)
				{
					String nom_prop = Console.readLine("entrez le nom du proprietaire à chercher:");
					String prenom_prop = Console.readLine("entrez le prenom du proprietaire à chercher:");
					try
					{		
							c = choixInt(0,1,"entrez 0 si il est a l'heure, 1 s'il est en retard");
							Console.printPrompt(facade.casUtilisation6a(nomRes, id_ag, nom_prop, prenom_prop, c==1));	
							return;		
					}
					catch (IllegalArgumentException iae)//on essai d'ajouter un proprietaire qui est deja marqué sur la liste d'appel
					{
							Console.printPrompt("ERREUR:"+iae.getMessage()+"\n");
							return;
					}			
				}
				else
				{
					Console.printPrompt("Commencez par choisir son représentant:\n");
					String nom_pp = Console.readLine("entrez le nom du proprietaire à chercher:");
					String prenom_pp = Console.readLine("entrez le prenom du proprietaire à chercher:");
					Console.printPrompt("Choisissez maintenant le propriétaire mandant:\n");
					String nom_m = Console.readLine("entrez le nom du proprietaire à chercher:");
					String prenom_m = Console.readLine("entrez le prenom du proprietaire à chercher:");
					try
					{				
						Console.printPrompt(facade.casUtilisation6b(nomRes, id_ag, nom_pp, prenom_pp, nom_m, prenom_m));
						return;
					}
					catch (IllegalArgumentException iae)//on essai d'ajouter un proprietaire qui est deja marqué sur la liste d'appel
					{
						Console.printPrompt("ERREUR:"+iae.getMessage()+"\n");
						return;
					}
				}
			}
			catch(NotFoundException nfe)
			{
				//we don't do anything and we'll just loop over in the while
				Console.printPrompt("ERREUR:"+nfe.getMessage()+"\n");
			}
		}
	}
	
	private static void casUtilisation7() throws CancelOperationBackToMenu
	{
		while(true)
		{
			try
			{
				String nomRes = Console.readLine("entrez le nom de la résidence à chercher:");
				int id_ag=choixInt(0,Integer.MAX_VALUE,"entrez l'id de l'assemblée générale à chercher");
				int id_q = choixInt(0,Integer.MAX_VALUE,"entrez l'id de la question votee à chercher");
				int c = choixInt(0,1,"entrez 0 si c'est un proprietaire present, 1 si c'est un mandataire");
				if(c==0)
				{
					String nom_prop = Console.readLine("entrez le nom du proprietaire à chercher:");
					String prenom_prop = Console.readLine("entrez le prenom du proprietaire à chercher:");
					// recupere la liste de tous les types de votes possibles
					ArrayList<TYPE_VOTE> tab_type_vote= new ArrayList<TYPE_VOTE>();
					for (TYPE_VOTE type : TYPE_VOTE.values()) 
					{
						tab_type_vote.add(type);
			        }
					//on choisit dedans
					TYPE_VOTE vv=choixTableauObjet(tab_type_vote,"Vote");
					try
					{		
							facade.casUtilisation7a(nomRes, id_ag, id_q, nom_prop, prenom_prop, vv);
							return;
					}
					catch (IllegalArgumentException iae)//on essai de faire voter deux fois la même personne
					{
							Console.printPrompt("ERREUR:"+iae.getMessage()+"\n");
							return;
					}							
				}
				else
				{
					Console.printPrompt("Commencez par choisir son représentant:\n");
					String nom_prop = Console.readLine("entrez le nom du proprietaire à chercher:");
					String prenom_prop = Console.readLine("entrez le prenom du proprietaire à chercher:");
					Console.printPrompt("Choisissez maintenant le propriétaire mandant:\n");
					String nom_m = Console.readLine("entrez le nom du proprietaire à chercher:");
					String prenom_m = Console.readLine("entrez le prenom du proprietaire à chercher:");
					// recupere la liste de tous les types de votes possibles
					ArrayList<TYPE_VOTE> tab_type_vote= new ArrayList<TYPE_VOTE>();
					for (TYPE_VOTE type : TYPE_VOTE.values()) 
					{
						tab_type_vote.add(type);
			        }
					//on choisit dedans
					TYPE_VOTE vv=choixTableauObjet(tab_type_vote,"Vote");
					try
					{		
							facade.casUtilisation7b(nomRes, id_ag, id_q, nom_prop, prenom_prop,nom_m,prenom_m, vv);			
							return;
					}
					catch (IllegalArgumentException iae)//on essai de faire voter deux fois la même personne
					{
							Console.printPrompt("ERREUR:"+iae.getMessage()+"\n");
							return;
					}
				}
			}
			catch(NotFoundException nfe)
			{
				//we don't do anything and we'll just loop over in the while
				Console.printPrompt("ERREUR:"+nfe.getMessage()+"\n");
			}
		}
	}
	
	/**
	 * Fonction main.
	 */
	public static void main(final String[] args) {
		facade = new SqueletteFacade();
		int choix;
		while ((choix = menu()) > 0) 
		{
			switch (choix) 
			{
				case 1:
					try
					{
						casUtilisation1();
					}
					catch(CancelOperationBackToMenu a)
					{
						break;
					}
					break;
				case 2:
					try
					{
						casUtilisation2();
					}
					catch(CancelOperationBackToMenu a)
					{
						break;
					}
					break;
				case 3:
					try
					{
						casUtilisation3();
					}
					catch(CancelOperationBackToMenu a)
					{
						break;
					}
					break;
				case 4:
					try
					{
						casUtilisation4();
					}
					catch(CancelOperationBackToMenu a)
					{
						break;
					}
					break;
				case 5:
					try
					{
						casUtilisation5();
					}
					catch(CancelOperationBackToMenu a)
					{
						break;
					}
					break;
				case 6:
					try
					{
						casUtilisation6();
					}
					catch(CancelOperationBackToMenu a)
					{
						break;
					}
					break;
				case 7:
					try
					{
						casUtilisation7();
					}
					catch(CancelOperationBackToMenu a)
					{
						break;
					}
					break;
				default:
					break;
			}
		}
			
	}

	/**
	 * Affichage d'un menu.
	 * 
	 * @return code du menu (0 ==> quit)
	 */
	static int menu() {
		System.out.println("\n" + ": Menu");
		System.out.println("  1- Ajouter une résidence");
		System.out.println("  2- Ajouter un copropriétaire dans une residence");
		System.out.println("  3- Ajouter un lot de copropriété dans une residence\n"
				+ "     (Remarque: il faut déjà avoir entré son propriétaire)");
		System.out.println("  4- Ajouter une assemblée générale pour une résidence");
		System.out.println("  5- Ajoute une question à une AG");
		System.out.println("  6- Ajouter un coproprieétaire présent/mandant à une AG\n"
				+ "     (=Remplir la feuille de présence)\n"
				+ "     ATTENTION: Ceci commencera l'AG et empêchera l'ajout de questions votées à l'AG par la suite");
		System.out.println("  7- Ajouter le vote d'un copropriétaire présent/mandant à une question\n"
				+ "     ATTENTION: Ceci empêchera de modifier/supprimer un copropriétaire de la feuille de présence");
		System.out.println("  0- quitter");
		int choix = 0;
		try {
			choix = Console.readInt("\nEntrer le choix :");
			if (choix==0)
				System.exit(0);			
		} catch (Exception e) {
			System.err.println("Erreur de saisie");
		}
		return choix;
	}
}
