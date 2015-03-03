/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Андрей
 */
public class LogApp {

    public static final Logger logger = Logger.getLogger(LogApp.class.getName());

   

    public static void writeLogInFile(Exception ex){
        FileHandler fh = null;
        try {
            fh = new FileHandler("LogApp.txt");
        } catch (IOException ex1) {
            logger.log(Level.WARNING,ex1.getMessage() );
        } catch (SecurityException ex1) {
            logger.log(Level.WARNING,ex1.getMessage() );
        }
        logger.addHandler(fh);
        logger.log(Level.WARNING,ex.getMessage());
    }
}
