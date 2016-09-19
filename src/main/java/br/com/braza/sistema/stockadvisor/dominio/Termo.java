package br.com.braza.sistema.stockadvisor.dominio;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue( value="TERMO")
public class Termo extends Papel {
	
	private static final long serialVersionUID = 1L;
	private String prazo;
	private Date dataVencimento;
	private Double precoExercicioEmPontos;

	private IndiceCorrecao indiceCorrecao;

	public Termo() {
		super();
	}

	public Termo(String prazo, Date dataVencimeto, Double precoExercicioEmPontos, IndiceCorrecao indiceCorrecao) {
		super();
		this.prazo = prazo;
		this.dataVencimento = dataVencimeto;
		this.precoExercicioEmPontos = precoExercicioEmPontos;
		this.indiceCorrecao = indiceCorrecao;
	}

	public String getPrazo() {
		return prazo;
	}

	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
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
		return "Termo [prazo=" + prazo + ", dataVencimeto=" + dataVencimento + ", precoExercicioEmPontos="
				+ precoExercicioEmPontos + "]";
	}
	
}
