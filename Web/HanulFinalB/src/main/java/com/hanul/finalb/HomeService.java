package com.hanul.finalb;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanul.finalb.product.ProductVO;

@Service
public class HomeService {
	@Autowired private SqlSession sql;
	
	
	
	//����ȭ�� ���â�� ���� ����Ʈ 
	public ProductVO product_info(int id) {
		
		return sql.selectOne("home.info", id);
	}
}


