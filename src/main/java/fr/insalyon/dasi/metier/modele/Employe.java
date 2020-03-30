/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import javax.persistence.Entity;


@Entity
public class Employe extends Utilisateur {
    
    private int nbConsultation;
    private char genre;
    private boolean disponible;

    public Employe(String email, String nom, String prenom, String numeroDeTelephone, String motDePasse, char genre) {
        super(email, nom, prenom, numeroDeTelephone, motDePasse);
        this.genre = genre;
    }
    
    public Employe(){
    }

    public int getNbConsultation() {
        return nbConsultation;
    }

    public void setNbConsultation(int nbConsultation) {
        this.nbConsultation = nbConsultation;
    }

    public char getGenre() {
        return genre;
    }

    public void setGenre(char genre) {
        this.genre = genre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    @Override
    public String toString() {
        return "id=" + getId() + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email +", tel="+ numeroDeTelephone+ ", motDePasse=" + motDePasse+", genre="+genre;
    }
 
}
