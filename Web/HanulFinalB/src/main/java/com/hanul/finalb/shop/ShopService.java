package com.hanul.finalb.shop;

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
	/** 검색된 상품 내용 조회 */
	public List<ProductVO> list(String keyword) {
		return sql.selectList("prod.list_keyword", keyword);
	}

	/** 아이디로 상품 개별 내용 조회 */
	public ProductVO info(int prod_id) {
		return sql.selectOne("prod.info", prod_id);
	}

	/** 상품 정보 등록 */
	public int insert(ProductVO vo) {

		return sql.insert("prod.insert", vo);
	}

	/** 상품 정보 수정 */
	public int update(ProductVO vo, boolean maintain) {
		int result = sql.update("prod.update", vo);
		if (!maintain) {
			sql.update("prod.updateFile", vo);
		}
		return result;
	}

	/** 상품 정보 삭제 */
	public int delete(int id) {

		return sql.delete("prod.delete", id);
	}

	/** 즉시 구매하기 위한 주문정보 VO 생성 */
	public void payNow(OrderVO vo) {
		OrderVO temp = sql.selectOne("order.prod_info", vo);
		vo.setPrice(temp.getPrice());
		vo.setProd_name(temp.getProd_name());
		vo.setProd_img(temp.getProd_img());
		sql.insert("order.payNowOrder", vo);
		vo.setOrder_id(sql.selectOne("order.payNowId", vo) + "");
	}

	/** 주문 명세 생성 */
	public int createCart(OrderVO vo) {

		return sql.insert("order.to_cart", vo);
	}
	/** 주문 명세 삭제 */
	public int deleteCart(int order_id) {

		return sql.delete("order.delete_cart", order_id);
	}
	/** 총 가격 생성 */
	public int getTotalPrice(List<OrderVO> list) {
		int totalPrice = 0;
		for (OrderVO vo : list) {
			totalPrice += vo.getPrice()*vo.getEa();
		}
		return totalPrice;
	}
	/** order_id 묶음 String 생성 */
	public String inOrderId(List<OrderVO> list) {
		StringBuffer sb = new StringBuffer("(");
		for(int i=0; i<list.size(); i++) {
			if(i == 0) {
				sb.append(list.get(i).getOrder_id()+"");
			} else {
				
			}
			sb.append(", "+list.get(i).getOrder_id());
		}
		sb.append(")");
		return sb.toString();
	}
	
	

	/** user_id로 카트 리스트 반환 */
	public List<OrderVO> cartList(String user_id) {

		return sql.selectList("order.cartList", user_id);
	}

	/** 주문 명세 생성 */
	public int insertPayments(PaymentVO vo) {
		int result = sql.insert("order.payments", vo);
		if (result == 1) {
			sql.update("order.endPayment", vo);

		}
		return result;
	}
	
	/** 리뷰 작성 */
	public int createReview(ReviewVO vo) {

		return sql.insert("review.insert", vo);
	}
	/** 리뷰 삭제 */
	public int deleteReview(int review_id) {

		return sql.delete("review.delete", review_id);
	}

	public String getToken() {
		String result = comm.requestAPI("https://api.iamport.kr/users/getToken",
				"imp_key=" + imp_key + "&imp_secret=" + imp_secret);
		System.out.println(result);
		Map<String, Object> map = new Gson().fromJson(result, new TypeToken<Map<String, Object>>() {
		}.getType());
		Map<String, Object> response = (Map<String, Object>) map.get("response");
		String token = (String) response.get("access_token");
		return token;
	}

	public String prepare(String uid, int amount) {
		String result = comm.portone("https://api.iamport.kr/payments/prepare",
				"merchant_uid=" + uid + "&amount=" + amount, getToken());
		return result;
	}

	public String paymentsCheck(String uid) {
		String result = comm.portone("https://api.iamport.kr/payments/" + uid, getToken());
		System.out.println(result);
		return result;
	}

	/* * 리뷰 정보를 가져오는 메소드 */
	public List<ReviewVO> getReview(int prod_id) {
		return sql.selectList("review.list", prod_id);
	}
	/* * 리뷰 작성 대상자인지 확인 */
	public List<OrderVO> can_review_check(ReviewVO vo) {
		return sql.selectList("review.can_review_check", vo);
	}

}
