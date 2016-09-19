package br.com.braza.sistema.stockadvisor.dominio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue( value="ACAO")
public class Acao extends Papel {
	private static final long serialVersionUID = 1L;

	private Integer fatorCotacao;

	public Acao() {
		super();
	}

	public Acao(Integer fatorCotacao) {
		super();
		this.fatorCotacao = fatorCotacao;
	}

	public Integer getFatorCotacao() {
		return fatorCotacao;
	}

	public void setFatorCotacao(Integer fatorCotacao) {
		this.fatorCotacao = fatorCotacao;
	}

	@Override
	public String toString() {
		return "Acao [fatorCotacao=" + fatorCotacao + "]";
	}
	
	
	
}
