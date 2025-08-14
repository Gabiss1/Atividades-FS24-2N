import Model.Aluno;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class MainApp {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(Aluno.class);

        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Aluno aluno = new Aluno();
        aluno.setNome_aluno("João");
        aluno.setIdade_aluno(18);
        aluno.setContato_aluno("8294659703");

        session.save(aluno);
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
    }

}
