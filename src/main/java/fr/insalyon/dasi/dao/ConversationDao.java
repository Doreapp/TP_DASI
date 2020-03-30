/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Conversation;
import javax.persistence.EntityManager;

/**
 *
 * @author antoi
 */
public class ConversationDao {
    
    public void creer(Conversation conv){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(conv);
    }
    
    public Conversation chercherParId(Long conversationId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Conversation.class, conversationId); // renvoie null si l'identifiant n'existe pas
    }
    
     public boolean mettreAJour(Conversation conv){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.merge(conv) != null;
    }
    
    public List<Conversation> rechercherConversationPourEmploye(long employeId){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Conversation> query = em.createQuery(
                "SELECT c "
                        + "FROM Conversation c "
                        + "WHERE ETAT = 2" //ETAT = 0 <=> ETAT = EN_ATTENTE
                            + "AND Employe_id = "+employeId
                        + "ORDER BY c.date ASC", Conversation.class);
        return query.getResultList();
    }
}
