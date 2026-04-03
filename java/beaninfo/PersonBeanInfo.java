package beaninfo;

import beans.Person;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class PersonBeanInfo extends SimpleBeanInfo {
    
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            // Creating PropertyDescriptor for 'name' and 'age' properties
            PropertyDescriptor nameDescriptor = new PropertyDescriptor("name", Person.class);
            PropertyDescriptor ageDescriptor = new PropertyDescriptor("age", Person.class);
            
            return new PropertyDescriptor[] { nameDescriptor, ageDescriptor };
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
