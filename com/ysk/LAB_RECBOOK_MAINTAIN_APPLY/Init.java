package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;

//com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.Init
import SomeUtils._hproc;
import SomeUtils.Bean.UserInfoViewBean;

/**
 * 進入(基本上跟新增頁面同頁)簽核頁面變執行的程式.
 * 主要用於帶出資料庫欄位以外的資料.
 * @author b0050
 *
 */
public class Init extends _hproc {

	@Override
	public String action(String paramString) throws Throwable {
		// TODO Auto-generated method stub

		UserInfoViewBean nowUser = getUserInfo(getUser());
		setValue("REQ_EMPID", getUser());
		setValue("REQ_EMPID_NAME", nowUser.getHecname());
		setValue("REQ_DEPT_NAME", nowUser.getDepName());

		return null;
	}

}
