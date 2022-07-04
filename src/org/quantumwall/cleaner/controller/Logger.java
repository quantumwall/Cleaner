package org.quantumwall.cleaner.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private final String LOG_FILE_PATH = "log/log.txt";;
    private final DateTimeFormatter FORMATTER;
    private LocalDateTime date;
    private final File logFile;
    private StringBuilder toLog = new StringBuilder();
    private PrintWriter writer = null;
    
    public Logger() throws IOException {
        FORMATTER = DateTimeFormatter.ofPattern("'['yyyy-MM-dd HH:mm:ss']' - ");
        logFile = new File(LOG_FILE_PATH);
        if(!logFile.exists()) {
            if(!logFile.getParentFile().exists()) {
                logFile.getParentFile().mkdirs();
            }
            try {
                logFile.createNewFile();
            } catch(IOException e) {
                
            }
        }
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(logFile, true)));
        } catch(IOException e) {}
    }
    
    public void add(String message, LogLevel level) {
        date = LocalDateTime.now();
        String toOut = String.format("%s[%s] %s\n", date.format(FORMATTER), level, message);
        toLog.append(toOut);
    }
    
    public void write() {
        if(writer != null) {
            writer.print(toLog);
            writer.close();
        }
    }
}
