package jspservlet.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jspservlet.db.DBConnect;
import jspservlet.vo.User;

public class DBConnect {  
	    private final String DBDRIVER = "com.mysql.jdbc.Driver" ;   
	    private final String DBURL = "jdbc:mysql://localhost/javawebdb" ;   
	    private final String DBUSER = "root" ;   
	    private final String DBPASSWORD = "";   
	    private Connection conn = null ;   
	  
	    public DBConnect()   {   
	        try{   
	            Class.forName(DBDRIVER) ;   
	            this.conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD) ;  
	        }catch (Exception e){ 
	        	System.out.println(e.getMessage());  
	        	}   
	    }   
	  
	    // 取得数据库连接   
	    public Connection getConnection(){   
	        return this.conn ;   
	    }   
	  
	    // 关闭数据库连接   
	    public void close(){   
	        try{   
	            this.conn.close() ;   
	        }catch (Exception e){ }          
	    }
}  
