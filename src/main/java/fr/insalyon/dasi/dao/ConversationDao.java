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
    
    //TODO rechercher conversation pour employé ?
    /*
    public Client chercherParMail(String clientMail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.mail = :mail", Client.class);
        query.setParameter("mail", clientMail); // correspond au paramètre ":mail" dans la requête
        List<Client> clients = query.getResultList();
        Client result = null;
        if (!clients.isEmpty()) {
            result = clients.get(0); // premier de la liste
        }
        return result;
    }
    
    public List<Client> listerClients() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c ORDER BY c.nom ASC, c.prenom ASC", Client.class);
        return query.getResultList();
    }*/
}
