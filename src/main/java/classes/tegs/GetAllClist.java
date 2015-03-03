/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.tegs;

import classes.daoFactory.DaoFactory;
import classes.entity.CoursesList;
import classes.idao.ICoursesListDao;
import static classes.logger.LogApp.writeLogInFile;
import java.util.Collection;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Андрей
 */
public class GetAllClist extends TagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public int doStartTag() throws JspException {
        DaoFactory factory = null;
        ICoursesListDao courseslistdao = null;
        Collection<CoursesList> list = null;
        try {
            factory = DaoFactory.createDaoFactory(DaoFactory.Databases.MySQL);
            courseslistdao = factory.createCoursesListDao();
            list = courseslistdao.findAllCoursesList();
            pageContext.getSession().setAttribute("allclist", list);
        } catch (Exception ex) {
            writeLogInFile(ex);

        }
        return SKIP_BODY;
    }
}
