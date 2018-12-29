package DouBan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class DaoMovie extends DouBan.DaoBase{
	private void MyPrint(String a) {
		System.out.println(a);
	}
	private String MovieName = "";
	private void PrintMovie(ResultSet rs) {
		int col = 0;
		try {col = rs.getMetaData().getColumnCount();}
		catch(SQLException e) {
			e.getNextException();
			
		}
		finally {}
		
		try {
			while(rs.next()){
				for(int i = 1;i<=col;i++) {
					System.out.print(rs.getString(i)+"  ");
				}
				System.out.print("\n");
			}
			rs.close();
		}
		catch(SQLException sqlE){
			sqlE.printStackTrace();
		}
		
		finally {}
	String actor = "SELECT Actor FROM Actor_Movie WHERE Movie='";
	actor+=this.MovieName;
	actor+="';";
	ResultSet Rs = this.Search(actor);
		try {
			while(Rs.next()) {
				System.out.print(Rs.getString(1)+"\n");
			}
			rs.close();
		}
		catch(SQLException sqlE) {
			sqlE.printStackTrace();
		}
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
			e.printStackTrace();
		}
	}
	public void InsertCinecism(int Userid) {
		this.MyPrint("Start to Write the Cinecism!");
		
		String Cin="INSERT INTO Cinecism VALUES(";
		Cin+=this.MovieName;
		Cin+="',";
		Cin+="'";
		Scanner s = new Scanner(System.in);
		Cin+=s.nextLine();
		Cin+="','";
		Cin+=String.valueOf(Userid);
		Cin+="');";
		s.close();
		this.Insert(Cin);
	}
	public void GetMovieCincism() {
		String query = "SELECT Cine FROM Cinecism WHERE MovieName='";
		query +=this.MovieName;
		query +="';";
		ResultSet rs = this.Search(query);
		try {
			while(rs.next()) {
				this.MyPrint(rs.getString(1));
			}
		}
		catch(SQLException sqlE){
			sqlE.printStackTrace();
	}
	}
	public void GetmyCincism(int UserId) {
		String query = "SELECT Cine FROM Cinecism WHERE UserId='";
		query +=String.valueOf(UserId);
		query +="';";
		ResultSet rs = this.Search(query);
		try {
			while(rs.next()) {
				this.MyPrint(rs.getString(1));
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
		String query = "SELECT * FROM Movie WHERE MovieName=";
		query+="'";
		query+=MovieName;
		query+="';";
		rs = this.Search(query);
		try {
			if(rs.next()) {
				this.MovieName = MovieName;
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
	
}
