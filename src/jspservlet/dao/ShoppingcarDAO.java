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
	
	//根据用户信息获取购物车信息
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
	
	//向存储购物车信息的表添加一项纪录
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
	
	//根据记录id从购物车删除一项购买记录
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
	
	//提交用户所有商品
	public boolean submitShoppingcar(User user){
		boolean result = false;
		String sql;
		Good[] goods = null;
		int goodnum = 0;
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;
        ResultSet rs = null;
        try{
        	//获取购物车里商品的总数
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
            //将购物车的商品暂存在数组里
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
            //将暂存在数组中的各个商品记录放入用户购买历史
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
            //从用户购物车中清空他的记录
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
