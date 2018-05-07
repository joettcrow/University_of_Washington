package serialization;

public class ProxyDemoSuperclass
{
    private final int   iField;
    
    public ProxyDemoSuperclass( int data )
    {
        iField = data;
    }
    
    public int getData()
    {
        return iField;
    }
}
