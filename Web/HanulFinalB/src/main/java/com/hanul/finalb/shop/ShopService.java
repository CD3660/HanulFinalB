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

	/** �Ǹ� ��ǰ ����Ʈ�� �������� �޼ҵ� */
	public List<ProductVO> list() {
		return sql.selectList("prod.list");
	}
}
