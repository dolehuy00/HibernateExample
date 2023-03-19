/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package hibernateex;

import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author PC
 */
public class HibernateEx {
    
    private static SessionFactory factory;
    
    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("Failed"+ e);
//            throw new ExceptionInInitializerError(e);
        }
        HibernateEx hibernateEx = new HibernateEx();
        hibernateEx.listDepartment();
    }
    
    public void listDepartment(){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List employ = session.createQuery("FROM Department").list();
            for (Iterator iterator = employ.iterator(); iterator.hasNext();) {
                Department department = (Department) iterator.next();
                System.err.println("Name: "+department.getName());
                System.err.println("StartDate: "+department.getStartDate());
                System.err.println("Budget: "+department.getBudget());  
            }
            tx.commit();
        } catch (HibernateException e) {
            if(tx!=null) tx.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
    }
}
