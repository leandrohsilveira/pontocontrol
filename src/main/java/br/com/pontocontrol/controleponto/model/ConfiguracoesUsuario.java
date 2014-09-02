/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pontocontrol.controleponto.model;

import br.com.pontocontrol.controleponto.ExtObject;
import br.com.pontocontrol.controleponto.util.TimeUtils;

/**
 *
 * @author silveira
 */
public class ConfiguracoesUsuario extends ExtObject {

    public ConfiguracoesUsuario(String login) {
        this.login = login;
    }
    
    //login e user path.
    private String login;
    
    //Expediente
    private boolean segunda = true;
    private boolean terca = true;
    private boolean quarta = true;
    private boolean quinta = true;
    private boolean sexta = true;
    private boolean sabado = false;
    private boolean domingo = false;
    
    private long offset = TimeUtils.OFFSET_8_HORAS;
    
    public String getPathUsuario() {
        return format("%s/%s", mainPath(), login);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isSegunda() {
        return segunda;
    }

    public void setSegunda(boolean segunda) {
        this.segunda = segunda;
    }

    public boolean isTerca() {
        return terca;
    }

    public void setTerca(boolean terca) {
        this.terca = terca;
    }

    public boolean isQuarta() {
        return quarta;
    }

    public void setQuarta(boolean quarta) {
        this.quarta = quarta;
    }

    public boolean isQuinta() {
        return quinta;
    }

    public void setQuinta(boolean quinta) {
        this.quinta = quinta;
    }

    public boolean isSexta() {
        return sexta;
    }

    public void setSexta(boolean sexta) {
        this.sexta = sexta;
    }

    public boolean isSabado() {
        return sabado;
    }

    public void setSabado(boolean sabado) {
        this.sabado = sabado;
    }

    public boolean isDomingo() {
        return domingo;
    }

    public void setDomingo(boolean domingo) {
        this.domingo = domingo;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }
    
    
    
}