import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class menbercheck{
private Connection conn;
private int num;
public void check(String name,String password) throws ClassNotFoundException, SQLException {
	SQLseverconn sql=new SQLseverconn();
	conn=sql.createconn();
	String str1="select * from userlist where userName=? and userPwd=?";
	PreparedStatement staus = conn.prepareStatement(str1);
	staus.setString(1, name);
	staus.setString(2, password);

	ResultSet rs = staus.executeQuery();
	if(rs.next()) 
	{
		System.out.println("you login in Success!");
		System.out.println();
		System.out.println("Entry you need group Number:");
		Scanner input1=new Scanner(System.in);
		int num =input1.nextInt();
		counts abx=new counts();
		for (int i=0;i<num;i++)
		abx.count(100000);
	}else {
		System.out.println("you entry wrong account or password");
	}
	rs.close();
	staus.close();
	sql.closeconn();
}






}
