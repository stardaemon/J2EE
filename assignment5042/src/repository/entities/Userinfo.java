/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author liyunhong
 */
@Entity
@Table(name = "USERINFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userinfo.findAll", query = "SELECT u FROM Userinfo u"),
    @NamedQuery(name = "Userinfo.findById", query = "SELECT u FROM Userinfo u WHERE u.id = :id"),
    @NamedQuery(name = "Userinfo.findByFirstname", query = "SELECT u FROM Userinfo u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "Userinfo.findByLastname", query = "SELECT u FROM Userinfo u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "Userinfo.findByAddress", query = "SELECT u FROM Userinfo u WHERE u.address = :address"),
    @NamedQuery(name = "Userinfo.findByPhonenumber", query = "SELECT u FROM Userinfo u WHERE u.phonenumber = :phonenumber"),
    @NamedQuery(name = "Userinfo.findByUsertype", query = "SELECT u FROM Userinfo u WHERE u.usertype = :usertype"),
    @NamedQuery(name = "Userinfo.findByEmail", query = "SELECT u FROM Userinfo u WHERE u.email = :email"),
    @NamedQuery(name = "Userinfo.findCombine", query = "SELECT u FROM Userinfo u WHERE u.lastname like :lastname and u.firstname like :firstname and u.usertype like :usertype and u.email like :email"),
    @NamedQuery(name = "Userinfo.findByPassword", query = "SELECT u FROM Userinfo u WHERE u.password = :password")})
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    //@NotNull
    private Integer id;
    @Size(max = 50)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Size(max = 50)
    @Column(name = "LASTNAME")
    private String lastname;
    @Size(max = 100)
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PHONENUMBER")
    //@NotNull
    //@Pattern(regexp = "[0-9]*", message = "Number Only")
    private Integer phonenumber;
    @Size(max = 20)
    @Column(name = "USERTYPE")
    private String usertype;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 150)
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(mappedBy = "workerid")
    private Collection<Serviceuse> serviceuseCollection;
    @OneToMany(mappedBy = "id")
    private Collection<Serviceuse> serviceuseCollection1;

    public Userinfo() {
    }

    public Userinfo(Integer id) {
        this.id = id;
    }

    public Userinfo(String ln, String fn, String add, int phNum, String type, String email, String passwd)
    {
        this.lastname = ln;
        this.firstname = fn;
        this.address = add;
        this.phonenumber = phNum;
        this.usertype = type;
        this.email = email;
        this.password = passwd;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Integer phonenumber) {
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

    @XmlTransient
    public Collection<Serviceuse> getServiceuseCollection() {
        return serviceuseCollection;
    }

    public void setServiceuseCollection(Collection<Serviceuse> serviceuseCollection) {
        this.serviceuseCollection = serviceuseCollection;
    }

    @XmlTransient
    public Collection<Serviceuse> getServiceuseCollection1() {
        return serviceuseCollection1;
    }

    public void setServiceuseCollection1(Collection<Serviceuse> serviceuseCollection1) {
        this.serviceuseCollection1 = serviceuseCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userinfo)) {
            return false;
        }
        Userinfo other = (Userinfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "repository.entities.Userinfo[ id=" + id + " ]";
    }
    
}
