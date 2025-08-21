package Controller;

import Util.HibernateUtil;
import Model.Pokemon;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PokemonController {
    public void cadastrarPokemon(Pokemon poke) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
    // Validações de negócio teste (nome, tipo, etc.)
            String segundoTipo = null;
//            if (nome == null || nome.trim().isEmpty()) {
//                throw new Exception("O nome do Pokémon é obrigatório.");
//            }
//
//            if(!revisaoDeTexto(nome) && !nome.trim().toLowerCase().equalsIgnoreCase("porygon2")){
//                throw new Exception("O nome do pokémon não deve conter números!");
//            }
//
//            if (tipoPrimario == null || tipoPrimario.trim().isEmpty()) {
//                throw new Exception("O tipo primário do Pokémon é obrigatório.");
//            }
//
//            if (!revisaoDeTexto(tipoPrimario)){
//                throw new Exception("O Tipo do Pokémon não deve conter números!");
//            }
//
//            if (tipoSecundario != null){
//                if (tipoPrimario.toLowerCase().equals(tipoSecundario.toLowerCase()) || !revisaoDeTexto(tipoSecundario)) {
//                    throw new Exception("O Tipo Secundário não pode ser igual ao Tipo Primário e também não pode conter números.");
//                }
//            }
//
//            if (nivel < 0 || nivel > 100 || String.valueOf(nivel).trim().isEmpty()) {
//                throw new Exception("O nível não é válido.");
//            }
//
//            if (hpMaximo < 0 || String.valueOf(hpMaximo).trim().isEmpty()) {
//                throw new Exception("O HP máximo não é válido.");
//            }

//            if (pokemonDAO.pokemonJaExiste(nome)) {
//                throw new Exception("Já existe um Pokémon com esse nome!");
//            }


            session.persist(poke);
            // Salva o objeto no banco
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao cadastrar poke: "+e.getMessage());
        }
    }

    public boolean revisaoDeTexto(String texto){
        List<Character> letrasTexto = new ArrayList<>();

        for(int i = 0; i<texto.length(); i++){
            letrasTexto.add(texto.charAt(i));
        }

        for (Character letra: letrasTexto){
            if (!Character.isLetter(letra)){
                return false;
            }
        }
        return true;
    }

    public void updatePoke(Pokemon pokemon){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(pokemon);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao atualizar poke: "+ e.getMessage());
        }
    }

    public List<Pokemon> listarTodosOsPokes(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            System.out.println("Teste");
            Query<Pokemon> query = session.createQuery("FROM Pokemon", Pokemon.class);
//            List<Pokemon> pokes = query.getResultList();
//            for(Pokemon poke : pokes){
//                System.out.println(poke.getId()+ " - "+ poke.getNome()+ " - "+ poke.getTipoPrimario()+ " - "+ poke.getTipoSecundario()+ " - "+ poke.getNivel()+ " - "+poke.getHpMaximo());
//            }
//            System.out.println(getById(180).getNome());
//            updatePoke(new Pokemon(180, "Steenee", "Grama", "", 40, 120));
            return query.getResultList();
        } catch (Exception e){
            System.out.println("Erro: "+e.getMessage());
            return null;
        }
    }

    public long contarPokemonsPorTipo(String tipo){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Pokemon WHERE tipoPrimario = :tipo", Long.class);// Consulta HQL
            query.setParameter("tipo", tipo);
            System.out.println("Pokes do tipo: "+tipo+ " = "+query.getSingleResult());
            return query.getSingleResult();
        }
    }

    public Pokemon getById(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(Pokemon.class, id);
        }
    }

    public Pokemon getByName(String name){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Pokemon> query = session.createQuery("SELECT * FROM Pokemon WHERE nome = :nome", Pokemon.class);
            query.setParameter("nome", name);
            return query.getSingleResult();
        }
    }

    public void removePoke(int id){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Pokemon pokemon = getById(id);
            if (pokemon != null){
                session.remove(pokemon);
            }
            transaction.commit();
        } catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao remover Poke: "+ e.getMessage());
        }
    }
}