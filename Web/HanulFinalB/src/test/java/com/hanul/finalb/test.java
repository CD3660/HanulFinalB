package com.hanul.finalb;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hanul.finalb.shop.ShopService;

public class test {

	
	@Test
	public void testMethod() {
		String url = "https://drive.google.com/thumbnail?sz=w640&id=1UVPCnyEWizYIn4w6r0nb0TxlDqzjMwer";
		System.out.println(url.replace("https://drive.google.com/thumbnail?sz=w640&id=", ""));
	}
	

	

}
