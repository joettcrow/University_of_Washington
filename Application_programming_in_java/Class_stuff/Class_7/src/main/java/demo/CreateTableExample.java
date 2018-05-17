package demo;

import java.sql.SQLException;

public class CreateTableExample
{
    public static void main(String[] args)
    {
        WeatherTable    table   = new WeatherTable();
        try
        {
            table.createTable();
        }
        catch ( SQLException exc )
        {
            exc.printStackTrace();
        }
    }
}
