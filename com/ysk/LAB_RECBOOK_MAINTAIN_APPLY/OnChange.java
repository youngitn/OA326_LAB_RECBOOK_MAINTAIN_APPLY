package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;

import jcx.db.talk;

import org.apache.commons.lang.StringUtils;

import SomeUtils._hproc;
import SomeUtils.Bean.LabRecbookUsingApplyBean;
import SomeUtils.Bean.UserInfoViewBean;
import SomeUtils.DAO.LabRecbookUsingApplyDAO;
import SomeUtils.DAO.UserInfoViewDAO;

/**
 * 當某個欄位變化時的事件處理.
 * 
 * @author b0050
 *
 */
public class OnChange extends _hproc {
	@Override
	public String action(String value) throws Throwable {
		/**
		 * qyeryPage QUERY_REQ_EMPID
		 */
		if (getName().equals("QUERY_REQ_EMPID")) {
			String queryEmpid = getValue(getName());
			if (!StringUtils.isEmpty(queryEmpid)) {
				UserInfoViewDAO ud = new UserInfoViewDAO(getTalk());
				UserInfoViewBean nowUser = ud.getUserInfo(getValue(getName()));
				ud = null ;
				//UserInfoViewBean nowUser = getUserInfo(getValue(getName()));
				if (nowUser != null) {
					setValue("QUERY_REQ_EMPID_NAME", nowUser.getHecname());
					setValue("QUERY_REQ_DEPT_NAME", nowUser.getDepName());
				}

			} else {
				setValue("QUERY_REQ_EMPID_NAME", "");
				setValue("QUERY_REQ_DEPT_NAME", "");
			}
		}
		/**
		 * addPage RECBOOK_NO
		 */
		if (getName().equals("RECBOOK_NO")) {
			String bookNo = getValue("RECBOOK_NO");
			LabRecbookUsingApplyDAO labdao = new LabRecbookUsingApplyDAO();
			talk tx = getTalk();
			LabRecbookUsingApplyBean l = labdao.getLabRecbookUsingApplyBean(tx,
					bookNo);
			if (l != null) {
				UserInfoViewDAO ud = new UserInfoViewDAO(getTalk());
				UserInfoViewBean nowUser = ud.getUserInfo(l.getREQ_EMPID().trim());
				ud = null ;
				//UserInfoViewBean nowUser = getUserInfo(l.getREQ_EMPID().trim());
				setValue("OLD_REQ_EMPID", l.getREQ_EMPID());
				setValue("OLD_REQ_EMPID_NAME", getName(l.getREQ_EMPID().trim()));
				setValue("OLD_REQ_DEPT_NAME", nowUser.getDepName());
				setValue("OLD_RECBOOK_NAME", l.getRECBOOK_NAME());
				setValue("OLD_REC_END_DATE", l.getREC_END_DATE());
				setValue("OLD_REC_START_DATE", l.getREC_START_DATE());
			} else {
				setValue("OLD_REQ_EMPID", "");
				setValue("OLD_REQ_EMPID_NAME", "");
				setValue("OLD_REQ_DEPT_NAME", "");
				setValue("OLD_RECBOOK_NAME", "");
				setValue("OLD_REC_END_DATE", "");
				setValue("OLD_REC_START_DATE", "");
			}
		}

		return value;

	}

	public String getInformation() {
		return "---------------DO_QUERY(\u9001\u51fa\u67e5\u8a62).html_action()----------------";
	}
}
