package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;

//import com.ysk.bean.UserInfoViewBean;
import SomeUtils._hproc;
import SomeUtils.Bean.UserInfoViewBean;


/**
 * 進入新增頁面時,需要一併帶出的資料在此設定.
 * 主要用於帶出資料庫欄位以外的資料.
 * @author b0050
 *
 */
public class GoToAdd extends _hproc {
	@Override
	public String action(String value) throws Throwable {
		// 可自定HTML版本各欄位的預設值與按鈕的動作
		// 傳入值 value
		//
		UserInfoViewBean nowUser = getUserInfo(getUser());
		setValue("REQ_EMPID", getUser());
		setValue("REQ_EMPID_NAME",nowUser.getHecname());
		setValue("REQ_DEPT_NAME",nowUser.getDepName());
		return value;
	}

	public String getInformation() {
		return "---------------DO_QUERY(\u9001\u51fa\u67e5\u8a62).html_action()----------------";
	}
}