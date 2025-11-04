package br.edu.fatec.demoaluno;

import br.edu.fatec.demoaluno.model.Aluno;
import br.edu.fatec.demoaluno.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoAlunoApplication implements CommandLineRunner {

    @Autowired
    private AlunoRepository alunoRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoAlunoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(">>> INICIANDO DEMONSTRAÇÃO <<<");

        Aluno aluno = new Aluno("Alessandro", "12345678900", "alessandro@fatec.com");
        alunoRepository.save(aluno);

        System.out.println("Alunos cadastrados:");
        List<Aluno> alunos = alunoRepository.findAll();
        alunos.forEach(System.out::println);

        System.out.println(">>> DEMONSTRAÇÃO FINALIZADA <<<");
    }
}
