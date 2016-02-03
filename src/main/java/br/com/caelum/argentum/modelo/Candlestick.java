package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public final class Candlestick {
	
	private final double abertura;
	private final double fechamento;
	private final double minimo;
	private final double maximo;
	private final double volume;
	private final Calendar data;
	
	public Candlestick(CandleBuilder builder) {
		this.abertura   = builder.abertura;
		this.fechamento = builder.fechamento;
		this.minimo     = builder.minimo;
		this.maximo     = builder.maximo;
		this.volume     = builder.volume;
		this.data       = builder.data;
	}

	// Cria nosso Builder
	public static class CandleBuilder {
		
		private double abertura;
		private double fechamento;
		private double minimo;
		private double maximo;
		private double volume;
		private Calendar data;
		
		/* Lista de boolean para saber se os metodos necess�rios para constru��o
		 * do builder foi chamado corretamente
		 */
		private ArrayList<Boolean> invocaMetodo; 
		
		// m�todo para avaliar n�meros negativos 
		public void verificaNegativos(double valor) {
			if (valor < 0) {
				throw new IllegalArgumentException("Valor n�o pode ser negativo.");
			}
		}
		
		public CandleBuilder(double abertura, double fechamento) {
			verificaNegativos(abertura);
			verificaNegativos(fechamento);
			
			this.abertura   = abertura;
			this.fechamento = fechamento;
			invocaMetodo = new ArrayList<>();
		}
		
		// cria nossas Fluent Interface
		public CandleBuilder minimo(double minimo) {
			verificaNegativos(minimo);
			
			this.minimo = minimo;
			this.invocaMetodo.add(true);
			return this;
		}
		
		public CandleBuilder maximo(double maximo) {
			verificaNegativos(maximo);
			
			if (maximo < minimo) {
				throw new IllegalArgumentException("O valor m�ximo n�o pode ser maior do que o m�nimo.");
			}
			this.maximo = maximo;
			this.invocaMetodo.add(true);
			return this;
		}
		
		public CandleBuilder volume(double volume) {
			verificaNegativos(volume);
			this.volume = volume;
			this.invocaMetodo.add(true);
			return this;
		}
		
		public CandleBuilder data(Calendar data) {
			if (data == null) {
				throw new IllegalArgumentException("A data n�o pode ser nula.");
			}
			this.data = data;
			this.invocaMetodo.add(true);
			return this;
		}
		
		public Candlestick build() {
			if (this.invocaMetodo.size() != 4) {
				throw new IllegalStateException("N�o foram chamado todos os m�todos necess�rios para constru��o do Candle.");
			}
			return new Candlestick(this);
		}
	}
	
	// nossos getters
	public double getAbertura() {
		return abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public double getMinimo() {
		return minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public double getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}
	
	public boolean isAlta() {
		if (this.abertura == this.fechamento) {
			return true;
		}
		return this.abertura < this.fechamento;
	}
	
	public boolean isBaixa() {
		return this.abertura > this.fechamento;
	}
	
	@Override
	public String toString() {
		return "[Abertura " + getAbertura() + ", Fechamento " + getFechamento() + ", M�nima " + getMinimo() + ", M�xima " + getMaximo() + ", Volume " + getVolume() + "]";
	}
}
