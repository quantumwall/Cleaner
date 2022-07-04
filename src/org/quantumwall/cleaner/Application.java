package org.quantumwall.cleaner;

import com.sun.jdi.InvalidTypeException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import org.quantumwall.cleaner.controller.Cleaner;
import org.quantumwall.cleaner.controller.LogLevel;
import org.quantumwall.cleaner.gui.CleanerFrame;
import org.quantumwall.cleaner.controller.Logger;

public class Application {
    public static void main(String[] args) {
        String dirPath;
        String[] pattern;
        Logger logger = null;
        {
            try{
                logger = new Logger();
            } catch(IOException e) {
                System.out.println("Can not create logger");
            }
        }
                
        if(args.length == 0) {
            CleanerFrame frame = new CleanerFrame();
            frame.setVisible(true);
        } else {
            dirPath = args[0];
            pattern = Arrays.copyOfRange(args, 1, args.length);
            try {
                Cleaner cleaner = new Cleaner(dirPath, pattern);
                cleaner.deleteMatchedFiles();
            } catch(FileNotFoundException e) {
                String message = String.format("File %s is not exists\n", dirPath);
                if(logger != null) {
                    logger.add(message, LogLevel.ERROR);
                    logger.write();
                } else {
                    System.out.println(message);
                }
            } catch(InvalidTypeException e) {
                String message = String.format("%s is not directory\n", dirPath);
                if(logger != null) {
                   logger.add(message, LogLevel.ERROR);
                   logger.write();
                } else {
                    System.out.println(message);
                }
            }
            
            
        }
    }
}
