package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;

//import com.ysk.bean.UserInfoViewBean;
import SomeUtils._hproc;

import com.ysk.field.Flow;

/**
 * �s�W��Ʀb���]�p
 * 
 * @author b0050
 *
 */
public class DoAdd extends _hproc {
	String nowTable = "LAB_RECBOOK_USING_APPLY";

	@Override
	public String action(String value) throws Throwable {

		String[][] field = { { "RECBOOK_NO", "����ï�s��" },
				{ "RECBOOK_NAME", "����ï�W��" }, { "REC_START_DATE", "�����}�l���" },
				{ "REC_END_DATE", "�����`�������" } };
		String condition = "RECBOOK_NO = '" + getValue("RECBOOK_NO")
				+ "' and REC_START_DATE <= '" + getValue("REC_START_DATE")
				+ "'";
		String[][] ret = selectFromWhere("PNO", nowTable, condition);
		if (checkEmpty(field)) {
			if (ret.length == 0) {
				DoInster(nowTable, "�ҥD��");
				sendEmailAfterAdd(getValue("REQ_EMPID").trim(),"SUB:�ҥD��","���e:�ҥD��",null,"",Flow.FLOW_SING_LEVEL_11);
			} else {
				message("������ï �s��:" + getValue("RECBOOK_NO") + " �w���H����ӽ�!");
			}
		}
		return value;
	}

	

	public String getInformation() {
		return "---------------DO_QUERY(\u9001\u51fa\u67e5\u8a62).html_action()----------------";
	}

}
