package sns.client;

import java.util.List;

import sns.client.pojo.UserInfo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
  String greetServer(String name) throws IllegalArgumentException;
  String addUserInfo(UserInfo userbo) throws IllegalArgumentException;
  String deleteUserInfo(Integer [] ids) throws IllegalArgumentException;
  List<UserInfo> FindUserInfo(String whereString) throws IllegalArgumentException;
}
