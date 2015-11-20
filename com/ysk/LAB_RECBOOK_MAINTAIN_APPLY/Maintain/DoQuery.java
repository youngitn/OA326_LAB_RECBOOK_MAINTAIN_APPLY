package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.Maintain;

//com/ysk/LAB_RECBOOK_MAINTAIN_APPLY/Maintain/DoQuery
import java.util.ArrayList;

import com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG;

import SomeUtils._hproc;
import SomeUtils.Bean.QueryItem;
import SomeUtils.Bean.UserInfoViewBean;
import SomeUtils.DAO.UserInfoViewDAO;

/**
 * ����d�ߪ��ʧ@�b���]�p
 * 
 * @author b0050
 *
 */
public class DoQuery extends _hproc {
	@Override
	public String action(String value) throws Throwable {
		// �إ�table header,�P�]�p�Ҧ��������춶�Ǥ@�P.
		ArrayList<QueryItem> list = new ArrayList<QueryItem>();

		// PNO�����Ĥ@��
		list.add(new QueryItem("PNO", "�渹", 0));// 0 �������d�߱���ӷ�
		list.add(new QueryItem("MAINTAIN_PERSON", "���ʤH", 1));
		list.add(new QueryItem("MAINTAIN_DATE", "���ʤ��", 2));
		list.add(new QueryItem("OLD_RECBOOK_NO", "�����ï�s��", 1));
		list.add(new QueryItem("OLD_RECBOOK_NAME", "�����ï�W��", 1));
		list.add(new QueryItem("OLD_REC_START_DATE", "������}�l���", 2));
		list.add(new QueryItem("OLD_REC_END_DATE", "������������", 2));
		list.add(new QueryItem("OLD_REQ_EMPID", "��ӽФH", 1));
		list.add(new QueryItem("NEW_RECBOOK_NO", "�s����ï�s��", 1));
		list.add(new QueryItem("NEW_RECBOOK_NAME", "�s����ï�W��", 1));
		list.add(new QueryItem("NEW_REC_START_DATE", "�s�����}�l���", 2));
		list.add(new QueryItem("NEW_REC_END_DATE", "�s�����������", 2));
		list.add(new QueryItem("NEW_REQ_EMPID", "�s�ӽФH", 1));

		/**
		 * �H�U�}�l���D�D�ɸ�ƪ���. �d��ñ�֪��A��SQL���O �����Φr��Ѽƥ�i�h����DB���W��,"ñ�֪��A"�� table
		 * header,0��ܦbsetQueryTable���������d�߱���.
		 */
		/*
		 * list.add(new QueryItem(
		 * "(select F_INP_STAT from LAB_RECBOOK_MAINTAIN_APPLY_FLOWC where PNO=a.PNO)"
		 * , "ñ�֪��A", 0)); list.add(new QueryItem("'ñ�֬���'", "ñ�֬���", 0));
		 * list.add(new QueryItem("'�ԲӸ�T'", "�ԲӸ�T", 0)); list.add(new
		 * QueryItem("REASON", "���ʭ�]", 0)); list.add(new QueryItem("CONTENT",
		 * "���׭q���e", 0));
		 */
		UserInfoViewDAO ud = new UserInfoViewDAO(getTalk());
		UserInfoViewBean user = ud.getUserInfo(getUser());
		ud = null;
		String otherConditionString = "";

		// ��o"�B" �ҥH��ParentNo
		// ��o�@ �G�� ���H �W�@�h�O��o�B :13,���B�� getParentNo �[�\ �@ ,�G�� & ��o�B���H
		if (user.getParentNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.RD_DEPT_NO
				|| user.getDepNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.RD_DEPT_NO) {

			otherConditionString += "where ((OLD_RECBOOK_NO like 'YF%' OR OLD_RECBOOK_NO like 'YJ%')"
					+ " OR (NEW_RECBOOK_NO like 'YF%' OR NEW_RECBOOK_NO like 'YJ%'))";

		}// �~��"��": 18 �Ϋ~�޽ҩ��U���z�Ʋյ�...
		else if (user.getParentNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.QC_DEPT_NO
				|| user.getDepNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.QC_DEPT_NO) {

			otherConditionString += "where ((OLD_RECBOOK_NO like 'QC%' OR OLD_RECBOOK_NO like 'AR%')"
					+ " OR (NEW_RECBOOK_NO like 'QC%' OR NEW_RECBOOK_NO like 'AR%'))";

		} else if (user.getEmpid().equals("admin")) {
			otherConditionString = "";
		} else {
			message("�L�d���v��!");
			return value;
		}
		/**
		 * �Ѽ� 3,4 �N��
		 * LIST����������m,�|�ϥΨ�t"���u�s��"&"ñ�֪��A"������,�U���list��5�M��6����m.(ArrayList�q0�}�l��)
		 * 
		 * �Ѽ� otherConditionString �B�~���d�߱��� .
		 */
		setQueryTable(list, "LAB_RECBOOK_MAINTAIN_RECORDS",
				otherConditionString);
		list.clear();

		return value;
	}

	public String getInformation() {
		return "---------------DO_QUERY(\u9001\u51fa\u67e5\u8a62).html_action()----------------";
	}
}