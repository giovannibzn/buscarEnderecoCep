import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ViaCepService viaCepService = new ViaCepService();
        List<Endereco> listaEnderecos = new ArrayList<>(); // Lista para armazenar os endereços

        while (true) {
            System.out.print("Digite o CEP para busca (ou 'sair' para encerrar): ");
            String cep = scanner.nextLine();

            if (cep.equalsIgnoreCase("sair")) {
                break;
            }

            try {
                Endereco endereco = viaCepService.buscarEnderecoPorCep(cep);

                if (endereco != null) {
                    listaEnderecos.add(endereco); // Adiciona o endereço à lista
                    System.out.println("Endereço adicionado à lista:");
                    System.out.println("Logradouro: " + endereco.getLogradouro());
                    System.out.println("Bairro: " + endereco.getBairro());
                    System.out.println("Cidade: " + endereco.getLocalidade());
                    System.out.println("Estado: " + endereco.getUf());
                } else {
                    System.out.println("CEP não encontrado!");
                }
            } catch (Exception e) {
                System.out.println("Erro ao buscar o endereço: " + e.getMessage());
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("enderecos.json")) {
            gson.toJson(listaEnderecos, writer);
            System.out.println("Lista de endereços salva em 'enderecos.json'.");
        } catch (IOException e) {
            e.printStackTrace();        }

        scanner.close();
    }
}