package nju.express.test;

import java.util.ArrayList;

import nju.express.dataservice.impl.UserDataServiceImpl;
import nju.express.vo.User;

public class SQLTest {

	public static void main(String[] args) {
		String sql="select * from user , job where user.job_id_fk = job.id ";
		Object[]obs={};
		ArrayList<User> users=new UserDataServiceImpl().getListBySql(User.class, sql, obs);
		System.out.println(users.get(0).getJob_name());

	}

}
