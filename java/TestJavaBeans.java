import beans.Person;
import beaninfo.PersonBeanInfo;
import bound.Employee;
import constrained.Student;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.PropertyDescriptor;

public class TestJavaBeans {
    public static void main(String[] args) {
        System.out.println("--- Testing Person JavaBean ---");
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(25);
        System.out.println("Person Name: " + person.getName() + ", Age: " + person.getAge());

        System.out.println("\n--- Testing PersonBeanInfo ---");
        PersonBeanInfo info = new PersonBeanInfo();
        PropertyDescriptor[] pd = info.getPropertyDescriptors();
        System.out.println("Properties found by BeanInfo:");
        if (pd != null) {
            for (PropertyDescriptor p : pd) {
                System.out.println("- " + p.getName());
            }
        }

        System.out.println("\n--- Testing Bound Property (Employee) ---");
        Employee emp = new Employee();
        emp.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("Property '" + evt.getPropertyName() + "' changed from '" + 
                                   evt.getOldValue() + "' to '" + evt.getNewValue() + "'");
            }
        });
        emp.setDesignation("Software Engineer");
        emp.setDesignation("Senior Software Engineer");

        System.out.println("\n--- Testing Constrained Property (Student) ---");
        Student student = new Student();
        student.addVetoableChangeListener(new VetoableChangeListener() {
            @Override
            public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
                System.out.println("Attempting to change '" + evt.getPropertyName() + "' from " + 
                                   evt.getOldValue() + " to " + evt.getNewValue());
            }
        });
        
        try {
            student.setAge(20);
            System.out.println("Successfully set age to " + student.getAge());
            
            System.out.println("\nTrying to set age to 16...");
            student.setAge(16); // This should throw PropertyVetoException
        } catch (PropertyVetoException pve) {
            System.out.println("Vetoed! Exception caught: " + pve.getMessage());
        }
    }
}
