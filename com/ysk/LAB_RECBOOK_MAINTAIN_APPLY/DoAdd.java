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
	String nowTable = "LAB_RECBOOK_MAINTAIN_APPLY";

	@Override
	public String action(String value) throws Throwable {
		/**
		 * 要檢查的欄位以二維陣列結構做為參數,送進去checkEmpty檢查,如空白則出現訊息提示.
		 */
		String[][] field = { { "REQ_EMPID", "申請人員編" },
				{ "RECBOOK_NO", "紀錄簿編號" }, { "DATE", "申請日期" },
				{ "REASON", "異動原因" }, { "CONTENT", "欲修訂內容" } };

		if (checkEmpty(field)) {

			DoInster(nowTable, "待處理");
			sendEmailAfterAdd(getValue("REQ_EMPID").trim(), "SUB:課主管",
					"內容:課主管", null, "", Flow.FLOW_SING_LEVEL_11);
		}

		return value;
	}

	public String getInformation() {
		return "---------------DO_QUERY(\u9001\u51fa\u67e5\u8a62).html_action()----------------";
	}

}
