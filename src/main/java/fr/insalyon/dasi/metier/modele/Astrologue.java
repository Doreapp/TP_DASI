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
public class Astrologue extends Medium {
    private String formation;
    private String promotion;

    public Astrologue(String formation, String promotion, String nom, String presentation, Character genre) {
        super(nom, presentation, genre);
        this.formation = formation;
        this.promotion = promotion;
    }

    protected Astrologue() {
        super();
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
    
    
}