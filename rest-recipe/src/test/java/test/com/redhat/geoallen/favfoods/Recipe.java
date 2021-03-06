package test.com.redhat.geoallen.favfoods;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import java.io.InputStream;
import java.time.LocalDateTime;




public class Recipe {

    private static final Logger LOG = Logger.getLogger(Recipe.class);
      

    
    public String title;

    public String userid;

    // need to use the userid from the authenicated token
    
    public String author;

    //photo 
    
    public String image_name; 


    public String mimetype; 

    
    public String cuisine;

    
    public String course;

    
    public String tags;

    
    public List<String> ingredients;
    
    
    public String description;

    
    public String directions;

    
    public String source;

    
    public Integer prep_time;

    
    public Integer cook_time;

    
    public Integer servings;

    public String serving_unit;

    
    //public boolean public_access;


    public Recipe() {
    }

    

}
