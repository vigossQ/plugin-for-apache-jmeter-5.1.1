package org.apache.jmeter.autotest.util;

import org.apache.jmeter.autotest.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vigoss
 * @date 2019/11/7
 */
public class ProjectUtil {

    public static List<Project> allProjects;
    public static String[] projectNames;

    static {
        initAllProject();
    }

    private static void initAllProject() {
        allProjects = getAllProjectInfo();
    }

    private static List<Project> getAllProjectInfo() {
        projectNames = new String[]{"project1", "project2", "project3"};
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        projects.add(new Project());
        return projects;
    }
}
