package com.hanul.finalb.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hanul.finalb.common.Common;
import com.hanul.finalb.product.ProductVO;

@Service
@PropertySource("classpath:db/conninfo.properties")
public class ShopService {
	@Autowired
	private SqlSession sql;
	@Autowired
	private Common comm;
	
	@Value("${portone.key}")
	String imp_key;
	@Value("${portone.secret}")
	String imp_secret;

	/** 전체 상품 내용 조회 */
	public List<ProductVO> list() {
		return sql.selectList("prod.list");
	}
	/** 아이디로 상품 개별 내용 조회 */
	public ProductVO info(int id) {
		return sql.selectOne("prod.info", id);
	}
	/** 상품 정보 등록 */
	public int insert(ProductVO vo) {
		
		return sql.insert("prod.insert", vo);
	}
	/** 상품 정보 수정 */
	public int update(ProductVO vo, boolean maintain) {
		int result = sql.update("prod.update", vo);
		if(!maintain) {
			sql.update("prod.updateFile", vo);
		}
		return result;
	}
	/** 상품 정보 삭제 */
	public int delete(int id) {
		
		return sql.delete("prod.delete", id);
	}
	/** 즉시 구매하기 위한 주문정보 레코드 생성 */
	public OrderVO payNow(OrderVO vo) {
		int result = sql.insert("order.payNow", vo);
		if(result == 1) {
			vo = sql.selectOne("order.findPayNow", vo);
		}
		return vo;
	}
	/** 주문 명세 생성 */
	public int createCart(String user_id, int prod_id, int ea) {
		
		return 0;
	}
	public String getToken() {
		String result = comm.requestAPI("https://api.iamport.kr/users/getToken", "imp_key="+imp_key+"&imp_secret="+imp_secret);
		System.out.println(result);
		Map<String, Object> map = new Gson().fromJson(result, new TypeToken<Map<String,Object>>(){}.getType());
		Map<String, Object> response = (Map<String, Object>) map.get("response");
		String token = (String) response.get("access_token");
		return token;
	}
	public String prepare(String uid, int amount) {
		String result = comm.portone("https://api.iamport.kr/payments/prepare", "merchant_uid="+uid+"&amount="+amount , getToken());
		return result;
	}
	public String paymentsCheck(String uid) {
		String result = comm.portone("https://api.iamport.kr/payments/"+uid, getToken());
		System.out.println(result);
		return result;
	}
}
