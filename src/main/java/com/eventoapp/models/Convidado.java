package com.eventoapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("deprecation")
@Entity
public class Convidado {
	
	@Id
	@NotEmpty
	private String rg;
	@NotEmpty
	private String nome_convidado;
	
	@ManyToOne
	private Evento evento;
	
	
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getNome_convidado() {
		return nome_convidado;
	}
	public void setNome_convidado(String nome_convidado) {
		this.nome_convidado = nome_convidado;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
