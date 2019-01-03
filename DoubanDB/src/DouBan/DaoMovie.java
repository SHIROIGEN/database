package DouBan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class DaoMovie extends DouBan.DaoBase{
	private void MyPrint(String a) {
		System.out.println(a);
	}
	private void GoodPrint(String m) {
		int z = m.length();
		int i = 0;
		
		while(true) {
			if(i+20<=z)
			System.out.println(m.substring(i, i+20));
			else {
				System.out.println(m.substring(i,z));
				break;
			}
			i+=20;
		}
	}
	private String MovieName = "";
	private void PrintMovie(ResultSet rs) {
		int col = 0;
		try {
			col = rs.getMetaData().getColumnCount();
		}
		catch(SQLException e) {
			e.getNextException();
			
		}
		try {
		
				for(int i = 1;i<=col;i++) {
					this.GoodPrint(rs.getString(i));
				}
				System.out.print("\n");
		
		}
		catch(SQLException sqlE){
			sqlE.printStackTrace();
		}
		
	String actor = "SELECT Actor FROM Actor_Movie WHERE MovieName='";
	actor+=this.MovieName;
	actor+="';";
	ResultSet Rs = this.Search(actor);
		try {
			System.out.println("Actor:");
			while(Rs.next()) {
				System.out.print(Rs.getString(1)+"\n");
			}
			rs.close();
		}
		catch(SQLException sqlE) {
			sqlE.printStackTrace();
		}
		System.out.println("Score:");
		this.GetScoreofMovie();
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
	public void InsertCinecism(int Userid) {
		this.MyPrint("Start to Write the Cinecism!");
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");

		
		String Cin="INSERT INTO Cinecism VALUES('";
		Cin+=this.MovieName;
		Cin+="',";
		Cin+="'";
		Scanner s = new Scanner(System.in);
		Cin+=s.nextLine();
		Cin+="',";
		Cin+=String.valueOf(Userid);
		Cin+=",'";
		Cin+=ft.format(date);
		Cin+="');";
		System.out.println(Cin);
		s.close();
		this.Insert(Cin);
	}
	public void GetMovieCincism() {
		String query = "SELECT Cinecism FROM Cinecism WHERE MovieName='";
		query +=this.MovieName;
		query +="';";
		ResultSet rs = this.Search(query);
		try {
			System.out.println("The Cinecism of "+this.MovieName);
			while(rs.next()) {
				this.GoodPrint(rs.getString(1));
			}
		}
		catch(SQLException sqlE){
			sqlE.printStackTrace();
	}
	}
	public void GetmyCincism(int UserId) {
		String query = "SELECT Cinecism FROM Cinecism WHERE UserId='";
		query +=String.valueOf(UserId);
		query +="';";
		ResultSet rs = this.Search(query);
		try {
			while(rs.next()) {
				this.GoodPrint(rs.getString(1));
			}
		}
		catch(SQLException sqlE){
			sqlE.printStackTrace();
	}
	}
	private void GetScoreofMovie() {
		String query ="SELECT AVG(MovieScore) as Sc FROM Score where MovieName='";
		query+=this.MovieName;
		query+="';";
		ResultSet rs = this.Search(query);
		try {
			if(rs.next()) {
				this.MyPrint(String.valueOf(rs.getFloat(1)));
			}
		}
		catch(SQLException sqlE){
			sqlE.printStackTrace();
	}
	}
	public void getMovie(String MovieName) {
		ResultSet rs = null;
		String query = "SELECT * FROM Moive WHERE MovieName='";
		query+=MovieName;
		query+="';";
		//System.out.println(query);
		rs = this.Search(query);
		try {
			if(rs.next()) {
				this.MovieName = MovieName;
				System.out.println("find the movie "+MovieName);
				this.PrintMovie(rs);
				
			}
			else {
				System.out.println("not find  "+MovieName);
			}
		}
		catch(SQLException sqlE){
			sqlE.printStackTrace();
	}
	}
	public void getScoreofUser(int UserId) {
		String query = "SELECT  MovieNaem,MovieScore  FROM Score where UserId='";
		query+=String.valueOf(UserId);
		query+="';";
		ResultSet rs = this.Search(query);
		try {
			while(rs.next()) {
				System.out.println(rs.getString(1)+"\t"+rs.getFloat(2));
			}
		}
		catch(SQLException sqlE){
			sqlE.printStackTrace();
	}	
	}
	public void GetMoivebyType(String type) {
		String query = "SELECT MovieName ,Type FROM Moive";
		ResultSet rs = this.Search(query);
		try {	
			while(rs.next()) {
				String child[]=null;
				child=rs.getString(2).split(type);
				if(child!=null) {
					this.getMovie(rs.getString(1));
				}
		}
		}
		catch(SQLException e) {}
	}
	public String GetName() {
		return this.MovieName;
	}
	public void Rating(int Userid) {
		String query = "SELECT MovieScore FROM Score WHERE UserId=";
		query+=String.valueOf(Userid);
		query+=" AND MovieName='";
		query+=this.MovieName;
		query+="';";
		ResultSet rs = this.Search(query);
		try { 
			
			if(rs.next())
			{
				System.out.println("the movie's rate is "+rs.getFloat(1)+"\nchange the rate");
				float c;
				Scanner s = new Scanner(System.in);
				c=s.nextFloat();
				//s.close();
				query = "UPDATE Score SET [MovieScore]=";
				query+=String.valueOf(c);
				query+=" WHEREi UserId=";
				query+=String.valueOf(Userid);
				query+=" AND MovieName='";
				query+=this.MovieName;
				query+="';";
			
				this.Insert(query);
				
				
			}
			else
			{
				System.out.println("rate the movie!");
				float c;
				Scanner s = new Scanner(System.in);
				if(s.hasNextFloat()) {
				c=s.nextFloat();
			//`	s.close();
				query = "INSERT INTO Score VALUES(";
				query+=String.valueOf(Userid);
				query+=",'";
				query+=this.MovieName;
				query+="',";
				query+=String.valueOf(c);
				query+=");";
				this.Insert(query);
				}
				
			}
			
		}
		catch(SQLException e) {}
		
	}
}
