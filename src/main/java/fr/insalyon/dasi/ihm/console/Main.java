package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DASI Team
 */
public class Main {

    public static void main(String[] args) {

        // TODO : Pensez à créer une unité de persistance "DASI-PU" et à vérifier son nom dans la classe JpaUtil
        // Contrôlez l'affichage du log de JpaUtil grâce à la méthode log de la classe JpaUtil
        //JpaUtil.init();

        initDatabase();
        
        //JpaUtil.destroy();
    }
    
    public static void initDatabase(){
        System.out.println();
        System.out.println("**** initDatabase() ****");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("utilisateur_persistence");
        EntityManager em = emf.createEntityManager();
        
        Client ada = new Client("Lovelace", "Ada", "ada.lovelace@insa-lyon.fr", "Ada1012");
        Client blaise = new Client("Pascal", "Blaise", "blaise.pascal@insa-lyon.fr", "Blaise1906");
        Client fred = new Client("Fotiadu", "Frédéric", "frederic.fotiadu@insa-lyon.fr", "INSA-Forever");
       
        System.out.println();
        System.out.println("** Clients avant persistance: ");
        System.out.println(ada);
        System.out.println(blaise);
        System.out.println(fred);
        
         try {
            em.getTransaction().begin();
            em.persist(ada);
            em.persist(blaise);
            em.persist(fred);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service", ex);
            try {
                em.getTransaction().rollback();
            }
            catch (IllegalStateException ex2) {
                // Ignorer cette exception...
            }
        } finally {
            em.close();
        }

        System.out.println();
        System.out.println("** Clients après persistance: ");
        System.out.println(ada);
        System.out.println(blaise);
        System.out.println(fred);
        System.out.println();
    }
}
