package createSQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class createProduct {
	public static void main(String[] args) {

		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Lung", "sa","passw0rd");
			Statement st = con.createStatement();
			System.out.print("成功連結資料庫");

			String sql = "CREATE TABLE Product("+ "pd_id INT IDENTITY PRIMARY KEY," + "pd_items NVARCHAR(MAX), " + "pd_product_name NVARCHAR(MAX),"
					+ "pd_content NVARCHAR(MAX)," + "pd_specification NVARCHAR(MAX)," + "pd_quantity INT," + "pd_amount INT)";
					

			st.executeUpdate(sql);
			System.out.println("Created table in given database...");
 
			con.close(); // 關閉資料庫連線

		}

		catch (ClassNotFoundException e) {

			e.printStackTrace();

		}

		catch (SQLException e) {

			e.printStackTrace();

		}

	}
}