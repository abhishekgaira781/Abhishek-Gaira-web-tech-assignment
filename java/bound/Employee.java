package bound;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String designation;
    
    // PropertyChangeSupport manages the list of listeners and dispatches events
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Employee() {
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String newDesignation) {
        String oldDesignation = this.designation;
        this.designation = newDesignation;
        // Fire property change event to notify listeners
        this.pcs.firePropertyChange("designation", oldDesignation, newDesignation);
    }
}
