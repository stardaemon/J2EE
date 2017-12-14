/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import javax.ejb.Remote;
import repository.entities.Service;
import repository.entities.Serviceuse;
import repository.entities.Subservice;
import repository.entities.Userinfo;

/**
 *
 * @author liyunhong
 */
@Remote
public interface Repository {
    
    //add an entity to table UserInfo
    public void addUserInfo(Userinfo user) throws Exception;
    
    //search a user
    public Userinfo searchUserById(int id) throws Exception;

    //search users
    public List<Userinfo> searchUserCombine(String ln, String fn, String type, String Email) throws Exception;
    
    //search all users
    public List<Userinfo> getAllUsers() throws Exception;

    //remove a user
    public void removeUser(int id) throws Exception;
    
    //edit a userinfo
    public void editUser(Userinfo user) throws Exception;

    //add an entity to table Service
    public void addService(Service service) throws Exception;
    
    public Service findServiceById(int id) throws Exception;
    
    //search  Services by id
    public List<Service> searchServiceById(int id) throws Exception;
    
    //search Services by name
    public List<Service> searchServiceByName(String name) throws Exception;

    //search Services by type
    public List<Service> searchServiceByType(String type) throws Exception;
    
    //search services by id, name and type.
    public List<Service> searchByServiceIdNameType(int id, String name, String type) throws Exception;
    
    //search all Services
    public List<Service> getAllServices() throws Exception;

    //remove a Service
    public void removeService(int id) throws Exception;
    
    //edit a Service
    public void editService(Service service) throws Exception;
    
    //add an entity to table Serviceuse
    public void addServiceuse(Serviceuse serviceuse) throws Exception;
    
    //search a Serviceuse
    public List<Serviceuse> searchServiceuseById(int id) throws Exception;

    //search services by user id 
    public List<Service> searchServiceByUser(int id) throws Exception;
    
    //search all Serviceuses
    public List<Serviceuse> getAllServiceuses() throws Exception;

    //remove a Serviceuse
    public void removeServiceuse(int id) throws Exception;
    
    //edit a Serviceuse
    public void editServiceuse(Serviceuse serviceuse) throws Exception;
    
    //add a subservice
    public void addSubService(Subservice sub) throws Exception;
    
    //update a subservice
    public void editSubService(Subservice sub) throws Exception;
    
    //search a subservice
    public Subservice findSubService(int id) throws Exception;
    
    //delete a subservice
    public void removeSubService(int id) throws Exception;
    
    //find subservices belong to one service.
    public List<Subservice> findSubServiceInOne(Service id) throws Exception;
    
    //close all resources
    public void close();

}
