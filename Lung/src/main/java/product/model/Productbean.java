package product.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Product_Table")
public class Productbean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer pd_id;
	 String pd_items;
	 String pd_product_name;
	 String pd_content;
	 String pd_specification;
	 Integer pd_quantity;
	 Integer pd_amount;

   public Productbean() {
		
	}
	
   public Productbean( Integer pd_id ,String pd_items, String pd_product_name, String pd_content, String pd_specification,Integer pd_quantity,Integer pd_amount) {
	   super();
	   
	   this.pd_id = pd_id;
	   this.pd_items = pd_items;
	   this.pd_product_name = pd_product_name;
	   this.pd_content = pd_content;
	   this.pd_specification = pd_specification;
	   this.pd_quantity = pd_quantity;
	   this.pd_amount = pd_amount;
   }
	public Productbean(String pd_items, String pd_product_name, String pd_content, String pd_specification,Integer pd_quantity,Integer pd_amount) {
		super();
		this.pd_items = pd_items;
		this.pd_product_name = pd_product_name;
		this.pd_content = pd_content;
		this.pd_specification = pd_specification;
		this.pd_quantity = pd_quantity;
		this.pd_amount = pd_amount;
	}
	
	public Productbean(Integer pd_id) {
		this.pd_id = pd_id;
	}
	
	public Integer getpd_id() {
		return pd_id;
	}
	
	public void setpd_id(Integer pd_id) {
		this.pd_id = pd_id;
	}
	
	public void setpd_items(String pd_items) {
		this.pd_items = pd_items;
	}

	public String getpd_items() {
		return pd_items;
	}

	public void setpd_product_name(String pd_product_name) {
		this.pd_product_name = pd_product_name;
	}

	public String getpd_product_name() {
		return pd_product_name;
	}

	public void setpd_content(String pd_content) {
		this.pd_content = pd_content;
	}

	public String getpd_content() {
		return pd_content;
	}


	public void setpd_specification(String pd_specification) {
		this.pd_specification = pd_specification;
	}

	public String getpd_specification() {
		return pd_specification;
	}

	public void setpd_quantity(Integer pd_quantity) {
		this.pd_quantity = pd_quantity;
	}

	public Integer getpd_quantity() {
		return pd_quantity;
	}

	public void setpd_amount(Integer pd_amount) {
		this.pd_amount = pd_amount;
	}

	public Integer getpd_amount() {
		return pd_amount;
	}

	
}