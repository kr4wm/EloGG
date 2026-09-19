package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "campeao")
public class Campeao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCampeao;

    private String nomeCampeao;
    private String funcaoCampeao;
    private String rotaCampeao;

    public Campeao() {
    }

    public Campeao(int idCampeao,
                   String nomeCampeao,
                   String funcaoCampeao,
                   String rotaCampeao) {

        this.idCampeao = idCampeao;
        this.nomeCampeao = nomeCampeao;
        this.funcaoCampeao = funcaoCampeao;
        this.rotaCampeao = rotaCampeao;
    }

    public int getIdCampeao() {
        return idCampeao;
    }

    public void setIdCampeao(int idCampeao) {
        this.idCampeao = idCampeao;
    }

    public String getNomeCampeao() {
        return nomeCampeao;
    }

    public void setNomeCampeao(String nomeCampeao) {
        this.nomeCampeao = nomeCampeao;
    }

    public String getFuncaoCampeao() {
        return funcaoCampeao;
    }

    public void setFuncaoCampeao(String funcaoCampeao) {
        this.funcaoCampeao = funcaoCampeao;
    }

    public String getRotaCampeao() {
        return rotaCampeao;
    }

    public void setRotaCampeao(String rotaCampeao) {
        this.rotaCampeao = rotaCampeao;
    }
    
}