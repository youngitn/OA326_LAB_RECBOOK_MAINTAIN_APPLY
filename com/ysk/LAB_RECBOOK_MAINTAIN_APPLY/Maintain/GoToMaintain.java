package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.Maintain;

// com/ysk/LAB_RECBOOK_MAINTAIN_APPLY/Maintain/Init;
import jcx.db.talk;

import org.apache.commons.lang.StringUtils;

import SomeUtils._hproc;
import SomeUtils.Bean.LabRecbookUsingApplyBean;
import SomeUtils.Bean.UserInfoViewBean;
import SomeUtils.DAO.LabRecbookUsingApplyDAO;
import SomeUtils.DAO.UserInfoViewDAO;

public class GoToMaintain extends _hproc {

	@Override
	public String action(String paramString) throws Throwable {
		// TODO Auto-generated method stub
		
		addScript("document.getElementById('flow_panel').style.display = 'none';");
		talk t = getTalk();
		String sql = "select * from LAB_RECBOOK_MAINTAIN_RECORDS where 1 = 0";
		String[][] allFieldName = t.getColumnsFromPool(sql);
		for (int i = 0; i < allFieldName.length; i++) {
			if (allFieldName[i][0].equals("PNO")
					|| StringUtils.contains(allFieldName[i][0], "MAINTAIN_")
					|| StringUtils.contains(allFieldName[i][0], "OLD_")) {
				continue;
			}

			setEditable(allFieldName[i][0], true);

		}
		setEditable("SAVE_MAINTAIN_REC", true);

		LabRecbookUsingApplyDAO ldao = new LabRecbookUsingApplyDAO(t);
		LabRecbookUsingApplyBean old_bean = ldao
				.getLabRecbookUsingApplyBean(getValue("RECBOOK_NO").trim());
		
		UserInfoViewDAO uDao = new UserInfoViewDAO(t);
		UserInfoViewBean user = uDao.getUserInfo(getUser());
		UserInfoViewBean oldUser = uDao.getUserInfo(old_bean.getREQ_EMPID().trim());
		setValue("MAINTAIN_DATE", getToday("YYYYmmdd"));
		setValue("MAINTAIN_PERSON",user.getEmpid().trim());
		setValue("MAINTAIN_PERSON_NAME", user.getHecname().trim());
		setValue("MAINTAIN_PERSON_DEPT_NAME", user.getDepName().trim());
		
		setValue("NEW_RECBOOK_NO", old_bean.getRECBOOK_NO().trim());
		setValue("NEW_RECBOOK_NAME", old_bean.getRECBOOK_NAME().trim());
		setValue("NEW_REQ_EMPID", old_bean.getREQ_EMPID().trim());
		setValue("NEW_REC_START_DATE", old_bean.getREC_START_DATE().trim());
		setValue("NEW_REC_END_DATE", old_bean.getREC_END_DATE().trim());
		setValue("OLD_RECBOOK_NO", old_bean.getRECBOOK_NO().trim());
		setValue("OLD_RECBOOK_NAME", old_bean.getRECBOOK_NAME().trim());
		setValue("OLD_REQ_EMPID", old_bean.getREQ_EMPID().trim());
		setValue("OLD_REC_START_DATE", old_bean.getREC_START_DATE().trim());
		setValue("OLD_REC_END_DATE", old_bean.getREC_END_DATE().trim());
		setValue("MAINTAIN_APPLY_PNO", getValue("PNO").trim());
		
		setValue("OLD_REQ_EMPID_NAME", oldUser.getHecname().trim());
		setValue("OLD_REQ_DEPT_NAME", oldUser.getDepName().trim());
		setValue("NEW_REQ_EMPID_NAME", oldUser.getHecname().trim());
		setValue("NEW_REQ_DEPT_NAME", oldUser.getDepName().trim());
		
		setEditable("SAVE_MAINTAIN_REC", true);
		return null;
	}
}
