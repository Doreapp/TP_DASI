package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author DASI Team
 */
@Entity
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    protected String email;
    protected String nom;
    protected String prenom;
    protected String numeroDeTelephone;
    protected String motDePasse;

    protected Utilisateur() {
    }

    public Utilisateur(String email, String nom, String prenom, String tel, String motDePasse) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroDeTelephone = tel;
        this.motDePasse = motDePasse;
    }

    public String getNumeroDeTelephone() {
        return numeroDeTelephone;
    }

    public void setNumeroDeTelephone(String numeroDeTelephone) {
        this.numeroDeTelephone = numeroDeTelephone;
    }

    public Long getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String mail) {
        this.email = mail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return "id=" + id + ", email=" + email + ", nom=" + nom + ", prenom=" + prenom + ", motDePasse=" + motDePasse;
    }
    

}
