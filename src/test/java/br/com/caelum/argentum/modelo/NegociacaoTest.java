package br.com.caelum.argentum.modelo;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import junit.framework.Assert;

public class NegociacaoTest {

	@Test
	public void dataDaNegociacaoEhImutavel() {
		Calendar c = Calendar.getInstance();
		
		// cria um objeto no dia 15
		c.set(Calendar.DAY_OF_MONTH, 15);
		Negociacao n = new Negociacao(10, 5, c);
		
		// muda o objeto para o dia 20
		n.getData().set(Calendar.DAY_OF_MONTH, 20);
		
		// ele continua no dia 15
		Assert.assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));
	}

	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoDataNull() {
		new Negociacao(10, 5, null);
	}
}
