package model;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-08-24T13:42:35", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Partida.class)
public class Partida_ { 

    public static volatile SingularAttribute<Partida, Integer> idPartida;
    public static volatile SingularAttribute<Partida, LocalDate> data;
    public static volatile SingularAttribute<Partida, LocalTime> hora;
    public static volatile SingularAttribute<Partida, String> modoDeJogo;

}