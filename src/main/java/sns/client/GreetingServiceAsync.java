package sns.client;

import java.util.List;

import sns.client.pojo.UserInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void addUserInfo(UserInfo userbo,AsyncCallback<String> callback) throws IllegalArgumentException;
	void FindUserInfo(String whereString, AsyncCallback<List<UserInfo>> callback);
	void deleteUserInfo(Integer [] ids, AsyncCallback<String> callback);
}
