package controller;
import dao.PartidaDAO;
import java.util.List;
import model.Partida;

public class PartidaController {
    
    // Instanciar DAO → Consultar Dados Posteriormente
    private PartidaDAO partidaDAO = new PartidaDAO();
      
    // Listar Todas as Partidas Registradas
    public List<Partida> listarPartidas() {
        return partidaDAO.listarPartidas();
    }

}