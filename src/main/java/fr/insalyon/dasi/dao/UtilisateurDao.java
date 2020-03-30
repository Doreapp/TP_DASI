package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Utilisateur;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UtilisateurDao {
    
    public Utilisateur chercherParMail(String mail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Utilisateur> query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.email = :mail", Utilisateur.class);
        query.setParameter("mail", mail); // correspond au paramètre ":mail" dans la requête
        List<Utilisateur> Utilisateurs = query.getResultList();
        Utilisateur result = null;
        if (!Utilisateurs.isEmpty()) {
            result = Utilisateurs.get(0); // premier de la liste
        }
        return result;
    }
    
}
