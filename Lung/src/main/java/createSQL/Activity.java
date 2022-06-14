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

public class Activity {

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
			String table = "DROP TABLE IF EXISTS ACTIVITIES "
					+ " CREATE TABLE ACTIVITIES("
					+ " ac_id int IDENTITY PRIMARY KEY,"
					+ " ac_name varchar(200) NOT NULL,"	 //活動標題
					+ " ac_image nvarchar(500) NOT NULL,"	 //活動照片
					+ " ac_date date NOT NULL,\r\n"  //活動時間
					+ " ac_participant varchar(500) NOT NULL,"//活動參加對象
					+ " ac_venue varchar(200) NOT NULL,"//活動地點
					+ " ac_quota int NOT NULL,"//活動名額
					+ " ac_waitlist_quota int NOT NULL,"		 //候補名額
					+ " ac_fee int NOT NULL,"			 //活動報名費用
					+ " ac_organizer nvarchar(500) NOT NULL,"			 //主辦單位
					+ ")";// FOREIGN KEY REFERENCES INFORMATION(in_title)
			st.executeUpdate(table);
			System.out.println("...ACTIVITIES已建立");
			
			
			
			//匯入CSV檔
//			FileInputStream fis = new FileInputStream("activities.csv");
//			InputStreamReader isr = new InputStreamReader(fis, "MS950");
//			try (BufferedReader br = new BufferedReader(isr)) {
//				String str;
//				while((str = br.readLine()) !=null ) {
//					String [] data = str.split(",");
//					System.out.println(str);
//					for(int i=0;i<data.length;i++) {
//						System.out.println(data[i]);
//					}
//					String insert = "INSERT INTO ACTIVITIES"
//							+ "(ac_name, ac_date, ac_participant, ac_venue, ac_quota, ac_waitlist_quota, ac_fee, ac_organizer) VALUES" + "(?, ?, ?, ?, ?, ?, ?, ?)";
//					PreparedStatement pstm = con.prepareStatement(insert);
//					
//					pstm.setString(1, data[0]);
//					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//					Date date = null;
//					try {
//			        	date = simpleDateFormat.parse(data[1]);
//					} catch (java.text.ParseException e) {
//						e.printStackTrace();
//					}
//					pstm.setDate(2, new java.sql.Date(date.getTime()));
//					pstm.setString(3, data[2]);
//					pstm.setString(4, data[3]);
//					pstm.setInt(5, Integer.parseInt(data[4]));
//					pstm.setInt(6, Integer.parseInt(data[5]));
//					pstm.setInt(7, Integer.parseInt(data[6]));
//					pstm.setString(8, data[7]);
//					pstm.executeUpdate();
//				}
//				br.close();
//			} catch (NumberFormatException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		} catch (SQLServerException e) {
			e.printStackTrace();
			System.out.println("連線失敗，請檢查資料庫");
		} 
//		catch (FileNotFoundException e) {
//			System.out.println("請檢察路徑是否有activities.csv檔案");
//			e.printStackTrace();
//		} 
//		catch (IOException e) {
//			e.printStackTrace();
//		} 
		catch (SQLException e) {
			System.out.println("匯入資料到SQL失敗，請檢查");
			e.printStackTrace();
		}
	}

}
