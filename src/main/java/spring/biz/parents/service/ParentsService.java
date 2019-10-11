package spring.biz.parents.service;

import java.util.List;

import spring.biz.parents.vo.ParentsVO;



public interface ParentsService {

	   //ParentsVO getUser(String user_id);
	   
	   List<ParentsVO> getParentsList();
	   
}
