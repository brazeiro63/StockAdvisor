package br.com.braza.sistema.stockadvisor.dominio;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroCotacao implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Long tipreg;
	private Date datpre;
	private String codbdi;
	private String codneg;
	private Long tpmerc;
	private String nomres;
	private String especi;
	private String prazot;
	private String modref;
	private Double preabe;
	private Double premax;
	private Double premin;
	private Double premed;
	private Double preult;
	private Double preofc;
	private Double preofv;
	private Long totneg;
	private Long quatot;
	private Double voltot;
	private Double preexe;
	private Long indopc;
	private Date datven;
	private Long fatcot;
	private Double ptoexe;
	private String codisi;
	private Long dismes;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public RegistroCotacao() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RegistroCotacao(Integer id, Long tipreg, Date datpre, String codbdi, String codneg, Long tpmerc,
			String nomres, String especi, String prazot, String modref, Double preabe, Double premax, Double premin,
			Double premed, Double preult, Double preofc, Double preofv, Long totneg, Long quatot, Double voltot,
			Double preexe, Long indopc, Date datven, Long fatcot, Double ptoexe, String codisi, Long dismes) {
		super();
		this.id = id;
		this.tipreg = tipreg;
		this.datpre = datpre;
		this.codbdi = codbdi;
		this.codneg = codneg;
		this.tpmerc = tpmerc;
		this.nomres = nomres;
		this.especi = especi;
		this.prazot = prazot;
		this.modref = modref;
		this.preabe = preabe;
		this.premax = premax;
		this.premin = premin;
		this.premed = premed;
		this.preult = preult;
		this.preofc = preofc;
		this.preofv = preofv;
		this.totneg = totneg;
		this.quatot = quatot;
		this.voltot = voltot;
		this.preexe = preexe;
		this.indopc = indopc;
		this.datven = datven;
		this.fatcot = fatcot;
		this.ptoexe = ptoexe;
		this.codisi = codisi;
		this.dismes = dismes;
	}

	public Long getTipreg() {
		return tipreg;
	}

	public void setTipreg(Long tipreg) {
		this.tipreg = tipreg;
	}

	public void setDatpre(Long datpre) {

		Integer year = (int) (datpre / 10000);
		Integer month = (int) (datpre - year * 10000) / 100;
		Integer day = (int) (datpre - (year * 10000 + month * 100));

		try {
		this.datpre = sdf.parse(day.toString() + "/" + month.toString() + "/" + year.toString());
	} catch (ParseException e) {
		e.printStackTrace();
	}

	}

	public void setDatven(Long datven) {
		Integer year = (int) (datven / 10000);
		Integer month = (int) (datven - year * 10000) / 100;
		Integer day = (int) (datven - (year * 10000 + month * 100));

		try {
			this.datven = sdf.parse(day.toString() + "/" + month.toString() + "/" + year.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getDatpre() {
		return datpre;
	}

	public void setDatpre(Date datpre) {
		this.datpre = datpre;
	}

	public String getCodbdi() {
		return codbdi;
	}

	public void setCodbdi(String codbdi) {
		this.codbdi = codbdi;
	}

	public String getCodneg() {
		return codneg;
	}

	public void setCodneg(String codneg) {
		this.codneg = codneg;
	}

	public Long getTpmerc() {
		return tpmerc;
	}

	public void setTpmerc(Long tpmerc) {
		this.tpmerc = tpmerc;
	}

	public String getNomres() {
		return nomres;
	}

	public void setNomres(String nomres) {
		this.nomres = nomres;
	}

	public String getEspeci() {
		return especi;
	}

	public void setEspeci(String especi) {
		this.especi = especi;
	}

	public String getPrazot() {
		return prazot;
	}

	public void setPrazot(String prazot) {
		this.prazot = prazot;
	}

	public String getModref() {
		return modref;
	}

	public void setModref(String modref) {
		this.modref = modref;
	}

	public Double getPreabe() {
		return preabe;
	}

	public void setPreabe(Double preabe) {
		this.preabe = preabe;
	}

	public Double getPremax() {
		return premax;
	}

	public void setPremax(Double premax) {
		this.premax = premax;
	}

	public Double getPremin() {
		return premin;
	}

	public void setPremin(Double premin) {
		this.premin = premin;
	}

	public Double getPremed() {
		return premed;
	}

	public void setPremed(Double premed) {
		this.premed = premed;
	}

	public Double getPreult() {
		return preult;
	}

	public void setPreult(Double preult) {
		this.preult = preult;
	}

	public Double getPreofc() {
		return preofc;
	}

	public void setPreofc(Double preofc) {
		this.preofc = preofc;
	}

	public Double getPreofv() {
		return preofv;
	}

	public void setPreofv(Double preofv) {
		this.preofv = preofv;
	}

	public Long getTotneg() {
		return totneg;
	}

	public void setTotneg(Long totneg) {
		this.totneg = totneg;
	}

	public Long getQuatot() {
		return quatot;
	}

	public void setQuatot(Long quatot) {
		this.quatot = quatot;
	}

	public Double getVoltot() {
		return voltot;
	}

	public void setVoltot(Double voltot) {
		this.voltot = voltot;
	}

	public Double getPreexe() {
		return preexe;
	}

	public void setPreexe(Double preexe) {
		this.preexe = preexe;
	}

	public Long getIndopc() {
		return indopc;
	}

	public void setIndopc(Long indopc) {
		this.indopc = indopc;
	}

	public Date getDatven() {
		return datven;
	}

	public void setDatven(Date datven) {
		this.datven = datven;
	}

	public Long getFatcot() {
		return fatcot;
	}

	public void setFatcot(Long fatcot) {
		this.fatcot = fatcot;
	}

	public Double getPtoexe() {
		return ptoexe;
	}

	public void setPtoexe(Double ptoexe) {
		this.ptoexe = ptoexe;
	}

	public String getCodisi() {
		return codisi;
	}

	public void setCodisi(String codisi) {
		this.codisi = codisi;
	}

	public Long getDismes() {
		return dismes;
	}

	public void setDismes(Long dismes) {
		this.dismes = dismes;
	}

	@Override
	public String toString() {
		return "RegistroCotacao [id=" + id + ", tipreg=" + tipreg + ", datpre=" + datpre + ", codbdi=" + codbdi
				+ ", codneg=" + codneg + ", tpmerc=" + tpmerc + ", nomres=" + nomres + ", especi=" + especi
				+ ", prazot=" + prazot + ", modref=" + modref + ", preabe=" + preabe + ", premax=" + premax
				+ ", premin=" + premin + ", premed=" + premed + ", preult=" + preult + ", preofc=" + preofc
				+ ", preofv=" + preofv + ", totneg=" + totneg + ", quatot=" + quatot + ", voltot=" + voltot
				+ ", preexe=" + preexe + ", indopc=" + indopc + ", datven=" + datven + ", fatcot=" + fatcot
				+ ", ptoexe=" + ptoexe + ", codisi=" + codisi + ", dismes=" + dismes + "]";
	}

}
