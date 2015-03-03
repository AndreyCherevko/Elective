/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.connection;

import static classes.logger.LogApp.writeLogInFile;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Андрей
 */
public class ConnectionManager {

    private static final ConnectionPool pool = new ConnectionPool();

    private ConnectionManager() {

    }
    
    private static class ConnectionPool {

        private static DataSource connectionPool = null;

        //Создание ConnectionPool
        private static void initialConnection() throws NamingException {

            if (connectionPool == null) {
                InitialContext initialContext = new InitialContext();
                Context context = (Context) initialContext.lookup("java:comp/env");
                connectionPool = (DataSource) context.lookup("connpool");

            }
        }

        public static Connection free() {

            Connection cn = null;
            try {
                // Если ConnectionPool еще не создан, тогда создаеться 
                if (connectionPool == null) {
                    initialConnection();
                }
                cn = connectionPool.getConnection();;

            } catch (SQLException ex) {
                writeLogInFile(ex);
            } catch (NamingException ex) {
               writeLogInFile(ex);
            }
            return cn;

        }
    }
    //Получение свободного Connection
    public static Connection getConnection() {

        return pool.free();
    }

}
