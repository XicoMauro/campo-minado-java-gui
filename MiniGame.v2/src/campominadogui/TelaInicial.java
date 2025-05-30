package campominadogui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TelaInicial extends JPanel {
    private JanelaPrincipal janela;
    private JTextField novoJogadorField;
    private JComboBox<String> jogadoresCombo;
    private DefaultComboBoxModel<String> comboModel;
    private JTextArea instrucoesArea;
    private JTextArea placarArea;
    private JButton iniciarBtn, sairBtn, criarBtn;

    public TelaInicial(JanelaPrincipal janela) {
        this.janela = janela;
        setLayout(new BorderLayout());

        JPanel topo = new JPanel(new GridLayout(1, 2, 8, 14));

        // Painel seleção jogador
        JPanel painelJogador = new JPanel(new BorderLayout(2, 2));
        painelJogador.setBorder(BorderFactory.createTitledBorder("Selecione ou crie um jogador"));

        comboModel = new DefaultComboBoxModel<>();
        jogadoresCombo = new JComboBox<>(comboModel);
        atualizarJogadores();

        novoJogadorField = new JTextField();
        criarBtn = new JButton("Criar Jogador");

        criarBtn.addActionListener(e -> criarJogador());

        painelJogador.add(new JLabel("Jogadores:"), BorderLayout.NORTH);
        painelJogador.add(jogadoresCombo, BorderLayout.CENTER);

        JPanel criarPainel = new JPanel(new BorderLayout(5, 5));
        criarPainel.add(new JLabel("Novo jogador:"), BorderLayout.NORTH);
        criarPainel.add(novoJogadorField, BorderLayout.CENTER);
        criarPainel.add(criarBtn, BorderLayout.EAST);

        painelJogador.add(criarPainel, BorderLayout.SOUTH);

        topo.add(painelJogador);

        // Painel instruções
        JPanel painelInstrucoes = new JPanel(new BorderLayout());
        painelInstrucoes.setBorder(BorderFactory.createTitledBorder("Instruções do Jogo"));

instrucoesArea = new JTextArea(
    "🧠 Como Jogar Campo Minado:\n\n" +
    "- Clique com o botão esquerdo do mouse para revelar uma célula.\n" +
    "- Clique com o botão direito para marcar ou desmarcar uma célula suspeita de conter uma mina (🚩).\n" +
    "- Se você revelar uma célula com mina (💣), você perde o jogo.\n" +
    "- Se a célula não tiver mina, será revelado um número indicando quantas minas estão ao redor.\n" +
    "- O objetivo é revelar todas as células sem minas para vencer.\n\n" +
    "🎯 Pontuação:\n" +
    "- Cada célula revelada corretamente vale 1 ponto.\n" +
    "- Se vencer o jogo, você ganha +100 pontos.\n\n" +
    "Boa sorte e jogue com estratégia! 😄"
);
instrucoesArea.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
instrucoesArea.setEditable(false);
instrucoesArea.setOpaque(false); // Para fundo transparente se estiver sobre JPanel
instrucoesArea.setFocusable(false); // Para não receber foco de teclado
;


        painelInstrucoes.add(new JScrollPane(instrucoesArea), BorderLayout.CENTER);

        topo.add(painelInstrucoes);

        add(topo, BorderLayout.CENTER);

        // Painel inferior com botões e placar
        JPanel rodape = new JPanel(new BorderLayout());

        JPanel botoesPainel = new JPanel();
        iniciarBtn = new JButton("Iniciar");
        sairBtn = new JButton("Sair");

        iniciarBtn.addActionListener(e -> iniciarJogo());
        sairBtn.addActionListener(e -> System.exit(0));

        botoesPainel.add(iniciarBtn);
        botoesPainel.add(sairBtn);

        rodape.add(botoesPainel, BorderLayout.NORTH);

        placarArea = new JTextArea(10, 30);
        placarArea.setEditable(false);
        atualizarPlacar();

        rodape.add(new JScrollPane(placarArea), BorderLayout.CENTER);

        add(rodape, BorderLayout.SOUTH);
    }

    private void criarJogador() {
        String nome = novoJogadorField.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome do jogador.");
            return;
        }
        Ranking.criarJogadorSeNaoExistir(nome);
        atualizarJogadores();
        jogadoresCombo.setSelectedItem(nome);
        novoJogadorField.setText("");
        atualizarPlacar();
    }

private void atualizarJogadores() {
    comboModel.removeAllElements();
    List<String> jogadores = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : Ranking.getRanking()) {
        jogadores.add(entry.getKey());
    }
    for (String j : jogadores) {
        comboModel.addElement(j);
    }
}

private void atualizarPlacar() {
    StringBuilder sb = new StringBuilder();
    sb.append("Ranking dos Jogadores:\n\n");
    for (Map.Entry<String, Integer> entry : Ranking.getRanking()) {
        sb.append(String.format("%s: %d pontos\n", entry.getKey(), entry.getValue()));
    }
    placarArea.setText(sb.toString());
}


    private void iniciarJogo() {
        String jogador = (String) jogadoresCombo.getSelectedItem();
        if (jogador == null || jogador.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione ou crie um jogador para iniciar.");
            return;
        }
        janela.iniciarJogo(jogador);
    }
}
