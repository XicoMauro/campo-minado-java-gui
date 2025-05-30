package campominadogui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CampoMinadoGUI extends JPanel {
    private final int linhas = 8;
    private final int colunas = 8;
    private final int minas = 10;

    private final Tabuleiro tabuleiro;
    private final JButton[][] botoes;
    private final JanelaPrincipal janela;
    private final String jogador;

    public CampoMinadoGUI(JanelaPrincipal janela, String jogador) {
        this.janela = janela;
        this.jogador = jogador;

        setLayout(new GridLayout(linhas, colunas));
        tabuleiro = new Tabuleiro(linhas, colunas, minas);
        botoes = new JButton[linhas][colunas];

        inicializarBotoes();
        atualizarBotoes();
    }

    private void inicializarBotoes() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                final int x = i;
                final int y = j;

                JButton botao = new JButton();
                botao.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));
                botao.setFocusPainted(false);

                botao.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            tabuleiro.marcar(x, y);
                        } else {
                            boolean ok = tabuleiro.revelar(x, y);
                            if (!ok) {
                                atualizarBotoes();
                                perderJogo();
                                return;
                            }
                        }
                        atualizarBotoes();
                        if (tabuleiro.venceu()) {
                            Ranking.adicionarPonto(jogador);
                            atualizarBotoes();
                            ganharJogo();
                        }
                    }
                });

                botoes[i][j] = botao;
                add(botao);
            }
        }
    }

    private void atualizarBotoes() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                Celula c = tabuleiro.celulas[i][j];
                JButton b = botoes[i][j];

                if (c.marcada) {
                    b.setText("🚩");
                    b.setBackground(Color.YELLOW);
                } else if (!c.revelada) {
                    b.setText("");
                    b.setBackground(null);
                } else if (c.temMina) {
                    b.setText("💣");
                    b.setBackground(Color.RED);
                } else {
                    b.setText(c.minasAdjacentes == 0 ? "" : String.valueOf(c.minasAdjacentes));
                    b.setBackground(Color.LIGHT_GRAY);
                }
            }
        }
    }

    private void perderJogo() {
    // Forçar fonte com suporte a emoji
    UIManager.put("OptionPane.messageFont", new Font("Segoe UI Emoji", Font.PLAIN, 18));
    UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.PLAIN, 14));
    int pontos = tabuleiro.contarCelulasReveladas();
    Ranking.adicionarPontuacao(jogador, pontos);

    atualizarBotoes(); // mostra todas as bombas após perder

    int op = JOptionPane.showOptionDialog(this,
            "\uD83D\uDCA5 Você perdeu!\nCélulas reveladas: " + pontos + "\nDeseja jogar novamente?",
            "Fim de jogo",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.ERROR_MESSAGE,
            null,
            new String[]{"Reiniciar", "Voltar ao Início"},
            "Reiniciar");

    if (op == JOptionPane.YES_OPTION) {
        janela.iniciarJogo(jogador);
    } else {
        janela.mostrarTelaInicial();
    }
}

private void ganharJogo() {
    UIManager.put("OptionPane.messageFont", new Font("Segoe UI Emoji", Font.PLAIN, 18));
    UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.PLAIN, 14));
    int pontos = tabuleiro.contarCelulasReveladas() + 100; // bônus por vencer
    Ranking.adicionarPontuacao(jogador, pontos);

    atualizarBotoes();

    int op = JOptionPane.showOptionDialog(this,
            "\uD83C\uDF89 Parabéns, " + jogador + "! Você venceu!\nPontuação: " + pontos + "\nDeseja jogar novamente?",
            "Vitória",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new String[]{"Jogar Novamente", "Voltar ao Início"},
            "Jogar Novamente");

    if (op == JOptionPane.YES_OPTION) {
        janela.iniciarJogo(jogador);
    } else {
        janela.mostrarTelaInicial();
    }
}


}
