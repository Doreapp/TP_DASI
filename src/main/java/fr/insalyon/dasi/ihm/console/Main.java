package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.metier.modele.Utilisateur;
import fr.insalyon.dasi.metier.service.Service;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        JpaUtil.init();

        initialiserEmployeMedium();  
        inscriptionClient(); 
        testerAuthentification();

        JpaUtil.destroy();
    }

    public static void afficherUtilisateur(Utilisateur U) {
        System.out.println("-> Utilisateur : " + U);
    }

    public static void initialiserEmployeMedium() {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();

        Utilisateur u1 = new Employe("camile.martin@gmail.com","Martin","Camille","0123","mdp",'F');
        Utilisateur u2 = new Employe("pierre.dupont@gmail.com","Dupont","Pierre","0000","mdp",'H');
        Utilisateur u3 = new Employe("sandra.chevalier@gmail.com","Chevalier","Sandra","2222","mdp",'F');
        Utilisateur u4 = new Employe("patrick.petoux@gmail.com","Petoux","Patrick","4562","mdp",'H');
        
        Spirite m1 = new Spirite("Boule de Cristal","Gwenaëlle","Spécialiste des grandes conversations au-delà de TOUTES les frontières.",'F');
        Spirite m2 = new Spirite("Marc de café, boule de cristal, oreilles de lapin","Professeur Tran","Votre avenir est devant vous : regardons-le ensemble !",'H');
        Cartomancien m3 = new Cartomancien("Mme Irma","Comprenez votre entourage grâce à mes cartes ! Résultats rapides.",'F');
        Cartomancien m4 = new Cartomancien("Endora","Mes cartes répondront à toutes vos questions personnelles.",'F');
        Astrologue m5 = new Astrologue("École Normale Supérieure d’Astrologie (ENS-Astro)","2006","Serena","Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.",'F');
        Astrologue m6 = new Astrologue(" Institut des Nouveaux Savoirs Astrologiques","2010","Mr M","Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter!",'H');
        
        
        try{
            em.getTransaction().begin();
            em.persist(u1);
            em.persist(u2);
            em.persist(u3);
            em.persist(u4);
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
            em.persist(m4);
            em.persist(m5);
            em.persist(m6);
            em.getTransaction().commit();
        }catch(Exception ex){
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
        
        
        
        
    }
    
    public static void inscriptionClient(){
        //======================================
        // Tester l'inscription des Clients :
        
        System.out.println();
        System.out.println("**** testerInscriptionClient() ****");
        System.out.println();
        
        Service service = new Service();
        
        Client c = new Client("claude.chappe@insa-lyon.fr","Chappe", "Claude", "0123", "mdp", new Date("23/12/2002"), "Rue du Lila");
        Long id1 = service.inscrireClient(c);
        if (id1 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherUtilisateur(c);
        
        Client c2 = new Client("email","Hugo","Victor","00","mdp",new Date("12/15/2020"),"adresse");
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
    }
    
    public static void testerAuthentification(){
        //======================================
        // Tester l'authentification des utilisateurs :
        
        System.out.println();
        System.out.println("**** testerAuthentificationUtilisateur() ****");
        System.out.println();
        
        Service service = new Service();
        
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
