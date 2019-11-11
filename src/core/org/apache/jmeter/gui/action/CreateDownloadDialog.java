package org.apache.jmeter.gui.action;

import org.apache.jmeter.autotest.gui.DownloadDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

/**
 * @author vigoss
 * @date 2019/11/5
 */
public class CreateDownloadDialog extends AbstractAction {
    private static final Set<String> commands = new HashSet<>();

    static {
        commands.add(ActionNames.DOWNLOAD);
    }

    private DownloadDialog dialog;

    @Override
    public Set<String> getActionNames() {
        return commands;
    }

    private DownloadDialog createDownloadDialog(ActionEvent event) {
        JFrame parent = getParentFrame(event);
        return new DownloadDialog(parent);
    }

    /**
     * @see Command#doAction(ActionEvent)
     */
    @Override
    public void doAction(ActionEvent e) {
        // we create the dialog upon first display event only
        if (dialog == null) {
            dialog = createDownloadDialog(e);
        }
        dialog.setVisible(true);
    }
}
