package repository.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import repository.entities.Service;
import repository.entities.Userinfo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-27T16:17:51")
@StaticMetamodel(Serviceuse.class)
public class Serviceuse_ { 

    public static volatile SingularAttribute<Serviceuse, Userinfo> workerid;
    public static volatile SingularAttribute<Serviceuse, String> servicedate;
    public static volatile SingularAttribute<Serviceuse, Integer> serviceuseid;
    public static volatile SingularAttribute<Serviceuse, Userinfo> id;
    public static volatile SingularAttribute<Serviceuse, Service> serviceid;

}