/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5042appclient.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import repository.entities.Service;

/**
 *
 * @author liyunhong
 */
public class TableGUIImpl extends JFrame implements applicantGUI {
    private static final String[] TABLE_COLUMNS = {"Name", "ID", "Type","Description","Thumbnail"};

    private final JButton closeButton;
    //private final JButton addButton;
    private final JButton viewButton;
    private final JButton searchButton;
    //private final JButton updateButton;
    //private final JButton deleteButton;
    
    private final JPanel inputPanel;
    private final JPanel buttonPanel;

    private final JLabel IdLabel;
    private final JLabel nameLabel;
    private final JLabel typeLabel;

    private final JTextField IdTextField;
    private final JTextField nameTextField;
    private final JTextField typeTextField;
    
    private final JTable serviceTable;

    Service service;

    public TableGUIImpl(ActionListener actionListener, ListSelectionListener listSelectionListener) {
        super("Monash Real Estate Agency");

        // create buttons
        this.closeButton = new JButton("Close");
        this.viewButton = new JButton("View");
        this.searchButton = new JButton("Search");
       // this.addButton = new JButton("Add");
        //this.updateButton = new JButton("Update");
        //this.deleteButton = new JButton("Delete");

        // create container
        Container container = this.getContentPane();

        // create labels
        this.IdLabel = new JLabel("ID:");
        this.nameLabel = new JLabel("Name:");
        this.typeLabel = new JLabel("Type:");
        //this.sizeLabel = new JLabel("Size:");
        //this.priceLabel = new JLabel("Price:");

        // create text fields
        this.IdTextField = new JTextField();
        this.nameTextField = new JTextField();
        this.typeTextField = new JTextField();
        //this.sizeTextField = new JTextField();
        //this.priceTextField = new JTextField();
        
        // create table
        this.serviceTable = new JTable(new DefaultTableModel(TABLE_COLUMNS, 0));
        this.serviceTable.getSelectionModel().addListSelectionListener(listSelectionListener);       
        this.serviceTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        TableColumnModel propertyTableColumnModel = this.serviceTable.getColumnModel();
        propertyTableColumnModel.getColumn(0).setPreferredWidth(50);
        propertyTableColumnModel.getColumn(1).setPreferredWidth(300);
        propertyTableColumnModel.getColumn(2).setPreferredWidth(100);
        //propertyTableColumnModel.getColumn(3).setPreferredWidth(100);
        //propertyTableColumnModel.getColumn(4).setPreferredWidth(100);
        
        // create panels
        this.inputPanel = new JPanel();
        this.buttonPanel = new JPanel();

        // set layout manager
        container.setLayout(new BorderLayout());
        this.inputPanel.setLayout(new GridLayout(3,2));
        this.buttonPanel.setLayout(new GridLayout(1,3));

        // add action listeners
        this.closeButton.addActionListener(actionListener);
        //this.addButton.addActionListener(actionListener);
        this.viewButton.addActionListener(actionListener);
        this.searchButton.addActionListener(actionListener);
        //this.updateButton.addActionListener(actionListener);
        //this.deleteButton.addActionListener(actionListener);

        // add components
        this.inputPanel.add(IdLabel);
        this.inputPanel.add(IdTextField);
        this.inputPanel.add(nameLabel);
        this.inputPanel.add(nameTextField);
        this.inputPanel.add(typeLabel);
        this.inputPanel.add(typeTextField);
        //this.inputPanel.add(sizeLabel);
        //this.inputPanel.add(sizeTextField);
        //this.inputPanel.add(priceLabel);
        //this.inputPanel.add(priceTextField);

        // add buttons to panel
        //this.buttonPanel.add(this.addButton);
        //this.buttonPanel.add(this.updateButton);
        //this.buttonPanel.add(this.deleteButton);
        this.buttonPanel.add(this.searchButton);
        this.buttonPanel.add(this.viewButton);
        this.buttonPanel.add(this.closeButton);
        
        // add panels to content pane
        container.add(inputPanel,BorderLayout.NORTH);
        container.add(new JScrollPane(this.serviceTable), BorderLayout.CENTER);
        container.add(buttonPanel,BorderLayout.SOUTH);
       
        // change the default behaviour of the close button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setSize(650, 570);       
        this.setVisible(true);
        
        serviceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = serviceTable.rowAtPoint(evt.getPoint());
                int col = serviceTable.columnAtPoint(evt.getPoint());
               if (row >= 0 && col >= 0) {
                    serviceTable.getValueAt(row,col);
               }
            }
        });
        
    }

    @Override
    public void clearTextFields() {
        IdTextField.setText("");
        nameTextField.setText("");
        typeTextField.setText("");
    }

    @Override
    public void displayMessageInDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void displayServiceDetails(Service service) {
      this.clearServiceTable();
      ((DefaultTableModel)this.serviceTable.getModel()).addRow(new Object[]{service.getServiceid(), 
          service.getServicename(), service.getServicetype(), service.getDescription(), service.getThumbnail()});

    }

    private void clearServiceTable() {     
        int numberOfRow = this.serviceTable.getModel().getRowCount();
        
        if (numberOfRow > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) this.serviceTable.getModel();
            for (int index = (numberOfRow - 1); index >= 0; index --) {
                tableModel.removeRow(index);
            }            
        }
    }
        
    public boolean isServiceSelected() {
        return (this.serviceTable.getSelectedRow() >= 0);
    }
    
    @Override
    public void displaySelectedServiceDetails(Service service) {
        this.IdTextField.setText(String.valueOf(service.getServiceid()));
        this.nameTextField.setText(String.valueOf(service.getServicename()));
        this.typeTextField.setText(String.valueOf(service.getServicetype()));
    }

    @Override
    public void displayServiceDetails(List<Service> Services) {
       this.clearServiceTable();        
        for (Service service : Services) {
            ((DefaultTableModel)this.serviceTable.getModel()).addRow(new Object[]{service.getServiceid(), 
                service.getServicename(), service.getServicetype(), service.getDescription(), service.getThumbnail()});
        }
    }

    @Override
    public int getSelectedServiceId() throws Exception {
       int selectedRowIndex = this.serviceTable.getSelectedRow();      
        String propertyId = this.serviceTable.getValueAt(selectedRowIndex, 0).toString();
        return Integer.parseInt(propertyId); 
     }

    @Override
    public JButton getCloseButton() {
        return closeButton;   
    }

    @Override
    public JButton getSearchButton() {
        return searchButton;    
    }

    @Override
    public JButton getViewButton() {
        return viewButton;
    }

    @Override
    public JTable getServiceTable() {
        return serviceTable;    
    }

    @Override
    public Service getServiceDetails() {
        return new Service(Integer.parseInt(IdTextField.getText()), nameTextField.getText(), typeTextField.getText());		
    }

    @Override
    public int getServiceId() {
        String id = this.IdTextField.getText();
        return id == null || id.isEmpty() ? 0 : Integer.parseInt(id);
    }

    @Override
    public String getServiceName(){
        String name = this.nameTextField.getText();
        return name == null || name.isEmpty() ? " " : name; 
    }
    
    @Override
    public String getServiceType(){
        String type = this.typeTextField.getText();
         return type == null || type.isEmpty() ? " " : type;        
    }
    
    
}

