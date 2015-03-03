/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes.tegs;

import classes.daoFactory.DaoFactory;
import classes.entity.Login;
import classes.entity.Users;
import classes.idao.ILoginDao;
import classes.idao.IUsersDao;
import static classes.logger.LogApp.writeLogInFile;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.jsp.JspException;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Андрей
 */
public class GetAllNotConfirmUser extends TagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public int doStartTag() throws JspException {
        try {
            DaoFactory factory = DaoFactory.createDaoFactory(DaoFactory.Databases.MySQL);
            ILoginDao logindao = factory.createLoginDao();
            IUsersDao usersdao = factory.createUsersDao();
            Collection<Login> loginlist = logindao.findAllNotWorking();
            ArrayList<Users> list = new ArrayList();
            for(Login log:loginlist){
                list.add(usersdao.findWhereUserIdEquals(log.getId()));
            }
                pageContext.getSession().setAttribute("list", list);
            
        } catch (Exception ex) {
            writeLogInFile(ex);
        }
        return SKIP_BODY;
    }
    
}
