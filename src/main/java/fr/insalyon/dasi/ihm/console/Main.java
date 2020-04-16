package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Conversation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.metier.modele.Utilisateur;
import fr.insalyon.dasi.metier.service.Service;
import java.util.Date;
import java.util.List;
import javafx.util.Pair;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    private static Employe[] employes;
    private static Medium[] mediums;
    private static Client[] clients;
    private static Conversation[] conversations;

    public static void main(String[] args) {
        System.out.println("..Main..");

        JpaUtil.init();

        //INIT :
        initialiserEmployeMedium();  
        inscriptionClient(); 

        //TESTS :
        testerAuthentification();
        testerGetMediums();
        testerCreerConversation();          //initialise Conversation[] {conversations}
        testerRechercherConversation();
        testerCommencerFinirConversation(); //testerCreerConversation() nécessaire
        testerRechercherHistoriqueClient();
        testerNbConsultationMedium();
        testerNbConsultationEmploye();
        testGenererPredictions();

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

        employes = new Employe[4];
        employes[0] = new Employe("camile.martin@gmail.com", "Martin", "Camille", "0123", "mdp", 'F');
        employes[1] = new Employe("pierre.dupont@gmail.com", "Dupont", "Pierre", "0000", "mdp", 'H');
        employes[2] = new Employe("sandra.chevalier@gmail.com", "Chevalier", "Sandra", "2222", "mdp", 'F');
        employes[3] = new Employe("patrick.petoux@gmail.com", "Petoux", "Patrick", "4562", "mdp", 'H');

        mediums = new Medium[6];
        mediums[0] = new Spirite("Boule de Cristal", "Gwenaëlle", "Spécialiste des grandes conversations au-delà de TOUTES les frontières.", 'F');
        mediums[1] = new Spirite("Marc de café, boule de cristal, oreilles de lapin", "Professeur Tran", "Votre avenir est devant vous : regardons-le ensemble !", 'H');
        mediums[2] = new Cartomancien("Mme Irma", "Comprenez votre entourage grâce à mes cartes ! Résultats rapides.", 'F');
        mediums[3] = new Cartomancien("Endora", "Mes cartes répondront à toutes vos questions personnelles.", 'F');
        mediums[4] = new Astrologue("École Normale Supérieure d’Astrologie (ENS-Astro)", "2006", "Serena", "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.", 'F');
        mediums[5] = new Astrologue(" Institut des Nouveaux Savoirs Astrologiques", "2010", "Mr M", "Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter!", 'H');

        try {
            em.getTransaction().begin();
            for(Employe e : employes)
                em.persist(e);
            for(Medium m : mediums)
                em.persist(m);
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static void inscriptionClient() {

        Service service = new Service();

        clients = new Client[7];
        
        clients[0] = new Client("claude.chappe@insa-lyon.fr", "Chappe", "Claude", "0123", "mdp", new Date("23/12/2002"), "Rue du Lila");
        System.out.println(">on inscrit un client...");
        Long id1 = service.inscrireClient(clients[0]);
        if (id1 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        //afficherUtilisateur(c);

        clients[1] = new Client("email", "Hugo", "Victor", "00", "mdp", new Date("12/15/2020"), "adresse");
        System.out.println(">on inscrit un client...");
        Long id2 = service.inscrireClient(clients[1]);
        if (id2 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        //afficherUtilisateur(c2);

        clients[2] = new Client("rborrotimatiasdantas4171@free.fr", "BORROTI MATIAS DANTAS", "Raphaël", "0328178508", "mdp", new Date("10/07/1976"), "8 Rue Arago, Villeurbanne");
        System.out.println(">on inscrit un client...");
        Long id3 = service.inscrireClient(clients[2]);
        if (id3 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }

        clients[3] = new Client("nolmeadamarais1551@free.fr", "OLMEADA ", "Nor", "0418932546", "mdp", new Date("09/12/1983"), "5 Rue Léon Fabre, Villeurbanne");
        System.out.println(">on inscrit un client...");
        Long id4 = service.inscrireClient(clients[3]);
        if (id4 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }

        clients[4] = new Client("orayesgemez5313@free.fr", "RAYES ", "Olena", "0532731620", "mdp", new Date("09/12/1983"), "12 Rue de la Prevoyance, Villeurbanne");
        System.out.println(">on inscrit un client...");
        Long id5 = service.inscrireClient(clients[4]);
        if (id5 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }

        clients[5] = new Client("orayesgemez5313@free.fr", "SING", "Ainhoa", "0705224200", "mdp", new Date("09/12/1983"), "4 Rue Phelypeaux, Villeurbanne");
        System.out.println(">on inscrit un client...");
        Long id6 = service.inscrireClient(clients[5]);
        if (id6 != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }

    }

    public static void testerAuthentification() {
        //======================================
        // Tester l'authentification des utilisateurs :

        System.out.println();
        System.out.println("**** testerAuthentificationUtilisateur() ****");
        System.out.println();

        Service service = new Service();

        Utilisateur user;
        String mail;
        String motDePasse;

        mail = "camile.martin@gmail.com";
        motDePasse = "mdp";
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

    public static void testerGetMediums() {
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

    public static void testerCreerConversation() {
        System.out.println("****   Test creer conversation  ****");
        if(clients == null || mediums == null){
            System.out.println("Les clients et médiums doivent être initialisés");
            return;
        }
        
        Service service = new Service();

        conversations = new Conversation[3];
        conversations[0] = service.creerConversation(clients[0], mediums[0]);
        conversations[1] = service.creerConversation(clients[0], mediums[1]);
        conversations[2] = service.creerConversation(clients[1], mediums[2]);

        System.out.println("Conversations ajoutées :");
        System.out.println("> " + conversations[0]);
        System.out.println("> " + conversations[1]);
        System.out.println("> " + conversations[2]);
        
        
        
        System.out.println("****   fin test creer conversation  ****\n");
    }

    public static void testerRechercherConversation() {
        System.out.println("\n**** Test recherche conversation****");
        Service service = new Service();

        System.out.println("**** Recherche des conversations, point de vu employe");

        for(Employe e : employes){
            List<Conversation> conv = service.rechercherConversationPourEmploye(e);
            System.out.println("> conversations pour : " + e);
            if (conv != null) {
                for (Conversation c : conv) {
                    System.out.println("--> " + c);
                }
            }
        }
        
        System.out.println("**** fin Test recherche conversation****\n");
    }

    public static void testerRechercherHistoriqueClient() {
        System.out.println("\n**** Test recherche Historique Client ****");
        Service service = new Service();

        for(int i = 0; i < 5; i++){
            List<Conversation> conv = service.historiqueClient(clients[i]);
            System.out.println("> conversations pour : " + clients[i]);
            if (conv != null) {
                for (Conversation cv : conv) {
                    System.out.println("--> " + cv);
                }
            }
        }
        
        System.out.println("**** fin Test recherche Historique Client ****\n");
    }

    public static void testerCommencerFinirConversation() {
        System.out.println("**** Test commencer/finir conversation****");
        if(clients == null || mediums == null || conversations == null){
            System.out.println("Les clients, médiums et conversations doivent être initialisés");
            return;
        }

        Service service = new Service();

        boolean res1 = false, res2 = false;
        res1 = service.commencerConversation(conversations[0]);
        res2 = service.commencerConversation(conversations[2]);
        System.out.println("> conv 1 commencée ? [" + res1 + "]");
        if (res1) {
            System.out.println("--> " + conversations[0]);
        }

        System.out.println("> conv 2 commencée ? [" + res2 + "]");
        if (res2) {
            System.out.println("--> " + conversations[2]);
        }

        res1 = false;
        res2 = false;
        res1 = service.finirConversation(conversations[0], "bien");
        res2 = service.finirConversation(conversations[1], "bug");
        System.out.println("> conv 1 finie ? [" + res1 + "]");
        if (res1) {
            System.out.println("--> " + conversations[0]);
        }

        System.out.println("> conv 3 fermée ? [" + res2 + "]");
        if (res2) {
            System.out.println("--> " + conversations[1]);
        }
        
        System.out.println("**** fin Test commencer/finir conversation****\n");
    }

    public static void testerNbConsultationMedium() {
        System.out.println("**** Test Nb consulations medium      ****");

        Service service = new Service();

        List<Pair<Medium, Long>> mediums = service.nbConsultationParMedium();

        System.out.println("Mediums les plus utilisés");
        if (mediums != null) {
            for (Pair<Medium, Long> p : mediums) {
                System.out.println("> nb conv : (" + p.getValue() + "), medium : " + p.getKey());
            }
        }
        
        System.out.println("**** fin Test Nb consulations medium ****\n");
    }

    public static void testerNbConsultationEmploye() {
        System.out.println("**** Test Nb consulations medium      ****");

        Service service = new Service();

        List<Pair<Employe, Long>> list = service.nbConsultationParEmploye();

        System.out.println("Employés les plus solicités");
        if (list != null) {
            for (Pair<Employe, Long> p : list) {
                System.out.println("> nb conv : (" + p.getValue() + "), employé : " + p.getKey());
            }
        }
        System.out.println("**** fin Test Nb consulations medium  ****\n");
    }

    public static void testGenererPredictions(){
        System.out.println("**** Test Générer prédictions ****");
        Service service = new Service();
        List<String> prediction = service.genererPredictions("bleu","cochon",2,2,2);
        for(String s : prediction){
            System.out.println(s);
        }
        System.out.println("**** fin Test Générer prédictions ****\n");
    }

}
