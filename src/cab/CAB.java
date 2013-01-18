package cab;
import java.sql.*;
import java.io.*;
import java.util.*;
import cab.taxi ;
public class CAB 
{
    public void inserttaxi(taxi t)throws IOException
    {
        try
        {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cab","root","");
            PreparedStatement statement=con.prepareStatement(
                    "INSERT INTO taxi (reg_no, status, driver, capacity, type, reading, wheeler, maintenance, x, y) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, t.reg_no);
            statement.setInt(2, t.status);
            statement.setString(3, t.driver);
            statement.setInt(4, t.capacity );
            statement.setString(5, t.type);
            statement.setInt(6, t.reading);
            statement.setInt(7, t.wheeler);
            statement.setString(8, t.maintenance);
            statement.setDouble(9, t.x);
            statement.setDouble(10, t.y);
            statement.executeUpdate();
            
            con.close();
         }
         catch(Exception e){ e.printStackTrace(); }
     }
    public void updatetaxi(taxi t)throws IOException
    {
        try
        {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cab","root","");
            PreparedStatement statement=con.prepareStatement(
                    "UPDATE taxi SET status = ?" +", driver = ? , capacity = ? , type = ?, reading = ?, wheeler = ?, maintenance = ?, x = ?, y = ? WHERE reg_no = ?");
            
            statement.setInt(1, t.status);
            statement.setString(2, t.driver);
            statement.setInt(3, t.capacity );
            statement.setString(4, t.type);
            statement.setInt(5, t.reading);
            statement.setInt(6, t.wheeler);
            statement.setString(7, t.maintenance);
           
            statement.setDouble(8, t.x);
            statement.setDouble(9, t.y);
	    statement.setString(10, t.reg_no);
	    
            statement.executeUpdate();
        }
        catch(Exception e){e.printStackTrace(); }
    }
    public void deletetaxi(taxi t)throws IOException
    {
        try
        {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cab","root","");
            PreparedStatement statement=con.prepareStatement(
                    "DELETE FROM taxi WHERE reg_no = ?");
            statement.setString(1, t.reg_no);
            statement.executeUpdate();
        }
        catch(Exception e){e.printStackTrace(); 
	}
    }
    public taxi getTaxi(String reg_no) throws IOException
    {
        try {
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cab","root","");
            PreparedStatement statement=con.prepareStatement(
                    "SELECT * FROM taxi WHERE reg_no = ?");
            statement.setString(1, reg_no);
            taxi t;
            ResultSet result = statement.executeQuery();
            t=null;
            if (result.next()) 
            {
                 t = new taxi();
                 t.reg_no = result.getString("reg_no");
                 t.status= result.getInt("status");
                 t.driver=result.getString("driver");
                 t.capacity= result.getInt("capacity");
                 t.type=result.getString("type");
                 t.reading=result.getInt("reading");
                 t.wheeler=result.getInt("wheeler");
                 t.maintenance=result.getString("maintenance");
                 t.x=result.getDouble("x");
                 t.y=result.getDouble("y");
            }
            return t;
        }
	catch (SQLException exception) 
	{
            exception.printStackTrace();
            return null;
        }
    }
    //search by driver name
    public List<taxi> getSearch_driver(String keyword) throws IOException
    {
        try {
	    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cab","root","");
            PreparedStatement statement=con.prepareStatement(
                    "SELECT * FROM taxi WHERE driver LIKE  ? ");
            statement.setString(1, "%"+keyword+"%");
            List<taxi> list = new ArrayList<taxi>();
	    ResultSet result = statement.executeQuery();
            while (result.next()) 
	    {
                    taxi t = new taxi();
                    t.reg_no = result.getString("reg_no");
                    t.status= result.getInt("status");
                    t.driver=result.getString("driver");
                    t.capacity= result.getInt("capacity");
                    t.type=result.getString("type");
		    t.reading=result.getInt("reading");
		    t.wheeler=result.getInt("wheeler");
		    t.maintenance=result.getString("maintenance");
		    t.x=result.getDouble("x");
		    t.y=result.getDouble("y");
                    
                    list.add(t); 
            }

            return list;
        } catch (SQLException exception) 
	{
            exception.printStackTrace();
            return null;
        } 
    }
    //search taxi by registration number
    public List<taxi> getSearch_reg_no(String keyword) throws IOException
    {
        try {
	    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cab","root","");
            PreparedStatement statement=con.prepareStatement(
                    "SELECT * FROM taxi WHERE reg_no LIKE  ? ");
            statement.setString(1, "%"+keyword+"%");
            List<taxi> list = new ArrayList<taxi>();
	    ResultSet result = statement.executeQuery();
            while (result.next()) 
	    {
                    taxi t = new taxi();
                    t.reg_no = result.getString("reg_no");
                    t.status= result.getInt("status");
                    t.driver=result.getString("driver");
                    t.capacity= result.getInt("capacity");
                    t.type=result.getString("type");
		    t.reading=result.getInt("reading");
		    t.wheeler=result.getInt("wheeler");
		    t.maintenance=result.getString("maintenance");
		    t.x=result.getDouble("x");
		    t.y=result.getDouble("y");
                    
                    list.add(t); 
            }

            return list;
        } 
	catch (SQLException exception) 
	{
            exception.printStackTrace();
            return null;
        } 
    }
    // for getting all taxis
    public  List<taxi> gettaxi() throws IOException
    {
        try {
	    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cab","root","");
	    Statement statement = con.createStatement();
            List<taxi> list;
	    ResultSet result = statement.executeQuery("SELECT * FROM taxi");
	    list = new ArrayList<taxi>();
	    
            while(result.next())
	    {
                    taxi t = new taxi();
                    t.reg_no = result.getString("reg_no");
                    t.status= result.getInt("status");
                    t.driver=result.getString("driver");
                    t.capacity= result.getInt("capacity");
                    t.type=result.getString("type");
		    t.reading=result.getInt("reading");
		    t.wheeler=result.getInt("wheeler");
		    t.maintenance=result.getString("maintenance");
		    t.x=result.getDouble("x");
		    t.y=result.getDouble("y");
                    
                    list.add(t); 
                }
          return list;    
        }
	catch (SQLException exception)
	{
            exception.printStackTrace();
            return null;
	}
    }
    
    public void updateStatus(String reg_no,int status) throws IOException
    {
        try 
	{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cab","root","");
	    PreparedStatement statement =con.prepareStatement(
                    "UPDATE taxi SET " +" status = ? " +"WHERE reg_no = ?");

            statement.setInt(2, status);
            statement.setString(1, reg_no);
            statement.executeUpdate();
	    con.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        } 
   }
}
