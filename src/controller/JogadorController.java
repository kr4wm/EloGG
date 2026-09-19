package controller;

import dao.EstatisticaPartidaDAO;
import dao.JogadorDAO;
import java.util.List;
import model.EstatisticaPartida;
import model.Jogador;

public class JogadorController {

    private final JogadorDAO jogadorDAO = new JogadorDAO();
    private final EstatisticaPartidaDAO estatisticaDAO = new EstatisticaPartidaDAO();


    public Jogador buscarJogador(String nickname, String hashtag) {
        return jogadorDAO.buscarPorNickId(nickname, hashtag);
    }


    public double calcularWinRate(Jogador jogador) {

        List<EstatisticaPartida> historico =
                estatisticaDAO.buscarHistoricoDoJogador(jogador.getIdJogador());

        if (historico.isEmpty()) {
            return 0;
        }

        long vitorias = historico.stream()
                .filter(EstatisticaPartida::isVitoria)
                .count();

        return ((double) vitorias / historico.size()) * 100;
    }


    public double calcularKDA(Jogador jogador) {

        List<EstatisticaPartida> historico =
                estatisticaDAO.buscarHistoricoDoJogador(jogador.getIdJogador());

        if (historico.isEmpty()) {
            return 0;
        }

        int kills = 0;
        int deaths = 0;
        int assists = 0;

        for (EstatisticaPartida e : historico) {
            kills += e.getKills();
            deaths += e.getDeaths();
            assists += e.getAssists();
        }

        if (deaths == 0) {
            return kills + assists;
        }

        return (double)(kills + assists) / deaths;
    }
}