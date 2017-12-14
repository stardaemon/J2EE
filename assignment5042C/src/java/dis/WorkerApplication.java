package dis;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import java.io.Serializable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
//import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import javax.enterprise.context.ApplicationScoped;
import repository.JPARepositoryImpl;
//import repository.JPARepositoryImplBean;
import repository.Repository;
import repository.entities.Service;

/**
 *
 * @author liyunhong
 */
@Named(value = "workerApplication")
@ApplicationScoped
//@ManagedBean(name = "workerApplication")
//@SessionScoped
public class WorkerApplication {

    @EJB
    private Repository ejb;
    
    private JPARepositoryImpl imp;
    private List<Service> services;
    private String serviceId;
    private String serviceName;
    private String serviceThum;
    private String serviceType;
    private String serviceDes;
    private List<Service> webServices;



    /**
     * Creates a new instance of WorkerApplication
     * @throws java.lang.Exception
     */
    public WorkerApplication() throws Exception {
        imp = new JPARepositoryImpl();
        findAllServices();
        webServices = new ArrayList<Service>();
        findWebServices();
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
        this.services = services;
    }
    
    
    //get all services by calling JPQL
    public void findAllServices() throws Exception {    
        List<Service> serviceuses = imp.getAllServices();
        this.services = serviceuses;
    }
    
    //read json from url
    private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
    
    
       
    //web server services.
    public void findWebServices() throws ClassNotFoundException, SQLException, IOException, JSONException
    {
        /*JSONObject json = readJsonFromUrl("http://localhost:54995/webresources/webController/getData");
        List<webModel> wmm = new ArrayList<>();
        for(int i = 0; i < json.length(); i++)
        {
            webModel wmn = new webModel();
            wmn.setServiceid(json.getInt("serviceid"));
            wmn.setServiceName(json.getString("servicename"));
            wmn.setServiceThum(json.getString("thumbnail"));
            wmn.setServiceType(json.getString("servicetype"));
            wmn.setServiceDes(json.getString("description"));
            wmm.add(wmn);
        }*/
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
    
    //add a new service
    public void addService() throws Exception
    {
        try {
            Service ser = createNewService(this.serviceName,this.serviceType,this.serviceThum,this.serviceDes);          
            ejb.addService(ser);
            this.services.add(ser);
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
    
    //search for specific service therefore help user change value or delete
    public Service findService() throws Exception
    {
        int newId = Integer.parseInt(this.serviceId);
        System.out.println(newId);
        Service searchService = new Service();
        try{
            searchService = ejb.searchServiceById(newId).get(0);
        }
        catch(Exception e)
        {
            System.out.println("Error occurs");
        }
        return searchService;
    }
    
    //delete a service 
    public void delService() throws Exception
    {
        int newId = Integer.parseInt(this.serviceId);
        Service ser = new Service();
        try{
            ejb.removeService(newId);
        }
        catch(Exception e){
            System.out.println("Error occurs");
        }
    }
    
    
    //update a service
    public void updateService() throws Exception
    {
        Service ser = new Service();
        try{
            ser = findService();
            ser.setServicename(this.serviceName);
            ser.setServicetype(this.serviceType);
            ser.setThumbnail(this.serviceThum);
            ser.setDescription(this.serviceDes);
            ejb.editService(ser);
            for(Service s : this.services)
            {
                if( Objects.equals(s.getServiceid(), ser.getServiceid()))
                {
                    this.services.set(services.indexOf(s), ser);
                }
            }
        }
        catch(Exception e)
        {
            System.out.print("Error occurs");
        }
    }
    
    //select a service
    public void selected(Service ser)
    {
        this.serviceName = ser.getServicename();
        this.serviceType = ser.getServicetype();
        this.serviceThum = ser.getThumbnail();
        this.serviceDes = ser.getDescription();
    }
    
    
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
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

    public List<Service> getWebServices() {
        return webServices;
    }

    public void setWebServices(List<Service> webServices) {
        this.webServices = webServices;
    }
    
    
}
