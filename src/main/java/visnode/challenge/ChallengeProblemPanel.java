package visnode.challenge;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JPanel;
import visnode.application.Messages;
import visnode.commons.swing.WindowFactory;
import visnode.commons.swing.components.MarkdownViewer;

/**
 * Challenge problem panel
 */
public class ChallengeProblemPanel extends JPanel {

    /** Challenge */
    private final Challenge challenge;

    /**
     * Creates a new problem panel
     *
     * @param challenge
     */
    private ChallengeProblemPanel(Challenge challenge) {
        this.challenge = challenge;
        initGui();
    }

    /**
     * Shows the dialog
     *
     * @param challenge
     */
    public static void showDialog(Challenge challenge) {
        Messages.get().message("challenge").subscribe((msg) -> {
            WindowFactory.modal().title(msg).create((container) -> {
                container.setBorder(null);
                container.add(new ChallengeProblemPanel(challenge));
            }).setVisible(true);
        });
    }

    /**
     * Initializes the interface
     */
    private void initGui() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 500));
        add(buildContainer());
    }

    /**
     * Build the problem container
     *
     * @return JComponent
     */
    private JComponent buildContainer() {
        MarkdownViewer viewer = new MarkdownViewer();
        viewer.load(challenge.getProblem());
        return viewer;
    }

}
