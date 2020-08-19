package com.Shopify.CommonUtils;

public class PriceParserTest {

	public static Double priceParser(String price) {
		System.out.println("input price "+price);
		String[] p1 = price.split(" ");
		System.out.println("value of P1 "+p1);
		System.out.println(p1.length);
		for (int i =0;i<p1.length;i++) {
			System.out.println("Value of P1 of"+ i +p1[i]);
		}
		System.out.println("Value of P1 of 1"+p1[1]);
//		
		String mainprice = p1[1].replace(",", "");
		System.out.println("MainPrice is "+ mainprice);
		Double numPrice = Double.parseDouble(mainprice);
		System.out.println("Num price is "+numPrice);
		return numPrice;

	}
	
	public static Double priceParserDiscount(String price) {
		System.out.println("input price "+price);
		String[] p1 = price.split(" ");
		System.out.println("value of P1 "+p1);
		System.out.println(p1.length);
		for (int i =0;i<p1.length;i++) {
			System.out.println("Value of P1 of"+ i +p1[i]);
		}
		System.out.println("Value of P1 of 1"+p1[2]);
//		
		String mainprice = p1[2].replace(",", "");
		System.out.println("MainPrice is "+ mainprice);
		Double numPrice = Double.parseDouble(mainprice);
		System.out.println("Num price is "+numPrice);
		return numPrice;

	}
}
