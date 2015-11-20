package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.Maintain;

//com/ysk/LAB_RECBOOK_MAINTAIN_APPLY/Maintain/DoQuery
import java.util.ArrayList;

import com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG;

import SomeUtils._hproc;
import SomeUtils.Bean.QueryItem;
import SomeUtils.Bean.UserInfoViewBean;
import SomeUtils.DAO.UserInfoViewDAO;

/**
 * 執行查詢的動作在此設計
 * 
 * @author b0050
 *
 */
public class DoQuery extends _hproc {
	@Override
	public String action(String value) throws Throwable {
		// 建立table header,與設計模式的表格欄位順序一致.
		ArrayList<QueryItem> list = new ArrayList<QueryItem>();

		// PNO必須第一個
		list.add(new QueryItem("PNO", "單號", 0));// 0 不做為查詢條件來源
		list.add(new QueryItem("MAINTAIN_PERSON", "異動人", 1));
		list.add(new QueryItem("MAINTAIN_DATE", "異動日期", 2));
		list.add(new QueryItem("OLD_RECBOOK_NO", "原紀錄簿編號", 1));
		list.add(new QueryItem("OLD_RECBOOK_NAME", "原紀錄簿名稱", 1));
		list.add(new QueryItem("OLD_REC_START_DATE", "原紀錄開始日期", 2));
		list.add(new QueryItem("OLD_REC_END_DATE", "原紀錄結束日期", 2));
		list.add(new QueryItem("OLD_REQ_EMPID", "原申請人", 1));
		list.add(new QueryItem("NEW_RECBOOK_NO", "新紀錄簿編號", 1));
		list.add(new QueryItem("NEW_RECBOOK_NAME", "新紀錄簿名稱", 1));
		list.add(new QueryItem("NEW_REC_START_DATE", "新紀錄開始日期", 2));
		list.add(new QueryItem("NEW_REC_END_DATE", "新紀錄結束日期", 2));
		list.add(new QueryItem("NEW_REQ_EMPID", "新申請人", 1));

		/**
		 * 以下開始為非主檔資料表資料. 查詢簽核狀態的SQL指令 直接用字串參數丟進去做為DB欄位名稱,"簽核狀態"為 table
		 * header,0表示在setQueryTable中不做為查詢條件.
		 */
		/*
		 * list.add(new QueryItem(
		 * "(select F_INP_STAT from LAB_RECBOOK_MAINTAIN_APPLY_FLOWC where PNO=a.PNO)"
		 * , "簽核狀態", 0)); list.add(new QueryItem("'簽核紀錄'", "簽核紀錄", 0));
		 * list.add(new QueryItem("'詳細資訊'", "詳細資訊", 0)); list.add(new
		 * QueryItem("REASON", "異動原因", 0)); list.add(new QueryItem("CONTENT",
		 * "欲修訂內容", 0));
		 */
		UserInfoViewDAO ud = new UserInfoViewDAO(getTalk());
		UserInfoViewBean user = ud.getUserInfo(getUser());
		ud = null;
		String otherConditionString = "";

		// 研發"處" 所以取ParentNo
		// 研發一 二課 的人 上一層是研發處 :13,此處用 getParentNo 涵蓋 一 ,二課 & 研發處的人
		if (user.getParentNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.RD_DEPT_NO
				|| user.getDepNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.RD_DEPT_NO) {

			otherConditionString += "where ((OLD_RECBOOK_NO like 'YF%' OR OLD_RECBOOK_NO like 'YJ%')"
					+ " OR (NEW_RECBOOK_NO like 'YF%' OR NEW_RECBOOK_NO like 'YJ%'))";

		}// 品管"課": 18 或品管課底下的理化組等...
		else if (user.getParentNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.QC_DEPT_NO
				|| user.getDepNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.QC_DEPT_NO) {

			otherConditionString += "where ((OLD_RECBOOK_NO like 'QC%' OR OLD_RECBOOK_NO like 'AR%')"
					+ " OR (NEW_RECBOOK_NO like 'QC%' OR NEW_RECBOOK_NO like 'AR%'))";

		} else if (user.getEmpid().equals("admin")) {
			otherConditionString = "";
		} else {
			message("無查詢權限!");
			return value;
		}
		/**
		 * 參數 3,4 代表
		 * LIST當中的元素位置,會使用到含"員工編號"&"簽核狀態"的元素,各位於list第5和第6的位置.(ArrayList從0開始算)
		 * 
		 * 參數 otherConditionString 額外的查詢條件 .
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