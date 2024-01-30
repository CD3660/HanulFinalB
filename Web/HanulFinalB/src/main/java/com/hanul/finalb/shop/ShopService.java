package com.hanul.finalb.shop;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanul.finalb.product.ProductVO;

@Service
public class ShopService {
	@Autowired
	private SqlSession sql;

	/** 판매 제품 리스트를 가져오는 메소드 */
	public List<ProductVO> list() {
		return sql.selectList("prod.list");
	}
}
