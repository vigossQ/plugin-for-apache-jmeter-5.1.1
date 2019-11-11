package org.apache.jmeter.autotest.gui;

import org.apache.jmeter.util.JMeterUtils;
import org.apache.jmeter.util.LocaleChangeEvent;
import org.apache.jmeter.util.LocaleChangeListener;
import org.apache.jorphan.gui.JLabeledChoice;
import org.apache.jorphan.gui.JLabeledTextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author vigoss
 * @date 2019/11/5
 */
public class UploadDialog extends JDialog implements ActionListener, ChangeListener, LocaleChangeListener {

    private static final Logger logger = LoggerFactory.getLogger(UploadDialog.class);

    private JLabeledChoice projectList;

    private JButton uploadButton;

    private JButton cancelButton;

    private JLabeledTextField usernameTF;

    private JLabeledTextField passwordTF;

    public UploadDialog(JFrame parent) {
        super(parent, JMeterUtils.getResString("upload"), false); //$NON-NLS-1$
        init();
        JMeterUtils.addLocaleChangeListener(this);
    }

    private void init() {
        this.getContentPane().setLayout(new BorderLayout(10, 10));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }

    @Override
    public void localeChanged(LocaleChangeEvent event) {

    }
}
