package fr.insalyon.dasi.metier.modele;

import javax.persistence.Entity;

/**
 *
 * @author DASI Team
 */
@Entity
public class Client extends Utilisateur {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/
    private String dateDeNaissance;
    private String numeroDeTelephone;
    private String signeZodiaque;
    private String couleurBonheur;
    private String animalTotem;
    private String adressePostale;

    protected Client() {
    }

    public Client(String email, String nom, String prenom, String motDePasse, String date, String numeroDeTelephone, String adresse) {
        super(email, nom, prenom, motDePasse);
        this.dateDeNaissance = date;
        this.numeroDeTelephone = numeroDeTelephone;
        this.adressePostale = adresse;
    }

    public String getDate() {
        return dateDeNaissance;
    }

    public void setDate(String date) {
        this.dateDeNaissance = date;
    }

    public String getNumeroDeTelephone() {
        return numeroDeTelephone;
    }

    public void setNumeroDeTelephione(String numeroDeTelephone) {
        this.numeroDeTelephone = numeroDeTelephone;
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

    public String getAdressePostale() {
        return adressePostale;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public String toString() {
        return "id=" + getId() + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", motDePasse=" + motDePasse+", adresse="+adressePostale+", numTel="+numeroDeTelephone;
    }
    

}
