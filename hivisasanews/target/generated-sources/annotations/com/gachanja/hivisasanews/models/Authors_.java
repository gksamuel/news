package com.gachanja.hivisasanews.models;

import com.gachanja.hivisasanews.models.Articles;
import com.gachanja.hivisasanews.models.Status;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-26T19:19:01")
@StaticMetamodel(Authors.class)
public class Authors_ { 

    public static volatile SingularAttribute<Authors, String> username;
    public static volatile CollectionAttribute<Authors, Articles> articlesCollection;
    public static volatile SingularAttribute<Authors, Long> mobilenumber;
    public static volatile SingularAttribute<Authors, Integer> authorid;
    public static volatile SingularAttribute<Authors, String> lastname;
    public static volatile SingularAttribute<Authors, Date> datecreated;
    public static volatile SingularAttribute<Authors, Date> datemodified;
    public static volatile SingularAttribute<Authors, Status> statusid;
    public static volatile SingularAttribute<Authors, String> firstname;
    public static volatile SingularAttribute<Authors, String> password;

}