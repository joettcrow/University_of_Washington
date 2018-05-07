package serialization;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLDemo
{
    private static final Logger log = LoggerFactory.getLogger( XMLDemo.class );
    
    private int         iField;
    private double      dField;
    private String      sField;

    public static void main(String[] args)
    {
        XMLDemo  demo    = new XMLDemo( 5, 260, "Spot" );
        System.out.println( demo );
        
        byte[]  bArr    = encode( demo );   
        String  msg =   "VALID demo object has been persisted.\n"
                      + "press OK to restore";
        JOptionPane.showMessageDialog( null, msg );
        
        demo = decode( bArr );
        System.out.println( demo );

        msg =   "press OK to dump XML";
        JOptionPane.showMessageDialog( null, msg );
        dumpXML( bArr );
        System.out.println( demo );
    }
    
    // Required for XML serialization
    public int getIField()
    {
        return iField;
    }

    public void setIField(int iField)
    {
        this.iField = iField;
    }

    public double getDField()
    {
        return dField;
    }

    public void setDField(double dField)
    {
        this.dField = dField;
    }

    public String getSField()
    {
        return sField;
    }

    public void setSField(String sField)
    {
        this.sField = sField;
    }

    public XMLDemo()
    {
    }
    
    public XMLDemo( int iVal, double dVal, String sVal )
    {
        iField = iVal;
        dField = dVal;
        sField = sVal;
    }

    @Override
    public String toString()
    {
        String  fmt =   "%d, %.4f, %s%n";
        String  str =   String.format( fmt, iField, dField, sField );
        return str;
    }
    
    private static byte[] encode( XMLDemo demo )
    {
        byte[]                  rval    = null;
        ByteArrayOutputStream   outStr  = null;
        try(
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            XMLEncoder encoder = new XMLEncoder( bStream );
        ) 
        {
            outStr = bStream;
            encoder.writeObject( demo );
            log.info( "XML encoding complete" );
        }
        catch ( IOException exc )
        {
            log.error( "Decode output failure", exc );
            System.exit( 1 );
        }
        rval = outStr.toByteArray();
        
        return rval;
    }
    
    private static XMLDemo decode( byte[] bArr )
    {
        XMLDemo   demo  = null;
        
        try (
            ByteArrayInputStream bStream = new ByteArrayInputStream( bArr );
            XMLDecoder decoder = new XMLDecoder( bStream );
        )
        {
            demo = (XMLDemo)decoder.readObject();
            log.info( "XML decoding complete" );
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
            log.error( "Decode input failure", exc );
        }
        
        return demo;
    }
    
    private static void dumpXML( byte[] bArr )
    {
        try(
            ByteArrayInputStream bStream    = new ByteArrayInputStream( bArr );
            InputStreamReader iStream = new InputStreamReader( bStream );
            BufferedReader reader = new BufferedReader( iStream );
        )
        {
            String  line;
            
            while ( (line = reader.readLine()) != null )
                System.out.println( line );
        }
        catch ( IOException exc )
        {
            log.error( "XML dump failure", exc );
            System.exit( 1 );
        }
    }
}
