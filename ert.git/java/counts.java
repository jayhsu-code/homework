import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class counts {
	private int []list1=new int [42];
	private int []listnum=new int [42];
	private int temp=0;
	private int tempnum=0;
	
	public void count(int num) throws ClassNotFoundException, SQLException {
	
		for (int i=0;i<num;i++) {
			int j =(int) (Math.random()*42+1);
			list1[j-1]+=1;
			listnum[j-1]=j;
			
		}
		for(int i=0;i<list1.length-1;i++) {
			for(int j=i+1;j<list1.length;j++) {
			if(list1[i]<=list1[j]) {
				temp=list1[i];
				list1[i]=list1[j];
				list1[j]=temp;
				tempnum=listnum[i];
				listnum[i]=listnum[j];
				listnum[j]=tempnum;
			}else {
				continue;
			}
		} 
			}
		
		for(int i=0;i<6;i++) {
			updatelettrynum(listnum[i],list1[i]);
		}
		list1= new int[42];
		listnum= new int[42];
		System.out.println("data save finished!");
		
		
	}
	public void updatelettrynum(int i,int j) throws ClassNotFoundException, SQLException {
		String urlstr= "insert into letterynum(letteryNumber,letterycount)values(?,?)";
		SQLseverconn sql=new SQLseverconn();
		Connection conn=sql.createconn();
		PreparedStatement state = conn.prepareStatement(urlstr);
		state.setInt(1, i);
		state.setInt(2, j);
		state.executeUpdate();
		state.close();
		sql.closeconn();
		
		
		
		}

}
