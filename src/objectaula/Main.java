// O pacote pode ser diferente no seu projeto. Mantenha o seu.
package objectaula; 

import javax.persistence.*; // A mudança principal está aqui!
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pontos.odb");
        EntityManager em = emf.createEntityManager();

        try {
            // Inserir dados
            em.getTransaction().begin();
            System.out.println("Inserindo 10 pontos...");
            for (int i = 0; i < 10; i++) {
                Point p = new Point(i, i * 2);
                em.persist(p);
            }
            em.getTransaction().commit();

            // Consultar dados
            System.out.println("\nConsultando todos os pontos salvos:");
            TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
            List<Point> resultados = query.getResultList();
            for (Point p : resultados) {
                System.out.println(p);
            }

        } finally {
            // Fechar a conexão
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
            emf.close();
        }
        System.out.println("\nPrograma finalizado com sucesso.");
    }
}