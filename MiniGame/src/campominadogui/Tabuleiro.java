package campominadogui;

import java.util.Random;

public class Tabuleiro {
    public Celula[][] celulas;
    private int linhas, colunas, minas;

    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;
        celulas = new Celula[linhas][colunas];
        inicializar();
    }

    private void inicializar() {
        for (int i = 0; i < linhas; i++)
            for (int j = 0; j < colunas; j++)
                celulas[i][j] = new Celula();

        colocarMinas();
        calcularMinasAdjacentes();
    }

    private void colocarMinas() {
        Random rand = new Random();
        int colocadas = 0;
        while (colocadas < minas) {
            int x = rand.nextInt(linhas);
            int y = rand.nextInt(colunas);
            if (!celulas[x][y].temMina) {
                celulas[x][y].temMina = true;
                colocadas++;
            }
        }
    }

    private void calcularMinasAdjacentes() {
        for (int i = 0; i < linhas; i++)
            for (int j = 0; j < colunas; j++) {
                int count = 0;
                for (int dx = -1; dx <= 1; dx++)
                    for (int dy = -1; dy <= 1; dy++) {
                        int nx = i + dx;
                        int ny = j + dy;
                        if (nx >= 0 && ny >= 0 && nx < linhas && ny < colunas)
                            if (celulas[nx][ny].temMina)
                                count++;
                    }
                celulas[i][j].minasAdjacentes = count;
            }
    }

    public void marcar(int x, int y) {
        Celula c = celulas[x][y];
        if (!c.revelada) c.marcada = !c.marcada;
    }

    public boolean revelar(int x, int y) {
        Celula c = celulas[x][y];
        if (c.marcada || c.revelada) return true;
        c.revelada = true;
        if (c.temMina) return false;
        if (c.minasAdjacentes == 0) {
            // revelar adjacentes automaticamente
            for (int dx = -1; dx <= 1; dx++)
                for (int dy = -1; dy <= 1; dy++) {
                    int nx = x + dx;
                    int ny = y + dy;
                    if (nx >= 0 && ny >= 0 && nx < linhas && ny < colunas)
                        if (!celulas[nx][ny].revelada)
                            revelar(nx, ny);
                }
        }
        return true;
    }

    public boolean venceu() {
        for (int i = 0; i < linhas; i++)
            for (int j = 0; j < colunas; j++) {
                Celula c = celulas[i][j];
                if (!c.temMina && !c.revelada)
                    return false;
            }
        return true;
    }
}
