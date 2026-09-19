package dao;

import javax.persistence.EntityManager;
import java.util.List;
import model.Campeao;
import util.JPAUtil;

public class CampeaoDAO {

    public List<Campeao> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = """
                SELECT c
                FROM Campeao c
                """;

            return em.createQuery(jpql, Campeao.class)
                     .getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Campeao> buscarPorNome(String nomeCampeao) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = """
                SELECT c
                FROM Campeao c
                WHERE LOWER(c.nomeCampeao)
                LIKE LOWER(:nomeCampeao)
                """;

            return em.createQuery(jpql, Campeao.class)
                     .setParameter("nomeCampeao", "%" + nomeCampeao + "%")
                     .getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}