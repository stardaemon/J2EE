/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import repository.entities.Service;
import repository.entities.Serviceuse;
import repository.entities.Subservice;
import repository.entities.Userinfo;

/**
 *
 * @author liyunhong
 */
public class JPARepositoryImpl implements Repository {
     private static final String PERSISTENCE_UNIT = "assignment5042PU";
    
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    public JPARepositoryImpl() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }
    
    //add a property to the table
    @Override
    public void addUserInfo(Userinfo user)throws Exception {
        EntityTransaction transaction = this.entityManager.getTransaction();
        
        try {
            transaction.begin();
            this.entityManager.persist(user);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                System.out.print("An error occurs because of transaction!");
            }
        }
    }

    
    //search a user 
    @Override
    public Userinfo searchUserById(int id) throws Exception {
        Userinfo user = this.entityManager.find(Userinfo.class, id);       
        return user;   
    }

    //search all users
    @Override
    public List<Userinfo> getAllUsers() throws Exception {
        return this.entityManager.createNamedQuery("Userinfo.findAll").getResultList();
    }

    //remove a user
    @Override
    public void removeUser(int id) throws Exception {
        EntityTransaction transaction = this.entityManager.getTransaction();
        
        try {
            transaction.begin();
            this.entityManager.remove(this.searchUserById(id));
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                System.out.print("An error occurs because of transaction!");                
            }
        }    
    }

    //edit a user
    @Override
    public void editUser(Userinfo user) throws Exception {
        EntityTransaction transaction = this.entityManager.getTransaction();       
        try {
            transaction.begin();
            this.entityManager.merge(user);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                System.out.print("An error occurs because of transaction!");                
            }
        }
    }

    //add a service
    @Override
    public void addService(Service service) throws Exception {
        EntityTransaction transaction = this.entityManager.getTransaction();
        
        try {
            transaction.begin();
            this.entityManager.persist(service);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                System.out.print("An error occurs because of transaction!");
            }
        }
    }

    //search service by id
    @Override
    public List<Service> searchServiceById(int id) throws Exception {
        Query query = entityManager.createNamedQuery("Service.findByServiceid");
        query.setParameter("serviceid",id);
        return query.getResultList();  
    }

    //search a service by id
    @Override
    public Service findServiceById(int id) throws Exception {
        Query query = entityManager.createNamedQuery("Service.findByServiceid");
        query.setParameter("serviceid",id);
        return (Service) query.getSingleResult();  
    }
    
    @Override
    public List<Service> searchByServiceIdNameType(int id, String name, String type) throws Exception {
        Query query =entityManager.createNamedQuery("Service.findByIdNameType");
        query.setParameter("serviceid",id);
        query.setParameter("servicename", name);
        query.setParameter("servicetype", type);
        return query.getResultList();
    }
    
    //search by a service name
    @Override
    public List<Service> searchServiceByName(String name) throws Exception{
        Query query = entityManager.createNamedQuery("Service.findByServicename");
        query.setParameter("servicename",name);
        return query.getResultList();        
    }

    //search a Service by type
    public List<Service> searchServiceByType(String type) throws Exception{
         Query query = entityManager.createNamedQuery("Service.findByServicetype");
         query.setParameter("servicetype",type);
         return query.getResultList(); 
    } 
    
    // get all services
    @Override
    public List<Service> getAllServices() throws Exception {
        return this.entityManager.createNamedQuery("Service.findAll").getResultList();
    }

    //remove a service
    @Override
    public void removeService(int id) throws Exception {
        EntityTransaction transaction = this.entityManager.getTransaction();       
        try {
            transaction.begin();
            this.entityManager.remove(this.searchServiceById(id));
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                System.out.print("An error occurs because of transaction!");                
            }
        }
    }

    //edit a service
    @Override
    public void editService(Service service) throws Exception {
        EntityTransaction transaction = this.entityManager.getTransaction();       
        try {
            transaction.begin();
            this.entityManager.merge(service);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                System.out.print("An error occurs because of transaction!");                
            }
        }
    }

    //add a serviceuse
    @Override
    public void addServiceuse(Serviceuse serviceuse) throws Exception {
        EntityTransaction transaction = this.entityManager.getTransaction();
        
        try {
            transaction.begin();
            this.entityManager.persist(serviceuse);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                System.out.print("An error occurs because of transaction!");
            }
        }
    }

    //search a user' service  by its id
    @Override
    public List<Serviceuse> searchServiceuseById(int id) throws Exception {
        Query query = entityManager.createNamedQuery("Serviceuse.findByServiceuseid");
        query.setParameter("serviceuseid", id); 
        return query.getResultList();  
    }

    //search all services used by a user id
    @Override
    public List<Service> searchServiceByUser(int id) throws Exception {
         Query query = entityManager.createNamedQuery("Serviceuse.findServiceById");
         query.setParameter("serviceuseid", id);       
        return query.getResultList();
    }
    
    //get all services used
    @Override
    public List<Serviceuse> getAllServiceuses() throws Exception {
        return this.entityManager.createNamedQuery("Serviceuse.findAll").getResultList();
    }

    //remove a service use
    @Override
    public void removeServiceuse(int id) throws Exception {
        EntityTransaction transaction = this.entityManager.getTransaction();
       
        try {
            transaction.begin();
            this.entityManager.remove(this.searchServiceuseById(id));
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                System.out.print("An error occurs because of transaction!");                
            }
        }
    }

    //edit a service use
    @Override
    public void editServiceuse(Serviceuse serviceuse) throws Exception {
        EntityTransaction transaction = this.entityManager.getTransaction();       
        try {
            transaction.begin();
            this.entityManager.merge(serviceuse);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                System.out.print("An error occurs because of transaction!");                
            }
        }
    }
    
    //close entity manager
    @Override
    public void close(){
        entityManager.close();
    }

    @Override
    public void addSubService(Subservice sub) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editSubService(Subservice sub) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Subservice findSubService(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeSubService(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Subservice> findSubServiceInOne(Service serviceid) throws Exception {
         Query query = entityManager.createNamedQuery("Subservice.findOneGroup");
         query.setParameter("serviceid",serviceid);
         return query.getResultList(); 
    }

    @Override
    public List<Userinfo> searchUserCombine( String ln, String fn, String type, String Email) throws Exception {
         Query query = entityManager.createNamedQuery("Userinfo.findCombine");
         query.setParameter("lastname", "%" + ln + "%");
         query.setParameter("firstname", "%" + fn + "%");
         query.setParameter("usertype", type);
         query.setParameter("email", "%" + Email + "%");
         System.out.println(query.toString());
         return query.getResultList(); 
    }

    
}
