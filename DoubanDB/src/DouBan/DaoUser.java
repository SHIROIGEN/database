package DouBan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.security.MessageDigest;
import java.math.BigInteger;
public class DaoUser extends DouBan.DaoBase{
	private int userid =-1;
	public DaoUser(String url) {
		this.url = url;
	}
	public DaoUser() {
		this.url =  "jdbc:sqlserver://127.0.0.1:1433;databaseName=DouBanDB;user=student;password=student";
	}
	protected ResultSet Search(String query) {
		Connection conn = this.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return rs;
	}
	protected void Insert(String query) {
		Connection conn = this.getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(query);
		}
		catch (SQLException e) {
			
		}
	}
	public void Login(String username,String passwd) {
		 try {
	            
	            MessageDigest md = MessageDigest.getInstance("MD5");
	         
	            md.update(passwd.getBytes());
	    
	            passwd = new BigInteger(1, md.digest()).toString(16);
	        } catch (Exception e) {
	           e.printStackTrace();
	        
	        }
		this.userid = -1;
		String query = "SELECT UserId FROM UserName WHERE UserN='";
		query +=username;
		query +="';";
		ResultSet rs = this.Search(query);
		try {
			if(rs.next()){
				this.userid = rs.getInt(1);
			}
		}
		catch(SQLException sqlE){
				sqlE.printStackTrace();
		}
		if(this.userid==-1) {
			System.out.println("user is not exist!");
			return ;
		}
		else {
			String yanzheng = "SELECT Passwd FROM TheUser WHERE UserId=";
			yanzheng+=this.userid;
			yanzheng+=";";
			rs = this.Search(yanzheng);
			try {
				if(rs.next()) {
					String Passwd = rs.getString(1);
					if(Passwd.equals(passwd)) {
						System.out.println("Login Success!");
						return ;
					}
					else {
						System.out.println("Login Faild!");
						this.userid=-1;
						return ;
					}
				}
			}
			catch(SQLException sqlE){
				sqlE.printStackTrace();
		}
		}
	}
	public void Register(String username,String passwd) {
		try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(passwd.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            passwd = new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
           e.printStackTrace();
        
        }
		this.userid = -1;
		String query = "SELECT UserId FROM UserName WHERE UserN='";
		query +=username;
		query +="';";
		System.out.println(query);
		ResultSet rs = this.Search(query);
		try {
			if(rs.next()){
				System.out.println("the username is exist");
				return ;
			}
			else {//INSERT
				int count=-1;
				query="SELECT COUNT(*) as tt FROM TheUser;";
		
				rs =this.Search(query);
					if(rs.next())
						count=rs.getInt("tt");
				count++;
				query = "INSERT INTO TheUser values(";
				query+=String.valueOf(count);
				query+=",'";
				query+=passwd;
				query+="');";
				this.Insert(query);
					query = "INSERT INTO UserName values('";
					query+=username;
					query+="',";
					query+=String.valueOf(count);
					query+=");";
			
					this.Insert(query);
					this.userid=count;
				}
				
			}
		catch(SQLException e) {}
				
		}
	public int getid() {
		return this.userid;
	}
	public String geturl() {
		return this.url;
	}
}
