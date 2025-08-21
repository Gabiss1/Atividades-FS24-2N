import Controller.PokemonController;
import Model.Pokemon;
import View.Lista151Pokes;
import View.ListaPokemonsPanel;
import View.PokemonForm;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {

    private JDesktopPane desktopPane;
    private PokemonController pokemonController;

    public Main() {
        super("Sistema de Gerenciamento de Pokémons");
        this.pokemonController = new PokemonController();

        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        createMenuBar();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Menu Pokémons
        JMenu menuPokemons = new JMenu("Pokémons");
        JMenuItem itemCadastrarPokemon = new JMenuItem("Cadastrar Pokémon");
        JMenuItem itemListarPokemons = new JMenuItem("Listar Pokémons");
        JMenuItem itemInserirListaPokemons = new JMenuItem("Inserir Lista de Pokémons");

        itemCadastrarPokemon.addActionListener(e -> openPokemonForm(null));
        itemListarPokemons.addActionListener(e -> openListaPokemonsPanel());
        itemInserirListaPokemons.addActionListener(e -> {
            try {
                insereListaPokemons();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        menuPokemons.add(itemCadastrarPokemon);
        menuPokemons.add(itemListarPokemons);
        menuPokemons.add(itemInserirListaPokemons);

        menuBar.add(menuPokemons);

        // Menu Sair
        JMenu menuSair = new JMenu("Sair");
        JMenuItem itemSair = new JMenuItem("Sair do Sistema");
        itemSair.addActionListener(e -> System.exit(0));

        menuSair.add(itemSair);
        menuBar.add(menuSair);

        setJMenuBar(menuBar);
    }

    private void openPokemonForm(Integer idPokemon) {
        PokemonForm pokemonForm = new PokemonForm(pokemonController, idPokemon);
        desktopPane.add(pokemonForm);
        pokemonForm.setVisible(true);
        pokemonForm.toFront();
    }

    private void openListaPokemonsPanel() {
        ListaPokemonsPanel listaPokemons = new ListaPokemonsPanel(pokemonController);
        desktopPane.add(listaPokemons);
        listaPokemons.setVisible(true);
        listaPokemons.toFront();
    }

    private void insereListaPokemons() throws SQLException {
        Lista151Pokes lista = new Lista151Pokes();
        List<Pokemon> listaPokemons = lista.gerarListaPokes();
        pokemonController.insereListaPokemns(listaPokemons);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}