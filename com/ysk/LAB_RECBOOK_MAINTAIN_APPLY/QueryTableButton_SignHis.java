package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;

//com/ysk/LAB_RECBOOK_MAINTAIN_APPLY/QueryTableButton_SignHis;
import SomeUtils._bproc;

import com.ysk.service.SignFlowHistoryService;

/**
 * 顯示簽核流程歷史.為一表格內按鈕.<br>
 * 用於emaker設計模式中,進入表格欄位,預設值.
 * 這支如果顯示資訊的元件名稱不變 則此程式不需修改.
 * @author b0050
 *
 */
public class QueryTableButton_SignHis extends _bproc {

	@Override
	public String getDefaultValue(String arg0) throws Throwable {

		String functionName = this.getFunctionName();
		SignFlowHistoryService service = new SignFlowHistoryService(this);
		String key = "a.PNO='" + getValue("QUERY_LIST.PNO") + "'";
		service.doDisplaySignFlowHistory(functionName, key);
		return null;

	}
}
