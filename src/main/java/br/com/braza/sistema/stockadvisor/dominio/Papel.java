package br.com.braza.sistema.stockadvisor.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_papel")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoPapel")
public class Papel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codPapel;
	private String codigoNegociacao;
	private String empresa;
	private String codigoIsin;
	private String especificacao;
	private Integer fatorCotacao;

	private Mercado mercado;
	private Bdi bdi;

	@OneToMany(mappedBy = "papel")
	private List<Cotacao> cotacoes;

	public Papel() {
		this.cotacoes = new ArrayList<Cotacao>();
	}

	public Papel(Integer codPapel, String codigoNegociacao, String empresa, Integer fatorCotacao,String codigoIsin, Mercado mercado, Bdi bdi,
			String especificacao) {
		super();
		this.codPapel = codPapel;
		this.codigoNegociacao = codigoNegociacao;
		this.empresa = empresa;
		this.fatorCotacao = fatorCotacao;
		this.codigoIsin = codigoIsin;
		this.mercado = mercado;
		this.bdi = bdi;
		this.especificacao = especificacao;
		this.cotacoes = new ArrayList<Cotacao>();
	}

	public Integer getCodPapel() {
		return codPapel;
	}

	public void setCodPapel(Integer codPapel) {
		this.codPapel = codPapel;
	}

	public String getCodigoNegociacao() {
		return codigoNegociacao;
	}

	public void setCodigoNegociacao(String codigoNegociacao) {
		this.codigoNegociacao = codigoNegociacao;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getCodigoIsin() {
		return codigoIsin;
	}

	public void setCodigoIsin(String codigoIsin) {
		this.codigoIsin = codigoIsin;
	}

	public Mercado getMercado() {
		return mercado;
	}

	public void setMercado(Mercado mercado) {
		this.mercado = mercado;
	}

	public Bdi getBdi() {
		return bdi;
	}

	public void setBdi(Bdi bdi) {
		this.bdi = bdi;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public Integer getFatorCotacao() {
		return fatorCotacao;
	}

	public void setFatorCotacao(Integer fatorCotacao) {
		this.fatorCotacao = fatorCotacao;
	}

	public List<Cotacao> getCotacoes() {
		return cotacoes;
	}

	public void setCotacoes(List<Cotacao> cotacoes) {
		this.cotacoes = cotacoes;
	}

	public void addCotacao(Cotacao x) {
		this.cotacoes.add(x);
	}

	public boolean removeCotacao(Cotacao x) {
		return this.cotacoes.remove(x);
	}

	@Override
	public String toString() {
		return "Papel [codPapel=" + codPapel + ", codigoNegociacao=" + codigoNegociacao + ", empresa=" + empresa
				+ ", codigoIsin=" + codigoIsin + ", fatorCotacao=" + fatorCotacao + ", mercado="
				+ (mercado == null ? "Vazio" : mercado.toString()) + ", bdi=" + (bdi == null ? "Vazio" : bdi.toString())
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codPapel == null) ? 0 : codPapel.hashCode());
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
		Papel other = (Papel) obj;
		if (codPapel == null) {
			if (other.codPapel != null)
				return false;
		} else if (!codPapel.equals(other.codPapel))
			return false;
		return true;
	}

}
