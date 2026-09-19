package dao;

import javax.persistence.EntityManager;
import java.util.List;
import model.EstatisticaPartida;
import util.JPAUtil;

public class EstatisticaPartidaDAO {

    public List<EstatisticaPartida> buscarHistoricoDoJogador(int idJogador) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = """
                SELECT e
                FROM EstatisticaPartida e
                WHERE e.jogador.idJogador = :idJogador
                ORDER BY e.idParticipacao DESC
                """;

            return em.createQuery(jpql, EstatisticaPartida.class)
                     .setParameter("idJogador", idJogador)
                     .getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public List<EstatisticaPartida> buscarUltimasPartidas(int idJogador, int quantidade) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = """
                SELECT e
                FROM EstatisticaPartida e
                WHERE e.jogador.idJogador = :idJogador
                ORDER BY e.idParticipacao DESC
                """;

            return em.createQuery(jpql, EstatisticaPartida.class)
                     .setParameter("idJogador", idJogador)
                     .setMaxResults(quantidade)
                     .getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    public List<EstatisticaPartida> buscarPorPartida(int idPartida) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            String jpql = """
                SELECT e
                FROM EstatisticaPartida e
                WHERE e.partida.idPartida = :idPartida
                ORDER BY CASE e.campeao.rotaCampeao
                    WHEN 'Top' THEN 1
                    WHEN 'Jungle' THEN 2
                    WHEN 'Mid' THEN 3
                    WHEN 'Bot - ADC' THEN 4
                    WHEN 'Bot - Sup' THEN 5
                    ELSE 6
                END
                """;

            return em.createQuery(jpql, EstatisticaPartida.class)
                    .setParameter("idPartida", idPartida)
                    .getResultList();

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}