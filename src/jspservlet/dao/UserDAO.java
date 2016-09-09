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
	
	//�����û����������ж��û��Ƿ�Ϸ������ز���ֵ��ʾ�Ƿ��½�ɹ�
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
	
	//�û�ע��
	public boolean userRegister(User user){
		boolean result = false;
		PreparedStatement pstm;
		Connection con = null;
		DBConnect dbc = null;
		//����û���Ϣ�Ƿ���д����
		if(user.getUsername().length() > 0 && user.getPassword().length() > 0 ){
			//����û����Ƿ��ͻ
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
			//���û���Ϣд�����ݿ�
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
        	// �ҳ��û��Ĺ����¼����
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
            //���û������¼�����������з���
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
