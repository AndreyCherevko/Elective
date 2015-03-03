/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.tegs;

import classes.daoFactory.DaoFactory;
import classes.entity.Courses;
import classes.entity.Users;
import classes.idao.IUsersDao;
import static classes.logger.LogApp.writeLogInFile;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Андрей
 */
public class GetCoursesAndUser extends TagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public int doStartTag() throws JspException {
        try {
            Courses course = null;
            DaoFactory factory = DaoFactory.createDaoFactory(DaoFactory.Databases.MySQL);
            IUsersDao usersdao = factory.createUsersDao();
            Iterator<Courses> it = (Iterator<Courses>) pageContext.getSession().getAttribute("it");
            if (it.hasNext()) {
                course = it.next();
            }
            Users user = usersdao.findWhereUserIdEquals(course.getUsers());
            pageContext.getSession().setAttribute("user", user);
            pageContext.getSession().setAttribute("course", course);

        
        } catch (Exception ex) {
            writeLogInFile(ex);
        }
        return SKIP_BODY;
    }
}
