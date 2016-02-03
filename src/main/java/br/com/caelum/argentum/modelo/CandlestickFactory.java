package br.com.caelum.argentum.modelo;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.modelo.Candlestick.CandleBuilder;

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
}
