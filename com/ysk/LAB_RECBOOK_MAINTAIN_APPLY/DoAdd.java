package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;

//import com.ysk.bean.UserInfoViewBean;
import SomeUtils._hproc;

import com.ysk.field.Flow;

/**
 * 新增資料在此設計
 * 
 * @author b0050
 *
 */
public class DoAdd extends _hproc {
	String nowTable = "LAB_RECBOOK_USING_APPLY";

	@Override
	public String action(String value) throws Throwable {

		String[][] field = { { "RECBOOK_NO", "紀錄簿編號" },
				{ "RECBOOK_NAME", "紀錄簿名稱" }, { "REC_START_DATE", "紀錄開始日期" },
				{ "REC_END_DATE", "紀錄節結束日期" } };
		String condition = "RECBOOK_NO = '" + getValue("RECBOOK_NO")
				+ "' and REC_START_DATE <= '" + getValue("REC_START_DATE")
				+ "'";
		String[][] ret = selectFromWhere("PNO", nowTable, condition);
		if (checkEmpty(field)) {
			if (ret.length == 0) {
				DoInster(nowTable, "課主管");
				sendEmailAfterAdd(getValue("REQ_EMPID").trim(),"SUB:課主管","內容:課主管",null,"",Flow.FLOW_SING_LEVEL_11);
			} else {
				message("此紀錄簿 編號:" + getValue("RECBOOK_NO") + " 已有人先行申請!");
			}
		}
		return value;
	}

	

	public String getInformation() {
		return "---------------DO_QUERY(\u9001\u51fa\u67e5\u8a62).html_action()----------------";
	}

}
