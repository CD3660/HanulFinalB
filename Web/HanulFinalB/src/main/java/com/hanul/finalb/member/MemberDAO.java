package com.hanul.finalb.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
    @Autowired
    private SqlSession sqlSession;

    public String findIdByEmail(String email) {
        return sqlSession.selectOne("map.member_mapper.findIdByEmail", email);
    }
}