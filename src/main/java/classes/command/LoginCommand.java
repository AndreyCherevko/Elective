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
import servlet.managers.MessageManager;

public class LoginCommand implements ICommand {

    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        DaoFactory factory = null;
        IUsersDao userdao = null;
        ILoginDao logindao = null;
        Users user = null;
        //извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_LOGIN);
        String pass = request.getParameter(PARAM_PASSWORD);
        //Проверка на вход админа
        if (login.equals("Admin") && pass.equals("root")) {
            request.getSession(true);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_MAIN_PAGE);
        } else {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_LOGIN_PAGE_PATH);
          

            try {
                factory = DaoFactory.createDaoFactory(DaoFactory.Databases.MySQL);
                logindao = factory.createLoginDao();
                userdao = factory.createUsersDao();
                  //проверка логина и пароля
                if (logindao.checkLogin(login, pass)) {
                    request.getSession(true);
                } else {
                    //вывод страницы ошибки
                    request.setAttribute("error",
                        MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
                    return page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_LOGIN_PAGE_PATH);
                }
                //Получение id пользователя и добавление в сессию атрибута
                Integer id = logindao.findByDynamicSelect(login, pass).getId();
                request.getSession(false).setAttribute("id", id);
                user = userdao.findWhereUserIdEquals(id);
                //В зависимости от типа пользователя открываеться нужная главная страница
                if (user.getUserType().equals("student")) {
                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.STUDENT_MAIN_PAGE_PATH);
                } else if (user.getUserType().equals("teacher")) {
                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.TEACHER_MAIN_PAGE_PATH);
                }
            } catch (Exception ex) {
                writeLogInFile(ex);
                request.setAttribute("error",ex);
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_LOGIN_PAGE_PATH);
            }
        }

        return page;
    }
}
