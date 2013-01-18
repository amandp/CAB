package cab;
import java.io.*;
import java.util.*;

public class taxi implements Serializable
{
    public String reg_no;
    public int status;      //booked or not.
    public String driver;
    public int capacity;    // number of people atmax
    public String type;     //ac or not
    public int reading;     //km reading per day
    public int wheeler;     // 3 or 4
    public String maintenance;  //put detail
    public double x;
    public double y;
    public taxi(String reg_no, int status, String driver, int capacity, String type, int reading, int wheeler, String maintenance, double x, double y )
    {
        this.reg_no=reg_no;
        this.status=status;
        this.driver=driver;
        this.capacity=capacity;
        this.type=type;
        this.reading=reading;
        this.wheeler=wheeler;
        this.maintenance=maintenance;
        this.x=x;
        this.y=y;
    }
    public taxi()
    {
        reg_no=null;
        status=0;
        driver=null;
        capacity=0;
        type=null;
        reading=0;
        wheeler=0;
        maintenance=null;
        x=0.0;
        y=0.0;
    }
}
