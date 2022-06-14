package member.dao;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import member.dao.impl.MemberDaoImpl;


public class OutputJson {

	MemberDao memberdao =  new MemberDaoImpl();

	public void outputJson(String filename) {
		JSONObject outerjson = new JSONObject();  //json的key值
		List<Map<String,String>> allMembers = memberdao.listMapMembers();  //取得List<Map<>>型態的所有會員
		int counter = 0;
		for( Map<String, String> mapMember : allMembers ) {
			         //new JSONObject(mapMember),把map放進去產生json物件,json型態很像map
    		JSONObject rowjson = new JSONObject(mapMember);  //把一列資料變成json object
    		counter += 1;  //放完一筆(row)就記一次，代表json資料的序號
    		outerjson.put(counter, rowjson);  //outerjson內放的是(序號, 一列會員資料)
    		if(counter == 1000) {
    			//System.out.println(counter);
    			break;
    		}
    	}
		
		try {
			Files.write(Paths.get(filename), outerjson.toJSONString().replace("\\", "").getBytes());
		    System.out.println("output Json success");   //toJSONString()會自動補反斜線(跳脫字元)所以把反斜線替換成空字串
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
