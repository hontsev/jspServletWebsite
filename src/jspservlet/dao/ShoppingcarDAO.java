package jspservlet.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import jspservlet.db.DBConnect;
import jspservlet.vo.User;
import jspservlet.vo.Good;

public class ShoppingcarDAO {
	
	//�����û���Ϣ��ȡ���ﳵ��Ϣ
	public Good[] getUserShoppingcar(User user){
		Good[] usergoods = null;
		String sql;
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null; 
        try{
        	int goodnum = 0;
        	sql = "select count(*) sum from shoppingcar where userName =?";
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
            if(goodnum == 0){
            	return usergoods;
            }else{
            	usergoods = new Good[goodnum];
            	sql = "select * from shoppingcar where userName =?";
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
                	usergoods[i].setId(Integer.parseInt(rs.getString("id")));
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
	
	//��洢���ﳵ��Ϣ�ı����һ���¼
	public boolean addGood(Good good){
		boolean result = false;
		if(good.getName()!=null 
			&& good.getName().length()>0 
			&& good.getUserName()!=null
			&& good.getUserName().length()>0
			&& good.getUserAddress()!=null
			&& good.getUserAddress().length()>0 
			){
			String sql;
	        PreparedStatement pstmt = null ;   
	        DBConnect dbc = null; 
	        try{
	        	sql = "insert into shoppingcar(goodName,userName,userAddress,time) values(?,?,?,?)";
	            dbc = new DBConnect() ;
	            pstmt = dbc.getConnection().prepareStatement(sql) ; 
	            pstmt.setString(1,good.getName()) ;
	            pstmt.setString(2,good.getUserName());
	            pstmt.setString(3,good.getUserAddress());
	            pstmt.setDate(4, new Date(new java.util.Date().getTime())); 
	            pstmt.execute(); 
	            result = true;
	            pstmt.close();
	        }catch (SQLException e){   
	            System.out.println(e.getMessage());   
	        }finally{   
	            dbc.close() ;   
	        }  
		}
		return result;
	}
	
	//���ݼ�¼id�ӹ��ﳵɾ��һ����¼
	public void removeGood(int goodId){
		String sql;
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null; 
        try{
        	sql = "delete from shoppingcar where id=?";
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ; 
            pstmt.setInt(1,goodId) ;
            pstmt.execute();  
            pstmt.close();
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        } 
	}
	
	//�ύ�û�������Ʒ
	public boolean submitShoppingcar(User user){
		boolean result = false;
		String sql;
		Good[] goods = null;
		int goodnum = 0;
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;
        ResultSet rs = null;
        try{
        	//��ȡ���ﳵ����Ʒ������
        	sql = "select count(*) as 'sum' from shoppingcar where userName =?";
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ; 
            pstmt.setString(1,user.getUsername()) ;
            rs = pstmt.executeQuery();
            if(rs.next()){
            	goodnum = rs.getInt("sum");
            	goods = new Good[goodnum];
            }
            rs.close();
            pstmt.close();
            //�����ﳵ����Ʒ�ݴ���������
            int i=0;
            sql = "select * from shoppingcar where userName =?";
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ; 
            pstmt.setString(1,user.getUsername()) ;
            rs = pstmt.executeQuery();
            while(rs.next()){
            	goods[i]=new Good();
            	goods[i].setName(rs.getString("goodName"));
            	goods[i].setUserName(rs.getString("userName"));
            	goods[i].setUserAddress(rs.getString("userAddress"));
            	i++;
            }
            rs.close();
            pstmt.close();
            //���ݴ��������еĸ�����Ʒ��¼�����û�������ʷ
            for(int j=0;j<goods.length;j++){
            	sql = "insert into shoppinghistory(goodName,userName,userAddress,time) values(?,?,?,?)";
            	dbc = new DBConnect() ;
            	pstmt = dbc.getConnection().prepareStatement(sql) ; 
            	pstmt.setString(1,goods[j].getName());
            	pstmt.setString(2,goods[j].getUserName());
            	pstmt.setString(3,goods[j].getUserAddress());
            	pstmt.setDate(4, new Date(new java.util.Date().getTime())); 
            	pstmt.execute();
                pstmt.close();
            }
            //���û����ﳵ��������ļ�¼
            sql = "delete from shoppingcar where userName = ?";
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ; 
            pstmt.setString(1,user.getUsername()) ;
            pstmt.execute();
            pstmt.close();
            result = true;
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            dbc.close() ;   
        }
		return result;
	}
}
