package com.Shopify.CommonUtils;

public class PriceParserTest {

	public static Double priceParser(String price) {

		String[] p1 = price.split(" ");
//		System.out.println(p1.length);
//		System.out.println(p1[1]);
//		
		String mainprice = p1[1].replace(",", "");
		Double numPrice = Double.parseDouble(mainprice);
		return numPrice;

	}
}
