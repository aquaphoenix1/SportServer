//package demo.dao;
//
//import demo.entities.ProfileEntity;
//import demo.entities.TrainerEntity;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import java.util.List;
//
//public class TrainerDAO extends DAO {
//    public static List<TrainerEntity> getAll(){
//        try (Session session = getSession()) {
//            //return session.createQuery("from TrainerEntity ").list();
//            return null;
//        }
//    }
//
//    public static TrainerEntity findById(int id) {
//        try (Session session = getSession()) {
//            return session.get(TrainerEntity.class, id);
//        }
//    }
//
//    public static void add(String name, String surname, int age, int profileEntityId){
//        TrainerEntity entity = new TrainerEntity();
//        entity.setName(name);
//        entity.setSurname(surname);
//        entity.setAge(age);
//        ProfileEntity profile = ProfileDAO.findById(profileEntityId);
//        entity.setProfileByProfileId(profile);
//
//        try (Session session = getSession()){
//            Transaction tx1 = session.beginTransaction();
//            session.save(entity);
//            tx1.commit();
//        }
//    }
//
//    public static void update(int id, String name, String surname, int age, int profileId){
//        TrainerEntity trainer = TrainerDAO.findById(id);
//        trainer.setName(name);
//        trainer.setSurname(surname);
//        trainer.setAge(age);
//
//        ProfileEntity profile = ProfileDAO.findById(profileId);
//
//        trainer.setProfileByProfileId(profile);
//
//        try (Session session = getSession()) {
//            Transaction tx1 = session.beginTransaction();
//            session.update(trainer);
//            tx1.commit();
//        }
//    }
//
//    public static void remove(int id){
//        TrainerEntity trainer = TrainerDAO.findById(id);
//
//        try (Session session = getSession()) {
//            Transaction tx1 = session.beginTransaction();
//            session.remove(trainer);
//            tx1.commit();
//        }
//    }
//}
