package com.gachanja.mavenproject3.models;

import com.gachanja.mavenproject3.models.Articles;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-26T14:27:21")
@StaticMetamodel(Categories.class)
public class Categories_ { 

    public static volatile CollectionAttribute<Categories, Articles> articlesCollection;
    public static volatile SingularAttribute<Categories, Date> datemodified;
    public static volatile SingularAttribute<Categories, String> description;
    public static volatile SingularAttribute<Categories, Date> datecreated;
    public static volatile SingularAttribute<Categories, String> category;
    public static volatile SingularAttribute<Categories, Integer> categoryid;

}