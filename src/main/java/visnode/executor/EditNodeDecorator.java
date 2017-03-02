package visnode.executor;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * Node decorator that add editing capabilities
 */
public class EditNodeDecorator implements Node {

    /** Decorated node */
    private final Node decorated;
    /** Node position */
    private Point position;
    
    /**
     * Creates a new node decorator for editing 
     * 
     * @param decorated 
     */
    public EditNodeDecorator(Node decorated) {
        this(decorated, new Point());
    }
    
    /**
     * Creates a new node decorator for editing 
     * 
     * @param decorated
     * @param position 
     */
    public EditNodeDecorator(Node decorated, Point position) {
        this.decorated = decorated;
        this.position = new Point(position);
    }

    /**
     * Returns the position of the node
     * 
     * @return Point
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Sets the position of the node
     * 
     * @param position 
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Returns the decorated node
     * 
     * @return Node
     */
    public Node getDecorated() {
        return decorated;
    }
    
    @Override
    public Object getAttribute(String attribute) {
        return decorated.getAttribute(attribute);
    }
    
    @Override
    public void addParameter(String parameter, Object value) {
        decorated.addParameter(parameter, value);
    }
    
    @Override
    public List<NodeParameter> getInputParameters() {
        return decorated.getInputParameters();
    }

    @Override
    public List<NodeParameter> getOutputParameters() {
        return decorated.getOutputParameters();
    }

    @Override
    public NodeConnector getConnector() {
        return decorated.getConnector();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        decorated.addPropertyChangeListener(listener);
    }

    public String getName() {
        return decorated.getName();
    }
    
}