package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.Maintain;
//com/ysk/LAB_RECBOOK_MAINTAIN_APPLY/Maintain/OnChange;
import org.apache.commons.lang.StringUtils;

import SomeUtils._hproc;
import SomeUtils.Bean.UserInfoViewBean;
import SomeUtils.DAO.UserInfoViewDAO;

public class OnChange extends _hproc {
	@Override
	public String action(String value) throws Throwable {
		if (getName().equals("NEW_REQ_EMPID")) {
			String queryEmpid = getValue(getName());
			if (!StringUtils.isEmpty(queryEmpid)) {
				UserInfoViewDAO ud = new UserInfoViewDAO(getTalk());
				UserInfoViewBean nowUser = ud.getUserInfo(getValue(getName()));
				ud = null ;
				//UserInfoViewBean nowUser = getUserInfo(getValue(getName()));
				if (nowUser != null) {
					setValue("NEW_REQ_EMPID_NAME", nowUser.getHecname());
					setValue("NEW_REQ_DEPT_NAME", nowUser.getDepName());
				}

			} else {
				setValue("NEW_REQ_EMPID_NAME", "");
				setValue("NEW_REQ_DEPT_NAME", "");
			}
		}
		
		return value;
	}
}
