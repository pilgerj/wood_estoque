package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import domain.ControleCadastro;
import domain.ControleEstoque;
import domain.EstoqueRepository;
import domain.Ferramenta;
import domain.Gaveta;
import domain.OperacaoRepository;
import infraestructure.EstoqueMemoryRepository;
import infraestructure.OperacaoMemoryRepository;

public class TesteUnitarioEstoque {

	OperacaoRepository oR = new OperacaoMemoryRepository();
	EstoqueRepository eR = new EstoqueMemoryRepository();
	ControleEstoque c1 = new ControleEstoque(oR, eR);
	ControleCadastro cc = new ControleCadastro(eR);
	Ferramenta m1 = new Ferramenta("Martelo", 1);
	Ferramenta m4 = new Ferramenta("Martelo", 4);
	Ferramenta m3 = new Ferramenta("Martelo", 8);
	Ferramenta m2 = new Ferramenta("Macaco", 2);
	Ferramenta m5 = new Ferramenta("Prego", 5);
	Gaveta g1 = new Gaveta(1, "gavetaUm");
	Gaveta g2 = new Gaveta(2, "gavetaDois");
	Gaveta g3 = new Gaveta(3, "gavetaTres");
	
	@Before
	public void beforeTests() 
	{		
		cc.saveGaveta(g1);
		cc.saveGaveta(g2);
		cc.saveGaveta(g3);
	}

	@Test
	public void testEqualsMarteloPrego() {
		Ferramenta martelo = new Ferramenta("Martelo", 1);
		Ferramenta prego = new Ferramenta("Prego", 2);
		assertFalse(martelo.equals(prego));
	}
	
	@Test
	public void testEqualsMarteloMartelo() {
		Ferramenta martelo1 = new Ferramenta("Martelo", 1);
		Ferramenta martelo2 = new Ferramenta("Martelo", 1);
		Ferramenta martelo3 = martelo2;
		
		assertFalse(martelo1 == martelo2);
		assertTrue(martelo1.equals(martelo2));
		
		assertTrue(martelo2 == martelo3);
		assertTrue(martelo2.equals(martelo3));
	}
	
	@Test
	public void testTranfereQtdGaveta() {
		c1.compraFerramenta(m1,g1,5);
		assertEquals(5, c1.getQtdGaveta(m1, g1));
		assertEquals(0, c1.getQtdGaveta(m1, g2));
		
		c1.transferirProduto(g1, m1, g2, 2);

		assertEquals(3, c1.getQtdGaveta(m1, g1));
		assertEquals(2, c1.getQtdGaveta(m1, g2));
	}
	
	@Test
	public void testTdd2() {
		c1.compraFerramenta(m1, g1, 5);
		assertEquals(5, c1.getQtdGaveta(m1, g1));
		
			
	}

	@Test
	public void testControleOperacoes()
	{
		
		c1.compraFerramenta(m1,g1,5);
		
		c1.transferirProduto(g1, m1, g2, 5);
		
		assertEquals(3, c1.listaOperacoesFerramenta(m1).size());
	}
	
	@Test
	public void testBusca()
	{
		c1.compraFerramenta(m1, g2, 5);
		c1.compraFerramenta(m1, g2, 5);
		c1.compraFerramenta(m2, g3, 5);
		
		
		assertEquals(g3, c1.buscaGaveta(m2));
		assertEquals(g2, c1.buscaGaveta(m1));
		
	}
	
	@Test
	public void testQtd() { 
		//todos sao MARTELOS (M1, M3 E M4)
		c1.compraFerramenta(m1, g2, 5);
		c1.compraFerramenta(m3, g2, 5);
		c1.compraFerramenta(m4, g1, 5);
		
		assertEquals(15, c1.getTotalFerramenta(m1));
				
	}
	
	@Test 
	public void testVenda() {
		c1.compraFerramenta(m1, g2, 5);
		c1.compraFerramenta(m3, g1, 5);
		c1.compraFerramenta(m4, g1, 5);
		
		c1.vendaFerramenta(m3, g1, 2);
		c1.vendaFerramenta(m2, g3, 3);
		
		assertEquals(13, c1.getTotalFerramenta(m3));
		assertEquals(0, c1.getTotalFerramenta(m2));
		
	}
	
	@Test
	public void testTransfereQtd() {
		c1.compraFerramenta(m1, g1, 10);
		c1.compraFerramenta(m2, g2, 15);
		c1.compraFerramenta(m3, g2, 5);
					
		assertFalse(c1.transferirProduto(g1, m1, g2, 15));
		c1.transferirProduto(g1, m1, g2, 10);
		assertEquals(15, c1.getQtdGaveta(m1, g2));
				
	}
	
	@Test
	public void testBuscaGaveta()
	{
		c1.compraFerramenta(m1, g1, 10);
		c1.compraFerramenta(m2, g2, 15);
		c1.transferirProduto(g2, m2, g1, 15);
		
		
		assertEquals(g1, c1.buscaGaveta(m2));
		assertEquals(g1, c1.buscaGaveta(m1));
		
		assertEquals(0, c1.getQtdGaveta(m2, g2));
		assertEquals(15, c1.getQtdGaveta(m2, g1));
		
		
	}
	
	@Test
	public void testVendasEcompras() {

		c1.compraFerramenta(m2, g2, 5);
		
		c1.compraFerramenta(m1, g1, 5);
		c1.compraFerramenta(m3, g2, 50);
		
		assertEquals(55, c1.getTotalFerramenta(m3));
		assertEquals(5, c1.getQtdGaveta(m1, g1));
		assertEquals(50, c1.getQtdGaveta(m3, g2));
		
		//c1.vendaFerramenta(m1,g1,10);
		c1.vendaFerramenta(m1,g1,2);
		assertEquals(3, c1.getQtdGaveta(m1,g1));
		//System.out.println(c1.getQtdGaveta(m1,g1));
		
		assertEquals(53, c1.getTotalFerramenta(m1));
		
		c1.vendaFerramenta(m1,g2,40);
		assertEquals(10, c1.getQtdGaveta(m1,g2));
		 
		//c1.listarOperacoes();		
		//c1.buscaoperacoes(m1);
		
		
	}
	
	@Test
	public void testTotalGaveta() {
		c1.compraFerramenta(m1, g1, 45);
		c1.compraFerramenta(m1, g1, 10);
		
		assertEquals(55, c1.getTotalFerramentaGaveta(g1));
	}
	



}

