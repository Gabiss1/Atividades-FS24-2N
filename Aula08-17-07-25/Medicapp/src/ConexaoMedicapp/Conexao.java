package ConexaoMedicapp;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Conexao {
    private static String URL = "jdbc:postgresql://localhost:5432/";
    private static final String usuario = "postgres";
    private static final String senha = "root";

    public static Connection conectar() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(URL, usuario, senha);
            System.out.println("Conectado com sucesso!");
        } catch (SQLException error) {
            System.err.println("Erro ao conectar com o banco de dados: " + error.getMessage());

        } return conexao;
    }

    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexao fechada com sucesso!");
            } catch (SQLException error) {
                System.err.println("Erro ao fechar conexao: " + error.getMessage());
            }
        }
    }

    public static void main(String[] args){
        String nomeDatabase = "s"; // Nome do banco de dados a ser criado

        try (Connection connection = DriverManager.getConnection(URL, usuario, senha)) {
            Statement statement = connection.createStatement();
            String sql = "CREATE DATABASE " + nomeDatabase;
            statement.executeUpdate(sql);
            System.out.println("Banco de dados criado com sucesso: " + nomeDatabase);

        } catch (SQLException e) {
            System.err.println("Erro ao criar o banco de dados: " + e.getMessage());
        }
        Connection testeConexao = Conexao.conectar();
        if (testeConexao != null) {
            Conexao.fecharConexao(testeConexao);
        }

        //System.out.println("\n--- Conectado com sucesso! ---");
        //setUsers("Gabriel", "gabi@gmail.com", "1827-4765", "984930078", "15/07/2006");
        //setUsers("Lucca", "lucca@gmail.com", "7263-0219", "984933798", "20/04/2003");


        //System.out.println("\n--- Conectado com sucesso! ---");
        //getUsers();
        createTables();
    }

    public static void createTables(){
        String tableUser = "CREATE TABLE Primary_UserTest IF NOT EXISTS (" +
                "    User_ID BIGSERIAL PRIMARY KEY NOT NULL," +
                "    User_Name VARCHAR(255) NOT NULL," +
                "    User_Email VARCHAR(255) NOT NULL," +
                "    User_Address VARCHAR(255) NOT NULL," +
                "    User_Contact VARCHAR(255) NOT NULL," +
                "    User_Birth_Date DATE NOT NULL" +
                ")";
        String tableMedication = "CREATE TABLE MedicationTest IF NOT EXISTS(" +
                "    Medication_ID BIGSERIAL PRIMARY KEY NOT NULL," +
                "    Medication_Name VARCHAR(255) NOT NULL," +
                "    Medication_Category VARCHAR(255) NOT NULL," +
                "    Dosage VARCHAR(255) NOT NULL," +
                "    Time_Of_Usage VARCHAR(255) NOT NULL," +
                "    Medication_Pharmaceutical_Form VARCHAR(20) NOT NULL" +
                ")";
        PreparedStatement stmt = null;

        try (Connection conexao = DriverManager.getConnection(URL, usuario, senha);
             Statement statement = conexao.createStatement()) {

            statement.execute(tableUser);
            statement.execute(tableUser);
            System.out.println("Tabela criada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    public static void setUsers(String name, String email, String address, String contact, String birth_date){
        String sql = "INSERT INTO Primary_User (User_Name, User_Email, User_Address, User_Contact, User_Birth_Date) VALUES (?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = Conexao.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, address);
                stmt.setString(4, contact);
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date date = (Date) formato.parse(birth_date);
                stmt.setDate(5, date);

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Aluno "+ name + " foi adicionado com sucesso!");
                }
            }
        } catch (SQLException | ParseException error) {
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

    public static void getUsers(){
        String sql = "SELECT * FROM Prymary_User ORDER BY User_ID";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = Conexao.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                rs = stmt.executeQuery();
                System.out.println("\n--- Alunos cadastrados no BD ---");
                Boolean encontrouAluno = false;
                while (rs.next()) {
                    encontrouAluno = true;
                    int id = rs.getInt("User_ID");
                    String name = rs.getString("User_Name");
                    String email = rs.getString("User_Email");
                    String address = rs.getString("User_Address");
                    String contact = rs.getString("User_Contact");
                    Date birth_date = rs.getDate("User_Birth_Date");
                    System.out.println("ID: "+id + ", Name: " + name + ", Email: " + email + ", Address: " + address+
                            ", Contact: " + contact + ", Birth Date: " + birth_date);
                }
                if (!encontrouAluno) {
                    System.out.println("Nenhum aluno encontrado");
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
    }
}
