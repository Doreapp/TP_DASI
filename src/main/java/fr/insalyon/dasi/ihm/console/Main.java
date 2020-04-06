package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.metier.modele.Utilisateur;
import fr.insalyon.dasi.metier.modele.Conversation;
import fr.insalyon.dasi.metier.service.Service;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        JpaUtil.init();

        //initialiserEmployeMedium();  
        //inscriptionClient(); 
        //testerAuthentification();
        //testerGetMediums();
        //testerCreerConversation();
        //testerHistoriqueClient();


        JpaUtil.destroy();
    }

    public static void afficherUtilisateur(Utilisateur U) {
        System.out.println("-> Utilisateur : " + U);
    }
    
    public static void afficherMedium(Medium M) {
        System.out.println("-> Medium : " + M);
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
        
        Service service = new Service();
        
        Client c = new Client("claude.chappe@insa-lyon.fr","Chappe", "Claude", "0123", "mdp", new Date("23/12/2002"), "Rue du Lila");
        Long id1 = service.inscrireClient(c);
        if (id1 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        //afficherUtilisateur(c);
        
        Client c2 = new Client("email","Hugo","Victor","00","mdp",new Date("12/15/2020"),"adresse");
        Long id2 = service.inscrireClient(c2);
        if (id2 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        //afficherUtilisateur(c2);
        
        Client c3 = new Client("rborrotimatiasdantas4171@free.fr","BORROTI MATIAS DANTAS","Raphaël","0328178508","mdp",new Date("10/07/1976"),"8 Rue Arago, Villeurbanne");
        Long id3 = service.inscrireClient(c3);
        if (id3 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        
        Client c4 = new Client("nolmeadamarais1551@free.fr","OLMEADA ","Nor","0418932546","mdp",new Date("09/12/1983"),"5 Rue Léon Fabre, Villeurbanne");
        Long id4 = service.inscrireClient(c4);
        if (id4 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        
        Client c5 = new Client("orayesgemez5313@free.fr","RAYES ","Olena","0532731620","mdp",new Date("09/12/1983"),"12 Rue de la Prevoyance, Villeurbanne");
        Long id5 = service.inscrireClient(c5);
        if (id5 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        
        Client c6 = new Client("asing8183@free.fr","SING","Ainhoa","0705224200","mdp",new Date("09/12/1983"),"4 Rue Phelypeaux, Villeurbanne");
        Long id6 = service.inscrireClient(c6);
        if (id6 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
 
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
    
    public static void testerGetMediums(){
        System.out.println();
        System.out.println("**** testerListeMediums() ****");
        System.out.println();
        
        Service service = new Service();
        List<Medium> liste = service.getMediums();
        System.out.println("*** Liste des Mediums");
        for (Medium m : liste) {
            afficherMedium(m);
        }
        
        System.out.println();
        System.out.println("**** Fin testerListeMediums() ****");
        System.out.println();
    }
    
    public static void testerCreerConversation(){
        System.out.println("************************************");
        System.out.println("****   Test creer conversation  ****");
        System.out.println("************************************");
        
        Service service = new Service();
        
        
        System.out.println("****   Ajout des clients ");
        
        // 1) add client 
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
        
        
        System.out.println("****   Ajouts des médiums et employés");
        
        // 2) Add employés et médiums
        Utilisateur u1 = new Employe("camile.martin@gmail.com","Martin","Camille","0123","mdp",'F');
        Utilisateur u2 = new Employe("pierre.dupont@gmail.com","Dupont","Pierre","0000","mdp",'H');
        
        // médiums :
        Spirite m2 = new Spirite("Marc de café, boule de cristal, oreilles de lapin","Professeur Tran","Votre avenir est devant vous : regardons-le ensemble !",'H');
        Cartomancien m3 = new Cartomancien("Mme Irma","Comprenez votre entourage grâce à mes cartes ! Résultats rapides.",'F');
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(u1);
            em.persist(u2);
            em.persist(m2);
            em.persist(m3);
            em.getTransaction().commit();
        }catch(Exception ex){
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        
        System.out.println("****   Test des conversation ");
        
        Conversation conv1 = service.creerConversation(c, m2);
        Conversation conv2 = service.creerConversation(c2, m2);
        Conversation conv3 = service.creerConversation(c2, m3);
        
        System.out.println("Conversations ajoutées :");
        System.out.println("> "+conv1);
        System.out.println("> "+conv2);
        System.out.println("> "+conv3);
    }

    public static void testerHistoriqueClient(){
        System.out.println("************************************");
        System.out.println("****   Test historique Client  ****");
        System.out.println("************************************");

        Service service = new Service();

        Client c = new Client("claude.chappe@insa-lyon.fr","Chappe", "Claude", "0123", "mdp", new Date("23/12/2002"), "Rue du Lila");
        Long id1 = service.inscrireClient(c);
        
        Client c2 = new Client("email","Hugo","Victor","00","mdp",new Date("12/15/2020"),"adresse");
        Long id2 = service.inscrireClient(c2);
        
        // 2) Add employés et médiums
        Utilisateur u1 = new Employe("camile.martin@gmail.com","Martin","Camille","0123","mdp",'F');
        Utilisateur u2 = new Employe("pierre.dupont@gmail.com","Dupont","Pierre","0000","mdp",'H');
        
        // médiums :
        Spirite m2 = new Spirite("Marc de café, boule de cristal, oreilles de lapin","Professeur Tran","Votre avenir est devant vous : regardons-le ensemble !",'H');
        Cartomancien m3 = new Cartomancien("Mme Irma","Comprenez votre entourage grâce à mes cartes ! Résultats rapides.",'F');
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DASI-PU");
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(u1);
            em.persist(u2);
            em.persist(m2);
            em.persist(m3);
            em.getTransaction().commit();
        }catch(Exception ex){
            em.getTransaction().rollback();
        }finally{
            em.close();
        }

        Conversation conv1 = service.creerConversation(c, m2);
        Conversation conv2 = service.creerConversation(c2, m2);
        Conversation conv3 = service.creerConversation(c2, m3);
        
        List<Conversation> liste_c = service.historiqueClient(c);
        System.out.println("Historique Claude : ");
        for(Conversation conv : liste_c){
            System.out.println(conv.toString());
        }
        System.out.println();
        List<Conversation> liste_c2 = service.historiqueClient(c2);
        System.out.println("Historique Hugo : ");
        for(Conversation conv : liste_c2){
            System.out.println(conv.toString());
        }
    }
}
