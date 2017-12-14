/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5042appclient;

import assignment5042appclient.gui.TableGUIImpl;
import assignment5042appclient.gui.applicantGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import repository.JPARepositoryImpl;
import repository.Repository;
import repository.RepositoryFactory;
import repository.entities.Service;

/**
 *
 * @author liyunhong
 */
public class Main implements ActionListener, ListSelectionListener {

    private String name;

    private final Repository serviceRepository;
    private applicantGUI gui;

    public Main(String name) throws Exception {
        this.name = name;
        this.serviceRepository = new JPARepositoryImpl();
    }

    public Main() throws Exception {
        this.serviceRepository = new JPARepositoryImpl();
    }    
    
    public void initView() {
        this.gui = new TableGUIImpl(this, this);
        this.displayAllProperties();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == gui.getViewButton()) {
            this.displayAllProperties();
        }  else if (event.getSource() == gui.getSearchButton()) {
            if(gui.getServiceType() != " "){
                this.searchPropertyByType();
            } else if (gui.getServiceName() != " "){
                this.searchPropertyByName();
            } else{
                this.searchPropertyById();
            }
        }  else {
            this.serviceRepository.close();
            System.exit(0);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        if ((event.getSource() == this.gui.getServiceTable().getSelectionModel())
            && (! event.getValueIsAdjusting()))
        {
            try
            {
                if (this.gui.isServiceSelected()) {
                    int serviceId = this.gui.getSelectedServiceId();
                
                    Service property = RepositoryFactory.getInstance().findServiceById(serviceId);
                    this.gui.displaySelectedServiceDetails(property);
                }               
            }
            catch (Exception e)
            {
                gui.displayMessageInDialog(e.getMessage());
            }
        }
    }
    
  
    public void searchPropertyById() {
        
        int id = this.gui.getServiceId();
        
        try {
            
            List<Service> service = this.serviceRepository.searchServiceById(id);
            
            if (service != null) {
                this.gui.displayServiceDetails(service);
            } else {
                this.gui.displayMessageInDialog("Service not found");
            }  
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search service by ID: " + ex.getMessage());
        } finally {
            this.gui.clearTextFields();
        }
    }

    public void searchPropertyByName() {
        
        String name = this.gui.getServiceName();
        
        try {
            
            List<Service> service = this.serviceRepository.searchServiceByName(name);
            
            if (service != null) {
                this.gui.displayServiceDetails(service);
            } else {
                this.gui.displayMessageInDialog("Service not found");
            }  
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search service by Name: " + ex.getMessage());
        } finally {
            this.gui.clearTextFields();
        }
    }
    
    
        public void searchPropertyByType() {
        
        String type = this.gui.getServiceType();
        
        try {
            
            List<Service> service = this.serviceRepository.searchServiceByType(type);
            
            if (service != null) {
                this.gui.displayServiceDetails(service);
            } else {
                this.gui.displayMessageInDialog("Service not found");
            }  
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search service by Type: " + ex.getMessage());
        } finally {
            this.gui.clearTextFields();
        }
    }
    
    private void displayAllProperties() {
        try {
            List<Service> services = this.serviceRepository.getAllServices();
            
            if (services != null) {
                this.gui.displayServiceDetails(services);
            }
            
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to retrieve properties: " + ex.getMessage());
        }
    }

   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        try {
            final Main agency = new Main("Monash Real Estate Agency");
            //JDK 1.7
          /*  SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    agency.initView();
                }
            });
            */
//            //JDK 1.8
            SwingUtilities.invokeLater(()-> {
                agency.initView();
            });
        } catch (Exception ex) {
            System.out.println("Failed to run application: " + ex.getMessage());
        }
    }

}

