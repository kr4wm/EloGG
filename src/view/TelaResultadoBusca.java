package view;

import controller.JogadorController;
import dao.EstatisticaPartidaDAO;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.EstatisticaPartida;
import model.Jogador;

public class TelaResultadoBusca {

    private final JFrame telaResultadoBusca;

    private JLabel lblLogo;

    private JTable tblHistorico;
    private DefaultTableModel tblModelo;

    private final String txtSugestao = "Nick + #BR1";

    private JTextField txtBusca;
    private JButton btnBusca;
    private JButton btnVoltar;
    
    private final Color COLOR_BG = new Color(24, 25, 29);
    private final Color COLOR_CARD_BG = new Color(33, 36, 44);
    private final Color COLOR_INPUT_BG = new Color(40, 43, 53);
    private final Color COLOR_ACCENT = new Color(83, 131, 255);
    private final Color COLOR_ACCENT_HOVER = new Color(105, 148, 255);
    private final Color COLOR_TEXT_PRIMARY = new Color(240, 243, 255);
    private final Color COLOR_TEXT_MUTED = new Color(130, 136, 155);
    private final Color COLOR_GOLD = new Color(229, 185, 94);
    private final Color COLOR_BORDER = new Color(45, 49, 62);

    public TelaResultadoBusca(Jogador jogadorAtual) {
        this.telaResultadoBusca = new JFrame();

        inicializarJanela();
        adicionarPainelHistorico();
        adicionarPainelPerfil(jogadorAtual);
        criarTabela();
        carregarTabela(jogadorAtual.getIdJogador());
        adicionarEventos();

        telaResultadoBusca.setVisible(true);
    }

    private void inicializarJanela() {
        telaResultadoBusca.setTitle("Elo.GG | Resultado da Busca");
        telaResultadoBusca.setSize(1280, 768);
        telaResultadoBusca.setResizable(false);
        telaResultadoBusca.setLocationRelativeTo(null);
        telaResultadoBusca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaResultadoBusca.setLayout(null);
        telaResultadoBusca.getContentPane().setBackground(COLOR_BG);
    }

    private void adicionarPainelPerfil(Jogador jogadorAtual) {
        JPanel pnlPerfil = new JPanel(null);
        
        pnlPerfil.setBounds(50, 265, 200, 280);
        pnlPerfil.setBackground(COLOR_CARD_BG);
        pnlPerfil.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));

        JogadorController jogadorController = new JogadorController();

        double winRate = jogadorController.calcularWinRate(jogadorAtual);
        double kdaMedio = jogadorController.calcularKDA(jogadorAtual);

        JLabel lblNick = new JLabel(
                jogadorAtual.getNickname()
                + " #"
                + jogadorAtual.getHashtag()
        );
        lblNick.setBounds(20, 25, 170, 30);
        lblNick.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblNick.setForeground(COLOR_TEXT_PRIMARY);

        JLabel lblElo = new JLabel(
                "Elo Atual: " + jogadorAtual.getElo()
        );
        lblElo.setBounds(20, 75, 170, 25);
        lblElo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblElo.setForeground(COLOR_GOLD);

        JLabel lblRota = new JLabel(
                "Rota Principal: " + jogadorAtual.getRotaPrincipal()
        );
        lblRota.setBounds(20, 105, 170, 25);
        lblRota.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblRota.setForeground(COLOR_TEXT_MUTED);

        JLabel lblWinRate = new JLabel(
                String.format("Win Rate: %.1f%%", winRate)
        );
        lblWinRate.setBounds(20, 165, 170, 25);
        lblWinRate.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblWinRate.setForeground(COLOR_ACCENT);

        JLabel lblKda = new JLabel(
                String.format("KDA Médio: %.2f", kdaMedio)
        );
        lblKda.setBounds(20, 205, 170, 25);
        lblKda.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblKda.setForeground(COLOR_GOLD);

        pnlPerfil.add(lblNick);
        pnlPerfil.add(lblElo);
        pnlPerfil.add(lblRota);
        pnlPerfil.add(lblWinRate);
        pnlPerfil.add(lblKda);

        telaResultadoBusca.add(pnlPerfil);
    }

    private void adicionarPainelHistorico() {
        lblLogo = new JLabel("ELO.GG", SwingConstants.CENTER);
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 56));
        lblLogo.setForeground(COLOR_ACCENT);
        lblLogo.setBounds(0, 50, 1280, 65);

        JPanel pnlBusca = new JPanel(null);
        pnlBusca.setBounds(290, 145, 700, 52);
        pnlBusca.setBackground(COLOR_INPUT_BG);
        pnlBusca.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));

        txtBusca = new JTextField(txtSugestao);
        txtBusca.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtBusca.setForeground(COLOR_TEXT_MUTED);
        txtBusca.setBackground(COLOR_INPUT_BG);
        txtBusca.setCaretColor(COLOR_TEXT_PRIMARY);
        txtBusca.setBounds(15, 8, 555, 36);
        txtBusca.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        btnBusca = new JButton(".GG");
        btnBusca.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnBusca.setForeground(Color.WHITE);
        btnBusca.setBackground(COLOR_ACCENT);
        btnBusca.setBounds(580, 6, 114, 40);
        btnBusca.setFocusPainted(false);
        btnBusca.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBusca.setBorder(BorderFactory.createEmptyBorder());
        
        btnVoltar = new JButton("← Voltar");
        btnVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnVoltar.setForeground(COLOR_TEXT_MUTED);
        btnVoltar.setBackground(COLOR_BG);
        btnVoltar.setBounds(15, 665, 95, 30);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVoltar.setBorder(BorderFactory.createEmptyBorder());

        pnlBusca.add(txtBusca);
        pnlBusca.add(btnBusca);

        telaResultadoBusca.add(lblLogo);
        telaResultadoBusca.add(pnlBusca);
        telaResultadoBusca.add(btnVoltar);
    }

    private void criarTabela() {
        JLabel lblSecao = new JLabel("Últimas Partidas Jogadas:");
        lblSecao.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblSecao.setForeground(COLOR_GOLD);
        lblSecao.setBounds(290, 235, 300, 20);

        tblModelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblModelo.addColumn("Partida");
        tblModelo.addColumn("Data");
        tblModelo.addColumn("Hora");
        tblModelo.addColumn("Modo");
        tblModelo.addColumn("Campeão");
        tblModelo.addColumn("Função");
        tblModelo.addColumn("Rota");
        tblModelo.addColumn("K/D/A");
        tblModelo.addColumn("Resultado");
        tblModelo.addColumn("Build");

        tblHistorico = new JTable(tblModelo);
        tblHistorico.setRowHeight(34);
        tblHistorico.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblHistorico.setBackground(COLOR_CARD_BG);
        tblHistorico.setForeground(COLOR_GOLD);
        tblHistorico.setGridColor(COLOR_BORDER);
        tblHistorico.setSelectionBackground(COLOR_ACCENT);
        tblHistorico.setSelectionForeground(Color.WHITE);
        tblHistorico.setShowVerticalLines(false);

        tblHistorico.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 12)
        );
        tblHistorico.getTableHeader().setBackground(COLOR_BORDER);
        tblHistorico.getTableHeader().setForeground(COLOR_TEXT_MUTED);
        tblHistorico.getTableHeader().setReorderingAllowed(false);
        tblHistorico.getTableHeader().setResizingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setBackground(COLOR_CARD_BG);
        centerRenderer.setForeground(COLOR_GOLD);

        for (int i = 0; i < 10; i++) {
            tblHistorico.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(centerRenderer);
        }

        tblHistorico.getColumnModel().getColumn(0).setPreferredWidth(55);
        tblHistorico.getColumnModel().getColumn(1).setPreferredWidth(85);
        tblHistorico.getColumnModel().getColumn(2).setPreferredWidth(65);
        tblHistorico.getColumnModel().getColumn(3).setPreferredWidth(95);
        tblHistorico.getColumnModel().getColumn(4).setPreferredWidth(90);
        tblHistorico.getColumnModel().getColumn(5).setPreferredWidth(75);
        tblHistorico.getColumnModel().getColumn(6).setPreferredWidth(60);
        tblHistorico.getColumnModel().getColumn(7).setPreferredWidth(75);
        tblHistorico.getColumnModel().getColumn(8).setPreferredWidth(85);
        tblHistorico.getColumnModel().getColumn(9).setPreferredWidth(200);

        JScrollPane scrollTabela = new JScrollPane(tblHistorico);
        scrollTabela.setBounds(290, 265, 940, 280);;
        scrollTabela.setBorder( BorderFactory.createLineBorder(COLOR_BORDER, 1));
        scrollTabela.getViewport().setBackground(COLOR_CARD_BG);

        telaResultadoBusca.add(lblSecao);
        telaResultadoBusca.add(scrollTabela);
    }

    public void carregarTabela(int idJogador) {
        tblModelo.setRowCount(0);

        EstatisticaPartidaDAO dao = new EstatisticaPartidaDAO();
        List<EstatisticaPartida> historicoPartidas = dao.buscarUltimasPartidas(idJogador, 10);

        for (EstatisticaPartida partidaAtual : historicoPartidas) {
            String kda = partidaAtual.getKills() + "/"
                    + partidaAtual.getDeaths() + "/"
                    + partidaAtual.getAssists();

            tblModelo.addRow(new Object[]{
                partidaAtual.getPartida().getIdPartida(),
                partidaAtual.getPartida().getData(),
                partidaAtual.getPartida().getHora(),
                partidaAtual.getPartida().getModoDeJogo(),
                partidaAtual.getCampeao().getNomeCampeao(),
                partidaAtual.getCampeao().getFuncaoCampeao(),
                partidaAtual.getCampeao().getRotaCampeao(),
                kda,
                partidaAtual.isVitoria() ? "Vitória" : "Derrota",
            });
        }
    }

    private void adicionarEventos() {
        txtBusca.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtBusca.getText().equals(txtSugestao)) {
                    txtBusca.setText("");
                    txtBusca.setForeground(COLOR_TEXT_PRIMARY);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtBusca.getText().trim().isEmpty()) {
                    txtBusca.setText(txtSugestao);
                    txtBusca.setForeground(COLOR_TEXT_MUTED);
                }
            }
        });

        btnBusca.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnBusca.setBackground(COLOR_ACCENT_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnBusca.setBackground(COLOR_ACCENT);
            }
        });

        btnBusca.addActionListener((ActionEvent e) -> {
            String pesquisa = txtBusca.getText().trim();

            if (pesquisa.equals(txtSugestao) || pesquisa.isEmpty()) {
                JOptionPane.showMessageDialog(
                        telaResultadoBusca,
                        "Digite o Nick + #.\nExemplo: NICK#BR1",
                        "ELO.GG | Campo Obrigatório",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (!pesquisa.contains("#")) {
                JOptionPane.showMessageDialog(
                        telaResultadoBusca,
                        "Use o Formato: NICK#BR1",
                        "ELO.GG | Formato Inválido",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            String[] partes = pesquisa.split("#", 2);
            String nickname = partes[0].trim();
            String hashtag = partes[1].trim();

            JogadorController jogadorController = new JogadorController();
            Jogador jogadorAtual = jogadorController.buscarJogador(nickname, hashtag);

            if (jogadorAtual != null) {
                new TelaResultadoBusca(jogadorAtual);
                telaResultadoBusca.dispose();

            } else {
                JOptionPane.showMessageDialog(
                        telaResultadoBusca,
                        "Jogador Não Encontrado!",
                        "ELO.GG | Não Encontrado",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
            
        tblHistorico.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linhaSelecionada = tblHistorico.getSelectedRow();

                if (linhaSelecionada != -1) {
                    int idPartida = (int) tblModelo.getValueAt(
                            linhaSelecionada,
                            0
                    );
                    new TelaDetalhesPartida(idPartida);
                }
            }
        });
        
        btnVoltar.addActionListener((ActionEvent e) -> {
            new TelaInicial();
            telaResultadoBusca.dispose();
        });
    }
}