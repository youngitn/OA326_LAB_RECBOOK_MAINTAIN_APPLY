package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;

import java.util.ArrayList;

import SomeUtils._hproc;
import SomeUtils.Bean.QueryItem;
import SomeUtils.Bean.UserInfoViewBean;

/**
 * ����d�ߪ��ʧ@�b���]�p
 * @author b0050
 *
 */
public class DoQuery extends _hproc {
	@Override
	public String action(String value) throws Throwable {
		// �إ�table header,�P�]�p�Ҧ��������춶�Ǥ@�P.
		ArrayList<QueryItem> list = new ArrayList<QueryItem>();
		
		// PNO�����Ĥ@��
		list.add(new QueryItem("PNO", "�ӽг渹", 0));// 0 �������d�߱���ӷ�
		list.add(new QueryItem("RECBOOK_NO", "����ï�s��", 1));
		list.add(new QueryItem("DATE", "�ӽФ��", 2));// 2 ��T�϶�		
		list.add(new QueryItem("REQ_EMPID", "�ӽФH���s", 1));

		/**
		 * �H�U�}�l���D�D�ɸ�ƪ���. �d��ñ�֪��A��SQL���O �����Φr��Ѽƥ�i�h����DB���W��,"ñ�֪��A"�� table
		 * header,0��ܦbsetQueryTable���������d�߱���.
		 */
		list.add(new QueryItem(
				"(select F_INP_STAT from LAB_RECBOOK_MAINTAIN_APPLY_FLOWC where PNO=a.PNO)",
				"ñ�֪��A", 0));
		list.add(new QueryItem("'ñ�֬���'", "ñ�֬���", 0));
		list.add(new QueryItem("'�ԲӸ�T'", "�ԲӸ�T", 0));
		list.add(new QueryItem("REASON", "���ʭ�]", 0));
		list.add(new QueryItem("CONTENT", "���׭q���e", 0));
		UserInfoViewBean user = getUserInfo(getUser());
		String otherConditionString = "";

		// ��o"�B" �ҥH��ParentNo
		// ��o�@ �G�� ���H �W�@�h�O��o�B :13,���B�� getParentNo �[�\ �@ ,�G�� & ��o�B���H
		if (user.getParentNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.RD_DEPT_NO
				|| user.getDepNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.RD_DEPT_NO) {

			otherConditionString += "where (RECBOOK_NO like 'YF%' OR RECBOOK_NO like 'YJ%')";

		}// �~��"��": 18 �Ϋ~�޽ҩ��U���z�Ʋյ�...
		else if (user.getParentNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.QC_DEPT_NO
				|| user.getDepNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.QC_DEPT_NO) {

			otherConditionString += "where (RECBOOK_NO like 'QC%' OR RECBOOK_NO like 'AR%')";

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
		setQueryTable(list, "LAB_RECBOOK_MAINTAIN_APPLY", "����Ǭ���ï��γ��Ʋ��ʥӽг�", 3, 4,
				otherConditionString);
		list.clear();
		
		System.gc();
		return value;
	}

	public String getInformation() {
		return "---------------DO_QUERY(\u9001\u51fa\u67e5\u8a62).html_action()----------------";
	}
}