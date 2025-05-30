package campominadogui;

import java.io.*;
import java.util.*;

public class Ranking {
    private static Map<String, Integer> placar = new HashMap<>();
    private static final String ARQUIVO = "ranking.dat";

    static {
        carregar();
    }

    public static void adicionarPontuacao(String jogador, int pontos) {
        placar.put(jogador, placar.getOrDefault(jogador, 0) + pontos);
        salvar();
    }

    public static void criarJogadorSeNaoExistir(String jogador) {
        if (!placar.containsKey(jogador)) {
            placar.put(jogador, 0);
            salvar();
        }
    }

    public static List<Map.Entry<String, Integer>> getRanking() {
        List<Map.Entry<String, Integer>> lista = new ArrayList<>(placar.entrySet());
        lista.sort((a, b) -> b.getValue() - a.getValue());
        return lista;
    }

    private static void salvar() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            out.writeObject(placar);
        } catch (IOException e) {
            System.err.println("Erro ao salvar ranking: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void carregar() {
        File file = new File(ARQUIVO);
        if (!file.exists()) return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            placar = (Map<String, Integer>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar ranking: " + e.getMessage());
        }
    }

    static void adicionarPonto(String jogador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
