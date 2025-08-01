package View;

import Controller.EmprestimoController;
import Model.DAO.AlunoDAO;
import Model.DAO.LivroDAO;
import Model.Emprestimo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaEmprestimos extends JInternalFrame {

    private EmprestimoController controller;
    private JTable tabelaEmprestimos;
    private DefaultTableModel tableModel;
    private JButton btnAtualizar, btnRemover, btnBuscarPorAluno, btnBuscarPorLivro;
    private JTextField txtBuscaNomeAluno, txtBuscaTituloLivro;

    public ListaEmprestimos(EmprestimoController controller) { // Altere o tipo do parâmetro
        super("Lista de Emprestimos", true, true, true, true);
        this.controller = controller; // Atribui o novo controller

        setSize(850, 500);
        setLayout(new BorderLayout());

        String[] colunas = {"ID", "ID do Livro", "ID do Aluno", "Data do Empréstimo", "Data de Devolução"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaEmprestimos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaEmprestimos);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtBuscaNomeAluno = new JTextField(5);
        txtBuscaTituloLivro = new JTextField(5);
        btnBuscarPorAluno = new JButton("Buscar por Nome Aluno");
        btnBuscarPorLivro = new JButton("Buscar por Título Livro");
        btnAtualizar = new JButton("Atualizar Tabela");
        btnRemover = new JButton("Remover Selecionado");

        panelAcoes.add(new JLabel("Nome do Aluno:"));
        panelAcoes.add(txtBuscaNomeAluno);
        panelAcoes.add(btnBuscarPorAluno);
        panelAcoes.add(new JLabel("Título do Livro:"));
        panelAcoes.add(txtBuscaTituloLivro);
        panelAcoes.add(btnBuscarPorLivro);
        panelAcoes.add(btnAtualizar);
        panelAcoes.add(btnRemover);
        add(panelAcoes, BorderLayout.NORTH);

        btnAtualizar.addActionListener(e -> carregarEmprestimosNaTabela());
        btnRemover.addActionListener(e -> removerEmprestimoSelecionado());
        btnBuscarPorAluno.addActionListener(e -> buscarEmprestimosPorNomeAluno());
        btnBuscarPorLivro.addActionListener(e -> buscarEmprestimosPorTituloLivro());

    }

    private void carregarEmprestimosNaTabela() {
        tableModel.setRowCount(0); // Limpa as linhas existentes na tabela
        java.util.List<Emprestimo> emprestimos = controller.listarEmprestimos(); // Busca todos os emprestimos
        for (Emprestimo emprestimo : emprestimos) {
            // Adiciona cada emprestimo como uma nova linha na tabela
            tableModel.addRow(new Object[]{
                    emprestimo.getId_emprestimo(),
                    emprestimo.getFk_livro(),
                    emprestimo.getFk_aluno(),
                    emprestimo.getData_emprestimo(),
                    emprestimo.getData_devolucao()
            });
        }
    }

    private void removerEmprestimoSelecionado() {
        int selectedRow = tabelaEmprestimos.getSelectedRow(); // Obtém a linha selecionada
        if (selectedRow >= 0) { // Verifica se alguma linha foi selecionada
            int idEmprestimo = (int) tableModel.getValueAt(selectedRow, 0); // Obtém o ID da célula da tabela

            // Confirmação antes de remover
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja remover o emprestimo ID: " + idEmprestimo + "?",
                    "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    controller.removerEmprestimo(idEmprestimo); // Chama o controller para remover
                    JOptionPane.showMessageDialog(this, "Emprestimo removido com sucesso!");
                    carregarEmprestimosNaTabela(); // Atualiza a tabela após a remoção
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao remover emprestimo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um emprestimo para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void buscarEmprestimosPorNomeAluno() {
        String nomeBusca = txtBuscaNomeAluno.getText().trim(); // Obtém o texto do campo de busca
        tableModel.setRowCount(0); // Limpa a tabela
        LivroDAO livro = new LivroDAO();

        List<Emprestimo> emprestimos;
        if (nomeBusca.isEmpty()) {
            // Se o campo de busca estiver vazio, lista todos
            emprestimos = controller.listarEmprestimos();
        } else {
            // Caso contrário, busca por nome
            emprestimos = controller.getEmprestimosByAluno(nomeBusca);
        }

        if (emprestimos.isEmpty() && !nomeBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum empréstimo encontrado com o ID de Aluno: '" + nomeBusca + "'", "Busca", JOptionPane.INFORMATION_MESSAGE);
        }

        for (Emprestimo emprestimo : emprestimos) {
            tableModel.addRow(new Object[]{
                    emprestimo.getId_emprestimo(),
                    livro.getLivroByID(emprestimo.getFk_livro()).getTitulo(),
                    nomeBusca,
                    emprestimo.getData_emprestimo(),
                    emprestimo.getData_devolucao()
            });
        }
    }

    private void buscarEmprestimosPorTituloLivro() {
        String tituloBusca = txtBuscaTituloLivro.getText().trim(); // Obtém o texto do campo de busca
        tableModel.setRowCount(0); // Limpa a tabela
        AlunoDAO aluno = new AlunoDAO();

        List<Emprestimo> emprestimos;
        if (tituloBusca.isEmpty()) {
            // Se o campo de busca estiver vazio, lista todos
            emprestimos = controller.listarEmprestimos();
        } else {
            // Caso contrário, busca por nome
            emprestimos = controller.getEmprestimosByLivro(tituloBusca);
        }

        if (emprestimos.isEmpty() && !tituloBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum emprestimo encontrado com o ID de Livro: '" + tituloBusca + "'", "Busca", JOptionPane.INFORMATION_MESSAGE);
        }

        for (Emprestimo emprestimo : emprestimos) {
            tableModel.addRow(new Object[]{
                    emprestimo.getId_emprestimo(),
                    tituloBusca,
                    aluno.getAlunoByID(emprestimo.getFk_aluno()).getNome(),
                    emprestimo.getData_emprestimo(),
                    emprestimo.getData_devolucao()
            });
        }
    }
}
