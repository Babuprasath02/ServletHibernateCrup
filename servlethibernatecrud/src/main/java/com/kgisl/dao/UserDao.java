package com.kgisl.dao;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.kgisl.model.User;

public class UserDao {
    // SessionFactory sessionFactory=HibernateUtil.getSessionFactory().openSession()
    //     Session session=sessionFactory.openSession();
public void saveUser(User user){
    Transaction transaction = null;
    try(Session session = HibernateUtil.getSessionFactory().openSession()){
        transaction=session.beginTransaction();
        session.save(user);
        transaction.commit();
    }catch(Exception e){
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
    
  
}
public void updateUser(User user){
    Transaction transaction = null;
    try(Session session = HibernateUtil.getSessionFactory().openSession()){
        transaction=session.beginTransaction();
        session.update(user);
        transaction.commit();
    }catch(Exception e){
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
        
}
public void deleteUser(int id){
    Transaction transaction=null;
    try(Session session =HibernateUtil.getSessionFactory().openSession()){
        transaction=session.beginTransaction();
        User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                System.out.println("user is deleted");
            }
            transaction.commit();
    }catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }

}
public User getUser(int id) {

    Transaction transaction = null;
    User user = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        // start a transaction
        transaction = session.beginTransaction();
        // get an user object
        user = session.get(User.class, id);
        // commit transaction
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
    return user;
}

/**
 * Get all Users
 * @return
 */
@SuppressWarnings("unchecked")
public List < User > getAllUser() {

    Transaction transaction = null;
    List < User > listOfUser = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        // start a transaction
        transaction = session.beginTransaction();
        // get an user object

        listOfUser = session.createQuery("from User").getResultList();

        // commit transaction
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
    return listOfUser;
}
    
}
