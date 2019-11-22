package com.student.dao;

import java.util.Map;

public interface UserDao {
	Map<String,Object> findUserByIdAndPwd(String loginId,String pwd) throws Exception;
}
