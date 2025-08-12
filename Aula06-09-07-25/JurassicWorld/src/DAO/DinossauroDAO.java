package DAO;

import ConexaoJurassica.Conexao;
import Model.Dinossauro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ConexaoJurassica.Conexao.fecharConexao;

public class DinossauroDAO {

    public void setDinossauro (Dinossauro dino){
        String sql = "INSERT INTO dinossauros (nome_Dinossauro, especie_Dinossauro, dieta_Dinossauro, idade_Dinossauro, idade_Estimada_Dinossauro, status_Cercado) values (?, ?, ?, ?, ?, ?)";
        Connection conexao = Conexao.conectar();
        PreparedStatement stmt = null;

        try{
            conexao = Conexao.conectar();
            if(conexao != null){
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, dino.getNome_Dinossauro());
                stmt.setString(2, dino.getEspecie_Dinossauro());
                stmt.setString(3, dino.getDieta_Dinossauro());
                stmt.setInt(4, dino.getIdade_Dinossauro());
                stmt.setInt(5, dino.getIdade_Estimada_Dinossauro());
                stmt.setString(6, dino.getStatus_Dinossauro());
                int linhasAfetadas = stmt.executeUpdate();
                if(linhasAfetadas > 0){
                    System.out.println("O Dinossauro "+dino.getNome_Dinossauro()+" foi registrado no banco de dados com sucesso!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir Dinossauro no nosso banco de dados: "+ e.getMessage());
        } finally{
            try{
                if(stmt != null) stmt.close();
                if(conexao != null) fecharConexao(conexao);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar o banco de dados: "+ e.getMessage());
            }
        }
    }

    public List<Dinossauro> getDinossauros(){
        String sql = "SELECT * FROM dinossauros";
        Connection conexao = Conexao.conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Dinossauro> listaDino = new ArrayList<>();

        try {
            conexao = Conexao.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                rs = stmt.executeQuery();
                System.out.println("\n--- Dinossauros Cadastrados no Banco de Dados ---");

                while (rs.next()) {

                    int id = rs.getInt("id_Dinossauro");
                    String nome = rs.getString("nome_Dinossauro");
                    String especie = rs.getString("especie_Dinossauro");
                    String dieta = rs.getString("dieta_Dinossauro");
                    String status = rs.getString("status_Cercado");
                    int idadeEstimada = rs.getInt("idade_Estimada_Dinossauro");
                    int idade = rs.getInt("idade_Dinossauro");

                    listaDino.add(new Dinossauro(id, nome, especie, dieta, status, idade, idadeEstimada));
                }
                System.out.println("--------------------------------");
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

        return listaDino;
    }

    public void atualizarDinossauro(Dinossauro dino){
        String sql = "UPDATE dinossauros SET nome_Dinossauro = ?, especie_Dinossauro = ?, dieta_Dinossauro = ?, idade_Dinossauro = ?, idade_Estimada_Dinossauro = ?, status_Cercado = ? WHERE id_Dinossauro = ?";
        Connection conexao = Conexao.conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = Conexao.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt = conexao.prepareStatement(sql);
                String nome = rs.getString("nome_Dinossauro");
                String especie = rs.getString("especie_Dinossauro");
                String dieta = rs.getString("dieta_Dinossauro");
                String status = rs.getString("status_Cercado");
                int idadeEstimada = rs.getInt("idade_Estimada_Dinossauro");
                int idade = rs.getInt("idade_Dinossauro");
                int id = rs.getInt("id_Dinossauro");
                int linhasAfetadas = stmt.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Dinossauro com ID " + id + " atualizado com sucesso!");
                } else {
                    System.out.println("Nenhum Dinossauro com o ID " + id + " foi encontrado");
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
    }

    public void removerDinossauro(int id){
        String sql = "DELETE FROM dinossauros WHERE id_Dinossauro = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = Conexao.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, id);
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Dinossauro com ID "+ id +" deletado com sucesso!");
                } else {
                    System.out.println("Nenhum Dinossauro encontrado com ID "+id);
                }
            }
        } catch (SQLException error) {
            System.err.println("Erro ao inserir o Dinossauro: " + error.getMessage());
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
