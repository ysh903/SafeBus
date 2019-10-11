package spring.biz.parents.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring.biz.parents.vo.ParentsVO;
import spring.biz.user.vo.UserVO;

@Component("parentsmybatis")
public class ParentsDAO_Mybatis implements ParentsDAO {

	
	@Autowired
	SqlSession sqlSession=null;
	
	public ParentsDAO_Mybatis() {

	}

	public List<ParentsVO> getParentsList() {
        System.out.println("ParentsDAO_MyBatis 연동");

        return sqlSession.selectList("parents.list");
	}
	
	
}
