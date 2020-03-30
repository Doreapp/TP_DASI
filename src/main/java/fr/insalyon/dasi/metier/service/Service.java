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
}
