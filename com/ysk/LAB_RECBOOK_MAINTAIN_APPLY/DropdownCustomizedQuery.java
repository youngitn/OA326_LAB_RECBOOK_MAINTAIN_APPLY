package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;
//com/ysk/LAB_RECBOOK_MAINTAIN_APPLY/DropdownCustomizedQuery;
import SomeUtils._bQuery;
import SomeUtils.Bean.UserInfoViewBean;
import SomeUtils.DAO.UserInfoViewDAO;

public class DropdownCustomizedQuery extends _bQuery {

	@Override
	public String getFilter()throws Throwable{
		// �^�ǭȬ��۩w�d�߱���
		// �^�ǭȥ����O�ťթΥH and �}�l,�p "and FIELD1='ABC'"
		// �]�i�H�^�ǧ��㪺 SQL �y�k���N��]�w���� �p select distinct display_field,data_field from table1 where type=100
		//  getRow()==-1 �ɽЦ^�ǳ̦h���ƪ� where ����
		UserInfoViewDAO ud = new UserInfoViewDAO(getTalk());
		UserInfoViewBean user = ud.getUserInfo(getUser());
		ud = null ;
		String otherConditionString = "";

		// ��o"�B" �ҥH��ParentNo
		// ��o�@ �G�� ���H �W�@�h�O��o�B :13,���B�� getParentNo �[�\ �@ ,�G�� & ��o�B���H
		if (user.getParentNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.RD_DEPT_NO
				|| user.getDepNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.RD_DEPT_NO) {

			otherConditionString += "and (a.RECBOOK_NO like 'YF%' OR a.RECBOOK_NO like 'YJ%')";

		}// �~��"��": 18 �Ϋ~�޽ҩ��U���z�Ʋյ�...
		else if (user.getParentNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.QC_DEPT_NO
				|| user.getDepNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.QC_DEPT_NO) {

			otherConditionString += "and (a.RECBOOK_NO like 'QC%' OR a.RECBOOK_NO like 'AR%')";

		} else if (user.getEmpid().equals("admin")) {
			otherConditionString = "";
		} else {
			message("�L�d���v��!");
			
		}
		
		if(getRow()==-1){
			return "";
		} else {
			return "and b.F_INP_STAT = '�k��' " + otherConditionString;
		}
	}

}
