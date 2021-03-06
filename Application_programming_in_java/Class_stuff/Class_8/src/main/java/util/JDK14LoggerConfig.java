package util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDK14LoggerConfig
{
    private static final Logger rootLogger  = Logger.getLogger( "" );
    
    private static final Map<String, FileHandler>   fileHandlerMap  = 
        new HashMap<>();

    public enum LevelProxy
    {
        LOW,
        MEDIUM,
        HIGH
    };
    
    static public void setLogLevel( LevelProxy level )
    {
        Level   jdkLogLevel = Level.SEVERE;
        switch ( level )
        {
        case LOW:
            jdkLogLevel = Level.SEVERE;
            break;
        case MEDIUM:
            jdkLogLevel = Level.INFO;
            break;
        case HIGH:
            jdkLogLevel = Level.ALL;
            break;
        }
        
        rootLogger.setLevel( jdkLogLevel );
    }
    
    static public void removeFileHandler( String name )
    {
        FileHandler handler = fileHandlerMap.get( name );
        if ( handler != null )
            rootLogger.removeHandler( handler );
    }
    
    static public void addFileHandler( String name, boolean keep )
    {
        try
        {
            File    file    = File.createTempFile( name, ".log" );
            if ( !keep )
                file.deleteOnExit();
            FileHandler fileHandler = 
                new FileHandler( file.getAbsolutePath(), false );
            fileHandlerMap.put( name, fileHandler );
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
    }
}
