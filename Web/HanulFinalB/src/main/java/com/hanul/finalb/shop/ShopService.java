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
}
