package storage;

import model.characters.hero.Hero;
import model.equipment.weapon.Weapon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.List;

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

    public List<Hero> getHeroByid(){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Hero.class);
        Root<Weapon> root = cq.from(Hero.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        List<Hero> heroes = query.getResultList();
        for (Hero hero : heroes) {
            hero.setHeroHp();
            hero.setHeroDamage();
            hero.setHeroDefense();
            hero.setName(hero.getNameH());
        }
        session.close();
        return heroes;
    }
}
