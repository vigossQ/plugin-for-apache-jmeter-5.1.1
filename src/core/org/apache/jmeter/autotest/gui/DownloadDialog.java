package org.apache.jmeter.autotest.gui;

import org.apache.jmeter.autotest.Project;
import org.apache.jmeter.autotest.util.PermissionUtil;
import org.apache.jmeter.autotest.util.ProjectUtil;
import org.apache.jmeter.gui.action.KeyStrokes;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jmeter.util.LocaleChangeEvent;
import org.apache.jmeter.util.LocaleChangeListener;
import org.apache.jorphan.gui.ComponentUtil;
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
import java.util.Arrays;

/**
 * @author vigoss
 * @date 2019/11/5
 */
public class DownloadDialog extends JDialog implements ActionListener, ChangeListener, LocaleChangeListener {

    private static final Logger logger = LoggerFactory.getLogger(DownloadDialog.class);

    private JLabeledChoice projectList;

    private JButton downloadButton;

    private JButton cancelButton;

    private JLabeledTextField usernameTF;

    private JLabeledTextField passwordTF;

    private JLabel statusLabel;

    public DownloadDialog(JFrame parent) {
        super(parent, JMeterUtils.getResString("download"), false); //$NON-NLS-1$
        init();
        JMeterUtils.addLocaleChangeListener(this);
    }

    /**
     * Allow Dialog to be closed by ESC key
     */
    @Override
    protected JRootPane createRootPane() {
        JRootPane rootPane = new JRootPane();
        javax.swing.Action escapeAction = new AbstractAction("ESCAPE") {

            private static final long serialVersionUID = -4036804004190858925L;

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
            }
        };
        rootPane.getActionMap().put(escapeAction.getValue(Action.NAME), escapeAction);
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStrokes.ESC, escapeAction.getValue(Action.NAME));
        return rootPane;
    }

    private void init() {
        this.getContentPane().setLayout(new BorderLayout(10, 10));
        initializeProjectList();
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        comboPanel.add(projectList);
        this.getContentPane().add(comboPanel, BorderLayout.NORTH);

        usernameTF = new JLabeledTextField(JMeterUtils.getResString("username") + "：", 20); //$NON-NLS-1$
        usernameTF.setAlignmentY(TOP_ALIGNMENT);
        passwordTF = new JLabeledTextField(JMeterUtils.getResString("password") + "： ", 20); //$NON-NLS-1$
        passwordTF.setAlignmentY(TOP_ALIGNMENT);

        statusLabel = new JLabel(" ");
        statusLabel.setPreferredSize(new Dimension(100, 20));
        statusLabel.setMinimumSize(new Dimension(100, 20));

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(4, 1));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(7, 3, 3, 3));
        loginPanel.add(usernameTF);
        loginPanel.add(passwordTF);
        loginPanel.add(statusLabel);

        JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));
        downloadButton = createButton("download"); //$NON-NLS-1$
        downloadButton.addActionListener(this);
        cancelButton = createButton("cancel"); //$NON-NLS-1$
        cancelButton.addActionListener(this);
        buttonsPanel.add(downloadButton);
        buttonsPanel.add(cancelButton);

        JPanel downloadPanel = new JPanel();
        downloadPanel.setLayout(new BorderLayout());
        downloadPanel.add(loginPanel, BorderLayout.CENTER);
        downloadPanel.add(buttonsPanel, BorderLayout.SOUTH);
        this.getContentPane().add(downloadPanel);
        usernameTF.requestFocusInWindow();

        this.pack();
        ComponentUtil.centerComponentInWindow(this);
    }

    private void initializeProjectList() {
        String[] projectNames = ProjectUtil.projectNames;
        Arrays.sort(projectNames, String::compareToIgnoreCase);
        projectList = new JLabeledChoice(JMeterUtils.getResString("choose_project"), projectNames); //$NON-NLS-1$
        projectList.addChangeListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        statusLabel.setText("");
        if (source == cancelButton) {
            usernameTF.requestFocusInWindow();
            this.setVisible(false);
        } else if (source == downloadButton) {
            String projectName = projectList.getText();
            String projectId = "";
            for (Project project : ProjectUtil.allProjects) {
                if (projectName.equals(project.getName())){
                    projectId = project.getProjectId();
                    break;
                }
            }
//            Project currentProject = ProjectUtil.allProjects.stream().filter(project -> projectName.equals(project.getName())).findAny().get();
            String username = usernameTF.getText();
            String password = passwordTF.getText();
            //TODO check user account and user permission
            PermissionUtil.checkUser(username, password, projectId);


        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }

    @Override
    public void localeChanged(LocaleChangeEvent event) {
        event.getSource();
    }

    private JButton createButton(String messageKey) {
        return new JButton(JMeterUtils.getResString(messageKey));
    }
}
