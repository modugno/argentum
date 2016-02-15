package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CandlestickFactory {
	
	public Candlestick constroiCandleParaData(Calendar data, List<Negociacao> negociacoes) {
		
		double maximo = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double minimo = negociacoes.isEmpty() ? 0 : Double.MAX_VALUE;
		double volume = 0;
		
		for (Negociacao negociacao : negociacoes) {
			// somatoria do volume das transa��es
			volume += negociacao.getVolume();
			
			// verifica o valor m�ximo e m�nimo do dia
			if(negociacao.getPreco() > maximo) {
				maximo = negociacao.getPreco();
			} else if(negociacao.getPreco() < minimo) {
				minimo = negociacao.getPreco();
			}
		}
		
		// verifica se o valor m�ximo � menor que o m�nimo 
		if (maximo < minimo) {
			throw new IllegalArgumentException("O valor m�ximo n�o pode ser menor que o m�nimo");
		}
		
		// pega valores de abertura e fechamento de caixa
		double abertura   = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 : negociacoes.get( negociacoes.size() - 1 ).getPreco();
		
		return new Candlestick.CandleBuilder(abertura, fechamento)
							               	.minimo(minimo)
							               	.maximo(maximo)
							               	.volume(volume)
							               	.data(data)
							               	.build();
				
	}

	public List<Candlestick> constroiCandles(List<Negociacao> todasNegociacoes) {
		List<Candlestick> candles = new ArrayList<Candlestick>();
		
		List<Negociacao> negociacoesDoDia = new ArrayList<Negociacao>();
		Calendar dataAtual = todasNegociacoes.get(0).getData();
		
		for (Negociacao negociacao : todasNegociacoes) {
			// verifica se esta fora de ordem a data
			if (negociacao.getData().before(dataAtual)) {
				throw new IllegalArgumentException("Negocia��es em ordem errada.");
			}
			// se n�o for mesmo dia, fecha candle e reinicia vari�veis
			if (!negociacao.isMesmoDia(dataAtual)) {
				Candlestick candleDoDia = constroiCandleParaData(dataAtual, negociacoesDoDia);
				candles.add(candleDoDia);
				negociacoesDoDia = new ArrayList<Negociacao>();
				dataAtual = negociacao.getData();
			}
			negociacoesDoDia.add(negociacao);
		}
		// adiciona �ltimo candle
		Candlestick candleDoDia = constroiCandleParaData(dataAtual, negociacoesDoDia);
		candles.add(candleDoDia);
		return candles;
	}
}
