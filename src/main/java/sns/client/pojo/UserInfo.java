package sns.client.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="eltuser")
public class UserInfo implements java.io.Serializable {


	private static final long serialVersionUID = 1L;

	public static final String USER_SESSION = "userInfoSession";// 用户session常量

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;//

	

	@Column(length = 100)
	private String email;// 电子邮件

	@Column(length = 100)
	private String name;// 真实姓名

	@Column(length = 100)
	private String password;// 密码


	/** default constructor */
	public UserInfo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}




}
