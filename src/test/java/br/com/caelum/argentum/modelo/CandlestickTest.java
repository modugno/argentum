package br.com.caelum.argentum.modelo;

import java.util.Calendar;

import org.junit.Test;

import junit.framework.Assert;

public class CandlestickTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo() {
		Candlestick candle = new Candlestick.CandleBuilder(100.0, 100.0)
				                                         .minimo(30.0)
				                                         .maximo(20.0)
				                                         .volume(100.0)
				                                         .data(Calendar.getInstance())
				                                         .build();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testaNegativosEdataNula() {
		Candlestick candle = new Candlestick.CandleBuilder(100, 1)
				                            .minimo(30)
				                            .maximo(-40)
				                            .volume(200)
				                            .data(Calendar.getInstance())
				                            .build();
	}
	// certifica que todos os metodos foram chamados para construção do Candle
	@Test(expected=IllegalStateException.class)
	public void geracaoCandleIncompleto() {
		Candlestick candle = new Candlestick.CandleBuilder(100, 1)
							                .minimo(30)
							                .volume(200)
							                .build();
	}
	
	// caso a abertura e fechamento forem iguais, retorna alta
	@Test
	public void quandoAberturaIgualFechamentoEhAlta() {
		Candlestick candle = new Candlestick.CandleBuilder(100, 100)
							                .minimo(30)
							                .maximo(40)
							                .volume(200)
							                .data(Calendar.getInstance())
							                .build();
		
		Assert.assertEquals(true, candle.isAlta());
	}

}
