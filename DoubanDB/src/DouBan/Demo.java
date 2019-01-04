package DouBan;
import java.sql.Connection;
import java.util.Date;
import java.text.*;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DouBan.DaoUser;
import DouBan.DaoMovie;
import java.util.Scanner;
public class Demo {
	public static void main(String arg[]) {
		//String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=DoubanDB;user=student;password=student";
		Scanner s = new Scanner(System.in);
		DaoUser user = new DaoUser();
		DaoMovie Movie = new DaoMovie();
		String username,passwd;
		String moviename;
		String cmd;
		
		while(true) {
			System.out.println("please input the options\n");
			
			cmd = s.next();
			
			if(cmd.equals("Login")) {
				s = new Scanner(System.in);
				System.out.println("please input the username\n");
				username = s.next();
				System.out.println("please input the password\n");
				passwd = s.next();
				user.Login(username, passwd);
			}
			if(cmd.equals("Movie")) {
				System.out.println("please input the MovieName\n");
				//s.close();
				s = new Scanner(System.in);
				moviename = s.next();
				Movie.getMovie(moviename);
				Movie.GetMovieCincism();
			}
			if(cmd.equals("Search")) {
				s = new Scanner(System.in);
				String type;
				cmd = s.next();
				if(cmd.equals("Type"))
				{
					type = s.next();
					Movie.GetMoivebyType(type);
				}
				
			}
			if(cmd.equals("Cinecism")) {
				if(Movie.GetName().equals("")) {
					System.out.println("Not yet selected the Movie\n");
				}
				else {
					if(user.getid()==-1) {
						System.out.println("Please Login first\n");
					}
					else {
					Movie.InsertCinecism(user.getid());
					}
				}
			}
			if(cmd.equals("Rating")) {
				System.out.println("Please rate the movie");
				Movie.Rating(user.getid());
			}
			if(cmd.equals("Register")) {
				System.out.println("please input the username\n");
				username = s.next();
				System.out.println("please input the password\n");
				passwd = s.next();
				user.Register(username, passwd);
			}
			if(cmd.equals("Tuijian")){
				Movie.Tuijian(user.getid());
			}
			if(cmd.equals("quit"))
				break;
			
		}
		System.out.println("Bye!");
		s.close();
		System.exit(0);
	}
}
