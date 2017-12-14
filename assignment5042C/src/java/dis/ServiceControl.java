package dis;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import repository.entities.Service;

/**
 *
 * @author liyunhong
 */
@Named(value = "serviceControl")
@RequestScoped
public class ServiceControl {

    private String serviceName;
    private int serviceid;
    private Service service;
    //private JPARepositoryImpl imp;

    
    public ServiceControl() throws Exception {
        //System.out.println((FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("serviceid")));
        serviceid = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("serviceid"));
        service = getService();
        serviceName = service.getServicename() + " | " + ServiceApplication.APP_TITLE;
       // imp = new JPARepositoryImpl();
    }

    public String getServiceName() {
        return serviceName;
    }


    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Service getService() throws Exception{
        if(service == null) {
            // Get application context bean service Application
            //System.out.print(serviceid);
            ELContext context = FacesContext.getCurrentInstance().getELContext();
            ServiceApplication app = (ServiceApplication) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context, null, "serviceApplication");           
            //ServiceApplication app = new ServiceApplication();
            service = app.findOneService(serviceid);
            return service;

        }
        return service;
    }    
    
}
