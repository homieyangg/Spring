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
import java.text.SimpleDateFormat;
import java.util.Date;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class createAnnounce {

	public static void main(String[] args) {
		//create Datasource
		SQLServerDataSource ds = new SQLServerDataSource();
				ds.setUser("sa"); 
				ds.setPassword("passw0rd"); 
				ds.setServerName("localhost");
				ds.setPortNumber(1433);
				ds.setDatabaseName("Lung");
		
		try {
			//連線
			Connection con = ds.getConnection();
			Statement st = con.createStatement();
			System.out.println("連線至資料庫Lung");
			//創Table
			String dropTable = "DROP TABLE IF EXISTS ANNOUNCE";
			String creatTable = "CREATE TABLE ANNOUNCE (\r\n"
					+ "AN_NO int IDENTITY PRIMARY KEY,\r\n"
					+ "AN_TITLE nvarchar(50),\r\n"
					+ "AN_CONTENT nvarchar(100),\r\n"
					+ "AN_TYPE nvarchar(1),\r\n"
					+ "AN_EDITOR nvarchar(10),\r\n"
					+ "AN_DATE date,\r\n"
					+ ")";
			
			st.executeUpdate(dropTable);
			System.out.println("ANNOUCE table已刪除");
//			st.clearBatch();
			st.executeUpdate(creatTable);
			System.out.println("ANNOUNCE table已建立");
			
			//匯入CSV檔
			FileInputStream fis = new FileInputStream("ANNOUNCE.csv");
			InputStreamReader isr = new InputStreamReader(fis, "MS950");
			BufferedReader br = new BufferedReader(isr);
			String str;
			br.readLine();
			while((str = br.readLine()) !=null ) {
				String [] data = str.split(",");
				System.out.println(str);
				for(int i = 0; i < data.length; i++) {
					System.out.println(data[i]);
				}
				String insert = "INSERT INTO ANNOUNCE"
						+ "(AN_TITLE, AN_CONTENT, AN_TYPE, AN_EDITOR, AN_DATE) VALUES" + "(?, ?, ?, ?, ?)";
				PreparedStatement pstm = con.prepareStatement(insert);
				
				pstm.setString(1, data[0]);
				pstm.setString(2, data[1]);
				pstm.setString(3, data[2]);
				pstm.setString(4, data[3]);
				
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
		        	date = simpleDateFormat.parse(data[4]);
		        	pstm.setDate(5, new java.sql.Date(date.getTime()));
		        	pstm.executeUpdate();
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
			}
			br.close();
		} catch (SQLServerException e) {
			e.printStackTrace();
			System.out.println("連線失敗，請檢查資料庫");
		} catch (FileNotFoundException e) {
			System.out.println("請檢察路徑是否有ANNOUNCE.csv檔案");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("匯入資料到SQL失敗，請檢查");
			e.printStackTrace();
		}
	}

}
