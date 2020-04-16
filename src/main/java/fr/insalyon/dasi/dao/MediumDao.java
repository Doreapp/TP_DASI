
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Medium;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class MediumDao {
    
    public List<Medium> listerMediums(){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT m FROM Medium m ORDER BY m.nom ASC", Medium.class);
        return query.getResultList();
    }
    /**
     * Recherche un médium par son Id
     *
     * @param id id à cehrcher
     * @return le médium, null si non-trouvé
     */
    public Medium chercherParId(Long id) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Medium.class, id); // renvoie null si l'identifiant n'existe pas
    }
}
