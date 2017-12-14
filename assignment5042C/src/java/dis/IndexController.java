package dis;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author liyunhong
 */
@Named(value = "indexController")
@RequestScoped
public class IndexController {

   private String serviceName;
    /**
     * Creates a new instance of IndexController
     */
    public IndexController() {
        serviceName = "Viewing Services" + " | " + ServiceApplication.APP_TITLE ;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
   
}
