/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes.command;


import classes.daoFactory.DaoFactory;
import classes.entity.Archives;
import classes.idao.IArchivesDao;
import static classes.logger.LogApp.writeLogInFile;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.managers.ConfigurationManager;
import servlet.managers.MessageManager;

/**
 *
 * @author Андрей
 */
public class AddArchiveCommand implements ICommand{
 
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String page=null;
        DaoFactory factory = null;
        IArchivesDao archivedao = null;
        Archives archive = new Archives();
        //Получение полей новой архивной записи
        String courseid = request.getParameter("courseid") ;
        String mark =request.getParameter("mark");
        //Проверка на заполнение формы
        if(courseid==null || mark==null){
                   return page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.TEACHER_STUDENT_LIST); 
        }
        archive.setCourse(Integer.parseInt(courseid));
        archive.setMark(Integer.parseInt(mark));
        try {
            factory = DaoFactory.createDaoFactory(DaoFactory.Databases.MySQL);
            archivedao= factory.createArchivesDao();
            archivedao.insertArchive(archive);
             page=ConfigurationManager.getInstance().getProperty(ConfigurationManager.TEACHER_STUDENT_LIST);
        } catch (Exception ex) {
            writeLogInFile(ex);
            request.setAttribute("error",  MessageManager.getInstance().getProperty(MessageManager.ADD_IN_ARCHIVES_MESSAGE));
            page=ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
    
}
