package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.Maintain;

//com/ysk/LAB_RECBOOK_MAINTAIN_APPLY/Maintain/SaveMaintainRec;
import jcx.db.talk;
import SomeUtils._hproc;
import SomeUtils.Bean.LabRecbookUsingApplyBean;
import SomeUtils.DAO.LabRecbookUsingApplyDAO;

public class SaveMaintainRec extends _hproc {

	@Override
	public String action(String paramString) throws Throwable {
		message("");
		talk t = getTalk();
		
		LabRecbookUsingApplyDAO dao = new LabRecbookUsingApplyDAO(t);
		LabRecbookUsingApplyBean bean = dao
				.getLabRecbookUsingApplyBean(getValue("OLD_RECBOOK_NO"));
		//��s��γ�
		bean.setRECBOOK_NO(getValue("NEW_RECBOOK_NO").trim());
		bean.setRECBOOK_NAME(getValue("NEW_RECBOOK_NAME").trim());
		bean.setREC_START_DATE(getValue("NEW_REC_START_DATE"));
		bean.setREC_END_DATE(getValue("NEW_REC_END_DATE").trim());
		bean.setREQ_EMPID(getValue("NEW_REQ_EMPID").trim());
		dao.update(bean);
		
		//�B�z���ʬ��� ���ܧ����~�x�s���x���� �S�ܧ�����ŭ�.
		if (getValue("OLD_RECBOOK_NO").trim().equals(
				getValue("NEW_RECBOOK_NO").trim())) {
			setValue("NEW_RECBOOK_NO", "");			

		}else{
			t.execFromPool("update LAB_RECBOOK_MAINTAIN_APPLY set "
					+ " RECBOOK_NO = '" + getValue("NEW_RECBOOK_NO").trim()
					+ "'" + " where PNO = '"
					+ getValue("MAINTAIN_APPLY_PNO").trim() + "'");
		}
		
		if (getValue("OLD_RECBOOK_NAME").trim().equals(
				getValue("NEW_RECBOOK_NAME").trim())) {
			setValue("NEW_RECBOOK_NAME","");
		}
		if (getValue("OLD_REC_START_DATE").trim().equals(
				getValue("NEW_REC_START_DATE").trim())) {
			setValue("NEW_REC_START_DATE","");
		}
		if (getValue("OLD_REC_END_DATE").trim().equals(
				getValue("NEW_REC_END_DATE").trim())) {
			setValue("NEW_REC_END_DATE","");
		}
		if (getValue("OLD_REQ_EMPID").trim().equals(
				getValue("NEW_REQ_EMPID").trim())) {
			setValue("NEW_REQ_EMPID","");
		}
		
		DoInster("LAB_RECBOOK_MAINTAIN_RECORDS");
		

		addScript("alert('���ʧ���,�Э���ñ�֭���.');" + "alert('�������Y�N����!');"
				+ "window.close();");

		dao = null;
		return paramString;

	}
}