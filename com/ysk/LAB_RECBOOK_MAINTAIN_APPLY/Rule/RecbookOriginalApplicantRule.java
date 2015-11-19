package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.Rule;
//com/ysk/LAB_RECBOOK_MAINTAIN_APPLY/Rule/RecbookOriginalApplicantRule
import java.util.Vector;

import jcx.db.talk;

import com.ysk.util.Log;
import com.ysk.util.LogUtil;

import SomeUtils._bRule;
import SomeUtils.Bean.LabRecbookUsingApplyBean;
import SomeUtils.DAO.LabRecbookUsingApplyDAO;
/**
 * 實驗室紀錄簿-原申請人關卡.
 * @author YangTing
 *
 */
public class RecbookOriginalApplicantRule extends _bRule{
	Log log = LogUtil.getLog(this.getClass());
	@Override
	public Vector<String> getIDs(String arg0) throws Throwable {
		
		Vector<String> id = new Vector<String>();
		// TODO Auto-generated method stub	
		
		talk t = getTalk();
		LabRecbookUsingApplyDAO l = new LabRecbookUsingApplyDAO(t);
		if (l != null){
			LabRecbookUsingApplyBean lbean = l.getLabRecbookUsingApplyBean(getData("RECBOOK_NO"));
			id.addElement(lbean.getREQ_EMPID().trim());
		}
//		t.queryFromPool("select REQ_EMPID")
		id.addElement("admin");
		l = null;
		
		return id;
	}
}
