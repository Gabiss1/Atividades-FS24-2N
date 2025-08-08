package Controller;

import Model.DAO.PrimaryUserDAO;
import Model.PrimaryUser;

public class UserController {
    PrimaryUserDAO userDAO;

    public UserController() {this.userDAO = new PrimaryUserDAO();}

    public void createCpfUser(String name, String email, String address, String contact, String birth_date, String cpf) throws Exception{
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("O nome é obrigatório.");
        }
        if (String.valueOf(idade).trim().isEmpty() || idade < 0 || idade > 150) {
            throw new Exception("Idade é obrigatória e deve ser válida!");
        }
        if (contato == null || contato.trim().isEmpty()) {
            throw new Exception("Contato é obrigatório");
        }

        PrimaryUser user = new PrimaryUser(name, email, address, contact);
        userDAO.setUsers(user);
    }

    public void createInstitute(String name, String email, String address, String contact, String cnpj) throws Exception{
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("O nome é obrigatório.");
        }
        if (String.valueOf(idade).trim().isEmpty() || idade < 0 || idade > 150) {
            throw new Exception("Idade é obrigatória e deve ser válida!");
        }
        if (contato == null || contato.trim().isEmpty()) {
            throw new Exception("Contato é obrigatório");
        }

        Aluno aluno = new Aluno(nome, idade, contato);
        userDAO.
    }
}
