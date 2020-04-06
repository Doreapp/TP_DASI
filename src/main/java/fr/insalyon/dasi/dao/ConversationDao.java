package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Conversation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Antoine MANDIN
 */
public class ConversationDao {

    /**
     * Persiste la conversation
     * @param conv à persister
     */
    public void creer(Conversation conv) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(conv);
    }

    /**
     * Recherche une conversation par son Id
     * @param conversationId id à cehrcher
     * @return la conversation trouvé
     *  null si la conversation n'est pas trouvée
     */
    public Conversation chercherParId(Long conversationId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Conversation.class, conversationId); // renvoie null si l'identifiant n'existe pas
    }

    /**
     * Met à jour la conversation dans la BDD
     * @param conv conversation à mettre à jour
     * @return si la mise à jour c'est bien effectué (true) ou non (false)
     */
    public boolean mettreAJour(Conversation conv) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(conv) != null;
    }

    /**
     * Recherche les conversations conrespondantes à l'employé encore en attente
     * @param employeId identifiant de l'employé 
     * @return les conversation correspondante @nullable
     */
    public List<Conversation> rechercherConversationPourEmploye(long employeId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Conversation> query = em.createQuery(
                "SELECT c "
                + "FROM Conversation c "
                + "WHERE ETAT = 2" //ETAT = 0 <=> ETAT = EN_ATTENTE
                + "AND Employe_id = " + employeId
                + "ORDER BY c.date ASC", Conversation.class);
        return query.getResultList();
    }

    /**
     * Recherche les conversations d'un client
     * @param clientId identifiant du client
     * @return les conversation correspondante
     */
    public List<Conversation> listerConversationClient(long clientId){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Conversation> query = em.createQuery(
                "SELECT c "
                + "FROM Conversation c "
                + "WHERE c.client.id = :id "
                + "ORDER BY c.dateConsultation ASC", Conversation.class);
        query.setParameter("id",clientId);
        return query.getResultList();
    }
}