package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author DASI Team
 */
@Entity
public class Client extends Utilisateur {

    protected Client() {
    }

    public Client(String nom, String prenom, String mail, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = mail;
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return "Client :" + super.toString();
    }
}
