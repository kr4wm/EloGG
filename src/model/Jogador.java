package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jogador")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJogador;

    private String nickname;
    private String hashtag;
    private String elo;
    private String rotaPrincipal;


    public Jogador() {
    }


    public Jogador(int idJogador,
                   String nickname,
                   String hashtag,
                   String elo,
                   String rotaPrincipal) {

        this.idJogador = idJogador;
        this.nickname = nickname;
        this.hashtag = hashtag;
        this.elo = elo;
        this.rotaPrincipal = rotaPrincipal;
    }


    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }


    public String getElo() {
        return elo;
    }

    public void setElo(String elo) {
        this.elo = elo;
    }


    public String getRotaPrincipal() {
        return rotaPrincipal;
    }

    public void setRotaPrincipal(String rotaPrincipal) {
        this.rotaPrincipal = rotaPrincipal;
    }
    
}