# 🧨 Campo Minado Java (GUI)

Este é um jogo clássico de Campo Minado implementado em Java com interface gráfica utilizando Swing.

## 🎮 Funcionalidades

- Interface gráfica amigável com botões representando as células do campo
- Sistema de marcação de minas com bandeiras (🚩)
- Ranking persistente de jogadores salvo em arquivo
- Sistema de pontuação baseado em desempenho:
  - +1 ponto para cada célula revelada corretamente
  - +100 pontos extras se vencer o jogo
- Tela inicial com instruções, seleção ou criação de jogador
- Opção de reiniciar ou voltar ao menu após o fim do jogo

## 📁 Estrutura do Projeto

- `Main.java` – Ponto de entrada do jogo
- `JanelaPrincipal.java` – Tela inicial e interface principal
- `CampoMinadoGUI.java` – Interface e lógica gráfica do jogo
- `Tabuleiro.java`, `Celula.java` – Lógica do jogo e estrutura do campo
- `Ranking.java` – Sistema de pontuação e persistência em arquivo

## 🧠 Como Jogar

- Clique com o botão **esquerdo** para revelar uma célula.
- Clique com o botão **direito** para marcar ou desmarcar uma bandeira (🚩) onde você suspeita haver uma mina.
- Se você clicar em uma célula com mina (💣), perde o jogo.
- Números nas células reveladas indicam quantas minas estão ao redor.
- Revele todas as células seguras para vencer o jogo!

### 🏆 Sistema de Pontuação

- ✅ Cada célula revelada corretamente: **+1 ponto**
- 🏅 Vitória: **+100 pontos**

## ✅ Requisitos

- Java JDK 8 ou superior
- IDE como NetBeans, IntelliJ, Eclipse **ou** compilar via terminal

## 🚀 Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/campo-minado-java-gui.git
   cd campo-minado-java-gui
