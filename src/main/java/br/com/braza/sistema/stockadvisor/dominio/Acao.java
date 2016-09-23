package br.com.braza.sistema.stockadvisor.dominio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue( value="ACAO")
public class Acao extends Papel {
	private static final long serialVersionUID = 1L;


	public Acao() {
		super();
	}


	@Override
	public String toString() {
		return "Acao []";
	}

}
