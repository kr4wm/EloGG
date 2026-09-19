package view;

import dao.EstatisticaPartidaDAO;
import dao.PartidaDAO;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.EstatisticaPartida;
import model.Partida;

public class TelaDetalhesPartida {

    private final JFrame telaDetalhesPartida;

    private JTable tblTimeAzul;
    private JTable tblTimeVermelho;

    private DefaultTableModel modeloTimeAzul;
    private DefaultTableModel modeloTimeVermelho;

    private final Color COLOR_BG = new Color(24, 25, 29);
    private final Color COLOR_CARD_BG = new Color(33, 36, 44);
    private final Color COLOR_AZUL = new Color(83, 131, 255);
    private final Color COLOR_VERMELHO = new Color(220, 70, 80);
    private final Color COLOR_TEXT_MUTED = new Color(130, 136, 155);
    private final Color COLOR_GOLD = new Color(229, 185, 94);
    private final Color COLOR_BORDER = new Color(45, 49, 62);

    public TelaDetalhesPartida(int idPartida) {
        telaDetalhesPartida = new JFrame();

        inicializarJanela();
        criarTabelas(idPartida);
        carregarTabelas(idPartida);

        telaDetalhesPartida.setVisible(true);
    }

    private void inicializarJanela() {
        telaDetalhesPartida.setTitle("Elo.GG | Detalhes da Partida");
        telaDetalhesPartida.setSize(900, 590);
        telaDetalhesPartida.setResizable(false);
        telaDetalhesPartida.setLocationRelativeTo(null);
        telaDetalhesPartida.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaDetalhesPartida.setLayout(null);
        telaDetalhesPartida.getContentPane().setBackground(COLOR_BG);
    }

    private DefaultTableModel criarModeloTabela() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addColumn("Nick");
        modelo.addColumn("Campeão");
        modelo.addColumn("Função");
        modelo.addColumn("Rota");
        modelo.addColumn("K/D/A");
        modelo.addColumn("Build");

        return modelo;
    }

    private JTable criarTabela(DefaultTableModel modelo, Color corTime) {
        JTable tabela = new JTable(modelo);

        tabela.setRowHeight(30);
        tabela.setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabela.setBackground(COLOR_CARD_BG);
        tabela.setForeground(corTime);
        tabela.setGridColor(COLOR_BORDER);
        tabela.setSelectionBackground(corTime);
        tabela.setSelectionForeground(Color.WHITE);
        tabela.setShowVerticalLines(false);

        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabela.getTableHeader().setBackground(COLOR_BORDER);
        tabela.getTableHeader().setForeground(COLOR_TEXT_MUTED);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.getTableHeader().setResizingAllowed(false);

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();

        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        centralizado.setBackground(COLOR_CARD_BG);
        centralizado.setForeground(corTime);

        for (int i = 0; i < 5; i++) {
            tabela.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(centralizado);
        }

        tabela.getColumnModel().getColumn(0).setPreferredWidth(140);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(140);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(110);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(90);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(260);

        return tabela;
    }

    private void criarTabelas(int idPartida) {
        PartidaDAO partidaDAO = new PartidaDAO();
        Partida partidaAtual = partidaDAO.buscarPorId(idPartida);
        
        JLabel lblTitulo = new JLabel("Detalhes da Partida", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(COLOR_GOLD);
        lblTitulo.setBounds(0, 25, 900, 30);
        
        JLabel lblDetalhes = new JLabel("Data: " + partidaAtual.getData() + 
                " | Hora: " + partidaAtual.getHora(), SwingConstants.CENTER);
        lblDetalhes.setForeground(COLOR_GOLD);
        lblDetalhes.setBounds(0, 50, 900, 30);

        JLabel lblTimeAzul = new JLabel("TIME AZUL");
        lblTimeAzul.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTimeAzul.setForeground(COLOR_AZUL);
        lblTimeAzul.setBounds(50, 80, 300, 25);

        JLabel lblTimeVermelho = new JLabel("TIME VERMELHO");
        lblTimeVermelho.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTimeVermelho.setForeground(COLOR_VERMELHO);
        lblTimeVermelho.setBounds(50, 310, 300, 25);

        modeloTimeAzul = criarModeloTabela();
        tblTimeAzul = criarTabela(modeloTimeAzul, COLOR_AZUL);

        modeloTimeVermelho = criarModeloTabela();
        tblTimeVermelho = criarTabela(modeloTimeVermelho, COLOR_VERMELHO);

        JScrollPane scrollTimeAzul = new JScrollPane(tblTimeAzul);
        scrollTimeAzul.setBounds(50, 110, 800, 174);
        scrollTimeAzul.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
        scrollTimeAzul.getViewport().setBackground(COLOR_CARD_BG);

        JScrollPane scrollTimeVermelho = new JScrollPane(tblTimeVermelho);
        scrollTimeVermelho.setBounds(50, 340, 800, 174);
        scrollTimeVermelho.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
        scrollTimeVermelho.getViewport().setBackground(COLOR_CARD_BG);

        telaDetalhesPartida.add(lblTitulo);
        telaDetalhesPartida.add(lblDetalhes);
        telaDetalhesPartida.add(lblTimeAzul);
        telaDetalhesPartida.add(scrollTimeAzul);
        telaDetalhesPartida.add(lblTimeVermelho);
        telaDetalhesPartida.add(scrollTimeVermelho);
    }

    private void carregarTabelas(int idPartida) {
        modeloTimeAzul.setRowCount(0);
        modeloTimeVermelho.setRowCount(0);

        EstatisticaPartidaDAO dao = new EstatisticaPartidaDAO();

        List<EstatisticaPartida> jogadoresDaPartida
                = dao.buscarPorPartida(idPartida);

        for (EstatisticaPartida jogadorNaPartida : jogadoresDaPartida) {
            String kda = jogadorNaPartida.getKills() + "/"
                    + jogadorNaPartida.getDeaths() + "/"
                    + jogadorNaPartida.getAssists();

            Object[] linha = {
                jogadorNaPartida.getJogador().getNickname(),
                jogadorNaPartida.getCampeao().getNomeCampeao(),
                jogadorNaPartida.getCampeao().getFuncaoCampeao(),
                jogadorNaPartida.getCampeao().getRotaCampeao(),
                kda
            };

            if (jogadorNaPartida.isVitoria()) {
                modeloTimeAzul.addRow(linha);
            } else {
                modeloTimeVermelho.addRow(linha);
            }
        }
    }
}