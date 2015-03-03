/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes.tegs;

import classes.daoFactory.DaoFactory;
import classes.entity.Users;
import classes.idao.IUsersDao;
import static classes.logger.LogApp.writeLogInFile;
import java.util.Collection;
import javax.servlet.jsp.JspException;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Андрей
 */
public class GetAllTeacherList extends TagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public int doStartTag() throws JspException {
        try {
            DaoFactory factory = DaoFactory.createDaoFactory(DaoFactory.Databases.MySQL);
            IUsersDao usersdao = factory.createUsersDao();
            Collection<Users> list = usersdao.findAllUser("teacher");
                pageContext.getSession().setAttribute("teachers", list);
            
        } catch (Exception ex) {
             writeLogInFile(ex);
        }
        return SKIP_BODY;
    }
    
}

