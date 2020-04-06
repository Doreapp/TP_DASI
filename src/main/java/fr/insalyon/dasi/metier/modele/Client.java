package fr.insalyon.dasi.metier.modele;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Client extends Utilisateur {

    @Temporal(TemporalType.DATE)
    private Date dateDeNaissance;
    private String signeZodiaque;
    private String signeChinois;
    private String couleurBonheur;
    private String animalTotem;
    private String adressePostale;

    protected Client() {
    }

    public Client(String email, String nom, String prenom, String numeroDeTelephone, String motDePasse, Date date, String adresse) {
        super(email, nom, prenom, numeroDeTelephone, motDePasse);
        this.dateDeNaissance = date;
        this.adressePostale = adresse;
    }

    public Date getDate() {
        return dateDeNaissance;
    }

    public void setDate(Date date) {
        this.dateDeNaissance = date;
    }

    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    public String getCouleurBonheur() {
        return couleurBonheur;
    }

    public void setCouleurBonheur(String couleurBonheur) {
        this.couleurBonheur = couleurBonheur;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }

    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public String toString() {
        return "id=" + getId() + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", tel="+numeroDeTelephone +", motDePasse=" + motDePasse+", adresse="+adressePostale;
    }
    

}
