package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Utilisateur;
import fr.insalyon.dasi.metier.service.Service;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        JpaUtil.init();

        initialiserClients();           

        JpaUtil.destroy();
    }

    public static void afficherUtilisateur(Utilisateur U) {
        System.out.println("-> Utilisateur : " + U);
    }

    public static void initialiserClients() {
        
        System.out.println();
        System.out.println("**** initialiserClients() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();

        Utilisateur u3 = new Employe("mail","Nana","Tata","mdp",'F');
        
        try{
            em.getTransaction().begin();
            em.persist(u3);
            em.getTransaction().commit();
        }catch(Exception ex){
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
        
        Service service = new Service();
        
        //======================================
        // Tester l'inscription des Clients :
        
        System.out.println();
        System.out.println("**** testerInscriptionClient() ****");
        System.out.println();
        
        Client c = new Client("claude.chappe@insa-lyon.fr","Chappe", "Claude","mdp", new Date("12/12/2012"), "0123", "Rue du Lila");
        Long id1 = service.inscrireClient(c);
        if (id1 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherUtilisateur(c);
        
        Client c2 = new Client("email","Hugo","Victor","mdp",new Date("12/15/2020"),"00","adresse");
        Long id2 = service.inscrireClient(c2);
        if (id2 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherUtilisateur(c2);
        
        System.out.println();
        System.out.println("**** Fin testerInscriptionClient() ****");
        System.out.println();
        
        //===========================
        
        
        //======================================
        // Tester l'authentification des utilisateurs :
        
        System.out.println();
        System.out.println("**** testerAuthentificationUtilisateur() ****");
        System.out.println();
        
        Utilisateur user;
        String mail;
        String motDePasse;

        mail = "ada.lovelace@insa-lyon.fr";
        motDePasse = "Ada1012";
        user = service.authentifierUtilisateur(mail, motDePasse);
        if (user != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherUtilisateur(user);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
        
        mail = "claude.chappe@insa-lyon.fr";
        motDePasse = "mdp";
        user = service.authentifierUtilisateur(mail, motDePasse);
        if (user != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherUtilisateur(user);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
        
        mail = "claude.chappe@insa-lyon.fr";
        motDePasse = "mmm";
        user = service.authentifierUtilisateur(mail, motDePasse);
        if (user != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherUtilisateur(user);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
        
        mail = "mail";
        motDePasse = "mdp";
        user = service.authentifierUtilisateur(mail, motDePasse);
        if (user != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherUtilisateur(user);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
        
        System.out.println();
        System.out.println("**** Fin testerAuthentificationUtilisateur() ****");
        System.out.println();
        
        //======================================
    }
    
}
