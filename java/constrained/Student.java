package constrained;

import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int age;
    
    // VetoableChangeSupport manages the list of listeners and dispatches events
    private final VetoableChangeSupport vcs = new VetoableChangeSupport(this);

    public Student() {
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
        this.vcs.addVetoableChangeListener(listener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        this.vcs.removeVetoableChangeListener(listener);
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int newAge) throws PropertyVetoException {
        // Implement the constraint logic
        if (newAge < 18) {
            throw new PropertyVetoException("Age cannot be less than 18", null);
        }
        
        int oldAge = this.age;
        // Notify listeners, any listener can throw a PropertyVetoException here
        this.vcs.fireVetoableChange("age", oldAge, newAge);
        
        // Only set the property if no veto occurred
        this.age = newAge;
    }
}
