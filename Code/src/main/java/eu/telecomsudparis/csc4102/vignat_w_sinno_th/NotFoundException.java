// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.vignat_w_sinno_th;

public class NotFoundException extends Exception
{
	public NotFoundException(String name_class)
	{
		super("L'objet de type "+name_class+"pas trouvé");
	}
}
