package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;

//com/ysk/LAB_RECBOOK_MAINTAIN_APPLY/QueryTableButton_Detail;
import SomeUtils._bproc;
import SomeUtils.Bean.UserInfoViewBean;

/**
 * 用來在表格中做為按鈕,顯示詳細資料.<br>
 * 用於emaker設計模式中,進入表格欄位,預設值.
 * @author b0050
 *
 */
public class QueryTableButton_Detail extends _bproc {

	@Override
	public String getDefaultValue(String arg0) throws Throwable {
		// 0:id 1:name
		String[] empIdName = getValue("QUERY_LIST.REQ_EMPID").split("-");
		// TODO Auto-generated method stub
		UserInfoViewBean user = getUserInfo(empIdName[0].trim());
		//message(user.getEmpid());
		setValue("PNO", getValue("QUERY_LIST.PNO"));
		setValue("REQ_EMPID", user.getEmpid());
		setValue("REC_START_DATE", getValue("QUERY_LIST.REC_START_DATE"));
		setValue("REC_END_DATE", getValue("QUERY_LIST.REC_END_DATE"));
		setValue("RECBOOK_NAME", getValue("QUERY_LIST.RECBOOK_NAME"));
		setValue("RECBOOK_NO", getValue("QUERY_LIST.RECBOOK_NO"));

		setValue("REQ_EMPID_NAME", empIdName[1]);
		setValue("REQ_DEPT_NAME", user.getDepName());
		setVisible("DoAdd", false);
		// QUERY SEND COMPUTE

		return null;
	}

	
}
