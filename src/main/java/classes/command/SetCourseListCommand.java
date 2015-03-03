/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.command;

import classes.daoFactory.DaoFactory;
import classes.entity.CoursesList;
import classes.idao.ICoursesListDao;
import static classes.logger.LogApp.writeLogInFile;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.managers.ConfigurationManager;

/**
 *
 * @author Андрей
 */
public class SetCourseListCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        DaoFactory factory = null;
        ICoursesListDao courseslistdao = null;
        CoursesList course = new CoursesList();
        String name = request.getParameter("name");
        String user = request.getParameter("user");
        System.out.println(name + " " + user);
        if (name != null && user != null) {
            course.setName(name);
            course.serUser(Integer.parseInt(user));
            try {
                factory = DaoFactory.createDaoFactory(DaoFactory.Databases.MySQL);
                courseslistdao = factory.createCoursesListDao();
                courseslistdao.insertCourselist(course);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_NEW_COURSE);

            } catch (Exception ex) {
                writeLogInFile(ex);
                request.setAttribute("error",
                        ex);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
            }
        }
        return page;
    }

}
