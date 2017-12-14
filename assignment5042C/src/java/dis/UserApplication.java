/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dis;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.eclipse.persistence.jpa.jpql.Assert;
import repository.JPARepositoryImpl;
import repository.Repository;
import repository.entities.Userinfo;

/**
 *
 * @author liyunhong
 */
@Named(value = "userApplication")
@ApplicationScoped
public class UserApplication {
    
    @EJB
    private Repository ejb;
    
    private JPARepositoryImpl imp;
    
    private List<Userinfo> userList;
    private String id;
    private String firstname;
    private String lastname;
    private String address;
    private String phonenumber;
    private String usertype;
    private String email;
    private String password;
    
    /**
     * Creates a new instance of UserApplication
     */
    public UserApplication() throws Exception {
        imp = new JPARepositoryImpl();
        findAllUsers();
        this.id = "";
        this.firstname = "";
        this.lastname = "";
        this.address = "";
        this.phonenumber = "";
        this.usertype = "";
        this.email = "";
        this.password = "";
    }
    
        //initial
    @PostConstruct
    public void init()
    {
        this.userList = userList;
    }
    
    //get all users by calling JPQL
    public void findAllUsers() throws Exception {    
        List<Userinfo> users = imp.getAllUsers();
        this.userList = users;
    }

    
    //add a new user
    public void addUser() throws Exception
    {
        try {
            Userinfo user = createNewUser(this.lastname,this.firstname,this.address,this.phonenumber, this.usertype, this.email, this.password);          
            System.out.println(user.getAddress());
            ejb.addUserInfo(user);
            this.userList.add(user);
        }
        catch (EJBException e) {
        @SuppressWarnings("ThrowableResultIgnored")
        Exception cause = e.getCausedByException();
        if (cause instanceof ConstraintViolationException) {
            @SuppressWarnings("ThrowableResultIgnored")
            ConstraintViolationException cve = (ConstraintViolationException) e.getCausedByException();
            for (Iterator<ConstraintViolation<?>> it = cve.getConstraintViolations().iterator(); it.hasNext();) {
                ConstraintViolation<? extends Object> v = it.next();
                System.err.println(v);
                System.err.println("==>>"+v.getMessage());
            }
        }
        Assert.fail("ejb exception");
    }
    }
    
    //create a new user to faciliate administor add it to the db
    public Userinfo createNewUser(String ln, String fn, String add, String phNum, String type, String email, String passwd)
    {
        int phoneNum = Integer.valueOf(phNum);
        String encrptPass = sha256(passwd);
        Userinfo user = new Userinfo(ln,fn,add,phoneNum,type,email,encrptPass);
        System.out.println(encrptPass);  
        System.out.println(user.getEmail());
        return user;
    }
    
    //search for specific user therefore help administor change value or delete
    public void findUserCom() throws Exception
    {
        List<Userinfo> searchUser = new ArrayList<Userinfo>();
        try{
            searchUser = imp.searchUserCombine(this.lastname, this.firstname, this.usertype, this.email);   
        }
        catch(Exception e)
        {
            System.out.println("Error occurs");
        }
        this.userList =  searchUser;
    }
    
    //delete a user 
    public void delUser(int newId) throws Exception
    {
        try{
            ejb.removeUser(newId);
        }
        catch(Exception e){
            System.out.println("Error occurs");
        }
    }
    
    //find a user by id.
    public Userinfo findUser() throws Exception
    {
        int newId = Integer.parseInt(this.id);
        Userinfo user = new Userinfo();
        try{
            user = ejb.searchUserById(newId);
        }
        catch(Exception e)
        {
            System.out.println("Error occurs");
        }
        return user;
    }
    
    
    //update a user
    public void updateUserInfo() throws Exception
    {
        Userinfo user = new Userinfo();
        try{
            user = findUser();
            user.setLastname(lastname);
            user.setFirstname(firstname);
            user.setAddress(address);
            String encryptPass = sha256(password);
            user.setPassword(encryptPass);
            user.setPhonenumber(Integer.valueOf(this.phonenumber));
            user.setEmail(email);
            user.setUsertype(usertype);
            ejb.editUser(user);
            for(Userinfo u : this.userList)
            {
                if( Objects.equals(u.getId(), user.getId()))
                {
                    this.userList.set(userList.indexOf(u), user);
                }
            }
        }
        catch(Exception e)
        {
            System.out.print("Error occurs");
        }
    }
    
    
    //encrpt password to SHA-256
    public static String sha256(String base) {
    try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
           throw new RuntimeException(ex);
        }
    }
    
    //get current user
    public void selectedUser(Userinfo user)
    {
        //id = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("serviceid"));
        //service = getService();
        this.id = user.getId() + "";
        this.lastname = user.getLastname();
        this.firstname = user.getFirstname();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.phonenumber = user.getPhonenumber() + "";
        this.password = user.getPassword();
        this.usertype = user.getUsertype();
    }
    
    
    public void clear()
    {
        this.id = "";
        this.lastname = "";
        this.firstname = "";
        this.email = "";
        this.address = "";
        this.phonenumber = "";
        this.password = "";
        this.usertype = "";
    }
    
    public List<Userinfo> getUserList() {
        return userList;
    }

    public void setUserList(List<Userinfo> userList) {
        this.userList = userList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    
    
    
}
