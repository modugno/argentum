package br.com.caelum.argentum.teste;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.modelo.Candlestick;
import br.com.caelum.argentum.modelo.Candlestick.CandleBuilder;
import br.com.caelum.argentum.modelo.CandlestickFactory;
import br.com.caelum.argentum.modelo.Negociacao;

public class Testes {
	
	public static void main(String[] args) {
		
		Calendar hoje = Calendar.getInstance();
		Negociacao petrobras = new Negociacao(30, 0, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(petrobras);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		
		Candlestick c = new Candlestick.CandleBuilder(10.0, 10.0)
				                                     .data(hoje)
				                                     .build();
		
		boolean alta = c.isAlta();
		boolean baixa = c.isBaixa();
		System.out.println(baixa);
	}
}
