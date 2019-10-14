package demo.dao;

import demo.entities.LobbyEntity;
import demo.entities.SessionEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

public class SessionDAO extends DAO {
    public static List<SessionEntity> getAll() {
        try (Session session = getSession()) {
            return session.createQuery("from SessionEntity").list();
        }
    }

    public static SessionEntity findById(int id) {
        try (Session session = getSession()) {
            return session.get(SessionEntity.class, id);
        }
    }

    public static void add(int price, String description, Date startDate, Date endDate, int lobbyId) {
        SessionEntity session = new SessionEntity();
        session.setPrice(price);
        session.setDescription(description);
        session.setStartDate(startDate);
        session.setEndDate(endDate);
        LobbyEntity lobby = LobbyDAO.findById(lobbyId);
        session.setLobbyByLobbyId(lobby);

        try (Session sqlSession = getSession()) {
            Transaction tx1 = sqlSession.beginTransaction();
            sqlSession.save(session);
            tx1.commit();
        }
    }

    public static void update(int id, int price, String description, Date startDate, Date endDate, int lobbyId) {
        SessionEntity session = SessionDAO.findById(id);
        session.setPrice(price);
        session.setDescription(description);
        session.setStartDate(startDate);
        session.setEndDate(endDate);

        LobbyEntity lobby = LobbyDAO.findById(lobbyId);

        session.setLobbyByLobbyId(lobby);

        try (Session sqlSession = getSession()) {
            Transaction tx1 = sqlSession.beginTransaction();
            sqlSession.update(session);
            tx1.commit();
        }
    }

    public static void remove(int id) {
        SessionEntity session = SessionDAO.findById(id);

        try (Session sqlSession = getSession()) {
            Transaction tx1 = sqlSession.beginTransaction();
            sqlSession.remove(session);
            tx1.commit();
        }
    }
}
