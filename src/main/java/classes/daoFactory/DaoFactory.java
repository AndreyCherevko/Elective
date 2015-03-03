/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.daoFactory;

import classes.exception.DaoFactoryException;
import classes.idao.IArchivesDao;
import classes.idao.ICoursesDao;
import classes.idao.ICoursesListDao;
import classes.idao.ILoginDao;
import classes.idao.IUserTypeDao;
import classes.idao.IUsersDao;

/**
 *
 * @author Андрей
 */
public abstract class DaoFactory {
    /**
     * 
     * @return IArchivesDao
     */
    public abstract IArchivesDao createArchivesDao();
    /**
     * 
     * @return ICoursesDao
     */
    public abstract ICoursesDao createCoursesDao();
    /**
     * 
     * @return IUsersDao 
     */
    public abstract IUsersDao createUsersDao();
    /**
     * 
     * @return IUserTypeDao
     */
    public abstract IUserTypeDao createUserTypeDao();
    /**
     * 
     * @return ICoursesListDao
     */
    public abstract ICoursesListDao createCoursesListDao();
    
    public abstract ILoginDao createLoginDao();
    /**
     * 
     * @param database, chose database from Databases enumeration
     * @return DaoFactory
     * @throws classes.exception.DaoFactoryException, if chosen DB not exist in enumeration
     */
    public static DaoFactory createDaoFactory(Databases database) throws DaoFactoryException {
        switch (database) {
            case MySQL:
                return new MySQLDaoFactory();
            case Oracle:
                throw new DaoFactoryException("Oracle");
            default:
                throw new DaoFactoryException("");
        }
    }

    public static enum Databases {

        MySQL, Oracle
    };
}
