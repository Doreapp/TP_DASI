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
    
    public void creer(Client client) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(client);
    }
    
}
