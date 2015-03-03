/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.daoFactory;

import classes.dao.ArchivesDao;
import classes.dao.CoursesDao;
import classes.dao.CoursesListDao;
import classes.dao.LoginDao;
import classes.dao.UserTypeDao;
import classes.dao.UsersDao;
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
public class MySQLDaoFactory extends DaoFactory {

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public IArchivesDao createArchivesDao() {
        return new ArchivesDao();
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public ICoursesDao createCoursesDao() {
        return new CoursesDao();
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public IUsersDao createUsersDao() {
        return new UsersDao();
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public IUserTypeDao createUserTypeDao() {
        return new UserTypeDao();
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public ICoursesListDao createCoursesListDao() {
        return new CoursesListDao();
    }

    @Override
    public ILoginDao createLoginDao() {
        return new LoginDao();
    }

}
