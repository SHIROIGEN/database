package DouBan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoUser extends DouBan.DaoBase{
	private int userid =-1;
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
			e.printStackTrace();
		}
	}
	public void Login(String username,String passwd) {
		this.userid = -1;
		String query = "SELECT userid FROM user WHERE username=";
		query +=username;
		query +=";";
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
			String yanzheng = "SELECT passwd FROM uname WHERE userid=";
			yanzheng+=this.userid;
			yanzheng+=";";
			rs = this.Search(yanzheng);
			try {
				if(rs.next()) {
					if(rs.getString(1)==passwd) {
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
		this.userid = -1;
		String query = "SELECT userid FROM uname WHERE username='";
		query +=username;
		query +="';";
		ResultSet rs = this.Search(query);
		try {
			if(rs.next()){
				System.out.println("the username is exist");
				return ;
			}
			else {
				int count=-1;
				query="SELECT COUNT(*) as tt FROM uname;";
		
				rs =this.Search(query);
			
					if(rs.next())
				count=rs.getInt("tt");
				count++;
				query = "INSERT INTO uname values('";
				query+=username;
				query+="');";
				this.Insert(query);
				if(count>0) {
					query = "INSERT INTO auser values(";
					query+=String.valueOf(count);
					query+=",";
					query+="HashBytes('MD5','";
					query+=passwd;
					query+="'));";
			
					this.Insert(query);
					this.userid=count;
				}
				else {
					return ;
				}
			}
				
		}
		catch(SQLException sqlE){
			int count=-1;
			query="SELECT COUNT(*) FROM uname";
			rs =this.Search(query);
			try {
				count = rs.getInt(1);
			}
			catch(SQLException sql1){
				sql1.printStackTrace();
			}
			count++;
			query = "INSERT INTO uname values('";
			query+=username;
			query+="');";
			this.Insert(query);
			if(count>0) {
				query = "INSERT INTO user values(";
				query+=String.valueOf(count);
				query+=",";
				query+="md5('";
				query+=passwd;
				query+="'));";
				this.Insert(query);
				this.userid=count;
			}
			else {
				return ;
			}
	}
	}
	public int getid() {
		return this.userid;
	}
	public String geturl() {
		return this.url;
	}
}
