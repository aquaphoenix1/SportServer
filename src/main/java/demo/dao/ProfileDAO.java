package demo.dao;

import demo.entities.ProfileEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProfileDAO extends DAO {
    public static ProfileEntity findById(int id) {
        try (Session session = getSession()) {
            return session.get(ProfileEntity.class, id);
        }
    }

    public static List<ProfileEntity> getAll(){
        try (Session session = getSession()) {
            return session.createQuery("from ProfileEntity ").list();
        }
    }

    public static void add(String description){
        ProfileEntity entity = new ProfileEntity();
        entity.setDescription(description);

        try (Session session = getSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(entity);
            tx1.commit();
        }
    }

    public static void remove(int id){
        ProfileEntity profile = ProfileDAO.findById(id);

        try (Session session = getSession()) {
            Transaction tx1 = session.beginTransaction();
            session.remove(profile);
            tx1.commit();
        }
    }

    public static void update(int id, String description){
        ProfileEntity profile = ProfileDAO.findById(id);
        profile.setDescription(description);

        try (Session session = getSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(profile);
            tx1.commit();
        }
    }
}
