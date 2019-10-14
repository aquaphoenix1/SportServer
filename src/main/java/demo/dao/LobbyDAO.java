package demo.dao;

import demo.entities.LobbyEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LobbyDAO extends DAO {
    public static LobbyEntity findById(int id) {
        try (Session session = getSession()) {
            return session.get(LobbyEntity.class, id);
        }
    }

    public static List<LobbyEntity> getAll(){
        try (Session session = getSession()) {
            return session.createQuery("from LobbyEntity ").list();
        }
    }

    public static void add(String description){
        LobbyEntity lobby = new LobbyEntity();
        lobby.setDescription(description);

        try (Session session = getSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(lobby);
            tx1.commit();
        }
    }

    public static void remove(int id){
        LobbyEntity lobby = LobbyDAO.findById(id);

        try (Session session = getSession()) {
            Transaction tx1 = session.beginTransaction();
            session.remove(lobby);
            tx1.commit();
        }
    }

    public static void update(int id, String description){
        LobbyEntity lobby = LobbyDAO.findById(id);
        lobby.setDescription(description);

        try (Session session = getSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(lobby);
            tx1.commit();
        }
    }
}
