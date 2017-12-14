/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis;

import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import repository.JPARepositoryImpl;
import repository.Repository;
import repository.entities.Service;
import repository.entities.Subservice;

/**
 *
 * @author liyunhong
 */
@Named(value = "subServiceControl")
@ApplicationScoped
public class SubServiceControl {
    
    @EJB
    private Repository ejb;
   
    private JPARepositoryImpl imp;
    private String subSerName;
    private String subSerId;
    private Service service;
    private String serviceid;
    private List<Subservice> subSers;
    //private Subservice subservice;
    
    /**
     * Creates a new instance of SubServiceControl
     */
    public SubServiceControl() throws Exception{
        this.serviceid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("serviceid");
        this.service = getService();
        this.imp = new JPARepositoryImpl(); 
        this.subSerName = "";
        this.subSerId = "";
        //findAllSubServices();
        
    }
    
    //initial
    @PostConstruct
    public void init()
    {
        this.subSers = subSers;
    }
    
    
    public Service getService() throws Exception{
        if(service == null) {
            ELContext context = FacesContext.getCurrentInstance().getELContext();
            ServiceApplication app = (ServiceApplication) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context, null, "serviceApplication");
            int id = Integer.valueOf(serviceid);
            service = app.findOneService(id);
            return service;
        }
        return service;
    }  


    //get all services by calling API
    public void findAllSubServices() throws Exception { 
        int id = Integer.valueOf(serviceid);
        List<Subservice> serviceuses = imp.findSubServiceInOne(service);
        this.subSers = serviceuses;
    }
    
    //add a new service
    public void addSubService() throws Exception
    {
        try {
            Subservice ser = createNewSubService(this.subSerName);  
            ser.setServiceid(service);
            ejb.addSubService(ser); 
            this.subSers.add(ser);
        }
        catch(Exception ex){
            System.out.println("Some  error occurs");
        }
    }
    
    //create a new service to faciliate user add it to the db
    public Subservice createNewSubService(String name)
    {
        Subservice ser = new Subservice(name);        
        return ser;
    }
    
    //search for specific service therefore help user change value or delete
    public Subservice findSubService() throws Exception
    {
        int newId = Integer.parseInt(this.subSerId);
        System.out.println(newId);
        Subservice searchService = new Subservice();
        try{
            searchService = ejb.findSubService(newId); 
        }
        catch(Exception e)
        {
            System.out.println("Error occurs");
        }
        return searchService;
    }
    
    //delete a service 
    public void delSubService(int newId) throws Exception
    {
        //int newId = Integer.parseInt(this.subSerId);
        //Subservice ser = new Subservice();
        try{
            ejb.removeSubService(newId); 
        }
        catch(Exception e){
            System.out.println("Error occurs");
        }
    }
    
    
    //update a service
    public void updateService(Subservice ser) throws Exception
    {
        //Subservice ser = new Subservice();
        try{
            //ser = findSubService();
            ser.setSubname(subSerName);
            ejb.editSubService(ser);
            for(Subservice s : this.subSers)
            {
                if( Objects.equals(s.getId(), ser.getId()))
                {
                    this.subSers.set(subSers.indexOf(s), ser);
                }
            }
        }
        catch(Exception e)
        {
            System.out.print("Error occurs");
        }
    }

    public String getSubSerName() {
        return subSerName;
    }

    public void setSubSerName(String subSerName) {
        this.subSerName = subSerName;
    }

    public String getSubSerId() {
        return subSerId;
    }

    public void setSubSerId(String subSerId) {
        this.subSerId = subSerId;
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public List<Subservice> getSubSers() {
        return subSers;
    }

    public void setSubSers(List<Subservice> subSers) {
        this.subSers = subSers;
    }

}
