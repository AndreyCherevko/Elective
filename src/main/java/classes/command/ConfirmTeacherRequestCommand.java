/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.command;

import classes.daoFactory.DaoFactory;
import classes.entity.Users;
import classes.idao.ILoginDao;
import classes.idao.IUsersDao;
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
public class ConfirmTeacherRequestCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("userid"));
        String page = null;
        DaoFactory factory = null;
        IUsersDao usersdao = null;
        ILoginDao logindao = null;
        if (id == null) {
            return page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_TEACHER_REQUEST);
        } else {
            try {
                factory = DaoFactory.createDaoFactory(DaoFactory.Databases.MySQL);
                usersdao = factory.createUsersDao();
                logindao = factory.createLoginDao();
                //Поиск пользователя
                Users user = usersdao.findWhereUserIdEquals(id);
                //Подтверждение регистрации
                logindao.setWorking(user);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_TEACHER_REQUEST);
            } catch (Exception ex) {
                writeLogInFile(ex);
                request.setAttribute("error", ex);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
            }
        }
        return page;
    }
}
