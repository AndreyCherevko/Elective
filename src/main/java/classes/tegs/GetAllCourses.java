/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.tegs;

import classes.daoFactory.DaoFactory;
import classes.daoFactory.DaoFactory.Databases;
import classes.entity.Courses;
import classes.idao.ICoursesDao;
import classes.idao.IUsersDao;
import static classes.logger.LogApp.writeLogInFile;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Андрей
 */
public class GetAllCourses extends TagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public int doStartTag() throws JspException {
        try {
            DaoFactory factory = DaoFactory.createDaoFactory(Databases.MySQL);
            ICoursesDao coursesdao = factory.createCoursesDao();
            IUsersDao usersdao = factory.createUsersDao();
            Collection<Courses> courses = coursesdao.findAllCourses();
            Iterator<Courses> it = courses.iterator();
            pageContext.getSession().setAttribute("courses", courses);
            pageContext.getSession().setAttribute("it", it);
        } catch (Exception ex) {
         writeLogInFile(ex);
        }
        return SKIP_BODY;
    }
}
