package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.Rule;
//com/ysk/LAB_RECBOOK_MAINTAIN_APPLY/Rule/RecbookOriginalApplicantRule
import java.util.Vector;

import com.ysk.util.Log;
import com.ysk.util.LogUtil;

import SomeUtils._bRule;
import SomeUtils.Bean.LabRecbookUsingApplyBean;
import SomeUtils.DAO.LabRecbookUsingApplyDAO;
/**
 * ����Ǭ���ï-��ӽФH���d.
 * @author YangTing
 *
 */
public class RecbookOriginalApplicantRule extends _bRule{
	Log log = LogUtil.getLog(this.getClass());
	@Override
	public Vector<String> getIDs(String arg0) throws Throwable {
		
		Vector<String> id = new Vector<String>();
		// TODO Auto-generated method stub	
		LabRecbookUsingApplyDAO l = new LabRecbookUsingApplyDAO();
		
		if (l != null){
			LabRecbookUsingApplyBean lbean = l.getLabRecbookUsingApplyBean(getTalk(), getData("RECBOOK_NO"));
			id.addElement(lbean.getREQ_EMPID());
		}
		id.addElement("admin");
		
		
		return id;
	}
}