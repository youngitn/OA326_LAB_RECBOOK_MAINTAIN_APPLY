package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.Rule;

//com/ysk/LAB_RECBOOK_MAINTAIN_APPLY/Rule/LabBookManagerRule
import java.util.Vector;

import SomeUtils._bRule;
import SomeUtils.Bean.UserInfoViewBean;

import com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG;
import com.ysk.util.Log;
import com.ysk.util.LogUtil;

/**
 * 實驗室紀錄簿管理人簽核關卡, 在此會判斷申請者部門決定簽核者是誰.
 * 
 * @see 簽核群組號碼設定檔: LAB_RECBOOK_MAINTAIN_APPLY_RULE_CONFIG.java.
 * @author b0050
 *
 */
public class LabBookManagerRule extends _bRule {
	Log log = LogUtil.getLog(this.getClass());

	@Override
	public Vector<String> getIDs(String value) throws Throwable {
		// TODO Auto-generated method stub
		Vector<String> id = new Vector<String>();
		// _hproc hproc = new _hproc();
		UserInfoViewBean user = getUserInfo(getData("REQ_EMPID"));

		// 判斷申請者部門.
		switch (user.getDepNo()) {
		case LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.RD_DEPT_NO:
			id.addElement(getSignPeopleFromHruserDeptBas(LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.RD_LabManager_HRUSER_DEPT_BAS));
			break;
		case LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.QC_DEPT_NO:
			id.addElement(getSignPeopleFromHruserDeptBas(LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.QC_LabManager_HRUSER_DEPT_BAS));
			break;
		default:
			// 如申請者本身部門不包含在之前條件中,則往上判斷一層部門.
			switch (user.getParentNo()) {
			case LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.RD_DEPT_NO:
				id.addElement(getSignPeopleFromHruserDeptBas(LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.RD_LabManager_HRUSER_DEPT_BAS));
				break;
			case LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.QC_DEPT_NO:
				id.addElement(getSignPeopleFromHruserDeptBas(LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.QC_LabManager_HRUSER_DEPT_BAS));
				break;
			default:
				break;
			}
			break;
		}

		id.addElement("admin");
		return id;

	}

}
