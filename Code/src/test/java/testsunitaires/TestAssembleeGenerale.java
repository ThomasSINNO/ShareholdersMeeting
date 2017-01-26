package testsunitaires;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eu.telecomsudparis.csc4102.vignat_w_sinno_th.*;
public class TestAssembleeGenerale {
	
	private static Residence r;
	private static AssembleeGenerale ag;
	private static int j,m,a;
	private static int nb=0;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		r = new Residence("test",0);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		System.out.println(nb+"tests échoués");
	}

	@Before
	public void setUp() throws Exception {};   
	
	@After
	public void tearDown() throws Exception { ag = null ;}
	
	@Test
	public void testCtr1(){
		j=13;
		m=5;
		a=2017;
		try{
		ag = new AssembleeGenerale(j,m,a,r);	
		} catch (IllegalArgumentException a){
			nb=nb+1;
		}
	}
	
	@Test
	public void testCtr2(){
		j=35;
		m=5;
		a=2017;
		try{
		ag = new AssembleeGenerale(j,m,a,r);
		nb=nb+1;
		} catch (IllegalArgumentException a){
			
		}
		
	}
	
	@Test
	public void testCtr3(){
		j=12;
		m=15;
		a=2017;
		try{
			ag = new AssembleeGenerale(j,m,a,r);
			nb=nb+1;
			} catch (IllegalArgumentException a){
				
			}	
	}
	
	@Test
	public void testCtr4(){
		j=-2;
		m=8;
		a=2017;
		try{
			ag = new AssembleeGenerale(j,m,a,r);
			nb=nb+1;
			} catch (IllegalArgumentException a){
				
			}	
	}
	
	@Test
	public void testCtr5(){
		j=12;
		m=-3;
		a=2017;
		try{
			ag = new AssembleeGenerale(j,m,a,r);
			nb=nb+1;
			} catch (IllegalArgumentException a){
				
			}
	}
	
	@Test
	public void testCtr6(){
		j=12;
		m=8;
		a=-800;
		try{
			ag = new AssembleeGenerale(j,m,a,r);
			nb=nb+1;
			} catch (IllegalArgumentException a){
				
			}	
	}
	
	@Test
	public void testCtr7(){
		j=12;
		m=8;
		a=2017;
		r=null;
		try{
			ag = new AssembleeGenerale(j,m,a,r);
			nb=nb+1;
			} catch (IllegalArgumentException a){
				
			}		
		r= new Residence("test",0);
	}
}
