/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import javax.persistence.Entity;

/**
 *
 * @author antoi
 */
@Entity
public class Spirite extends Medium {

    private String support;

    public Spirite(String support, String nom, String presentation, Character genre) {
        super(nom, presentation, genre);
        this.support = support;
    }

    protected Spirite() {
        super();
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

}