package Model.DAO;

import Conexao.ConexaoPostgresDB;
import Model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Conexao.ConexaoPostgresDB.fecharConexao;

public class AlunoDAO {
    public void setAluno(Aluno aluno){
        String sql = "INSERT INTO aluno (nome_Aluno, idade_Aluno, contato_Aluno ) VALUES (?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoPostgresDB.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, aluno.getNome());
                stmt.setInt(2, aluno.getIdade());
                stmt.setString(3, aluno.getContato());
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Aluno "+ aluno.getNome() + " foi adicionado com sucesso!");
                }
            }
        } catch (SQLException error) {
            System.err.println("Erro ao inserir o aluno: " + error.getMessage());
        } finally {
            try{
                if (stmt != null) stmt.close();
                if(conexao != null) fecharConexao(conexao);
            } catch (SQLException error) {
                System.err.println("Erro ao fechar conexao: " + error.getMessage());
            }
        }
    }

    public List<Aluno> getAlunos(){
        String sql = "SELECT * FROM aluno ORDER BY id_Aluno";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aluno> alunos = new ArrayList<>();
        try {
            conexao = ConexaoPostgresDB.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                rs = stmt.executeQuery();
                System.out.println("\n--- Alunos cadastrados no BD ---");
                while (rs.next()) {
                    int id = rs.getInt("id_Aluno");
                    String nome = rs.getString("nome_Aluno");
                    int idade = rs.getInt("idade_Aluno");
                    String contato = rs.getString("contato_Aluno");

                    alunos.add(new Aluno(id, nome, idade, contato));
                }
            }
        } catch(SQLException error){
            System.out.println("Erro ao conectar com o banco de dados: " + error.getMessage());
        } finally {
            try{
                if (rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conexao != null) fecharConexao(conexao);
            } catch(SQLException error){
                System.err.println("Erro ao fechar recursos após pesquisa: " + error.getMessage());
            }
        }
        return alunos;
    }

    public void atualizarAluno(Aluno aluno, int id){
        String sql = "UPDATE aluno SET nome_Aluno = ?, idade_Aluno = ?, contato_Aluno = ? WHERE id_Aluno = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = ConexaoPostgresDB.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, aluno.getNome());
                stmt.setInt(2, aluno.getIdade());
                stmt.setString(3, aluno.getContato());
                stmt.setInt(4, id);
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Aluno com ID "+ id +" atualizado com sucesso!");
                } else {
                    System.out.println("Nenhum aluno com o ID "+ id +" foi encontrado");
                }
            }
        } catch (SQLException error) {
            System.err.println("Erro ao inserir o aluno: " + error.getMessage());
        } finally {
            try{
                if(stmt != null) stmt.close();
                if(conexao != null) fecharConexao(conexao);
            }
            catch(SQLException error){
                System.err.println("Erro ao fechar conexao: " + error.getMessage());}
        }
    }

    public void removerAluno(int id){
        String sql = "DELETE FROM aluno WHERE id_Aluno = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = ConexaoPostgresDB.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, id);
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Aluno com ID "+ id +" deletado com sucesso!");
                } else {
                    System.out.println("Nenhum aluno encontrado com ID "+id);
                }
            }
        } catch (SQLException error) {
            System.err.println("Erro ao inserir o aluno: " + error.getMessage());
        } finally {
            try{
                if(stmt != null) stmt.close();
                if(conexao != null) fecharConexao(conexao);
            } catch(SQLException error){
                System.out.println("Erro ao fechar conexao: " + error.getMessage());
            }
        }
    }
}