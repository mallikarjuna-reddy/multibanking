import java.sql.*;
import java.io.*;
import javax.servlet.*;
public class AdminServlet extends GenericServlet
{
  public void service(ServletRequest req,ServletResponse res) throws ServletException,IOException
  {
	  PrintWriter pw=res.getWriter();
	
	    
  
	   try{
		   Class.forName("oracle.jdbc.OracleDriver");
		   Connection con=DriverManager.getConnection("jdbc:oracle:thin:@12.12.46.250:1521:xe","project","project");
           Statement st=con.createStatement();
                     
		  
              ResultSet rs=st.executeQuery("select * from andhrabank");
		   ResultSetMetaData rsmd=rs.getMetaData();
		   pw.println("andhra bank details....");
			pw.println("<tr>");
			for(int i=1;i<rsmd.getColumnCount();i++)
				pw.println("<th>"+rsmd.getColumnName(i)+"</th>");
				pw.println("</tr>");
				while(rs.next())
		        {
				pw.println("<tr>");
				 for(int j=1;j<=rsmd.getColumnCount();j++)
					 pw.println("<th>"+rs.getString(j)+"</th>");
				  pw.println("</tr>");
				}

				rs=st.executeQuery("select * from icici");
				rsmd=rs.getMetaData();
				 pw.println("icici bank details....");
			pw.println("<tr>");
			for(int i=1;i<rsmd.getColumnCount();i++)
				pw.println("<th>"+rsmd.getColumnName(i)+"</th>");
				pw.println("</tr>");
				while(rs.next())
		        {
				pw.println("<tr>");
				 for(int j=1;j<=rsmd.getColumnCount();j++)
					 pw.println("<th>"+rs.getString(j)+"</th>");
				  pw.println("</tr>");
				}
               
			   rs=st.executeQuery("select * from hdfc");
				rsmd=rs.getMetaData();
			    pw.println("hdfc bank details....");
			pw.println("<tr>");
			for(int i=1;i<rsmd.getColumnCount();i++)
				pw.println("<th>"+rsmd.getColumnName(i)+"</th>");
				pw.println("</tr>");
				while(rs.next())
		        {
				pw.println("<tr>");
				 for(int j=1;j<=rsmd.getColumnCount();j++)
					 pw.println("<th>"+rs.getString(j)+"</th>");
				  pw.println("</tr>");
				}
                     

	    	 pw.close();  
	       con.close();
	       
               }
	     catch(Exception e)
	      {
               pw.println(e);}
	   pw.close();
	   
  }

}

