package br.com.braza.sistema.stockadvisor.dominio;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue( value="OPCAO")
public class Opcao extends Papel {
	private static final long serialVersionUID = 1L;

	private Double precoExercicio;
	private Double precoExercicioEmPontos;
	private Date dataVencimento;

	private IndiceCorrecao indiceCorrecao;

	public Opcao() {
		super();
	}

	public Opcao(Double precoExercicio, Double precoExercicioEmPontos, Date dataVencimento,
			IndiceCorrecao indiceCorrecao) {
		super();
		this.precoExercicio = precoExercicio;
		this.precoExercicioEmPontos = precoExercicioEmPontos;
		this.dataVencimento = dataVencimento;
		this.indiceCorrecao = indiceCorrecao;
	}

	public Double getPrecoExercicio() {
		return precoExercicio;
	}

	public void setPrecoExercicio(Double precoExercicio) {
		this.precoExercicio = precoExercicio;
	}

	public Double getPrecoExercicioEmPontos() {
		return precoExercicioEmPontos;
	}

	public void setPrecoExercicioEmPontos(Double precoExercicioEmPontos) {
		this.precoExercicioEmPontos = precoExercicioEmPontos;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public IndiceCorrecao getIndiceCorrecao() {
		return indiceCorrecao;
	}

	public void setIndiceCorrecao(IndiceCorrecao indiceCorrecao) {
		this.indiceCorrecao = indiceCorrecao;
	}

	@Override
	public String toString() {
		return "Super() : " + super.toString() + "\n" +  "Opcao [precoExercicio=" + precoExercicio + ", precoExercicioEmPontos=" + precoExercicioEmPontos
				+ ", dataVencimento=" + dataVencimento + ", indiceCorrecao=" + indiceCorrecao.toString() +"]";
	}

}
