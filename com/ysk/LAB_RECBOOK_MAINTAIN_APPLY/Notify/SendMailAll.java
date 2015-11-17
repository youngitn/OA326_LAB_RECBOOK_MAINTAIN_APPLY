package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.Notify;

// com/ysk/LAB_RECBOOK_USING_APPLY/Notify/SendMailAll
import java.util.Arrays;
import java.util.HashSet;

import jcx.jform.bProcFlow;

import com.ysk.service.BaseService;

public class SendMailAll extends bProcFlow {

	public boolean action(String value) throws Throwable {
		// 回傳值為 true 表示執行接下來的流程處理
		// 回傳值為 false 表示接下來不執行任何流程處理
		// 傳入值 value 為 "核准"

		BaseService service = new BaseService();

		String sendRS = "";
		String email;
		String[] AllApprovePeople = getAllApprovePeople();
		String title = getState() + "-結案";
		String content = getState();
		int isEmailAllSend = 0;

		for (String peopleString : AllApprovePeople) {
			// System.out.println("value=" + it.next().toString());

			email = getEmail(peopleString);
			// email = service.getUserInfoBean(peopleString).getEmail();
			String usr[] = { email };

			sendRS = service.sendMailbccUTF8(usr, title, content, null, "",
					"text/html");
			// if send mail Sending Failed,isEmailAllSend will +1 for check.
			if (!sendRS.trim().equals("")) {
				isEmailAllSend++;
			}

		}

		if (isEmailAllSend != 0) {
			message("EMAIL寄出失敗");
			return false;

		}

		message("EMAIL已寄出通知");
		return true;

	}

	public String getInformation() {
		return "---------------\u6838\u51c6.preProcess()----------------";
	}

	public String[] getAllApprovePeople() {
		String vid[][] = getFlowHistory();
		String ausr[] = new String[vid.length];
		for (int i = 0; i < vid.length; i++) {
			ausr[i] = vid[i][1].trim();
		}
		HashSet<String> set = new HashSet<String>();
		set.addAll(Arrays.asList(ausr));
		String usr[] = (String[]) set.toArray(new String[0]);
		return usr;

	}
}
