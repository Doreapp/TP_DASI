/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Utilisateur;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author antoi
 */
public class EmployeDao {

    public Employe chercherPourMedium(Medium medium) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery(
                "SELECT u "
                + "FROM Employe u "
                + "WHERE u.disponible = 'true' "
                + "AND u.genre = :genre "
                + "ORDER BY u.nbConsultation ASC ",
                 Employe.class);
        query.setParameter("genre", medium.getGenre());
        List<Employe> employes = query.getResultList();

        Employe result = null;
        if (!employes.isEmpty()) {
            result = employes.get(0); // premier de la liste
        }
        return result;
    }
}
