/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.tegs;

import classes.daoFactory.DaoFactory;
import classes.daoFactory.DaoFactory.Databases;
import classes.entity.Courses;
import classes.entity.CoursesList;
import classes.idao.ICoursesDao;
import classes.idao.ICoursesListDao;
import static classes.logger.LogApp.writeLogInFile;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Андрей
 */
public class GetAvailableCourses extends TagSupport {

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
            ICoursesListDao courseslistdao = factory.createCoursesListDao();
            Collection<CoursesList> courseslist = courseslistdao.findAllCoursesList();
            Integer id = (Integer) pageContext.getSession().getAttribute("id");
            if (id != null) {
                Collection<Courses> courses = coursesdao.findStudentWhereUsersIdEquals(id);
                if (courses.size() > 0) {
                    Iterator<Courses> cour = courses.iterator();
                    Iterator<CoursesList> courlist = courseslist.iterator();
                    while (cour.hasNext()) {
                        courlist = courseslist.iterator();
                        String currentName = cour.next().getName();
                        while (courlist.hasNext()) {
                            String cName = courlist.next().getName();
                            if (currentName.equals(cName)) {
                                courlist.remove();
                            }
                        }
                    }
                }
                pageContext.getSession().setAttribute("list", courseslist);
            }
        } catch (Exception ex) {
             writeLogInFile(ex);
        }
        return SKIP_BODY;
    }

}
