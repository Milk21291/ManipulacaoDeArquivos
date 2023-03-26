import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

// Pasta de destino
            System.out.println("Digite o caminho completo da pasta de destino:");
            String folderPath = scanner.nextLine();

// Cria um arquivo na pasta de destino
            File file = new File(folderPath, "dados.txt");
            try {
                FileWriter writer = new FileWriter(file);
                writer.write("Dados a serem salvos");
                writer.close();
                System.out.println("Dados salvos com sucesso em " + file.getAbsolutePath());

                // Solicita ao usuário se deseja Editar, Deletar ou Sair
                boolean continuar = true;
                while (continuar) {
                    System.out.println("O que deseja fazer? (E = Editar / D = Deletar / S = Sair)");
                    String resposta = scanner.nextLine();
                    if (resposta.equalsIgnoreCase("E")) {
                        // Lê o conteúdo do arquivo
                        System.out.println("Digite os novos dados:");
                        String novosDados = scanner.nextLine();
                        Scanner fileScanner = new Scanner(novosDados);
                        String dadosAntigos = fileScanner.nextLine();
                        fileScanner.close();
                        if (folderPath.equalsIgnoreCase(dadosAntigos)) {
                            System.out.println("Arquivo igual ao anterior, tente novamente!");
                        } else {
                            // Escreve os novos dados no arquivo
                            FileWriter writerNovosDados = new FileWriter(file);
                            writerNovosDados.write(novosDados);
                            writerNovosDados.close();
                            System.out.println("Dados salvos com sucesso em " + file.getAbsolutePath());

                            // Pergunta ao usuário se deseja continuar a editar
                            System.out.println("Deseja continuar a editar? (S = Sim / N = Não)");
                            String respostaContinuar = scanner.nextLine();
                            if (respostaContinuar.equalsIgnoreCase("N")) {
                                continuar = false;
                            }
                        }
                    } else if (resposta.equalsIgnoreCase("D")) {
                        // Deleta o arquivo
                        if (file.delete()) {
                            System.out.println("Arquivo deletado com sucesso!");
                            continuar = false;
                        } else {
                            System.out.println("Não foi possível deletar o arquivo, tente novamente!");
                        }
                    } else if (resposta.equalsIgnoreCase("S")) {
                        continuar = false;
                    }
                }
            } catch (IOException e) {
                // Mostra ao usuário o que tem de errado
                System.out.println("Erro ao salvar dados: " + e.getMessage());
            }
    }
}