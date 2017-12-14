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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author liyunhong
 */

@Entity
@Table(name = "SERVICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"),
    @NamedQuery(name = Service.GET_ALL_QUERY_NAME,query = "SELECT s FROM Service s"),
    @NamedQuery(name = "Service.findByServiceid", query = "SELECT s FROM Service s WHERE s.serviceid = :serviceid"),
    @NamedQuery(name = "Service.findByServicename", query = "SELECT s FROM Service s WHERE s.servicename = :servicename"),
    @NamedQuery(name = "Service.findByServicetype", query = "SELECT s FROM Service s WHERE s.servicetype = :servicetype"),
    @NamedQuery(name = "Service.findByThumbnail", query = "SELECT s FROM Service s WHERE s.thumbnail = :thumbnail"),
    @NamedQuery(name = "Service.findByDescription", query = "SELECT s FROM Service s WHERE s.description = :description"),
    @NamedQuery(name = "Service.findByIdNameType", query = "SELECT s FROM Service s WHERE s.serviceid = :serviceid and s.servicename = :servicename and s.servicetype = :servicetype")})
public class Service implements Serializable {

    @OneToMany(mappedBy = "serviceid")
    private Collection<Subservice> subserviceCollection;

    private static final long serialVersionUID = 1L;
    public static final String GET_ALL_QUERY_NAME = "Service.indAll";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SERVICEID")
    private Integer serviceid;
    @Size(max = 50)
    @Column(name = "SERVICENAME")
    private String servicename;
    @Size(max = 20)
    @Column(name = "SERVICETYPE")
    private String servicetype;
    @Size(max = 100)
    @Column(name = "THUMBNAIL")
    private String thumbnail;
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(mappedBy = "serviceid")
    private Collection<Serviceuse> serviceuseCollection;

    public Service() {
    }

    public Service(Integer serviceid) {
        this.serviceid = serviceid;
    }

    public Integer getServiceid() {
        return serviceid;
    }

    public Service(Integer serviceid, String servicename, String servicetype) {
        this.serviceid = serviceid;
        this.servicename = servicename;
        this.servicetype = servicetype;
    }

    public Service(String servicename, String servicetype)
    {
        this.servicename = servicename;
        this.servicetype = servicetype;
    }
    
    public void setServiceid(Integer serviceid) {
        this.serviceid = serviceid;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Serviceuse> getServiceuseCollection() {
        return serviceuseCollection;
    }

    public void setServiceuseCollection(Collection<Serviceuse> serviceuseCollection) {
        this.serviceuseCollection = serviceuseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceid != null ? serviceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.serviceid == null && other.serviceid != null) || (this.serviceid != null && !this.serviceid.equals(other.serviceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "repository.entities.Service[ serviceid=" + serviceid + " ]";
    }

    @XmlTransient
    public Collection<Subservice> getSubserviceCollection() {
        return subserviceCollection;
    }

    public void setSubserviceCollection(Collection<Subservice> subserviceCollection) {
        this.subserviceCollection = subserviceCollection;
    }
    
}
