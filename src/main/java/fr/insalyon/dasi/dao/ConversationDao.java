package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Conversation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Antoine MANDIN
 */
public class ConversationDao {

    /**
     * Persiste la conversation
     *
     * @param conv à persister
     */
    public void creer(Conversation conv) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(conv);
    }

    /**
     * Recherche une conversation par son Id
     *
     * @param conversationId id à cehrcher
     * @return la conversation trouvé null si la conversation n'est pas trouvée
     */
    public Conversation chercherParId(Long conversationId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Conversation.class, conversationId); // renvoie null si l'identifiant n'existe pas
    }

    /**
     * Met à jour la conversation dans la BDD
     *
     * @param conv conversation à mettre à jour
     * @return si la mise à jour c'est bien effectué (true) ou non (false)
     */
    public boolean mettreAJour(Conversation conv) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(conv) != null;
    }

    /**
     * Recherche les conversations conrespondantes à l'employé encore en attente
     *
     * @param employeId identifiant de l'employé
     * @return les conversation correspondante @nullable
     */
    public List<Conversation> rechercherConversationPourEmploye(long employeId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Conversation> query = em.createQuery(
                "SELECT c "
                + "FROM Conversation c "
                + "WHERE c.etat = :etat "
                + "AND c.employe.id = :employeId "
                + "ORDER BY c.dateConsultation ASC", Conversation.class);
        query.setParameter("employeId", employeId);
        query.setParameter("etat", Conversation.Etat.EN_ATTENTE);
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

    public List<Pair<Medium, Long>> nbConsultationParMedium() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        List<Object[]> res = em.createQuery(
                "SELECT c.medium, count(c.medium) nb "
                + "FROM Conversation c "
                + "GROUP BY c.medium "
                + "ORDER BY nb DESC")
                .getResultList();

        List<Pair<Medium, Long>> resultat = null;
        if (res != null) {
            resultat = new ArrayList<>();
            //Un objet sur deux est un médium
            for (Object[] p : res) {
                resultat.add(
                        new Pair<>(
                                (Medium) p[0],
                                (long) p[1]
                        )
                );
            }
        }

        return resultat;
    }

    public List<Pair<Employe, Long>> nbConsultationParEmploye() {
        System.out.println("nb called");
        EntityManager em = JpaUtil.obtenirContextePersistance();
        List<Object[]> res = em.createQuery(
                "SELECT c.employe, count(c.employe) nb "
                + "FROM Conversation c "
                + "GROUP BY c.employe "
                + "ORDER BY nb DESC")
                .getResultList();

        List<Pair<Employe, Long>> resultat = null;
        if (res != null) {
            resultat = new ArrayList<>();
            //Un objet sur deux est un médium
            for (Object[] p : res) {
                resultat.add(
                        new Pair<>(
                                (Employe) p[0],
                                (long) p[1]
                        )
                );
            }
        }

        return resultat;
    }
}
