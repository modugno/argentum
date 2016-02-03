package br.com.caelum.argentum.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
	
	@Test
	public void mesmoMilissegundoEhDoMesmoDia() {
		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();
		
		Negociacao negociacao = new Negociacao(40.0, 100, agora);
		Assert.assertTrue(negociacao.isMesmoDia(mesmoMomento));
	}
	
	@Test
	public void comHorariosDiferentesEhNoMesmoDia() {
		// usando GregorianCalendar(ano, mes, dia, hora, minuto)
		Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2011, 10, 20, 15, 30);
		
		Negociacao negociacao = new Negociacao(40.0, 100, manha);
		Assert.assertTrue(negociacao.isMesmoDia(tarde));
	}
}
