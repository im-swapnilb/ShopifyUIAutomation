package com.Shopify.CommonUtils;

public class CartPageDetalis {

	private String subtotal_amt = null;
	private String total_amt = null;
	private String discount_amt = null;
	private String product_Name = null;
	private String product_unit_price = null;
	private String discount_name = null;

	public Double num_Subtotal_amt;
	public Double num_total_amt;
	public Double num_discount_amt;
	public Double num_product_unit_price;

	public String getDiscount_name() {
		return discount_name;
	}

	public void setDiscount_name(String discount_name) {
		this.discount_name = discount_name;
	}

	public Double getSubtotal_amt() {
		num_Subtotal_amt = PriceParserTest.priceParser(subtotal_amt);
		return num_Subtotal_amt;
	}

	public void setSubtotal_amt(String subtotal_amt) {
		this.subtotal_amt = subtotal_amt;
	}

	public Double getTotal_amt() {
		num_total_amt = PriceParserTest.priceParser(total_amt);
		return num_total_amt;
	}

	public Double getProduct_unit_price() {
		num_product_unit_price = PriceParserTest.priceParser(product_unit_price);
		return num_product_unit_price;
	}

	public void setProduct_unit_price(String product_unit_price) {
		this.product_unit_price = product_unit_price;
	}

	public void setTotal_amt(String total_amt) {
		this.total_amt = total_amt;
	}

	public Double getDiscount_amt() {
		num_discount_amt = PriceParserTest.priceParser(discount_amt);
		return num_discount_amt;
	}

	public void setDiscount_amt(String discount_amt) {
		this.discount_amt = discount_amt;
	}

	public String getProduct_Name() {
		return product_Name;
	}

	public void setProduct_Name(String product_Name) {
		this.product_Name = product_Name;
	}

}
