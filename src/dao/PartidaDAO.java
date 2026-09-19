package dao;

import javax.persistence.EntityManager;
import java.util.List;
import model.Partida;
import util.JPAUtil;

public class PartidaDAO {

    public List<Partida> listarPartidas() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = """
                SELECT p
                FROM Partida p
                """;

            return em.createQuery(jpql, Partida.class)
                     .getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public Partida buscarPorId(int idPartida) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Partida.class, idPartida);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}