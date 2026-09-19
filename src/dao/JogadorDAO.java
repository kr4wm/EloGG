package dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import model.Jogador;
import util.JPAUtil;

public class JogadorDAO {

    public Jogador buscarPorNickId(String nickname, String hashtag) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = """
                SELECT j
                FROM Jogador j
                WHERE LOWER(j.nickname) = LOWER(:nickname)
                AND LOWER(j.hashtag) = LOWER(:hashtag)
                """;

            return em.createQuery(jpql, Jogador.class)
                     .setParameter("nickname", nickname)
                     .setParameter("hashtag", hashtag)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}