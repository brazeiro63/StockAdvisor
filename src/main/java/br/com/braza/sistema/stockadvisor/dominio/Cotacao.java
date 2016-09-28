package br.com.braza.sistema.stockadvisor.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_cotacao")
public class Cotacao implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codCotacao;
	private Date dataCotacao;
	private String moedaReferencia;
	private double precoAbertura;
	private double precoMaximo;
	private double precoMinimo;
	private double precoMedio;
	private double precoUltimo;
	private double precoOfertaVenda;
	private double precoOfertaCompra;
	private Integer quantidadeNegocios;
	private Long quantidadePapeisNegociados;
	private Double volumeNegocios;
	
	@ManyToOne
	private Papel papel;

	public Cotacao() {
	}

	public Cotacao(Integer codCotacao, Date dataCotacao, String moedaReferencia, double precoAbertura,
			double precoMaximo, double precoMinimo, double precoMedio, double precoUltimo, double precoOfertaVenda,
			double precoOfertaCompra, Integer quantidadeNegocios, Long quantidadePapeisNegociados,
			Double volumeNegocios, Papel papel) {
		super();
		this.codCotacao = codCotacao;
		this.dataCotacao = dataCotacao;
		this.moedaReferencia = moedaReferencia;
		this.precoAbertura = precoAbertura;
		this.precoMaximo = precoMaximo;
		this.precoMinimo = precoMinimo;
		this.precoMedio = precoMedio;
		this.precoUltimo = precoUltimo;
		this.precoOfertaVenda = precoOfertaVenda;
		this.precoOfertaCompra = precoOfertaCompra;
		this.quantidadeNegocios = quantidadeNegocios;
		this.quantidadePapeisNegociados = quantidadePapeisNegociados;
		this.volumeNegocios = volumeNegocios;
		this.papel = papel;
	}

	public Integer getCodCotacao() {
		return codCotacao;
	}

	public void setCodCotacao(Integer codCotacao) {
		this.codCotacao = codCotacao;
	}

	public Date getDataCotacao() {
		return dataCotacao;
	}

	public void setDataCotacao(Date dataCotacao) {
		this.dataCotacao = dataCotacao;
	}

	public String getMoedaReferencia() {
		return moedaReferencia;
	}

	public void setMoedaReferencia(String moedaReferencia) {
		this.moedaReferencia = moedaReferencia;
	}

	public double getPrecoAbertura() {
		return precoAbertura;
	}

	public void setPrecoAbertura(double precoAbertura) {
		this.precoAbertura = precoAbertura;
	}

	public double getPrecoMaximo() {
		return precoMaximo;
	}

	public void setPrecoMaximo(double precoMaximo) {
		this.precoMaximo = precoMaximo;
	}

	public double getPrecoMinimo() {
		return precoMinimo;
	}

	public void setPrecoMinimo(double precoMinimo) {
		this.precoMinimo = precoMinimo;
	}

	public double getPrecoMedio() {
		return precoMedio;
	}

	public void setPrecoMedio(double precoMedio) {
		this.precoMedio = precoMedio;
	}

	public double getPrecoUltimo() {
		return precoUltimo;
	}

	public void setPrecoUltimo(double precoUltimo) {
		this.precoUltimo = precoUltimo;
	}

	public double getPrecoOfertaVenda() {
		return precoOfertaVenda;
	}

	public void setPrecoOfertaVenda(double precoOfertaVenda) {
		this.precoOfertaVenda = precoOfertaVenda;
	}

	public double getPrecoOfertaCompra() {
		return precoOfertaCompra;
	}

	public void setPrecoOfertaCompra(double precoOfertaCompra) {
		this.precoOfertaCompra = precoOfertaCompra;
	}

	public Integer getQuantidadeNegocios() {
		return quantidadeNegocios;
	}

	public void setQuantidadeNegocios(Integer quantidadeNegocios) {
		this.quantidadeNegocios = quantidadeNegocios;
	}

	public Long getQuantidadePapeisNegociados() {
		return quantidadePapeisNegociados;
	}

	public void setQuantidadePapeisNegociados(Long quantidadePapeisNegociados) {
		this.quantidadePapeisNegociados = quantidadePapeisNegociados;
	}

	public Double getVolumeNegocios() {
		return volumeNegocios;
	}

	public void setVolumeNegocios(Double volumeNegocios) {
		this.volumeNegocios = volumeNegocios;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	@Override
	public String toString() {
		return "Cotacao [codCotacao=" + codCotacao + ", dataCotacao=" + dataCotacao + ", moedaReferencia="
				+ moedaReferencia + ", precoAbertura=" + precoAbertura + ", precoMaximo=" + precoMaximo
				+ ", precoMinimo=" + precoMinimo + ", precoMedio=" + precoMedio + ", precoUltimo=" + precoUltimo
				+ ", precoOfertaVenda=" + precoOfertaVenda + ", precoOfertaCompra=" + precoOfertaCompra
				+ ", quantidadeNegocios=" + quantidadeNegocios + ", quantidadePapeisNegociados="
				+ quantidadePapeisNegociados + ", volumeNegocios=" + volumeNegocios + ", papel=" + papel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codCotacao == null) ? 0 : codCotacao.hashCode());
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
		Cotacao other = (Cotacao) obj;
		if (codCotacao == null) {
			if (other.codCotacao != null)
				return false;
		} else if (!codCotacao.equals(other.codCotacao))
			return false;
		return true;
	}

}
