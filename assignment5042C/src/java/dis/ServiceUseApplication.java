/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import repository.JPARepositoryImpl;
import repository.Repository;
import repository.entities.Service;
import repository.entities.Serviceuse;
import repository.entities.Userinfo;

/**
 *
 * @author liyunhong
 */
@Named(value = "serviceUseApplication")
@ApplicationScoped
public class ServiceUseApplication {

    @EJB
    private Repository ejb;
    
    private String ssDate;
    private String ssServiceId;
    private String ssWorkerId;
    private String ssUserId;
    private Serviceuse su;
    private List<Serviceuse> sus;
    private JPARepositoryImpl imp;

    
    /**
     * Creates a new instance of ServiceUseApplication
     */
    public ServiceUseApplication() {
        this.imp = new JPARepositoryImpl(); 
        this.su = new Serviceuse();
        this.sus = new ArrayList<Serviceuse>();
        this.ssDate = "";
        this.ssServiceId = "";
        this.ssUserId = "";
        this.ssWorkerId = "";
    }
    
    //initial
    @PostConstruct
    public void init()
    {
        this.su = su;
    }

    //add a new serviceuse
    public void addService() throws Exception
    {
        try {
            Serviceuse ser = createNewServiceUse(this.ssDate,this.ssServiceId,this.ssWorkerId,this.ssUserId);          
            ejb.addServiceuse(ser);
        }
        catch(Exception ex){
            System.out.println("Some  error occurs");
        }
    }
    
    //create a new service to faciliate user add it to the db
    public Serviceuse createNewServiceUse(String sDate, String sService, String sWorker, String sUser) throws Exception
    {
        int serviceId = Integer.parseInt(sService);
        Service serviceWantUse = ejb.findServiceById(serviceId);
        int workerId = Integer.parseInt(sWorker);
        Userinfo workerWantUse = ejb.searchUserById(workerId);
        int userId = Integer.parseInt(sUser);
        Userinfo userWantUse = ejb.searchUserById(userId);
        Serviceuse ser = new Serviceuse(sDate,serviceWantUse,workerWantUse,userWantUse);        
        return ser;
    }

    public void findAllServiceInUse() throws Exception
    {
        int userid = Integer.parseInt(ssUserId);
        this.sus = imp.searchServiceuseById(userid);
    }
    
    public void setSsDate(String ssDate) {
        this.ssDate = ssDate;
    }

    public void setSsServiceId(String ssServiceId) {
        this.ssServiceId = ssServiceId;
    }

    public void setSsWorkerId(String ssWorkerId) {
        this.ssWorkerId = ssWorkerId;
    }

    public void setSsUserId(String ssUserId) {
        this.ssUserId = ssUserId;
    }

    public void setSu(Serviceuse su) {
        this.su = su;
    }

    public String getSsDate() {
        return ssDate;
    }

    public String getSsServiceId() {
        return ssServiceId;
    }

    public String getSsWorkerId() {
        return ssWorkerId;
    }

    public String getSsUserId() {
        return ssUserId;
    }

    public Serviceuse getSu() {
        return su;
    }

    public List<Serviceuse> getSus() {
        return sus;
    }

    public void setSus(List<Serviceuse> sus) {
        this.sus = sus;
    }


}
