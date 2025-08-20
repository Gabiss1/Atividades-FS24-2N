
import Controller.PokemonController;
import Model.Pokemon;
import Util.HibernateUtil;
import View.ListaPokemonsPanel;
import View.PokemonForm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class MainApp extends JFrame{

    private JDesktopPane desktopPane;
    private static PokemonController controller = new PokemonController();

    public MainApp() {
        super("Sistema de Gerenciamento de Pokémons");
        //this.controller = new PokemonController();

//        Configuration config = new Configuration();
//        config.configure("hibernate.cfg.xml");
//        config.addAnnotatedClass(Pokemon.class);

        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        createMenuBar();

        controller.contarPokemonsPorTipo("Agua");
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Menu Pokémons
        JMenu menuPokemons = new JMenu("Pokémons");
        JMenuItem itemCadastrarPokemon = new JMenuItem("Cadastrar Pokémon");
        JMenuItem itemListarPokemons = new JMenuItem("Listar Pokémons");
        JMenuItem itemInserirListaPokemons = new JMenuItem("Inserir Lista de Pokémons");

        itemCadastrarPokemon.addActionListener(e -> {
            try {
                openPokemonForm(null);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        itemListarPokemons.addActionListener(e -> openListaPokemonsPanel());

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

    private void openPokemonForm(Integer idPokemon) throws Exception {
//        Pokemon poke = new Pokemon("Bounsweet", "Grama", null, 22, 65);
//        Pokemon poke2 = new Pokemon("Buizel", "Água", null, 22, 65);
//
//        controller.cadastrarPokemon(poke);
//        controller.cadastrarPokemon(poke2);

        PokemonForm pokemonForm = new PokemonForm(controller, idPokemon);
        desktopPane.add(pokemonForm);
        pokemonForm.setVisible(true);
        pokemonForm.toFront();
    }

    private void openListaPokemonsPanel() {
        ListaPokemonsPanel listaPokemons = new ListaPokemonsPanel(controller);
        desktopPane.add(listaPokemons);
        listaPokemons.setVisible(true);
        listaPokemons.toFront();
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            new MainApp().setVisible(true);
        });
//        Configuration config = new Configuration();
//        config.configure("hibernate.cfg.xml");
//        config.addAnnotatedClass(Pokemon.class);
//
//        SessionFactory sessionFactory = config.buildSessionFactory();
//        Session session = sessionFactory.openSession();
//
//        session.beginTransaction();
//
//
//
//
//        session.save(poke);
//        session.save(poke2);
//        session.getTransaction().commit();
//
//        session.close();
//        sessionFactory.close();

    }

    public static void cadastrar() throws Exception {
    }

    public static void listar() throws Exception{
        List<Pokemon> listaPokes = controller.listarTodosOsPokes();
        for (Pokemon poke: listaPokes){
            System.out.println(poke.getNome() +" || "+ poke.getTipoPrimario());
        }
    }
}
