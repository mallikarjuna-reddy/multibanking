import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String n=request.getParameter("t1");
		String p=request.getParameter("t2");
		
		if(validate(n,p)){
			
			
			RequestDispatcher rd=request.getRequestDispatcher("sclients.html");
			rd.include(request,response);
		}
		else{
                        
			RequestDispatcher rd=request.getRequestDispatcher("clients.html");
			rd.include(request,response);
                        
		}
		
		out.close();
	}
public static boolean validate(String name,String pass){
boolean status=false;
try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","project","project");
	
	PreparedStatement ps=con.prepareStatement("select * from logindetails where username=? and password=? ");
	ps.setString(1,name);
	ps.setString(2,pass);
	
	ResultSet rs=ps.executeQuery();
	status=rs.next();
	//System.out.println("name"+name+" Pass"+pass+"  "+rs.next());
	
	//while(rs.next())
	//System.out.println(rs.getString(1)+" "+rs.getString(2));
	
}catch(Exception e){System.out.println(e);}
return status;
}

}
