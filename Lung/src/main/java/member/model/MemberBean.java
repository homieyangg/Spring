package member.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Member_Table")
public class MemberBean {
	@Id   //PK值
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //SQL自動新增
	private Integer mi_no;
	
	@Column(columnDefinition = "NVARCHAR(50) NOT NULL")
	private String mi_name;
	
	@Column(columnDefinition = "NVARCHAR(20) NOT NULL")
	private String mi_id;
	
	@Column(columnDefinition = "DATE NOT NULL")
	private Date mi_birth;
	
	@Column(columnDefinition = "NVARCHAR(20) NOT NULL")
	private String mi_phone;
	
	@Column(columnDefinition = "NVARCHAR(50) NOT NULL")
	private String mi_email;
	
	@Column(columnDefinition = "NVARCHAR(100) NOT NULL")
	private String mi_address;
	
	@Column(columnDefinition = "NVARCHAR(50) NOT NULL", unique = true) //UK值
	private String mi_account;
	
	@Column(columnDefinition = "NVARCHAR(50) NOT NULL")
	private String mi_password;

	
	//All Constructor  //9個欄位
	public MemberBean(Integer mi_no, String mi_name, String mi_id, Date mi_birth, String mi_phone, String mi_email,
			String mi_address, String mi_account, String mi_password) {
		super();
		this.mi_no = mi_no;
		this.mi_name = mi_name;
		this.mi_id = mi_id;
		this.mi_birth = mi_birth;
		this.mi_phone = mi_phone;
		this.mi_email = mi_email;
		this.mi_address = mi_address;
		this.mi_account = mi_account;
		this.mi_password = mi_password;
	}

	

	//Constructor without mi_no  //8個欄位
	public MemberBean(String mi_name, String mi_id, Date mi_birth, String mi_phone, String mi_email, String mi_address,
			String mi_account, String mi_password) {
		super();
		this.mi_name = mi_name;
		this.mi_id = mi_id;
		this.mi_birth = mi_birth;
		this.mi_phone = mi_phone;
		this.mi_email = mi_email;
		this.mi_address = mi_address;
		this.mi_account = mi_account;
		this.mi_password = mi_password;
	}
	
	
	


	// Constructor with account and password
	public MemberBean(String mi_account, String mi_password) {
		super();
		this.mi_account = mi_account;
		this.mi_password = mi_password;
	}




	public MemberBean(Integer mi_no) {
		super();
		this.mi_no = mi_no;
	}



	// Constructor with nothing
	public MemberBean() {
	}


	
	// getter and setter
	public Integer getMi_no() {
		return mi_no;
	}


	public void setMi_no(Integer mi_no) {
		this.mi_no = mi_no;
	}


	public String getMi_name() {
		return mi_name;
	}


	public void setMi_name(String mi_name) {
		this.mi_name = mi_name;
	}


	public String getMi_id() {
		return mi_id;
	}


	public void setMi_id(String mi_id) {
		this.mi_id = mi_id;
	}
	
	
	public Date getMi_birth() {
		return mi_birth;
	}

	
	public void setMi_birth(Date mi_birth) {
		this.mi_birth = mi_birth;
	}


	public String getMi_phone() {
		return mi_phone;
	}


	public void setMi_phone(String mi_phone) {
		this.mi_phone = mi_phone;
	}


	public String getMi_email() {
		return mi_email;
	}


	public void setMi_email(String mi_email) {
		this.mi_email = mi_email;
	}


	public String getMi_address() {
		return mi_address;
	}


	public void setMi_address(String mi_address) {
		this.mi_address = mi_address;
	}


	public String getMi_account() {
		return mi_account;
	}


	public void setMi_account(String mi_account) {
		this.mi_account = mi_account;
	}


	public String getMi_password() {
		return mi_password;
	}


	public void setMi_password(String mi_password) {
		this.mi_password = mi_password;
	}


	@Override
	public String toString() {
		return "MemberBean [mi_no=" + mi_no + ", mi_name=" + mi_name + ", mi_id=" + mi_id + ", mi_birth=" + mi_birth
				+ ", mi_phone=" + mi_phone + ", mi_email=" + mi_email + ", mi_address=" + mi_address + ", mi_account=" 
				+ mi_account + ", mi_password=" + mi_password + "]";
	}


}
	
	