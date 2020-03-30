package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.UtilisateurDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Utilisateur;
import fr.insalyon.dasi.metier.modele.Employe;
import java.util.List;

/**
 *
 * @author DASI Team
 */
public class Service {

    protected ClientDao clientDao = new ClientDao();
    protected UtilisateurDao utilisateurDao = new UtilisateurDao();

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

    public Conversation creerConversation(Client client, Medium medium) {
        Conversation conv = new Conversation(
                Conversation.Etat.EN_ATTENTE,
                new Date(System.currentTimeMillis()),
                null,
                medium,
                null,
                client
        );

        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            conversationDao.creer(conv);
            JpaUtil.validerTransaction();
            conv = null;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireClient(client)", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return conv;
    }
    
    public Conversation creerConversation(Client client, Medium medium) {
        Conversation conv = new Conversation(
                Conversation.Etat.EN_ATTENTE,
                new Date(System.currentTimeMillis()),
                null,
                medium,
                null,
                client
        );

        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            conversationDao.creer(conv);
            JpaUtil.validerTransaction();
            conv = null;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service creerConversation(client)", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return conv;
    }

    public List<Conversation> rechercherConversationPourEmploye(Employe employe){
        if(employe.getId() == null){
            System.err.println("ERROR : Service.rechercherConversationPourEmploye ("+employe+") : id null");
            return null;
        }
        
        List<Conversation> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche des conversation
            resultat = conversationDao.rechercherConversationPourEmploye(employe.getId());
        } catch (Exception ex) {
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return resultat;
    }

    public boolean commencerConversation(Conversation c){
        if(c.getId() == null){
            System.err.println("ERROR : Service.commencerConversation ("+c+") : id null");
            return false;
        }
        
        Conversation.Etat before = c.getEtat();
        c.setEtat(Conversation.Etat.EN_COURS);
        boolean resultat = false;
        
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            
            resultat = conversationDao.mettreAJour(c);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service commencerConversation(client)", ex);
            JpaUtil.annulerTransaction();
            resultat = false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        if(!resultat){
            //on remet les anciennes valeurs
            c.setEtat(before);
        }
        return resultat;
    }
    
    public boolean finirConversation(Conversation c, String commentaire){
        if(c.getId() == null){
            System.err.println("ERROR : Service.finirConversation ("+c+") : id null");
            return false;
        }
        
        Conversation.Etat before = c.getEtat();
        c.setEtat(Conversation.Etat.TERMINEE);
        c.setCommentaire(commentaire);
        
        boolean resultat = false;
        
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            
            resultat = conversationDao.mettreAJour(c);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service finirConversation(client)", ex);
            JpaUtil.annulerTransaction();
            resultat = false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        if(!resultat){
            //on remet les anciennes valeurs
            c.setEtat(before);
            c.setCommentaire(null);
        }
        return resultat;
    }
}
