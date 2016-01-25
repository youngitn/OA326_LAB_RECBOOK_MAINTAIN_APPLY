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
		String title = "實驗室紀錄簿領用單資料異動申請單,請進入系統簽核";
		String content = "申請單號:" + getValue("PNO") + "<br>";
		content += "申請日期:" + getValue("DATE") + "<br>";
		
		content += "申請人:" + getValue("REQ_EMPID") + "  "
				+ getValue("REQ_EMPID_NAME") + "<br>";
		content += "請進入 eHR 系統簽核( <a href=\"" + HRADDR[0][0].trim()
				+ "\">按此連結</a>)<br>";

		String usr[] = ((String[]) V2.toArray(new String[0]));

		String sendRS = service.sendMailbccUTF8(usr, title, content, null, "",
				"text/html");

		if (sendRS.trim().equals("")) {
			message("EMAIL已寄出通知");
		} else {
			message("EMAIL寄出失敗");
		}
	}
}
