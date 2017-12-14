package repository.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import repository.entities.Service;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-27T16:17:51")
@StaticMetamodel(Subservice.class)
public class Subservice_ { 

    public static volatile SingularAttribute<Subservice, String> subname;
    public static volatile SingularAttribute<Subservice, Integer> id;
    public static volatile SingularAttribute<Subservice, Service> serviceid;

}