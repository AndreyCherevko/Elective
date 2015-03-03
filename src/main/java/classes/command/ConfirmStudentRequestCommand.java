/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.command;

import classes.daoFactory.DaoFactory;
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
public class ConfirmStudentRequestCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("courseid"));
        String page = null;
        DaoFactory factory = null;
        ICoursesDao coursesdao = null;
        if (id == null) {
            return page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.TEAHCER_STUDENT_REQUEST);
        } else {
            try {
                factory = DaoFactory.createDaoFactory(DaoFactory.Databases.MySQL);
                coursesdao = factory.createCoursesDao();
                coursesdao.setConfirm(id);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.TEAHCER_STUDENT_REQUEST);

            } catch (Exception ex) {
                writeLogInFile(ex);
                request.setAttribute("error", ex);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
            }
        }
        return page;
    }

}
