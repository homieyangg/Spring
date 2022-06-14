package createSQL;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateProductValue {
	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Lung",
					"sa", "passw0rd");
			Statement st = con.createStatement();
			System.out.print("成功連結資料庫");

			FileReader fr = new FileReader("寵物商品.csv");// 抓CSV檔進java
			BufferedReader brdFile = new BufferedReader(fr);// bufferedReader
			String strLine = null;
			brdFile.readLine();
			while ((strLine = brdFile.readLine()) != null) {// 將CSV檔字串一列一列讀入並存起來直到沒有列為止

				String[] array = strLine.trim().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)",-1);//(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)",-1)忽略購號分隔
				System.out.println(strLine);
				for (int i = 0; i < array.length; i++) {// 偷看陣列元素有沒有切對				
					System.out.println(array[i]);
				}
				String qryInsert = "Insert INTO Product "
						  + "(  pd_items ,  pd_product_name , pd_content , pd_specification ,  pd_quantity ,pd_amount ) VALUES" +"(?,?,?,?,?,?)";// 動態SQL指令

				PreparedStatement pstmt = con.prepareStatement(qryInsert);// 因為是insert所以用PreparedStatement來接

				
				pstmt.setString(1, array[0]);
				pstmt.setString(2, array[1]);// 因為這欄是用int的格式，所以將array[1]值抓出來轉int
				pstmt.setString(3, array[2]);
				pstmt.setString(4, array[3]);
				pstmt.setInt(5, Integer.parseInt(array[4]));
				pstmt.setInt(6, Integer.parseInt(array[5]));
				

				pstmt.executeUpdate();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
