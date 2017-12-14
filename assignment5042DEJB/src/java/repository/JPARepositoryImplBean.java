/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import repository.entities.Service;
import repository.entities.Serviceuse;
import repository.entities.Subservice;
import repository.entities.Userinfo;

/**
 *
 * @author liyunhong
 */
@Stateless
public class JPARepositoryImplBean  implements Repository{

    @PersistenceContext(unitName = "assignment5042DPU")
    private EntityManager entityManager;
    
    @Override
    public void addUserInfo(Userinfo user) throws Exception {
        entityManager.persist(user);
    }

    @Override
    public Userinfo searchUserById(int id) throws Exception {
        Userinfo property = entityManager.find(Userinfo.class, id);
        return property;  
    }

    @Override
    public List<Userinfo> getAllUsers() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUser(int id) throws Exception {
        Userinfo property = this.searchUserById(id);
      
        if (property != null) {
            entityManager.remove(property);
        } 
    }

    @Override
    public void editUser(Userinfo user) throws Exception {
        entityManager.merge(user);
    }

    @Override
    public void addService(Service service) throws Exception {
        entityManager.persist(service);
    }

    @Override
    public Service findServiceById(int id) throws Exception {
        Service property = entityManager.find(Service.class, id);
        return property;
    }

    @Override
    public List<Service> searchServiceById(int id) throws Exception {
        Service property = entityManager.find(Service.class, id);
        List<Service> services = new ArrayList<Service>();
        services.add(property);
        return services;    
    }

    @Override
    public List<Service> searchServiceByName(String name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Service> searchServiceByType(String type) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Service> searchByServiceIdNameType(int id, String name, String type) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Service> getAllServices() throws Exception {
        return entityManager.createNamedQuery(Service.GET_ALL_QUERY_NAME).getResultList();
    }

    @Override
    public void removeService(int id) throws Exception {
        Service property = this.searchServiceById(id).get(0);
      
        if (property != null) {
            entityManager.remove(property);
        }    
    }

    @Override
    public void editService(Service service) throws Exception {
        entityManager.merge(service);
        
    }

    @Override
    public void addServiceuse(Serviceuse serviceuse) throws Exception {
        entityManager.persist(serviceuse);
    }

    @Override
    public List<Serviceuse> searchServiceuseById(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Service> searchServiceByUser(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Serviceuse> getAllServiceuses() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeServiceuse(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editServiceuse(Serviceuse serviceuse) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void addSubService(Subservice sub) throws Exception {
        entityManager.persist(sub);
    }

    @Override
    public void editSubService(Subservice sub) throws Exception {
        entityManager.merge(sub);
    }

    @Override
    public Subservice findSubService(int id) throws Exception {
        Subservice property = entityManager.find(Subservice.class, id);
        return property;
    }

    @Override
    public void removeSubService(int id) throws Exception {
        Subservice property = this.findSubService(id);     
        if (property != null) {
            entityManager.remove(property);
        }    
    }

    @Override
    public List<Subservice> findSubServiceInOne(Service id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Userinfo> searchUserCombine(String ln, String fn, String type, String Email) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
