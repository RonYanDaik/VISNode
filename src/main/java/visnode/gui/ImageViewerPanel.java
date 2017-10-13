package visnode.gui;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import org.paim.commons.Image;
import visnode.application.OutputImageFactory;
import visnode.application.ActionExportImage;
import visnode.commons.swing.WindowFactory;

/**
 * Image viewer dialog
 */
public class ImageViewerPanel extends JPanel {

    /** Image */
    private final Image image;

    /**
     * Creates a new image viewer dialog
     * 
     * @param image 
     */
    public ImageViewerPanel(Image image) {
        super();
        this.image = image;
        initGui();
    }
    
    /**
     * Shows the dialog
     * 
     * @param image 
     */
    public static void showDialog(Image image) {
        WindowFactory.frame().title("Image").create((container) -> {
            container.add(new ImageViewerPanel(image));
        }).setVisible(true);
    }
    
    /**
     * Initializes the interface
     */
    private void initGui() {
        setLayout(new BorderLayout());
        add(buildToolbar(), BorderLayout.NORTH);
        add(buildInfo(), BorderLayout.SOUTH);
        add(new JLabel(new ImageIcon(OutputImageFactory.getBuffered(image))));
    }
    
    /**
     * Builds the tool bar
     * 
     * @return 
     */
    private JComponent buildToolbar() {
        JToolBar toolbar = new JToolBar();
        toolbar.add(new ActionExportImage(image));
        return toolbar;
    }
    
    /**
     * Builds the info panel
     * 
     * @return JComponent
     */
    private JComponent buildInfo() {
        JLabel info = new JLabel();
        info.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 0));
        info.setText(String.format("%sx%s pixels     range(%s/%s)", 
                    image.getWidth(), 
                    image.getHeight(),
                    image.getPixelValueRange().getLower(),
                    image.getPixelValueRange().getHigher()
        ));
        return info;
    }
    
}
