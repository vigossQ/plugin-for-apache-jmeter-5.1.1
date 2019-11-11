package org.apache.jmeter.gui.action;

import org.apache.jmeter.autotest.gui.UploadDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

/**
 * @author vigoss
 * @date 2019/11/5
 */
public class CreateUploadDialog extends AbstractAction {
    private static final Set<String> commands = new HashSet<>();

    static {
        commands.add(ActionNames.UPLOAD);
    }

    private UploadDialog dialog;

    @Override
    public Set<String> getActionNames() {
        return commands;
    }

    private UploadDialog createUploadDialog(ActionEvent event) {
        JFrame parent = getParentFrame(event);
        return new UploadDialog(parent);
    }

    /**
     * @see Command#doAction(ActionEvent)
     */
    @Override
    public void doAction(ActionEvent e) {
        // we create the dialog upon first display event only
        if (dialog == null) {
            dialog = createUploadDialog(e);
        }
        dialog.setVisible(true);
    }
}
