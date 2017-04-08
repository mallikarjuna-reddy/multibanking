import java.sql.*;
import java.io.*;
import javax.servlet.*;
public class SignupServlet extends GenericServlet
{
  public void service(ServletRequest req,ServletResponse res) throws ServletException,IOException
  {
	  PrintWriter pw=res.getWriter();
	  String fn=req.getParameter("t1");
	  String ln=req.getParameter("t2");
          
	  String ed=req.getParameter("t3");
	  long m=Long.parseLong(req.getParameter("t4"));
         
          long abn=Long.parseLong(req.getParameter("t5"));
	    String abh=req.getParameter("t6");
          String aif=req.getParameter("t7");
	  
           
           long ibn=Long.parseLong(req.getParameter("t8"));
	    String ibh=req.getParameter("t9");
          String iif=req.getParameter("t10");
	  
             
           long hbn=Long.parseLong(req.getParameter("t11"));
	    String hbh=req.getParameter("t12");
          String hif=req.getParameter("t13");
	    
  
	   try{
		   Class.forName("oracle.jdbc.OracleDriver");
		   Connection con=DriverManager.getConnection("jdbc:oracle:thin:@12.12.46.250:1521:xe","project","project");
           PreparedStatement pst=con.prepareStatement("insert into requestdetails values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
           pst.setString(1,fn);  pst.setString(2,ln);pst.setString(3,ed);  pst.setLong(4,m);pst.setLong(5,abn);pst.setString(6,abh);pst.setString(7,aif);pst.setLong(8,ibn);pst.setString(9,ibh);pst.setString(10,iif);pst.setLong(11,hbn);pst.setString(12,hbh);pst.setString(13,hif);
           int i=pst.executeUpdate();
	       if(i==1)
                   {
                 pw.println(" successfully registerd,we verify the bank details provided and get in touch soon,thank you!!");
                
	    	   }
	       con.close();
	       }
	     catch(Exception e)
	      {
               pw.println(e);}
	   pw.close();
	   
  }

}

