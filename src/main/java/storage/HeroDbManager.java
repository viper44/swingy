package storage;

import model.characters.hero.Hero;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by msemenov on 12/4/18.
 */
public class HeroDbManager {
    SessionFactory sessionFactory;
    public HeroDbManager(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void heroAdd(Hero hero){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(hero);
        session.getTransaction().commit();
        session.close();
    }
}
