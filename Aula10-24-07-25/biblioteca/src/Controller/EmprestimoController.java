package Controller;

import Model.Emprestimo;
import Model.DAO.EmprestimoDAO;
import Model.Emprestimo;
import Model.Emprestimo;

import java.util.List;

public class EmprestimoController {
    EmprestimoDAO emprestimoDAO;

    public EmprestimoController(EmprestimoDAO emprestimo){
        this.emprestimoDAO = emprestimo;
    }

    public void cadastrarEmprestimo(int fk_id_livro, int fk_id_aluno, String data_emprestimo, String data_devolucao) throws Exception{
        if (String.valueOf(fk_id_aluno).trim().isEmpty()) {
            throw new Exception("O nome é obrigatório.");
        }
        if (String.valueOf(fk_id_livro).trim().isEmpty()) {
            throw new Exception("Idade é obrigatório, caso não tenha saia daqui!");
        }
        if (data_emprestimo == null || data_emprestimo.trim().isEmpty()) {
            throw new Exception("Contato é obrigatório");
        }

        if (data_devolucao == null || data_devolucao.trim().isEmpty()) {
            throw new Exception("Contato é obrigatório");
        }

        Emprestimo emprestimo = new Emprestimo(fk_id_livro, fk_id_aluno, data_emprestimo, data_devolucao);
        emprestimoDAO.setEmprestimo(emprestimo);
    }

    public void atualizarEmprestimo(int fk_id_livro, int fk_id_aluno, String data_emprestimo, String data_devolucao) throws Exception {
        if (String.valueOf(fk_id_aluno).trim().isEmpty() || String.valueOf(fk_id_livro).trim().isEmpty() || data_emprestimo == null || data_emprestimo.trim().isEmpty() || data_devolucao == null || data_devolucao.trim().isEmpty()) {
            throw new Exception("Todos os campos do dinossauro são obrigatórios e devem ser válidos.");
        }

        Emprestimo emprestimo = new Emprestimo(fk_id_livro, fk_id_aluno, data_emprestimo, data_devolucao);
        emprestimoDAO.setEmprestimo(emprestimo);
    }

    public List<Emprestimo> listarEmprestimos(){
        return emprestimoDAO.getEmprestimos();
    }

    public Emprestimo getEmprestimoById(int id){
        return emprestimoDAO.getEmprestimoByID(id);
    }

    public void removerEmprestimo(int id){
        emprestimoDAO.deletarEmprestimo(id);
    }
}
