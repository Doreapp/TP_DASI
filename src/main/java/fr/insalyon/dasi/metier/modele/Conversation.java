package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conversation implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dateConsultation;
    private String etat;
    private String commentaire;

    public Conversation() {
    }

    public Conversation(String dateConsultation, String etat, String commentaire) {
        this.dateConsultation = dateConsultation;
        this.etat = etat;
        this.commentaire = commentaire;
    }

    public Long getId() {
        return id;
    }

    public String getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(String dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
}
