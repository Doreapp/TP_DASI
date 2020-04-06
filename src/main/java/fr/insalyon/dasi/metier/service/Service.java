package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.ConversationDao;
import fr.insalyon.dasi.dao.EmployeDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.dao.UtilisateurDao;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Conversation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Utilisateur;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author Antoine MANDIN + Damien CARREAU
 */
public class Service {

    protected ClientDao clientDao = new ClientDao();
    protected ConversationDao conversationDao = new ConversationDao();
    protected UtilisateurDao utilisateurDao = new UtilisateurDao();
    protected MediumDao mediumDao = new MediumDao();
    protected EmployeDao employeDao = new EmployeDao();

    /**
     * Log une erreur 
     * @param error
     * @param ex 
     */
    public static void Log(String error, Throwable ex){
        Logger.getAnonymousLogger().log(Level.WARNING, error, ex);
    }
    
    /**
     * incrit un client dans la base de donnée
     * @param client Client à insrire
     * @return l'identifiant du client dans la BDD
     */
    public Long inscrireClient(Client client) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            clientDao.creer(client);
            JpaUtil.validerTransaction();
            resultat = client.getId();
        } catch (Exception ex) {
            System.out.println(ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    /**
     * Authentifie l'utilisateur correspondant aux informations
     * @param mail mail de l'utilisateur
     * @param motDePasse mot de passe entré
     * @return l'utilisateur authentifié ou null si erreur d'authentification
     */
    public Utilisateur authentifierUtilisateur(String mail, String motDePasse) {
        Utilisateur resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche de l'Utilisateur
            Utilisateur user = utilisateurDao.chercherParMail(mail);
            if (user != null) {
                // Vérification du mot de passe
                if (user.getMotDePasse().equals(motDePasse)) {
                    resultat = user;
                }
            }
        } catch (Exception ex) {
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    /**
     * Créer une conversation (en attente) au sein de la BDD
     * @param client client de la conversation
     * @param medium medium lié à la conversation
     * @return la conversation créée
     *  si l'employe de la conversation est null, alors il n'est pas possible de 
     *  lancer la conversation (message/notification d'erreur au client)
     */
    public Conversation creerConversation(Client client, Medium medium) {
        //TODO : Rechercher l'employé le plus à même de répondre à la conversation
        Employe employe = null;
        
        JpaUtil.creerContextePersistance();
        try {
            // Recherche des conversation
            employe = employeDao.chercherPourMedium(medium);
        } catch (Exception ex) {
            Log("Exception lors de l'appel au Service creerConversation(client, medium)", ex);
            employe = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        Conversation conv = new Conversation(
                Conversation.Etat.EN_ATTENTE,
                new Date(System.currentTimeMillis()),
                null,
                medium,
                employe,
                client
        );

        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            conversationDao.creer(conv);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            conv = null;
            Log("Exception lors de l'appel au Service creerConversation(client, medium)", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return conv;
    }

    /**
     * Recherche les conversations attribuées à l'employé, encore en attente
     * @param employe 
     * @return la liste des conversations correspondantes
     */
    public List<Conversation> rechercherConversationPourEmploye(Employe employe){
        if(employe.getId() == null){
            Log("Exception lors de l'appel au Service rechercherConversationPourEmploye(employé) : ID NULL", new Throwable());
            return null;
        }
        
        List<Conversation> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche des conversation
            resultat = conversationDao.rechercherConversationPourEmploye(employe.getId());
        } catch (Exception ex) {
            Log("Exception lors de l'appel au Service rechercherConversationPourEmploye(employé)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return resultat;
    }

    /**
     * Indique que la conversation a débutée
     * @param c conversation en question
     * @return si l'opération c'est bien passée
     */
    public boolean commencerConversation(Conversation c){
        if(c.getId() == null){
            Log("Exception lors de l'appel au Service commencerConversation(Conversation) : ID NULL", new Throwable());
            return false;
        }
        
        Conversation.Etat before = c.getEtat();
        c.setEtat(Conversation.Etat.EN_COURS);
        Employe employe = c.getEmploye();
        employe.setDisponible(false);
        boolean resultat = false;
        
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            
            resultat = conversationDao.mettreAJour(c) && 
                    employeDao.mettreAJour(employe);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Log("Exception lors de l'appel au Service commencerConversation(Conversation)", ex);
            JpaUtil.annulerTransaction();
            resultat = false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        if(!resultat){
            //on remet les anciennes valeurs
            c.setEtat(before);
            employe.setDisponible(true);
        }
        return resultat;
    }
    
    /**
     * Indique que la conversation est finie 
     * @param c conversation en question
     * @param commentaire à ajouter à la conversation
     * @return si l'opération c'est bien passées
     */
    public boolean finirConversation(Conversation c, String commentaire){
        if(c.getId() == null){
            Log("Exception lors de l'appel au Service finirConversation(Conversation, Commentaire) : ID NULL", new Throwable());
            return false;
        }
        
        Conversation.Etat before = c.getEtat();
        c.setEtat(Conversation.Etat.TERMINEE);
        Employe employe = c.getEmploye();
        employe.setDisponible(true);
        c.setCommentaire(commentaire);
        
        boolean resultat = false;
        
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            
            resultat = conversationDao.mettreAJour(c) && 
                    employeDao.mettreAJour(employe);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Log("Exception lors de l'appel au Service finirConversation(Conversation, Commentaire) : ID NULL", ex);
            JpaUtil.annulerTransaction();
            resultat = false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        if(!resultat){
            //on remet les anciennes valeurs
            c.setEtat(before);
            c.setCommentaire(null);
            employe.setDisponible(false);
        }
        return resultat;
    }
    
    /**
     * Retourne la liste de tous les 
     * @return 
     */
    public List<Medium> getMediums() {
        List<Medium> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.listerMediums();
        } catch (Exception ex) {
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    /**
     * Renvoi la liste des conversations d'un client 
     */
    public List<Conversation> historiqueClient(Client c){
        List<Conversation> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = conversationDao.listerConversationClient(c.getId());
        } catch (Exception ex) {
            resultat = null;
            System.out.println(ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public List<Pair<Medium, Long>> nbConsultationParMedium(){
        List<Pair<Medium, Long>> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = conversationDao.nbConsultationParMedium();
        } catch (Exception ex) {
            System.out.println("Erreur : ");
            ex.printStackTrace();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public List<Pair<Employe, Long>> nbConsultationParEmploye(){
        List<Pair<Employe, Long>> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = conversationDao.nbConsultationParEmploye();
        } catch (Exception ex) {
            System.out.println("Erreur : ");
            ex.printStackTrace();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
}
    
    

