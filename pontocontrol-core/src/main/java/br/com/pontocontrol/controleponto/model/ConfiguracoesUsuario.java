/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pontocontrol.controleponto.model;

import java.util.Calendar;

import br.com.pontocontrol.controleponto.PathsManager;
import br.com.pontocontrol.controleponto.util.TimeUtils;

/**
 *
 * @author silveira
 */
public class ConfiguracoesUsuario {

	public ConfiguracoesUsuario(final String login) {
		this.login = login;
	}

	// login e user path.
	private String login;

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	private String pathUsuario;

	// Expediente
	private boolean segunda = true;
	private boolean terca = true;
	private boolean quarta = true;
	private boolean quinta = true;
	private boolean sexta = true;
	private boolean sabado = false;
	private boolean domingo = false;

	private long offset = TimeUtils.SYSTEM_DEFAULT_OFFSET;
	private long almoco = TimeUtils.OFFSET_1_HORA;

	public boolean checarSeDiaExpediente() {
		return checarSeDiaExpediente(Calendar.getInstance());
	}

	public boolean checarSeDiaExpediente(final Calendar date) {
		final int dia = date.get(Calendar.DAY_OF_WEEK);
		switch (dia) {
		case Calendar.MONDAY:
			return segunda;
		case Calendar.TUESDAY:
			return terca;
		case Calendar.WEDNESDAY:
			return quarta;
		case Calendar.THURSDAY:
			return quinta;
		case Calendar.FRIDAY:
			return sexta;
		case Calendar.SATURDAY:
			return sabado;
		case Calendar.SUNDAY:
			return domingo;
		default:
			return false;
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public boolean isSegunda() {
		return segunda;
	}

	public void setSegunda(final boolean segunda) {
		this.segunda = segunda;
	}

	public boolean isTerca() {
		return terca;
	}

	public void setTerca(final boolean terca) {
		this.terca = terca;
	}

	public boolean isQuarta() {
		return quarta;
	}

	public void setQuarta(final boolean quarta) {
		this.quarta = quarta;
	}

	public boolean isQuinta() {
		return quinta;
	}

	public void setQuinta(final boolean quinta) {
		this.quinta = quinta;
	}

	public boolean isSexta() {
		return sexta;
	}

	public void setSexta(final boolean sexta) {
		this.sexta = sexta;
	}

	public boolean isSabado() {
		return sabado;
	}

	public void setSabado(final boolean sabado) {
		this.sabado = sabado;
	}

	public boolean isDomingo() {
		return domingo;
	}

	public void setDomingo(final boolean domingo) {
		this.domingo = domingo;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(final long offset) {
		this.offset = offset;
	}

	public long getAlmoco() {
		return almoco;
	}

	public void setAlmoco(final long almoco) {
		this.almoco = almoco;
	}

	public String getPathUsuario() {
		if (pathUsuario != null) {
			return pathUsuario;
		}
		return PathsManager.getInstance().getPathUsuario(getLogin());
	}

	public void setPathUsuario(final String pathUsuario) {
		this.pathUsuario = pathUsuario;
	}

}