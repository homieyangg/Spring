package createSQL;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class AbDogCreatSQL {
	
	public static void main(String[] args) {

       try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Lung","sa", "passw0rd");
			Statement stmt = con.createStatement();			
			System.out.print("成功連結資料庫");

               System.out.println("成功連結 text_db 資料庫");

               stmt = con.createStatement();  // 產生 SQL 敘述物件

            String sql="CREATE TABLE AbDog("
            		    + "id int IDENTITY  PRIMARY KEY ,"//自動給
					    + "abid VARCHAR(45) NOT NULL,"//
        		        + "abname varchar(50)," //
	            		+ "abphone varchar(50)," //
	                    + "abemail varchar(50)," //      
	                    + "abdogname varchar(50),"//
	                    + "abaddress varchar(50),"//            
	                    + "abtype varchar(50),"//                   
	                    + "abage INT ,"//              
	                    + "abdate DATE )";//            		 
			

	stmt.executeUpdate(sql);
	
	System.out.println("Created table in given database...");
	 con.close();      

        }

              catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

              catch (SQLException e) {

            e.printStackTrace();

        }

}
}

