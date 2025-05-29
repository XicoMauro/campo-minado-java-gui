package campominadogui;

import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {
    private TelaInicial telaInicial;
    private CampoMinadoGUI jogo;

    public JanelaPrincipal() {
        setTitle("Campo Minado");
        setSize(600, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    public void mostrarTelaInicial() {
        if (jogo != null) {
            remove(jogo);
            jogo = null;
        }
        telaInicial = new TelaInicial(this);
        setContentPane(telaInicial);
        revalidate();
        repaint();
        setVisible(true);
    }

    public void iniciarJogo(String jogador) {
        if (telaInicial != null) {
            remove(telaInicial);
            telaInicial = null;
        }
        jogo = new CampoMinadoGUI(this, jogador);
        setContentPane(jogo);
        revalidate();
        repaint();
    }
}
