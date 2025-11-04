package br.edu.fatec.cepbanco;

import br.edu.fatec.cepbanco.model.Endereco;
import br.edu.fatec.cepbanco.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CepBancoApplication implements CommandLineRunner {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CepBancoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(">>> INICIANDO CADASTRO DE ENDEREÇOS <<<");

        var enderecos = Arrays.asList(
                new Endereco("01001-000", "Praça da Sé", "Sé", "São Paulo", "SP"),
                new Endereco("20040-010", "Rua da Quitanda", "Centro", "Rio de Janeiro", "RJ"),
                new Endereco("30190-000", "Av. Afonso Pena", "Centro", "Belo Horizonte", "MG"),
                new Endereco("01001-000", "Duplicado", "Teste", "São Paulo", "SP") // Teste duplicado
        );

        for (Endereco e : enderecos) {
            enderecoRepository.findByCep(e.getCep()).ifPresentOrElse(
                    existente -> System.out.println("Endereço já cadastrado: " + existente),
                    () -> {
                        enderecoRepository.save(e);
                        System.out.println("Novo endereço salvo: " + e);
                    }
            );
        }

        System.out.println("\n>>> LISTANDO TODOS OS ENDEREÇOS <<<");
        enderecoRepository.findAll().forEach(System.out::println);

        System.out.println(">>> FIM DA EXECUÇÃO <<<");
    }
}
