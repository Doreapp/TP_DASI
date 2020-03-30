package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Conversation implements Serializable {
    public enum Etat {
        EN_ATTENTE, REFUSEE, EN_COURS, TERMINEE
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Etat etat;
    
    @Temporal(TemporalType.DATE)
    private Date dateConsultation;

    private String commentaire;
    
    @ManyToOne
    private Medium medium;
    
    @ManyToOne
    private Employe employe;
    
    @ManyToOne
    private Client client;

    protected Conversation() {
    }

    public Conversation(Etat etat, Date dateConsultation, String commentaire, Medium medium, Employe employe, Client client) {
        this.etat = etat;
        this.dateConsultation = dateConsultation;
        this.commentaire = commentaire;
        this.medium = medium;
        this.employe = employe;
        this.client = client;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    
    
    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Client getClinet() {
        return client;
    }

    public void setClinet(Client clinet) {
        this.client = clinet;
    }

    

    public Long getId() {
        return id;
    }

    public Date getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    

    public Etat getEtat() {
        return etat;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

}