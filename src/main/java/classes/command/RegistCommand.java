package classes.command;

import classes.daoFactory.DaoFactory;
import classes.entity.CoursesList;
import classes.entity.Login;
import classes.entity.Users;
import classes.idao.ILoginDao;
import classes.idao.IUsersDao;
import static classes.logger.LogApp.writeLogInFile;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlet.managers.ConfigurationManager;
import servlet.managers.MessageManager;

public class RegistCommand implements ICommand {

    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_SURNAME = "surname";
    private static final String PARAM_MIDDLENAME = "middlename";
    private static final String PARAM_PHONE = "phone";
    private static final String PARAM_USER_TYPE = "usertype";

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = null;
        String page = null;
        DaoFactory factory = null;
        IUsersDao userdao = null;
        ILoginDao logindao = null;
        Collection<CoursesList> list = null;
        Login newlogin = new Login();
        //извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_LOGIN);
        String pass = request.getParameter(PARAM_PASSWORD);
        Users user = new Users();
        newlogin.setLogin(login);
        newlogin.setPassword(pass);
        user.setName(request.getParameter(PARAM_NAME));
        user.setSurname(request.getParameter(PARAM_SURNAME));
        user.setMiddlename(request.getParameter(PARAM_MIDDLENAME));
        user.setPhone(request.getParameter(PARAM_PHONE));
        user.setUserType(request.getParameter(PARAM_USER_TYPE));

        try {
            factory = DaoFactory.createDaoFactory(DaoFactory.Databases.MySQL);
            userdao = factory.createUsersDao();
            logindao = factory.createLoginDao();
            //проверка логина и пароля
            if (logindao.checkLogin(login)) {
                System.out.println("error");
                request.setAttribute("error",
                        MessageManager.getInstance().getProperty(MessageManager.REGISTRATION_ERROR_MESSAGE));
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);

            } else {
                userdao.insertUser(user, newlogin);
                session = request.getSession(true);
                session.setAttribute("id", user.hashCode());
                if (user.getUserType().equals("student")) //определение пути к main.jsp
                {
                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.STUDENT_MAIN_PAGE_PATH);
                } else if (user.getUserType().equals("teacher")) {
                    if (logindao.checkLogin(login, pass)) {
                        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.TEACHER_MAIN_PAGE_PATH);
                    } else {
                        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
                    }
                }
            }
        } catch (Exception ex) {
            writeLogInFile(ex);
            request.setAttribute("error",
                    MessageManager.getInstance().getProperty(MessageManager.REGISTRATION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);

        }

        return page;
    }
}
