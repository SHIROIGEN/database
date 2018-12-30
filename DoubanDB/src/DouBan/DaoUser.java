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
            // ����һ��MD5���ܼ���ժҪ
            MessageDigest md = MessageDigest.getInstance("MD5");
            // ����md5����
            md.update(passwd.getBytes());
            // digest()���ȷ������md5 hashֵ������ֵΪ8λ�ַ�������Ϊmd5 hashֵ��16λ��hexֵ��ʵ���Ͼ���8λ���ַ�
            // BigInteger������8λ���ַ���ת����16λhexֵ�����ַ�������ʾ���õ��ַ�����ʽ��hashֵ
            //һ��byte�ǰ�λ�����ƣ�Ҳ����2λʮ�������ַ���2��8�η�����16��2�η���
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
