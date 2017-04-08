import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class TranServlet extends HttpServlet 
	{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Long san=Long.parseLong(request.getParameter("san"));
		Long ran=Long.parseLong(request.getParameter("ran"));
		String shn=request.getParameter("shn");
        String rhn=request.getParameter("rhn");
		String sif=request.getParameter("sif");
		String rif=request.getParameter("rif");
        Long amount=Long.parseLong(request.getParameter("amount"));
		try{
		    Class.forName("oracle.jdbc.OracleDriver");
		    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@12.12.46.250:1521:xe","project","project");
                 boolean status1=false;
		   boolean status2=false;
		   con.setAutoCommit(false);
		    PreparedStatement ps=con.prepareStatement("select * from bankdetails where acnumber=? and ifsc=? and balance>?");
	             ps.setLong(1,san);
	               ps.setString(2,sif);
	            ps.setLong(3,amount);
	               ResultSet rs=ps.executeQuery();
                            status1=rs.next();
			   
                     ps=con.prepareStatement("select * from bankdetails where acnumber=? and ifsc=?");
	             ps.setLong(1,ran);
	               ps.setString(2,rif);
	            
	               rs=ps.executeQuery();
                            status2=rs.next();
				if(status1==true&status2==true)
			       	{   
                   		  ps=con.prepareStatement("update bankdetails set balance=balance-? where acnumber=?");
	           		  ps.setLong(1,amount);
	           	          ps.setLong(2,san);
	            
	           	          rs=ps.executeQuery();
			          
				  ps=con.prepareStatement("update bankdetails set balance=balance+? where acnumber=?");
	           		  ps.setLong(1,amount);
	           	          ps.setLong(2,ran);
	            
	           	          rs=ps.executeQuery();
 				                                  
                                 con.commit();
                                 out.print("-------TRANSACTION SUCCESSFULL-----THANKS FOR USING MULTIBANKING"); 
				}
                                else{
                                 out.print("invalid sender details or reciver details,please check and try again>>>>   THANK YOU");
                                  }
                       out.close();
                        }


				catch(Exception e)
		                {
				 System.out.println(e);System.out.println("rollback");
				}
		
		
		
	  }
   }