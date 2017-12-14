/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5042appclient.gui;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import repository.entities.Service;

/**
 *
 * @author liyunhong
 */
public interface applicantGUI {
    
    /**
     * Clear all the TextField in the GUI
     */
    void clearTextFields();

    /**
     * Display a message in a dialog box
     *
     * @param message - the message to display
     */
    void displayMessageInDialog(String message);

    /**
     * Display the details of the Services
     *
     * @param Service - the details of the Service to display
     */
    void displayServiceDetails(Service service);
    
    /**
     * Display the details of the selected Services
     *
     * @param Service - the details of the selected Service to display
     */
    void displaySelectedServiceDetails(Service service);
    
    /**
     * Display the details of the Services
     *
     * @param Services - the details of the Services to display
     */
    void displayServiceDetails(List<Service> Services);
    
    /**
     * Return the ID of the Service selected in table
     *
     * @return the ID of the selected Service
     */
    int getSelectedServiceId() throws Exception;
    
    
    /**
     * Return the Add Service button
     *
     * @return the attribute addButton
     */
    //JButton getAddButton();

    /**
     * Return the Close Window button
     *
     * @return the attribute closeButton
     */
    JButton getCloseButton();
    
    /**
     * Return the Search Service by ID button
     *
     * @return the attribute searchButton
     */
    JButton getSearchButton();

    /**
     * Return the Display All Services button
     *
     * @return the attribute viewButton
     */
    JButton getViewButton(); 
    
    /**
     * Return the Update Service Details button
     *
     * @return the attribute updateButton
     */
   // public JButton getUpdateButton();

    /**
     * Return the Delete Service Details button
     *
     * @return the attribute deleteButton
     */
   // public JButton getDeleteButton();

    /**
     * Return the Service Details table
     *
     * @return the attribute ServiceTable
     */
    public JTable getServiceTable();

    /**
     * Return the details of the Services to add to the repository
     *
     * @return a Service object that contains all the details
     */
    Service getServiceDetails();

    /**
     * Return the id of the Service to search the repository for
     *
     * @return the id of the Service to search the repository for
     */
    int getServiceId();
    
    String getServiceName();
    
    String getServiceType();
    
    /**
     * Indicate whether any Service is selected
     *
     * @return true if a Service is selected, or false otherwise
     */
    boolean isServiceSelected();
}