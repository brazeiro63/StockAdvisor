package br.com.braza.sistema.stockadvisor.dominio;

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
