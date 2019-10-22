//package demo.dao;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//class DAO {
//    private static SessionFactory sessionFactory;
//
//    private static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            sessionFactory = new Configuration().configure().buildSessionFactory();
//        }
//        return sessionFactory;
//    }
//
//    static Session getSession() {
//        return getSessionFactory().openSession();
//    }
//}
