/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import org.json.JSONException;
import repository.Repository;
import repository.entities.Service;

/**
 *
 * @author liyunhong
 */
@Named(value = "webApplication")
@RequestScoped
public class WebApplication {
    private List<Service> webServices;
    private Service ser;
    private String serviceId;
    private String serviceName;
    private String serviceThum;
    private String serviceType;
    private String serviceDes;
    
    @EJB
    private Repository ejb;
    
    /**
     * Creates a new instance of WebApplication
     */
    public WebApplication() throws ClassNotFoundException, SQLException, IOException, JSONException {
        this.webServices = new ArrayList<Service>();
        findWebServices();
        this.ser = new Service();
        this.serviceDes = "";
        this.serviceId = "";
        this.serviceName = "";
        this.serviceThum = "";
        this.serviceType = "";
    }
    
    //initial
    @PostConstruct
    public void init()
    {
        this.webServices = webServices;
    }
    
    
    
    public void findWebServices() throws ClassNotFoundException, SQLException, IOException, JSONException
    {
        List<webModel> wm = webController.getDataInJSON();
        for(webModel w : wm)
        {
            Service s = new Service();
            s.setServicename(w.getServiceName());
            s.setServicetype(w.getServiceType());
            s.setThumbnail(w.getServiceThum());
            s.setDescription(w.getServiceDes());
            webServices.add(s);
        }
    }
    
    
    public void addService(Service s) throws Exception
    {
        try {
            //Service ser = createNewService(this.serviceName,this.serviceType,this.serviceThum,this.serviceDes);          
            ejb.addService(s);
        }
        catch(Exception ex){
            System.out.println("Some  error occurs");
        }
    }
    
    
   //create a new service to faciliate user add it to the db
    public Service createNewService(String name, String type, String thumbnail, String description)
    {
        Service ser = new Service(name,type);        
        ser.setThumbnail(thumbnail);
        ser.setDescription(description);
        return ser;
    }

    public List<Service> getWebServices() {
        return webServices;
    }

    public void setWebServices(List<Service> webServices) {
        this.webServices = webServices;
    }

    public Service getSer() {
        return ser;
    }

    public void setSer(Service ser) {
        this.ser = ser;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceThum() {
        return serviceThum;
    }

    public void setServiceThum(String serviceThum) {
        this.serviceThum = serviceThum;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceDes() {
        return serviceDes;
    }

    public void setServiceDes(String serviceDes) {
        this.serviceDes = serviceDes;
    }
    
}
