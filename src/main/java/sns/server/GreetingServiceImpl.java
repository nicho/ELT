package sns.server;

import java.util.List;

import sns.client.GreetingService;
import sns.client.pojo.UserInfo;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;

import core.dao.BaseDao;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
	@Inject
	private BaseDao baseDao;


	public String greetServer(String input) throws IllegalArgumentException {
		UserInfo us = (UserInfo) baseDao.findById(UserInfo.class, 1);
		return "username="+us.getName();
	}

	@Override
	public String addUserInfo(UserInfo userbo) throws IllegalArgumentException {
		baseDao.save(userbo);
		return "创建用户成功！";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> FindUserInfo(String whereString)
			throws IllegalArgumentException {
		List<UserInfo> userlt=baseDao.find("from eltuser ");
		return userlt;
	}

	@Override
	public String deleteUserInfo(Integer [] ids) throws IllegalArgumentException {
		for (int i = 0; i < ids.length; i++) {
			UserInfo us = (UserInfo) baseDao.findById(UserInfo.class, ids[i]);
			baseDao.delete(us);
		}

		return "删除用户成功！";
	}


}
