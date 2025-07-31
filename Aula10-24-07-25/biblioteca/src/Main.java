
import Model.DAO.EmprestimoDAO;
import Model.DAO.LivroDAO;
import Model.Aluno;
import Model.DAO.AlunoDAO;
import Model.Emprestimo;
import Model.Livro;

public class Main {
    public static void main(String[] args) {
        AlunoDAO alunoDao = new AlunoDAO();
        LivroDAO livro = new LivroDAO();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

//        System.out.println("\n--- Testando Inserção ---");
        Aluno novoAluno1 = new Aluno("Gabriel", 19, "519374654");
//        alunoDao.setAluno(novoAluno1);
//
        Aluno novoAluno2 = new Aluno("Lucas", 32, "519718620");
//        alunoDao.setAluno(novoAluno2);
//
//        System.out.println("\n--- Testando Listagem ---");
//        List<Aluno> listaAlunos = alunoDao.getAlunos();
//        if(!listaAlunos.isEmpty()){
//            for (Aluno a: listaAlunos){
//                System.out.println(a.getNome());
//            }
//        } else {
//            System.out.println("\n--- Lista Vazia ---");
//        }
//
//        System.out.println("\n--- Testando Atualização ---");
//        Aluno alunoAtt = new Aluno(1, "Joana D'Arc", 19, "182736445");
//        alunoDao.atualizarAluno(alunoAtt);
//        System.out.println("\n--- Listagem Após Atualização ---");
//        alunoDao.getAlunos();
//
//        System.out.println("\n--- Testando Remoção ---");
//        alunoDao.removerAluno(1);
//        System.out.println("\n--- Listagem Após Remoção ---");
//        alunoDao.getAlunos();

        Livro livro1 = new Livro("Império Asteca", "Kumbapiroga", "Autobiografia", "1435dc");
        //livro.setLivro(livro1);

        Livro livro2 = new Livro("A Arte da Guerra", "Sun Tzu", "Autobiografia", "201dc");
        //livro.setLivro(livro2);

        Livro livro3 = new Livro("Times New Roman", "Nero", "Escrita Antiga", "250dc");

        //livro.getLivros();
        //livro.updateLivros(livro2, 1);
        //livro.updateLivros(livro3, 2);
        //livro.getLivros();

        //livro.deletarLivro(livro1, 2);

    }
}