package demo.dao;

import demo.entities.ClientEntity;
import demo.entities.TrainerEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientDAO extends DAO {
    public static ClientEntity findById(String email) {
        try (Session session = getSession()) {
            return session.get(ClientEntity.class, email);
        }
    }

    public static List<ClientEntity> getAll() {
        try (Session session = getSession()) {
            return session.createQuery("from ClientEntity ").list();
        }
    }

    public static void save(String email, String password, String name, String surname, int age) {
        ClientEntity client = new ClientEntity();
        client.setName(name);
        client.setSurname(surname);
        client.setAge(age);
        client.setEmail(email);
        client.setPassword(password);

        try (Session session = getSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(client);
            tx1.commit();
        }
    }

    public static void remove(String id){
        ClientEntity client = ClientDAO.findById(id);

        try (Session session = getSession()) {
            Transaction tx1 = session.beginTransaction();
            session.remove(client);
            tx1.commit();
        }
    }

    public static void update(String email, String name, String surname, int age, Integer trainerId){
        ClientEntity client = ClientDAO.findById(email);
        client.setName(name);
        client.setSurname(surname);
        client.setAge(age);

        if(trainerId != null) {
            TrainerEntity trainer = TrainerDAO.findById(trainerId);
            client.setTrainerByTrainerId(trainer);
        }

        try (Session session = getSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(client);
            tx1.commit();
        }
    }

    public static void updatePassword(String email, String password){
        ClientEntity client = ClientDAO.findById(email);
        client.setPassword(password);

        try (Session session = getSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(client);
            tx1.commit();
        }
    }
}