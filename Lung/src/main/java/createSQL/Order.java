package createSQL;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class Order {

	public static void main(String[] args) {
		//create Datasource
		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setUser("sa"); 
		ds.setPassword("sa123456"); 
		ds.setServerName("localhost");
		ds.setPortNumber(1433);
		ds.setDatabaseName("Lung");
		
		try {
			//連線
			Connection con = ds.getConnection();
			Statement st = con.createStatement();
			System.out.println("連線至資料庫Lung");
			//創Table
			String dropTable="DROP TABLE IF EXISTS ORDER1";
			String table = "CREATE TABLE ORDER1 ("
					+ " od_id int IDENTITY PRIMARY KEY," //訂單編號 (自動生成)
					+ " od_name nvarchar(500) NOT NULL,"	 //訂單標題
					+ " od_catalogue nvarchar(500) NOT NULL,"//訂單目錄
					+ " od_content nvarchar(1000) NOT NULL,"//訂單內容
					+ " od_amount nvarchar(500) NOT NULL,"//訂單規格
					+ " od_number int NOT NULL,"		 //訂單數量
					+ " od_money int NOT NULL"			 //訂單金額
					+ ")";
			st.executeUpdate(dropTable);
			System.err.println("已刪除ORDER1");
			st.executeUpdate(table);
			System.err.println("...ORDER1已建立");
			
			
			
			//匯入CSV檔
			FileInputStream fis = new FileInputStream("order.csv");
			InputStreamReader isr = new InputStreamReader(fis, "MS950");
			BufferedReader br = new BufferedReader(isr);
			String str;
			while((str = br.readLine()) !=null ) {
				String [] data = str.split(",");
				System.out.println(str);
				for(int i=0;i<data.length;i++) {
					System.out.println(data[i]);
				}
				String insert = "INSERT INTO ORDER1"
						+ "(od_name, od_catalogue, od_content, od_amount, od_number, od_money) VALUES" + "(?, ?, ?, ?, ?, ?)";
				PreparedStatement pstm = con.prepareStatement(insert);
				
				pstm.setString(1, data[0]);
				pstm.setString(2, data[1]);
				pstm.setString(3, data[2]);
				pstm.setString(4, data[3]);
				pstm.setInt(5, Integer.parseInt(data[4]));
				pstm.setInt(6, Integer.parseInt(data[5]));
				pstm.executeUpdate();
			}
			br.close();
		} catch (SQLServerException e) {
			e.printStackTrace();
			System.out.println("連線失敗，請檢查資料庫");
		} catch (FileNotFoundException e) {
			System.out.println("請檢察路徑是否有order.csv檔案");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("匯入資料到SQL失敗，請檢查");
			e.printStackTrace();
		}
		
	}
}
