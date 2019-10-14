package demo.dao;

import demo.entities.ClientEntity;
import demo.entities.ClientSessionEntity;
import demo.entities.ClientSessionEntityPK;
import demo.entities.SessionEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientSessionDAO extends DAO {
    public static List<ClientSessionEntity> getAll() {
        try (Session session = getSession()) {
            return session.createQuery("from ClientSessionEntity").list();
        }
    }

    public static ClientSessionEntity findById(String email, int id) {
        ClientSessionEntityPK key = new ClientSessionEntityPK();
        key.setMail(email);
        key.setSessionId(id);
        try (Session session = getSession()) {
            return session.get(ClientSessionEntity.class, key);
        }
    }

    public static void add(String email, int id, boolean isPaid) {
        SessionEntity session = SessionDAO.findById(id);
        ClientEntity entity = ClientDAO.findById(email);
        ClientSessionEntity clientSession = new ClientSessionEntity();
        clientSession.setClientByMail(entity);
        clientSession.setSessionBySessionId(session);
        clientSession.setPaid(isPaid);
        clientSession.setMail(entity.getEmail());
        clientSession.setSessionId(session.getSessionId());

        try (Session sqlSession = getSession()) {
            Transaction tx1 = sqlSession.beginTransaction();
            sqlSession.save(clientSession);
            tx1.commit();
        }
    }

    public static void update(String email, int id, boolean isPaid) {
        ClientSessionEntity clientSession = ClientSessionDAO.findById(email, id);
        clientSession.setPaid(isPaid);

        try (Session sqlSession = getSession()) {
            Transaction tx1 = sqlSession.beginTransaction();
            sqlSession.update(clientSession);
            tx1.commit();
        }
    }

    public static void remove(String email, int id) {
        ClientSessionEntity clientSession = ClientSessionDAO.findById(email, id);

        try (Session sqlSession = getSession()) {
            Transaction tx1 = sqlSession.beginTransaction();
            sqlSession.remove(clientSession);
            tx1.commit();
        }
    }
}
