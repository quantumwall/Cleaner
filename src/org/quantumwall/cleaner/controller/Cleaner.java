package org.quantumwall.cleaner.controller;

import com.sun.jdi.InvalidTypeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Cleaner {
    private File directory;
    private String[] pattern;
    private Logger logger = null;
    
    public Cleaner() {
        
    }
    
    public Cleaner(String dirPath, String[] pattern)
            throws FileNotFoundException, InvalidTypeException {
        directory = new File(dirPath);
        if(!directory.exists()) {
            throw new FileNotFoundException(String.format("Directory %s not exists\n", directory.getAbsolutePath()));
        } else if(!directory.isDirectory()) {
            throw new InvalidTypeException();
        }
        try {
            logger = new Logger();
        } catch (IOException e) {
        }
        this.pattern = pattern;
    }
//    TODO uncomment this method for gui app
//    public void setDirPath(String dirPath) 
//            throws FileNotFoundException {
//        directory = new File(dirPath);
//        if(!directory.exists()) {
//            throw new FileNotFoundException(String.format("directory %s not exists\n", directory.getAbsolutePath()));
//        }
//    }
    
    public void setPattern(String[] pattern) {
        this.pattern = pattern;
    }
    
//    TODO uncomment this method for gui app
//    public String getDirPath() throws FileNotFoundException {
//        if(directory != null) {
//            return directory.getAbsolutePath();
//        } else {
//            throw new FileNotFoundException(String.format("directory %s not exists\n", directory.getAbsolutePath()));
//        }
//    }
    
    public String[] getPattern() {
        if(pattern != null) {
            return pattern;
        } else {
            return new String[] {};
        }
    }
    
    public void deleteMatchedFiles() {
        File[] files = getListFiles();
        for(File file : files) {
            if(match(file)) {
                file.delete();
                if(logger != null){ 
                    logger.add("File " + file + " was deleted", LogLevel.INFO);
                }
            }
        }
        logger.write();
    }

    boolean match(File file) {
        for(String ext : pattern) {
            if(file.getName().matches(".*" + ext + ".*")) {
                return true;
            }
        }
        return false;
    }
    
    private File[] getListFiles() {
        return directory.listFiles();
    }
}
