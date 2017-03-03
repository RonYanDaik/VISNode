package visnode.executor;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import visnode.commons.Image;

/**
 * Input node representation
 */
public class InputNode implements Node {

    /** Input image */
    private final Image imagem;
    /** Node connector */
    private final NodeConnector connector;

    /**
     * Creates a new input node
     *
     * @param imagem
     */
    public InputNode(Image imagem) {
        this.imagem = imagem;
        this.connector = new NodeConnector(this);
    }

    @Override
    public Object getParameter(String attribute) {
        return null;
    }

    @Override
    public Object getAttribute(String attribute) {
        if (attribute.equals("image")) {
            return imagem;
        }
        return null;
    }
    
    @Override
    public void addParameter(String parameter, Object value) {
    }
    
    @Override
    public List<NodeParameter> getInputParameters() {
        return new ArrayList<>();
    }

    @Override
    public List<NodeParameter> getOutputParameters() {
        List<NodeParameter> list = new ArrayList<>();
        list.add(new NodeParameter("image", Image.class));
        return list;
    }

    @Override
    public NodeConnector getConnector() {
        return connector;
    }
    
    @Override
    public visnode.pdi.Process executeProcess(String attribute) throws Exception {
        return null;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
    }
    
    public String getName() {
        return "Input";
    }

}
