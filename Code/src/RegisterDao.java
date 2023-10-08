import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao
 {

    private String dbUrl = "jdbc:mysql://localhost:3305/userdb";
    private String dbUname = "root";
    private String dbPassword = "Sathwikdb7*";
    private String dbDriver = "com.mysql.cj.jdbc.Driver";

    public void loadDriver(String dbDriver)
	 {
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e)
		 {
            e.printStackTrace();
        }
    }

    public Connection getConnection()
	 {
        Connection con = null;
        try {
            con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
        } catch (SQLException e) 
		{
            e.printStackTrace();
        }
        return con;
    }

    public String insert(Member member) 
	{
        loadDriver(dbDriver);
        Connection con = getConnection();

        String sql = "insert into member values(?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) 
		{
            ps.setString(1, member.getUname());
            ps.setString(2, member.getPassword());
            ps.setString(3, member.getEmail());
            ps.setString(4, member.getPhone());
            ps.executeUpdate();

            return "<!DOCTYPE html><html><head><title>Data Entered Successfully</title>" +
                    "<style>body{font-family: Arial, sans-serif;background-color: white;text-align: center;padding: 50px;}h1{color: #0ca845;}</style>" +
                    "</head><body><h1>Data Entered Successfully !</h1></body></html>";
        } catch (SQLException e)
		 {
            e.printStackTrace();
            return "<!DOCTYPE html><html><head><title>Data Entry Failed !</title>" +
                    "<style>body{font-family: Arial, sans-serif;background-color: white;text-align: center;padding: 50px;}h1{color: red;}</style>" +
                    "</head><body><h1>Data Entry Failed</h1></body></html>";
        }
    }
}

