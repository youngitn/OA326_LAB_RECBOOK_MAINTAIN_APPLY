package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;

//import com.ysk.bean.UserInfoViewBean;
import SomeUtils._hproc;

/**
 * �s�W��Ʀb���]�p
 * 
 * @author b0050
 *
 */
public class DoAdd extends _hproc {
	String nowTable = "LAB_RECBOOK_MAINTAIN_APPLY";

	@Override
	public String action(String value) throws Throwable {
		/**
		 * �n�ˬd�����H�G���}�C���c�����Ѽ�,�e�i�hcheckEmpty�ˬd,�p�ťիh�X�{�T������.
		 */
		String[][] field = { { "REQ_EMPID", "�ӽФH���s" },
				{ "RECBOOK_NO", "����ï�s��" }, { "DATE", "�ӽФ��" },
				{ "REASON", "���ʭ�]" }, { "CONTENT", "���׭q���e" } };

		if (checkEmpty(field)) {

			DoInster(nowTable, "��ӽФH");
			sendEmailAfterAdd(getValue("REQ_EMPID").trim(), "SUB:��ӽФH-"+ getValue("OLD_REQ_EMPID"),
					"���e:��ӽФH", null, "", getValue("OLD_REQ_EMPID"));
		}
		
		return value;
	}
	
	public String getInformation() {
		return "---------------DO_QUERY(\u9001\u51fa\u67e5\u8a62).html_action()----------------";
	}

}
