package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Medium;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author DASI Team
 */
public class ClientDao {
    
    public void creer(Client client){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(client);
    }
    
    /**
     * Recherche un client par son Id
     *
     * @param id id à cehrcher
     * @return le client trouvé, null si non-trouvé
     */
    public Client chercherParId(Long id) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Client.class, id); // renvoie null si l'identifiant n'existe pas
    }
}
