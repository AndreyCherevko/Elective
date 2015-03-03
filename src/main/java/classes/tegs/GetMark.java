/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.tegs;

import classes.daoFactory.DaoFactory;
import classes.entity.Archives;
import classes.entity.Courses;
import classes.idao.IArchivesDao;
import static classes.logger.LogApp.writeLogInFile;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Андрей
 */
public class GetMark extends TagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public int doStartTag() throws JspException {
        Courses courses = (Courses) pageContext.getAttribute("currentcourse");
          try {
            DaoFactory factory = DaoFactory.createDaoFactory(DaoFactory.Databases.MySQL);
            IArchivesDao archivesdao = factory.createArchivesDao();
            Archives archive = archivesdao.findWhereCoursesIdEquals(courses.getId());
            System.out.println(archive+"archive");
            pageContext.getSession().setAttribute("mark", archive.getMark());
            pageContext.getSession().setAttribute("name", courses.getName());
       
        } catch (Exception ex) {
            writeLogInFile(ex);
        }

        return SKIP_BODY;
    }

}
