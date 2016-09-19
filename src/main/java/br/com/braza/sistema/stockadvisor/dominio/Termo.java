package br.com.braza.sistema.stockadvisor.dominio;

import java.util.Date;

public class Termo extends Papel {
	
	private static final long serialVersionUID = 1L;
	private Integer prazo;
	private Date dataVencimeto;
	private Double precoExercicioEmPontos;

	private IndiceCorrecao indiceCorrecao;

	public Termo() {
		super();
	}

	public Termo(Integer prazo, Date dataVencimeto, Double precoExercicioEmPontos, IndiceCorrecao indiceCorrecao) {
		super();
		this.prazo = prazo;
		this.dataVencimeto = dataVencimeto;
		this.precoExercicioEmPontos = precoExercicioEmPontos;
		this.indiceCorrecao = indiceCorrecao;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}

	public Date getDataVencimeto() {
		return dataVencimeto;
	}

	public void setDataVencimeto(Date dataVencimeto) {
		this.dataVencimeto = dataVencimeto;
	}

	public Double getPrecoExercicioEmPontos() {
		return precoExercicioEmPontos;
	}

	public void setPrecoExercicioEmPontos(Double precoExercicioEmPontos) {
		this.precoExercicioEmPontos = precoExercicioEmPontos;
	}

	public IndiceCorrecao getIndiceCorrecao() {
		return indiceCorrecao;
	}

	public void setIndiceCorrecao(IndiceCorrecao indiceCorrecao) {
		this.indiceCorrecao = indiceCorrecao;
	}

	@Override
	public String toString() {
		return "Termo [prazo=" + prazo + ", dataVencimeto=" + dataVencimeto + ", precoExercicioEmPontos="
				+ precoExercicioEmPontos + "]";
	}
	
}
