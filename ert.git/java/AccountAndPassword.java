import java.sql.SQLException;
import java.util.Scanner;

public class AccountAndPassword {
private String account;
private String password;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		
//		AccountAndPassword pass=new AccountAndPassword();
//		pass.menber();
		
		
	}

	public void menber() throws ClassNotFoundException, SQLException {
		Scanner input1=new Scanner(System.in);
		System.out.println("請輸入帳號：");
		account =input1.next().trim();
		System.out.println("請輸入密碼：");
		
		password=input1.next().trim();
		if(account!=""&& password!="") {
			menbercheck check=new menbercheck();
			check.check(account, password);
		}
			else {
				System.out.println("you must be entry account or password");
				
			
		input1.close();
	}
	
}
}
