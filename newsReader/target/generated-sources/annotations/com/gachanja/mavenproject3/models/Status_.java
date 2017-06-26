package com.gachanja.mavenproject3.models;

import com.gachanja.mavenproject3.models.Articles;
import com.gachanja.mavenproject3.models.Authors;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-26T14:27:21")
@StaticMetamodel(Status.class)
public class Status_ { 

    public static volatile CollectionAttribute<Status, Articles> articlesCollection;
    public static volatile SingularAttribute<Status, Integer> statusid;
    public static volatile SingularAttribute<Status, Date> datemodified;
    public static volatile SingularAttribute<Status, String> description;
    public static volatile CollectionAttribute<Status, Authors> authorsCollection;
    public static volatile SingularAttribute<Status, Date> datecreated;

}