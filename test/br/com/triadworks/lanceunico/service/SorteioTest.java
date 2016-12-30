package br.com.triadworks.lanceunico.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.triadworks.lanceunico.modelo.Cliente;
import br.com.triadworks.lanceunico.modelo.Lance;
import br.com.triadworks.lanceunico.modelo.Promocao;
import builders.CriadorDePromocao;

public class SorteioTest {
	
	private Sorteio sorteio;
	
	private Cliente rafael;
	private Cliente rommel;
	private Cliente handerson;
	private Cliente alexandre;
	private Cliente talita;
	
	@Before
	public void setUp(){
		this.sorteio = new Sorteio();
		
		this.rafael = new Cliente("Rafael");
		this.rommel = new Cliente("Rommel");
		this.handerson = new Cliente("Handerson");
		this.alexandre = new Cliente("Alexandre");
		this.talita = new Cliente("Talita");
	}
	
	@After
	public void tearDown(){	
		//
	}

	@Test
	public void deveSortearLancesEmOrdemCrescente() {

		// passo 1: monta o cenario(ordem crescente)

		Promocao promocao = new CriadorDePromocao()
			.para("Xbox")
			.comLance(handerson, 250.0)
			.comLance(rommel, 400.0)
			.cria();
			

		// passo 2: executa a ação
		sorteio.sorteia(promocao);

		// passo 3: valida o resultado
		double maiorEsperado = 400.00;
		double menorEsperado = 250.00;

		assertEquals(maiorEsperado, sorteio.getMaiorLance(), 0.0001);
		assertEquals(menorEsperado, sorteio.getMenorLance(), 0.0001);

	}

	@Test
	public void deveSortearLancesEmOrdemDecrescente() {

		// passo 1: monta o cebario(ordem crescente)
		
		Promocao promocao = new CriadorDePromocao()
			.para("Xbox")
			.comLance(rafael, 400.0)
			.comLance(rommel, 300.0)
			.comLance(handerson, 250.0)
			.cria();

		// passo 2: executa a ação
		sorteio.sorteia(promocao);

		// passo 3: valida o resultado
		double maiorEsperado = 400.00;
		double menorEsperado = 250.00;

		assertEquals(maiorEsperado, sorteio.getMaiorLance(), 0.0001);
		assertEquals(menorEsperado, sorteio.getMenorLance(), 0.0001);

	}

	@Test
	public void deveSortearLancesQuandoHouverApenasUmLance() {

		// passo 1: monta o cebario(ordem crescente)
		Cliente rafael = new Cliente("Rafael");
		
		Promocao promocao = new CriadorDePromocao()
			.para("Xbox")
			.comLance(rafael, 600.0)
			.cria();
		

		// passo 2: executa a ação
		sorteio.sorteia(promocao);

		// passo 3: valida o resultado
		double maiorEsperado = 600.00;
		double menorEsperado = 600.00;

		assertEquals(maiorEsperado, sorteio.getMaiorLance(), 0.0001);
		assertEquals(menorEsperado, sorteio.getMenorLance(), 0.0001);

	}

	@Test
	public void deveSortearLancesEmOrdemAleatoria() {

		// passo 1: monta o cenario(ordem crescente)
		
		Promocao promocao = new CriadorDePromocao()
			.para("Xbox")
			.comLance(rafael, 1050.00)
			.comLance(rommel, 1290.99)
			.comLance(handerson, 24.70)
			.comLance(alexandre, 477.00)
			.comLance(talita, 1.25)
			.cria();

		// passo 2: executa a ação
		sorteio.sorteia(promocao);

		// passo 3: valida o resultado
		double maiorEsperado = 1290.99;
		double menorEsperado = 1.25;

		assertEquals(maiorEsperado, sorteio.getMaiorLance(), 0.0001);
		assertEquals(menorEsperado, sorteio.getMenorLance(), 0.0001);

	}

	@Test
	public void deveSortearOsTresMenoresLances() {

		Promocao promocao = new CriadorDePromocao()
		.para("Computador")
		.comLance(rafael, 300.00)
		.comLance(rommel, 100.00)
		.comLance(handerson, 20.00)
		.comLance(alexandre, 440.00)
		.comLance(talita, 1.25)
		.cria();

		sorteio.sorteia(promocao);

		List<Lance> menores = sorteio.getTresMenoresLances();

		assertEquals(3, menores.size());

		assertEquals(1.25, menores.get(0).getValor(), 0.00001);
		assertEquals(20.0, menores.get(1).getValor(), 0.00001);
		assertEquals(100.0, menores.get(2).getValor(), 0.00001);

	}

	@Test
	public void deveSortearTodosOsLancesQuandoHouverMenosDe3Lances() {

		Promocao promocao = new CriadorDePromocao()
		.para("Xbox")
		.comLance(rafael, 300.00)
		.comLance(rommel, 100.00)
		.cria();

		sorteio.sorteia(promocao);

		List<Lance> menores = sorteio.getTresMenoresLances();

		assertEquals(2, menores.size());

		assertEquals(100.00, menores.get(0).getValor(), 0.00001);
		assertEquals(300.00, menores.get(1).getValor(), 0.00001);

	}

	@Test(expected=RuntimeException.class)
	public void naoDeveSortearQuandoNaoHouverLances() {

		Promocao promocao = new Promocao("Computador");

		sorteio.sorteia(promocao);

		List<Lance> menores = sorteio.getTresMenoresLances();

		//assertEquals(0, menores.size());

	}

}