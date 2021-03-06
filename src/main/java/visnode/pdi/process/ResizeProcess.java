package visnode.pdi.process;

import org.paim.commons.Image;
import org.paim.commons.ImageFactory;
import visnode.commons.Input;
import visnode.commons.Output;

/**
 * Resize image process
 */
public class ResizeProcess implements visnode.pdi.Process {

    /** Resize process */
    private final org.paim.pdi.ResizeProcess process;
    
    /**
     * Creates a new resize process
     * 
     * @param image 
     * @param sizeX 
     * @param sizeY 
     */
    public ResizeProcess(@Input("image") Image image, @Input("sizeX") Double sizeX, @Input("sizeY") Double sizeY) {
        Image resultImage = image;
        if (image == null) {
            resultImage = ImageFactory.buildEmptyImage();
        }
        this.process = new org.paim.pdi.ResizeProcess(new Image(resultImage), sizeX, sizeY);
        
    }

    @Override
    public void process() {
        process.process();
    }
    
    /**
     * Returns the output image
     *
     * @return Image
     */
    @Output("image")
    public Image getImage() {
        return process.getOutput();
    }

}
