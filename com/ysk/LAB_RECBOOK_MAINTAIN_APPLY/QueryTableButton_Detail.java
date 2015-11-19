package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;

//com/ysk/LAB_RECBOOK_MAINTAIN_APPLY/QueryTableButton_Detail;
import jcx.db.talk;

import org.apache.commons.lang.StringUtils;

import SomeUtils._bproc;
import SomeUtils.Bean.LabRecbookUsingApplyBean;
import SomeUtils.Bean.UserInfoViewBean;
import SomeUtils.DAO.LabRecbookUsingApplyDAO;
import SomeUtils.DAO.UserInfoViewDAO;

/**
 * 用來在表格中做為按鈕,顯示詳細資料.<br>
 * 用於emaker設計模式中,進入表格欄位,預設值.
 * 
 * @author b0050
 *
 */
public class QueryTableButton_Detail extends _bproc {

	@Override
	public String getDefaultValue(String arg0) throws Throwable {
		// 0:id 1:name
		String[] empIdName = getValue("QUERY_LIST.REQ_EMPID").split("-");
		// TODO Auto-generated method stub
		UserInfoViewDAO ud = new UserInfoViewDAO(getTalk());
		UserInfoViewBean user = ud.getUserInfo(empIdName[0].trim());

		// UserInfoViewBean user = getUserInfo(empIdName[0].trim());
		// message(user.getEmpid());
		setValue("PNO", getValue("QUERY_LIST.PNO"));
		setValue("REQ_EMPID", user.getEmpid());
		setValue("DATE",
				StringUtils.remove(getValue("QUERY_LIST.DATE"), "00:00:00.0")
						.trim());
		setValue("RECBOOK_NO", getValue("QUERY_LIST.RECBOOK_NO"));

		setValue("REQ_EMPID_NAME", empIdName[1]);
		setValue("REQ_DEPT_NAME", user.getDepName());

		// 顯示 原紀錄簿資訊
		String bookNo = getValue("QUERY_LIST.RECBOOK_NO").trim();

		talk tx = getTalk();
		LabRecbookUsingApplyDAO labdao = new LabRecbookUsingApplyDAO(tx);
		LabRecbookUsingApplyBean l = labdao.getLabRecbookUsingApplyBean(bookNo);

		if (l != null) {
			UserInfoViewBean nowUser = ud.getUserInfo(l.getREQ_EMPID().trim());
			setValue("OLD_REQ_EMPID", l.getREQ_EMPID());
			setValue("OLD_REQ_EMPID_NAME", getName(l.getREQ_EMPID().trim()));
			setValue("OLD_REQ_DEPT_NAME", nowUser.getDepName());
			setValue("OLD_RECBOOK_NAME", l.getRECBOOK_NAME());
			setValue("OLD_REC_END_DATE", l.getREC_END_DATE());
			setValue("OLD_REC_START_DATE", l.getREC_START_DATE());
		}
		setValue("REASON", getValue("QUERY_LIST.REASON"));
		setValue("CONTENT", getValue("QUERY_LIST.CONTENT"));
		// 設定欄位可不可視
		setVisible("DoAdd", false);
		ud = null;
		labdao = null;
		return null;
	}

}
