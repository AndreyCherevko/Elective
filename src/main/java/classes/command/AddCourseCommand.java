/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.command;

import classes.daoFactory.DaoFactory;
import classes.entity.Courses;
import classes.idao.ICoursesDao;
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
public class AddCourseCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        DaoFactory factory = null;
        ICoursesDao coursesdao = null;
        Courses newcourse = new Courses();
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        //Проверка на заполнение формы
        if (name == null || id == null) {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.STUDENT_CLIST);
        } else {
            newcourse.setName(name);
            newcourse.setUsers(Integer.parseInt(id));
            try {
                factory = DaoFactory.createDaoFactory(DaoFactory.Databases.MySQL);
                coursesdao = factory.createCoursesDao();
                coursesdao.insertCourse(newcourse);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.STUDENT_CLIST);

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
