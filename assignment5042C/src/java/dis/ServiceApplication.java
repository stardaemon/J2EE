package dis;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import repository.JPARepositoryImpl;
import repository.entities.Service;

/**
 *
 * @author liyunhong
 */
@Named(value = "serviceApplication")
@ApplicationScoped
public class ServiceApplication {

    public static final String APP_TITLE = "Service Use Browser" ;
    private JPARepositoryImpl imp;
    private Service service;
    private List<Service> serviceuses;
    private int id;
    private String name;
    private String type;


    /**
    *  add all service uses of one user from the persistence
    */
    public ServiceApplication() throws Exception {
        imp = new JPARepositoryImpl();        
        service = new Service();
        serviceuses = new ArrayList<Service>();
        findAllServices();
        id = 1;
        name = "";
        type = "";
        
    }
    
    public ServiceApplication(int id, String name, String type) throws Exception {
        imp = new JPARepositoryImpl();        
        service = new Service();
        findAllServices() ;
    }
    

    
    
    public void findServicesById() throws Exception {
         this.serviceuses = imp.searchServiceById(id);
    }
   
    public Service findOneService(int id) throws Exception{
        this.service =  imp.searchServiceById(id).get(0);
        return service;
    }
    
    public void findAllServices() throws Exception {
        
        this.serviceuses = imp.getAllServices();
        //return serviceuses;
    }
    
    public void findServiceByName() throws Exception {
         this.serviceuses = imp.searchServiceByName(name);
    }
    
    public void findServiceByType() throws Exception {
         this.serviceuses = imp.searchServiceByType(type);
    }
    
    public List<Service> findServiceByIdNameType(int id, String name, String type) throws Exception {
        return imp.searchByServiceIdNameType(id, name, type);
    }
    
    public Service getService() {
        return service;
    }
    
    //get all service uses by this user
    public List<Service> getServiceuses() {
        return serviceuses;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
}
