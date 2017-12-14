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
@Table(name = "SUBSERVICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subservice.findAll", query = "SELECT s FROM Subservice s"),
    @NamedQuery(name = "Subservice.findById", query = "SELECT s FROM Subservice s WHERE s.id = :id"),
    @NamedQuery(name = "Subservice.findOneGroup", query = "SELECT s FROM Subservice s WHERE s.serviceid = :serviceid"),
    @NamedQuery(name = "Subservice.findBySubname", query = "SELECT s FROM Subservice s WHERE s.subname = :subname")})
public class Subservice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 40)
    @Column(name = "SUBNAME")
    private String subname;
    @JoinColumn(name = "SERVICEID", referencedColumnName = "SERVICEID")
    @ManyToOne
    private Service serviceid;

    public Subservice() {
    }
    
    public Subservice(String name){
        this.subname = name;
    }

    public Subservice(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public Service getServiceid() {
        return serviceid;
    }

    public void setServiceid(Service serviceid) {
        this.serviceid = serviceid;
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
        if (!(object instanceof Subservice)) {
            return false;
        }
        Subservice other = (Subservice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "repository.entities.Subservice[ id=" + id + " ]";
    }
    
}
