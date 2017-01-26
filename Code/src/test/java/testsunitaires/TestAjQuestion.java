/**
 * 
 */
package testsunitaires;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eu.telecomsudparis.csc4102.vignat_w_sinno_th.*;
/**
 * @author sinno_th
 *
 */
public class TestAjQuestion {
	
	private static Residence r;
	private static AssembleeGenerale ag;
	private static AlgorithmeVoteAbsolu t;
	private static int nb;
	private static String s;
	private static int c=0;
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		r = new Residence("test",0);
		ag = new AssembleeGenerale(26,6,2017,r);
		t = new AlgorithmeVoteAbsolu();
		nb=0;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println(nb+"tests échoués");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		c=c+1;
		s="test"+c;
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Méthode de test pour {@link eu.telecomsudparis.csc4102.vignat_w_sinno_th.AssembleeGenerale#ajQuestion(java.lang.String, eu.telecomsudparis.csc4102.vignat_w_sinno_th.AlgorithmeVote)}.
	 */
	@Test
	public void testAjQuestion1() {
		ag.setCan_a_vq(true);
		try{
		ag.ajQuestion(s, t);
		} catch (UnsupportedOperationException a){
			nb=nb+1;
		}
	}
	
	@Test
	public void testAjQuestion2() {
		ag.setCan_a_vq(false);
		try{
		ag.ajQuestion(s, t);
		nb=nb+1;
		} catch (UnsupportedOperationException a){
			
		}
	}

}
