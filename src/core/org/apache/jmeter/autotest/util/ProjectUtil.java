package org.apache.jmeter.autotest.util;

import org.apache.jmeter.autotest.Project;

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
        return null;
    }
}
