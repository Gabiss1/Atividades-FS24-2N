package Controller;

import Model.Aluno;
import Model.DAO.AlunoDAO;
import Model.DAO.LivroDAO;

public class LivroController {
    private LivroDAO livroDAO;

    public LivroController(AlunoDAO alunoDAO) {
        this.livroDAO = livroDAO;
    }
    public void cadastrarAluno(String nome, int idade, String contato) throws Exception{
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("O nome é obrigatório.");
        }
        if (String.valueOf(idade).trim().isEmpty()) {
            throw new Exception("Idade é obrigatório, caso não tenha saia daqui!");
        }
        if (contato == null || contato.trim().isEmpty()) {
            throw new Exception("Contato é obrigatório");
        }

        Aluno aluno = new Aluno(nome, idade, contato);
        //livroDAO.setLivro(aluno);
    }
}
