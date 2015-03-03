package servlet.logic;

import classes.command.AddArchiveCommand;
import classes.command.ChangeLanquageCommand;
import classes.command.ConfirmStudentRequestCommand;
import classes.command.ConfirmTeacherRequestCommand;
import classes.command.RedirectCommand;
import classes.command.ICommand;
import classes.command.LoginCommand;
import classes.command.NoCommand;
import classes.command.RegistCommand;
import classes.command.AddCourseCommand;
import classes.command.SetCourseListCommand;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

public class RequestHelper {

    private static RequestHelper instance = null;
    HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    private RequestHelper() {
        //заполнение таблицы командами
        commands.put("login", new LoginCommand());
        commands.put("reg", new RegistCommand());
        commands.put("set", new AddCourseCommand());
        commands.put("redirect",new RedirectCommand());
        commands.put("addArchive",new AddArchiveCommand());
        commands.put("confirmStudentRequest", new ConfirmStudentRequestCommand());
        commands.put("setCoursesList", new SetCourseListCommand());
        commands.put("confirmTeacherRequest", new ConfirmTeacherRequestCommand());
         commands.put("changeLan", new ChangeLanquageCommand());

    }

    public ICommand getCommand(HttpServletRequest request) {
        //извлечение команды из запроса
        String action = request.getParameter("command");
        //получение объекта, соответствующего команде
        ICommand command = commands.get(action);

        if (command == null) {
            //если команды не существует в текущем объекте
            command = new NoCommand();
        }
        return command;
    }

    //создание единственного объекта по шаблону Singleton
    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}
