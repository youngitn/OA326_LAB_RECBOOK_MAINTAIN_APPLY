package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.Maintain;

import jcx.db.talk;

import org.apache.commons.lang.StringUtils;

//com/ysk/LAB_RECBOOK_MAINTAIN_APPLY/Maintain/GoToMaintainDetail
import SomeUtils._hproc;
import SomeUtils.Bean.LabRecbookUsingApplyBean;
import SomeUtils.Bean.UserInfoViewBean;
import SomeUtils.DAO.LabRecbookUsingApplyDAO;
import SomeUtils.DAO.UserInfoViewDAO;

public class GoToMaintainDetail extends _hproc {

	@Override
	public String action(String paramString) throws Throwable {
		addScript("document.getElementById('flow_panel').style.display = 'none';");

		UserInfoViewDAO uDao = new UserInfoViewDAO(getTalk());
		UserInfoViewBean uBean = uDao.getUserInfo(getValue(
				"QUERY_LIST.MAINTAIN_PERSON").trim());
		setValue("MAINTAIN_DATE", getValue("QUERY_LIST.MAINTAIN_DATE").trim());
		setValue("MAINTAIN_PERSON", getValue("QUERY_LIST.MAINTAIN_PERSON")
				.trim());
		setValue("MAINTAIN_PERSON_NAME", uBean.getHecname().trim());
		setValue("MAINTAIN_PERSON_DEPT_NAME", uBean.getDepName().trim());

		setValue("NEW_RECBOOK_NO", getValue("QUERY_LIST.NEW_RECBOOK_NO").trim());
		setValue("NEW_RECBOOK_NAME", getValue("QUERY_LIST.NEW_RECBOOK_NAME")
				.trim());
		setValue("NEW_REQ_EMPID", getValue("QUERY_LIST.NEW_REQ_EMPID").trim());
		setValue("NEW_REC_START_DATE",
				getValue("QUERY_LIST.NEW_REC_START_DATE").trim());
		setValue("NEW_REC_END_DATE", getValue("QUERY_LIST.NEW_REC_END_DATE")
				.trim());
		setValue("OLD_RECBOOK_NO", getValue("QUERY_LIST.OLD_RECBOOK_NO").trim());
		setValue("OLD_RECBOOK_NAME", getValue("QUERY_LIST.OLD_RECBOOK_NAME")
				.trim());
		setValue("OLD_REQ_EMPID", getValue("QUERY_LIST.OLD_REQ_EMPID").trim());
		setValue("OLD_REC_START_DATE",
				getValue("QUERY_LIST.OLD_REC_START_DATE").trim());
		setValue("OLD_REC_END_DATE", getValue("QUERY_LIST.OLD_REC_END_DATE")
				.trim());
		// setValue("MAINTAIN_APPLY_PNO", getValue("PNO").trim());

		if (StringUtils.isEmpty(getValue("QUERY_LIST.OLD_REQ_EMPID").trim())) {

			uBean = uDao.getUserInfo(getValue("QUERY_LIST.OLD_REQ_EMPID")
					.trim());

			setValue("OLD_REQ_EMPID_NAME", uBean.getHecname().trim());
			setValue("OLD_REQ_DEPT_NAME", uBean.getDepName().trim());
		}
		
		if (StringUtils.isEmpty(getValue("QUERY_LIST.OLD_REQ_EMPID").trim())) {
			uBean = uDao.getUserInfo(getValue("QUERY_LIST.NEW_REQ_EMPID")
					.trim());
			setValue("NEW_REQ_EMPID_NAME", uBean.getHecname().trim());
			setValue("NEW_REQ_DEPT_NAME", uBean.getDepName().trim());
		}
		uDao = null;
		uBean = null;
		setVisible("SAVE_MAINTAIN_REC", false);
		setVisible("MAINTAIN_APPLY_PNO", false);
		setVisible("PNO", false);
		return paramString;
	}

}
