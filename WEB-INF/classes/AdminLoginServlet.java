import java.sql.*;
import java.io.*;
import javax.servlet.*;
public class AdminLoginServlet extends GenericServlet
{
  public void service(ServletRequest request,ServletResponse response) throws ServletException,IOException
  {
	  PrintWriter pw=response.getWriter();
	  String user=request.getParameter("a1");
	  String pwd=request.getParameter("a2");
	
	   try{
		   Class.forName("oracle.jdbc.OracleDriver");
		   Connection con=DriverManager.getConnection("jdbc:oracle:thin:@12.12.46.250:1521:xe","project","project");

           Statement st=con.createStatement();
           ResultSet rs=st.executeQuery("select * from alogindetails where username='"+user+"' and password='"+pwd+"'");
            if(rs.next())
             {
			RequestDispatcher rd=request.getRequestDispatcher("sadmin.html");
			rd.include(request,response);
              }
	      else
             {
              
			RequestDispatcher rd=request.getRequestDispatcher("admin.html");
			rd.include(request,response);
              }
	       con.close();
	       }
	     catch(Exception e)
	      {
               pw.println(e);}
	   pw.close();
	   
  }

}