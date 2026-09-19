package model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Campeao;
import model.Jogador;
import model.Partida;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-08-24T13:42:35", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(EstatisticaPartida.class)
public class EstatisticaPartida_ { 

    public static volatile SingularAttribute<EstatisticaPartida, Integer> kills;
    public static volatile SingularAttribute<EstatisticaPartida, Campeao> campeao;
    public static volatile SingularAttribute<EstatisticaPartida, Integer> assists;
    public static volatile SingularAttribute<EstatisticaPartida, Integer> idParticipacao;
    public static volatile SingularAttribute<EstatisticaPartida, Integer> deaths;
    public static volatile SingularAttribute<EstatisticaPartida, Boolean> vitoria;
    public static volatile SingularAttribute<EstatisticaPartida, Jogador> jogador;
    public static volatile SingularAttribute<EstatisticaPartida, Partida> partida;

}