package br.com.braza.sistema.stockadvisor.dominio;

import java.io.Serializable;

public class IndiceCorrecao implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer codigo;
	private String indice;
	private String descricao;

	public IndiceCorrecao() {
		super();
	}

	public IndiceCorrecao(Integer codigo, String indice, String descricao) {
		super();
		this.codigo = codigo;
		this.indice = indice;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getIndice() {
		return indice;
	}

	public void setIndice(String indice) {
		this.indice = indice;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "IndiceCorrecao [codigo=" + codigo + ", indice=" + indice + ", descricao=" + descricao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IndiceCorrecao other = (IndiceCorrecao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
