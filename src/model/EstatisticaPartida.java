package model;

import javax.persistence.*;

@Entity
@Table(name = "estatistica_partida")
public class EstatisticaPartida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idParticipacao;


    @ManyToOne
    @JoinColumn(name = "idJogador")
    private Jogador jogador;


    @ManyToOne
    @JoinColumn(name = "idPartida")
    private Partida partida;


    @ManyToOne
    @JoinColumn(name = "idCampeao")
    private Campeao campeao;


    private int kills;

    private int deaths;

    private int assists;

    private boolean vitoria;


    public EstatisticaPartida() {
    }


    public EstatisticaPartida(Jogador jogador,
                              Partida partida,
                              Campeao campeao,
                              int kills,
                              int deaths,
                              int assists,
                              boolean vitoria) {

        this.jogador = jogador;
        this.partida = partida;
        this.campeao = campeao;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.vitoria = vitoria;
    }


    public int getIdParticipacao() {
        return idParticipacao;
    }

    public void setIdParticipacao(int idParticipacao) {
        this.idParticipacao = idParticipacao;
    }


    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }


    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }


    public Campeao getCampeao() {
        return campeao;
    }

    public void setCampeao(Campeao campeao) {
        this.campeao = campeao;
    }


    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }


    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }


    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }


    public boolean isVitoria() {
        return vitoria;
    }

    public void setVitoria(boolean vitoria) {
        this.vitoria = vitoria;
    }

}