package com.hanul.finalb.shop;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

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
	/** 주문 명세 생성 */
	public int createCart(String user_id, int prod_id, int ea) {
		
		return 0;
	}
	public void getToken() {
		String result = comm.requestAPI("api.iamport.kr/users/getToken", "imp_key="+imp_key+"&imp_secret="+imp_secret);
		System.out.println(result);
	}
}
