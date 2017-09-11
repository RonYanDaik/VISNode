package visnode.gui;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import visnode.application.ProcessMetadata;
import visnode.commons.http.Http;
import visnode.commons.swing.components.CodeEditor;
import visnode.commons.swing.components.MarkdownViewer;
import visnode.pdi.Process;

/**
 * Panel with information about the process
 */
public class ProcessInformationPane extends JPanel {

    /** Process type */
    private final Class<? extends Process> type;
    /** Process metadata */
    private final ProcessMetadata metadata;

    /**
     * Creates a new Process Info panel
     *
     * @param type
     */
    public ProcessInformationPane(Class<? extends Process> type) {
        super();
        this.type = type;
        this.metadata = ProcessMetadata.fromClass(type);
        initGui();
    }

    /**
     * Initializes the interface
     */
    private void initGui() {
        setLayout(new BorderLayout());
        add(buildTabs());
    }

    /**
     * Build the tabs
     *
     * @return JComponent
     */
    private JComponent buildTabs() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Information", buildInformationPane());
        tabs.add("Code", buildCodePane());
        return tabs;
    }

    /**
     * Builds the information pane
     *
     * @return JComponent
     */
    private JComponent buildInformationPane() {
        MarkdownViewer viewer = new MarkdownViewer();
        add(viewer);
        if (metadata.getHelpUrl() != null) {
            viewer.loadUrl(metadata.getHelpUrl());
        }
        return viewer;
    }

    /**
     * Builds the code pane
     *
     * @return JComponent
     */
    private JComponent buildCodePane() {
        CodeEditor textArea = new CodeEditor();
        new Http().get(metadata.getCodeUrl()).thenAccept((result) -> {
            textArea.setText(result.asString());
        });
        textArea.setEditable(false);
        return textArea;
    }

    /**
     * Shows the pane in a dialog
     *
     * @param process
     */
    public static void showDialog(Class<? extends Process> process) {
        ProcessInformationPane pane = new ProcessInformationPane(process);
        JDialog dialog = new JDialog();
        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(pane);
        dialog.setModal(true);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

}
