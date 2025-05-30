package campominadogui;

import java.io.Serializable;

public class Celula implements Serializable {
    public boolean temMina = false;
    public boolean revelada = false;
    public boolean marcada = false;
    public int minasAdjacentes = 0;
}
