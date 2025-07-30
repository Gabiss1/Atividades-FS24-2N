package Controller;

import Model.DAO.EmprestimoDAO;
import Model.Emprestimo;

import java.util.List;

public class EmprestimoController {
    EmprestimoDAO emprestimoDAO;

    public EmprestimoController(EmprestimoDAO emprestimo){
        this.emprestimoDAO = emprestimo;
    }
    public List<Emprestimo> listarEmprestimos(){
        return emprestimoDAO.getEmprestimos();
    }

    public void removerEmprestimo(int id_livro, int id_aluno){
        emprestimoDAO.deletarEmprestimo(id_aluno, id_livro);
    }
}
