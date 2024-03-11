package com.hanul.finalb.product;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired private SqlSession sql;
	
	/** 판매 제품 리스트를 가져오는 메소드 */
	public List<ProductVO> list() {
		return sql.selectList("prod.list");
	}
	/** 판매 제품 리스트를 가져오는 메소드 */
	public ProductVO info(int prod_id) {
		return sql.selectOne("prod.info", prod_id);
	}
}
