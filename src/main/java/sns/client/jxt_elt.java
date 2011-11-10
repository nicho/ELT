package sns.client;

import java.util.List;

import sns.client.pojo.UserInfo;
import sns.client.testdata.EmployeeXmlDS;
import sns.client.testdata.UserInfoRecord;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.tree.events.DataArrivedEvent;
import com.smartgwt.client.widgets.tree.events.DataArrivedHandler;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class jxt_elt implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "失败";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		 final ListGrid countryGrid = new ListGrid();  
		 
		HLayout mainLayout = new HLayout();
		mainLayout.setWidth100();
		mainLayout.setHeight100();

		DataSource employeeDS = EmployeeXmlDS.getInstance();

		final TreeGrid treeGrid = new TreeGrid();
		treeGrid.setLoadDataOnDemand(false);
		treeGrid.setWidth("25%");
		treeGrid.setHeight("100%");
		treeGrid.setDataSource(employeeDS);
		treeGrid.setNodeIcon("icons/16/person.png");
		treeGrid.setFolderIcon("icons/16/person.png");
		treeGrid.setShowOpenIcons(false);
		treeGrid.setShowDropIcons(false);
		treeGrid.setClosedIconSuffix("");
		treeGrid.setAutoFetchData(true);
		treeGrid.setShowResizeBar(true);

		TreeGridField field = new TreeGridField();
		field.setName("树列表名称");
		field.setCellFormatter(new CellFormatter() {
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				return record.getAttribute("Job") + ": "
						+ record.getAttribute("Phone");
			}
		});
		treeGrid.setFields(field);

		treeGrid.addDataArrivedHandler(new DataArrivedHandler() {
			public void onDataArrived(DataArrivedEvent event) {
				// treeGrid.getData().openAll(); 打开全部树结构
			}
		});

		mainLayout.addMember(treeGrid);

		VLayout vLayout = new VLayout();
		vLayout.setWidth("75%");

		final DynamicForm form = new DynamicForm();
		form.setWidth("90%");

		final TextItem username = new TextItem();
		username.setName("username");
		username.setTitle("Username");
		username.setRequired(true);
		username.setDefaultValue("bob");

		final TextItem email = new TextItem();
		email.setName("email");
		email.setTitle("Email");
		email.setRequired(true);
		email.setDefaultValue("bob@isomorphic.com");

		MatchesFieldValidator validator = new MatchesFieldValidator();
		validator.setOtherField("password2");
		validator.setErrorMessage("Passwords do not match");

		final PasswordItem password = new PasswordItem();
		password.setName("password");
		password.setTitle("Password");
		password.setRequired(true);
		password.setValidators(validator);

		PasswordItem password2 = new PasswordItem();
		password2.setName("password2");
		password2.setTitle("Password again");
		password2.setRequired(true);

		// 创建窗口提示框
		final Dialog dialogBox = new Dialog();
		dialogBox.setTitle("提示框");
		dialogBox.setWidth(500);
		dialogBox.setHeight(300);
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>添加用户为：</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>服务器返回值：</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogBox.addMember(dialogVPanel);
		
		
		 	final Label label = new Label();  
	        label.setHeight(30);  
	        label.setPadding(10);  
	        label.setAlign(Alignment.CENTER);  
	        label.setValign(VerticalAlignment.CENTER);  
	        label.setWrap(false);  
	        //label.setIcon("icons/16/approved.png");  
	        label.setShowEdges(true);  
	        label.setContents("<i>messages</i>");  
	        label.draw();  
		
		

		final ButtonItem createAccount = new ButtonItem();
		createAccount.setName("createAccount");
		createAccount.setTitle("创建用户");

		createAccount.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				if (form.validate())
				{
					sendNameToServer();
					dialogBox.show();

				}
			}

			private void sendNameToServer() {
				UserInfo user = new UserInfo();
				user.setEmail(email.getValueAsString());
				user.setName(username.getValueAsString());
				user.setPassword(password.getValueAsString());
				String textToServer = "姓名：" + user.getName() + "<br>邮箱"
						+ user.getEmail() + ";";
				// Then, we send the input to the server.

				textToServerLabel.setContents(textToServer);
				serverResponseLabel.setText("");
				greetingService.addUserInfo(user, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setTitle("回调失败");
						serverResponseLabel
								.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.centerInPage();
						
					}

					public void onSuccess(String result) {
						dialogBox.setTitle("回调成功");
						serverResponseLabel
								.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.centerInPage();
						getUserDateSource(countryGrid);
						//  countryGrid.draw();  
					}
				});
			}
		});

		final ButtonItem deleteAccount = new ButtonItem();
		deleteAccount.setName("deleteAccount");
		deleteAccount.setTitle("删除用户");
		
		deleteAccount.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				 SC.confirm("确定要删除选中记录?", new BooleanCallback() {  
	                    public void execute(Boolean value) {  
	                        if (value != null && value) {  
	                        	deleteUserToServer();
	                        } else {  
	                       	 label.setContents("<font color='red'>取消删除</font>");  
	                        }  
	                    }  
	                });  
					
					
			}

			private void deleteUserToServer() {

			
				ListGridRecord [] lgr= countryGrid.getSelectedRecords();
				Integer [] ids=new Integer[lgr.length];
			//	 label.setContents("<font color='red'>"+lgr.length+"</font>-------------"+lgr[0].getAttribute("id"));  
				 for (int i = 0; i < lgr.length; i++) {
					 ids[i]=Integer.parseInt(lgr[i].getAttribute("id"));
				}
				 
				 
				 
				greetingService.deleteUserInfo(ids, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						 label.setContents("<font color='red'>回调失败</font>");  
						
					}

					public void onSuccess(String result) {
					
						 label.setContents("<font color='green'>回调成功</font>-----------"+result);  
						 getUserDateSource(countryGrid);
						//  countryGrid.draw();  
					}
				});
			}
		});

		
		
		
		form.setFields(username, email, password, password2, createAccount,deleteAccount);
		form.setShowResizeBar(true);

		form.draw();



		vLayout.addMember(form);
		vLayout.addMember(label);
		
		
		
		//数据表实现
		
	        countryGrid.setWidth("100%");  
	        countryGrid.setHeight("100%");  
	        countryGrid.setShowAllRecords(true);  
	  
	        ListGridField idfield = new ListGridField("id", "id", 100);  
	        ListGridField nameField = new ListGridField("name", "name",250);  
	        ListGridField emailField = new ListGridField("email", "email", 250);  
	        ListGridField pwdField = new ListGridField("password", "password");    

	  
	        countryGrid.setFields(new ListGridField[] {idfield, nameField, emailField,pwdField});  
	        countryGrid.setCanResizeFields(true);  

	   //     countryGrid.setData(CountryData.getRecords());  
	        getUserDateSource(countryGrid);
			
	        
	        
	        
		
	        countryGrid.draw();  
		
		
		
		vLayout.addMember(countryGrid);

		mainLayout.addMember(vLayout);
		mainLayout.draw();

	}
	private void getUserDateSource(final ListGrid countryGrid) {
		greetingService.FindUserInfo("", new AsyncCallback<List<UserInfo>>() {
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(List<UserInfo> result) {

				UserInfoRecord [] lgr=new UserInfoRecord[result.size()];
				for (int i = 0; i < result.size(); i++) {
					UserInfo user=result.get(i);
					lgr[i]=new UserInfoRecord(user.getId().toString(),user.getName(),user.getEmail(),user.getPassword());
				}
				countryGrid.setData(lgr);
				
			}
		});
	}

}
