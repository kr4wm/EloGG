package view;

import controller.JogadorController;
import dao.CampeaoDAO;
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
import model.Campeao;
import model.Jogador;

public class TelaInicial {

    private final JFrame telaInicial;

    private JLabel lblLogo;
    private JTextField txtBusca;
    private JButton btnBusca;

    private JTable tblCampeao;
    private DefaultTableModel tblModelo;

    private final String txtSugestao = "Nick + #BR1";

    private final Color COLOR_BG = new Color(24, 25, 29);
    private final Color COLOR_CARD_BG = new Color(33, 36, 44);
    private final Color COLOR_INPUT_BG = new Color(40, 43, 53);
    private final Color COLOR_ACCENT = new Color(83, 131, 255);
    private final Color COLOR_ACCENT_HOVER = new Color(105, 148, 255);
    private final Color COLOR_TEXT_PRIMARY = new Color(240, 243, 255);
    private final Color COLOR_TEXT_MUTED = new Color(130, 136, 155);
    private final Color COLOR_GOLD = new Color(229, 185, 94);
    private final Color COLOR_BORDER = new Color(45, 49, 62);

    public TelaInicial() {
        this.telaInicial = new JFrame();

        inicializarJanela();
        adicionarPainelCampeoes();
        criarTabela();
        carregarTabela();
        adicionarEventos();

        telaInicial.setVisible(true);
    }

    private void inicializarJanela() {
        telaInicial.setTitle("Elo.GG | As Melhores Stats");
        telaInicial.setSize(1280, 768);
        telaInicial.setResizable(false);
        telaInicial.setLocationRelativeTo(null);
        telaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaInicial.setLayout(null);
        telaInicial.getContentPane().setBackground(COLOR_BG);
    }

    private void adicionarPainelCampeoes() {
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

        pnlBusca.add(txtBusca);
        pnlBusca.add(btnBusca);

        telaInicial.add(lblLogo);
        telaInicial.add(pnlBusca);
    }

    private void criarTabela() {
        JLabel lblSecao = new JLabel("Top 5 Campeões do Patch");
        lblSecao.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblSecao.setForeground(COLOR_GOLD);
        lblSecao.setBounds(290, 235, 300, 20);

        tblModelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblModelo.addColumn("Posição");
        tblModelo.addColumn("Campeão");
        tblModelo.addColumn("Função");
        tblModelo.addColumn("Rota");

        tblCampeao = new JTable(tblModelo);
        tblCampeao.setRowHeight(34);
        tblCampeao.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblCampeao.setBackground(COLOR_CARD_BG);
        tblCampeao.setForeground(COLOR_GOLD);
        tblCampeao.setGridColor(COLOR_BORDER);
        tblCampeao.setSelectionBackground(COLOR_ACCENT);
        tblCampeao.setSelectionForeground(Color.WHITE);
        tblCampeao.setShowVerticalLines(false);

        tblCampeao.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblCampeao.getTableHeader().setBackground(COLOR_BORDER);
        tblCampeao.getTableHeader().setForeground(COLOR_TEXT_MUTED);
        tblCampeao.getTableHeader().setReorderingAllowed(false);
        tblCampeao.getTableHeader().setResizingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setBackground(COLOR_CARD_BG);
        centerRenderer.setForeground(COLOR_GOLD);

        tblCampeao.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblCampeao.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblCampeao.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        leftRenderer.setBackground(COLOR_CARD_BG);
        leftRenderer.setForeground(COLOR_GOLD);
        tblCampeao.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);

        tblCampeao.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblCampeao.getColumnModel().getColumn(1).setPreferredWidth(250);
        tblCampeao.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblCampeao.getColumnModel().getColumn(3).setPreferredWidth(200);

        JScrollPane scrollTabela = new JScrollPane(tblCampeao);
        scrollTabela.setBounds(290, 265, 700, 200);
        scrollTabela.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
        scrollTabela.getViewport().setBackground(COLOR_CARD_BG);

        telaInicial.add(lblSecao);
        telaInicial.add(scrollTabela);
    }

    public void carregarTabela() {
        tblModelo.setRowCount(0);

        CampeaoDAO dao = new CampeaoDAO();
        List<Campeao> tierList = dao.buscarTodos();

        int limite = Math.min(tierList.size(), 5);
        
        for (int i = 0; i < limite; i++) {
            Campeao campeao = tierList.get(i);
            tblModelo.addRow(new Object[]{
                campeao.getIdCampeao(),
                campeao.getNomeCampeao(),
                campeao.getFuncaoCampeao(),
                campeao.getRotaCampeao()
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
                        telaInicial,
                        "Digite o Nick + #.\nExemplo: NICK#BR1",
                        "ELO.GG | Campo Obrigatório",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (!pesquisa.contains("#")) {
                JOptionPane.showMessageDialog(
                        telaInicial,
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
                telaInicial.dispose();
                
            } else {
                JOptionPane.showMessageDialog(
                        telaInicial,
                        "Jogador Não Encontrado!",
                        "ELO.GG | Não Encontrado",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }
}