package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.Notify;

//com/ysk/LAB_RECBOOK_MAINTAIN_APPLY/Notify/EmailNotify
import java.util.Vector;

import jcx.db.talk;
import SomeUtils._bNotify;

import com.ysk.service.BaseService;

public class EmailNotify extends _bNotify {
	BaseService service;

	@Override
	public void actionPerformed(String arg0) throws Throwable {
		// TODO Auto-generated method stub
		service = new BaseService();
		// get sign people
		Vector<?> vid = getEngagedPeople();
		if (vid.size() == 0)
			return;

		Vector<String> V2 = new Vector<String>();
		for (int i = 0; i < vid.size(); i++) {
			V2.addElement(getEmail((String) vid.elementAt(i)));

		}
		if (V2.size() == 0)
			return;
		talk t = getTalk();

		String sqlc;
		// get sign-page link url.
		sqlc = "SELECT HRADDR FROM HRSYS";
		String[][] HRADDR = t.queryFromPool(sqlc);
		String title = "����Ǭ���ï��γ��Ʋ��ʥӽг�,�жi�J�t��ñ��";
		String content = "�ӽг渹:" + getValue("PNO") + "<br>";
		content += "�ӽФ��:" + getValue("DATE") + "<br>";
		
		content += "�ӽФH:" + getValue("REQ_EMPID") + "  "
				+ getValue("REQ_EMPID_NAME") + "<br>";
		content += "�жi�J eHR �t��ñ��( <a href=\"" + HRADDR[0][0].trim()
				+ "\">�����s��</a>)<br>";

		String usr[] = ((String[]) V2.toArray(new String[0]));

		String sendRS = service.sendMailbccUTF8(usr, title, content, null, "",
				"text/html");

		if (sendRS.trim().equals("")) {
			message("EMAIL�w�H�X�q��");
		} else {
			message("EMAIL�H�X����");
		}
	}
}
