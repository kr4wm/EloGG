package model;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "partida")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPartida;

    private String modoDeJogo;
    
    @Column(name = "dataPartida")
    private LocalDate data;

    private LocalTime hora;


    public Partida() {
    }


    public Partida(int idPartida,
                   String modoDeJogo,
                   LocalDate data,
                   LocalTime hora) {

        this.idPartida = idPartida;
        this.modoDeJogo = modoDeJogo;
        this.data = data;
        this.hora = hora;
    }


    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }


    public String getModoDeJogo() {
        return modoDeJogo;
    }

    public void setModoDeJogo(String modoDeJogo) {
        this.modoDeJogo = modoDeJogo;
    }


    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }


    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}