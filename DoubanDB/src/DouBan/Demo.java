package DouBan;
import java.sql.Connection;
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
		//String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Douban;user=student;password=student";
		Scanner s = new Scanner(System.in);
		DaoUser user = new DaoUser();
		DaoMovie Movie = new DaoMovie();
		String username,passwd;
		String moviename;
		String cmd;
		while(true) {
			System.out.println("please input the options");
			cmd = s.next();
			if(cmd.equals("Login")) {
				s = new Scanner(System.in);
				System.out.println("please input the username");
				username = s.next();
				System.out.println("please input the password");
				passwd = s.next();
				user.Login(username, passwd);
			}
			if(cmd.equals("Movie")) {
				System.out.println("please input the MovieName");
				s = new Scanner(System.in);
				moviename = s.next();
				Movie.getMovie(moviename);
			}
			if(cmd.equals("quit"))
				break;
			
		}
	//	System.out.println("Start to Login");
	
	//	Movie.getMovie("–ƒ¡È≤∂ ÷");
	//	Movie.GetMovieCincism();
		System.out.println("Bye!");
		System.exit(0);
	}
}
