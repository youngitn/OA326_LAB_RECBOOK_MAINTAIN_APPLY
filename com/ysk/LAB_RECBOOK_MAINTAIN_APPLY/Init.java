package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;

//com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.Init
import org.apache.commons.lang.StringUtils;

import jcx.db.talk;
import SomeUtils._hproc;
import SomeUtils.Bean.LabRecbookUsingApplyBean;
import SomeUtils.Bean.UserInfoViewBean;
import SomeUtils.DAO.LabRecbookUsingApplyDAO;

/**
 * 進入(基本上跟新增頁面同頁)簽核頁面變執行的程式. 主要用於帶出資料庫欄位以外的資料.
 * 
 * @author b0050
 *
 */
public class Init extends _hproc {

	@Override
	public String action(String paramString) throws Throwable {
		// TODO Auto-generated method stub

		UserInfoViewBean mUser = getUserInfo(getValue("REQ_EMPID"));

		try {
			if (!mUser.equals(null)) {
				setValue("REQ_EMPID", mUser.getEmpid());
				setValue("REQ_EMPID_NAME", mUser.getHecname());
				setValue("REQ_DEPT_NAME", mUser.getDepName());
				setValue("DATE",
						StringUtils.remove(getValue("DATE"), "00:00:00.0"));
				// 顯示 原紀錄簿資訊
				String bookNo = getValue("RECBOOK_NO").trim();
				LabRecbookUsingApplyDAO labdao = new LabRecbookUsingApplyDAO();
				talk tx = getTalk();
				LabRecbookUsingApplyBean l = labdao
						.getLabRecbookUsingApplyBean(tx, bookNo);
				if (l != null) {
					UserInfoViewBean oldUser = getUserInfo(l.getREQ_EMPID()
							.trim());
					setValue("OLD_REQ_EMPID", l.getREQ_EMPID());
					setValue("OLD_REQ_EMPID_NAME", getName(l.getREQ_EMPID()
							.trim()));
					setValue("OLD_REQ_DEPT_NAME", oldUser.getDepName());
					setValue("OLD_RECBOOK_NAME", l.getRECBOOK_NAME());
					setValue("OLD_REC_END_DATE", l.getREC_END_DATE());
					setValue("OLD_REC_START_DATE", l.getREC_START_DATE());
				}
			}
		} catch (NullPointerException e) {
			System.out.println("OA326--------------"+e.toString());
			
			// TODO: handle exception
		}

		return null;
	}

}
