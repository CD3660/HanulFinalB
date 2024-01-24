package com.hanul.finalb.product;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired private SqlSession sql;
	
	/** �Ǹ� ��ǰ ����Ʈ�� �������� �޼ҵ� */
	public List<ProductVO> list() {
		return sql.selectList("prod.list");
	}
}
