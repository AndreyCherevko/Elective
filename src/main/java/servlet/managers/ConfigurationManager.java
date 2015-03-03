package servlet.managers;

import java.util.ResourceBundle;

public class ConfigurationManager {

    private static ConfigurationManager instance;
    private ResourceBundle resourceBundle;

    //класс извлекает информацию из файла config.properties
    private static final String BUNDLE_NAME = "servlet.properties.config";
    public static final String DATABASE_DRIVER_NAME = "DATABASE_DRIVER_NAME";
    public static final String DATABASE_URL = "DATABASE_URL";
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String ERROR_LOGIN_PAGE_PATH = "ERROR_LOGIN_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String REG_PAGE_PATH = "REG_PAGE_PATH";
    public static final String STUDENT_MAIN_PAGE_PATH = "STUDENT_MAIN_PAGE_PATH";
    public static final String TEACHER_MAIN_PAGE_PATH = "TEACHER_MAIN_PAGE_PATH";
    public static final String ADMIN_MAIN_PAGE = "ADMIN_MAIN_PAGE";
    public static final String TEACHER_ADD_ARCHIVE = "TEACHER_ADD_ARCHIVE";
    public static final String TEAHCER_STUDENT_REQUEST = "TEAHCER_STUDENT_REQUEST";
    public static final String TEACHER_STUDENT_LIST = "TEACHER_STUDENT_LIST";
    public static final String STUDENT_CONFIRM_CLIST = "STUDENT_CONFIRM_CLIST";
    public static final String STUDENT_NOT_CONFIRM_CLIST = "STUDENT_NOT_CONFIRM_CLIST";
    public static final String STUDENT_CLIST = "STUDENT_CLIST";
    public static final String STUDENT_CURRENT_ARCHIVE = "STUDENT_CURRENT_ARCHIVE";
    public static final String ADMIN_TEACHER_REQUEST = "ADMIN_TEACHER_REQUEST";
    public static final String ADMIN_NEW_COURSE = "ADMIN_NEW_COURSE";
    public static final String ADMIN_MAIN="ADMIN_MAIN";
    
    
    
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }

        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
