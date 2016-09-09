package jspservlet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import jspservlet.db.DBConnect;
import jspservlet.vo.Good;
import jspservlet.vo.User;

public class UserDAO {	
	
	//根据用户名和密码判断用户是否合法，返回布尔值表示是否登陆成功
	public boolean queryByUsername(User user) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "select * from userinfo where userName=?";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;   
  
        try{   
            dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql) ; 
            pstmt.setString(1,user.getUsername()) ;     
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){   
                if(rs.getString("userPassword").equals(user.getPassword())){
                	result = true;
                } 
            }   
            rs.close() ; 
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        }   
		return result;
	}
	
	//用户注册
	public boolean userRegister(User user){
		boolean result = false;
		PreparedStatement pstm;
		Connection con = null;
		DBConnect dbc = null;
		//检查用户信息是否填写完整
		if(user.getUsername().length() > 0 && user.getPassword().length() > 0 ){
			//检查用户名是否冲突
			try{
				dbc = new DBConnect() ;   
				pstm = dbc.getConnection().prepareStatement("select * from userinfo where userName = ?") ; 
	            pstm.setString(1,user.getUsername()); 
	            ResultSet rs = pstm.executeQuery();
	            if(rs.next()){   
	                result = false;
	                return result;
	            }   
	            rs.close() ; 
	            pstm.close() ;
			}catch (SQLException e){   
	            System.out.println(e.getMessage());   
	        }
			//将用户信息写入数据库
			try {
				con=dbc.getConnection();
				pstm= con.prepareStatement("insert into userinfo(userName,userPassword) values(?,?)");
			    pstm.setString(1, user.getUsername());
				pstm.setString(2, user.getPassword());
				pstm.execute();
				result = true;
			} catch (Exception e) {
				System.err.println("CustomerDAO.createCustomer ERROR ## "+e.getMessage());
			}
		}
		return result;
	}
	
	public Good[] getUserShoppinghistory(User user){
		Good[] usergoods = null;
		String sql;
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null; 
        try{
        	// 找出用户的购买记录总数
        	int goodnum = 0;
        	sql = "select count(*) as 'sum' from shoppinghistory where userName =?";
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ; 
            pstmt.setString(1,user.getUsername()) ;   
            ResultSet rs;
            rs = pstmt.executeQuery();
            if(rs.next()){
            	goodnum = Integer.parseInt(rs.getString("sum"));
            }   
            rs.close() ; 
            pstmt.close() ;  
            //将用户购买记录储存在数组中返回
            if(goodnum > 0){
            	usergoods = new Good[goodnum];
            	sql = "select * from shoppinghistory where userName =?";
            	dbc = new DBConnect();
            	pstmt = dbc.getConnection().prepareStatement(sql) ; 
                pstmt.setString(1,user.getUsername()) ; 
                rs = pstmt.executeQuery();
                int i = 0;
                while(rs.next()){
                	usergoods[i]=new Good();
                	usergoods[i].setName(rs.getString("goodName"));
                	usergoods[i].setUserName(rs.getString("userName"));
                	usergoods[i].setUserAddress(rs.getString("userAddress"));
                	usergoods[i].setTime(rs.getString("time"));
                	usergoods[i].setId(rs.getInt("id"));
                	i++;
                }
                rs.close() ; 
                pstmt.close() ;  
            }
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        }  
        return usergoods;
	}
}
