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
public class Cartomancien extends Medium{

 protected Cartomancien() {
        super();
    }

    public Cartomancien(String nom, String presentation, Character genre) {
        super(nom, presentation, genre);
    }
    
}