package com.group86.ws;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.group86.HibernateUtil;
import com.group86.model.Complain;

public class ComplianDao {
	


	public List<Complain> getAll() {
		
		Session session = null;
        List<Complain> complains = null;
        try {
            session = HibernateUtil.getSession();
            String queryStr = "from Complain";
            @SuppressWarnings("unchecked")
			Query<Complain> query = session.createQuery(queryStr);
            complains = query.list();
        } catch(Exception ex) {
            ex.printStackTrace();
            // handle exception here
        } finally {
            try {if(session != null) session.close();} catch(Exception ex) {}
        }
        return complains;
	}
	
	
   public Complain get(Long id) {
		
	   System.out.println("Get complain by Id = " +id+ "\n.....................");
       Session session = null;
       Complain complain = null;
       try {
       	
           session = HibernateUtil.getSession();
           complain = session.get(Complain.class, id);

       } catch(Exception ex) {
           ex.printStackTrace();
           // handle exception here
       } finally {
           try {if(session != null) session.close();} catch(Exception ex) {}
       }
       return complain;
	}
	

	public Long add(Complain complain) {
		Long savedId = null;
		
		  Session session = null;
	        Transaction transaction = null;
	        try {
	            session = HibernateUtil.getSession();
	            transaction = session.beginTransaction();
	            savedId= (Long) session.save(complain);
	            System.out.println("inserted complain: "+complain.getId());
	            transaction.commit();
	        } catch(Exception ex) {
	            ex.printStackTrace();
	            // handle exception here
	            if(transaction != null) transaction.rollback();
	        } finally {
	            try {if(session != null) session.close();} catch(Exception ex) {}
	        }
	        
	        return savedId;
	}
	
	public boolean update(Complain complain) {
		Session session = null;
        Transaction transaction = null;
        
        try {
        	 System.out.println("update complain: "+complain.getId());
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.update(complain);
           
            transaction.commit();
        } catch(Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if(transaction != null) transaction.rollback();
            return false;
        } finally {
            try {if(session != null) session.close();} catch(Exception ex) {}
        }
        
       
		return true;
	}
	
	public boolean delete(Long id) {
		
		Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            Complain com= new  Complain();
            com.setId(id);
            session.delete(com);
            transaction.commit();
            System.out.println("deleted complain: "+com.getId());
        } catch(Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if(transaction != null) transaction.rollback();
            return false;
        } finally {
            try {if(session != null) session.close();} catch(Exception ex) {}
        }
        
        
		
		return true;
	}
}
