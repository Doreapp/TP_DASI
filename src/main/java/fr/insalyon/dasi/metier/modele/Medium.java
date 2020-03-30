/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author antoi
 */
@Entity
public abstract class Medium implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected String nom;
    protected String presentation;
    protected Character genre;

    protected Medium() {
    }

    protected Medium(String nom, String presentation, Character genre) {
        this.nom = nom;
        this.presentation = presentation;
        this.genre = genre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public Character getGenre() {
        return genre;
    }

    public void setGenre(Character genre) {
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public String toString() {
        return "Medium[id: "+id+"nom: "+nom+", pres:"+presentation+", genre:"+genre+"]";
    }
}
