/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author liyunhong
 */
@Entity
@Table(name = "SERVICEUSE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Serviceuse.findAll", query = "SELECT s FROM Serviceuse s"),
    @NamedQuery(name = "Serviceuse.findByServiceuseid", query = "SELECT s FROM Serviceuse s WHERE s.serviceuseid = :serviceuseid"),
    @NamedQuery(name = "Serviceuse.findByServicedate", query = "SELECT s FROM Serviceuse s WHERE s.servicedate = :servicedate")})
public class Serviceuse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SERVICEUSEID")
    private Integer serviceuseid;
    @Size(max = 50)
    @Column(name = "SERVICEDATE")
    private String servicedate;
    @JoinColumn(name = "SERVICEID", referencedColumnName = "SERVICEID")
    @ManyToOne
    private Service serviceid;
    @JoinColumn(name = "WORKERID", referencedColumnName = "ID")
    @ManyToOne
    private Userinfo workerid;
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    @ManyToOne
    private Userinfo id;

    public Serviceuse() {
    }

    public Serviceuse(Integer serviceuseid) {
        this.serviceuseid = serviceuseid;
    }
    
    public Serviceuse(String sDate, Service sService, Userinfo sWorker, Userinfo sUser)
    {
        this.servicedate = sDate;
        this.serviceid = sService;
        this.workerid = sWorker;
        this.id = sUser;        
    }

    public Integer getServiceuseid() {
        return serviceuseid;
    }

    public void setServiceuseid(Integer serviceuseid) {
        this.serviceuseid = serviceuseid;
    }

    public String getServicedate() {
        return servicedate;
    }

    public void setServicedate(String servicedate) {
        this.servicedate = servicedate;
    }

    public Service getServiceid() {
        return serviceid;
    }

    public void setServiceid(Service serviceid) {
        this.serviceid = serviceid;
    }

    public Userinfo getWorkerid() {
        return workerid;
    }

    public void setWorkerid(Userinfo workerid) {
        this.workerid = workerid;
    }

    public Userinfo getId() {
        return id;
    }

    public void setId(Userinfo id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceuseid != null ? serviceuseid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Serviceuse)) {
            return false;
        }
        Serviceuse other = (Serviceuse) object;
        if ((this.serviceuseid == null && other.serviceuseid != null) || (this.serviceuseid != null && !this.serviceuseid.equals(other.serviceuseid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "repository.entities.Serviceuse[ serviceuseid=" + serviceuseid + " ]";
    }
    
}
