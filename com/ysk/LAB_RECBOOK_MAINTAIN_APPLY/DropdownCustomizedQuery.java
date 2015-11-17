package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;
//com/ysk/LAB_RECBOOK_MAINTAIN_APPLY/DropdownCustomizedQuery;
import SomeUtils._bQuery;
import SomeUtils.Bean.UserInfoViewBean;
import SomeUtils.DAO.UserInfoViewDAO;

public class DropdownCustomizedQuery extends _bQuery {

	@Override
	public String getFilter()throws Throwable{
		// 回傳值為自定查詢條件
		// 回傳值必須是空白或以 and 開始,如 "and FIELD1='ABC'"
		// 也可以回傳完整的 SQL 語法取代原設定的值 如 select distinct display_field,data_field from table1 where type=100
		//  getRow()==-1 時請回傳最多筆數的 where 條件
		UserInfoViewDAO ud = new UserInfoViewDAO(getTalk());
		UserInfoViewBean user = ud.getUserInfo(getUser());
		ud = null ;
		String otherConditionString = "";

		// 研發"處" 所以取ParentNo
		// 研發一 二課 的人 上一層是研發處 :13,此處用 getParentNo 涵蓋 一 ,二課 & 研發處的人
		if (user.getParentNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.RD_DEPT_NO
				|| user.getDepNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.RD_DEPT_NO) {

			otherConditionString += "and (a.RECBOOK_NO like 'YF%' OR a.RECBOOK_NO like 'YJ%')";

		}// 品管"課": 18 或品管課底下的理化組等...
		else if (user.getParentNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.QC_DEPT_NO
				|| user.getDepNo() == LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG.QC_DEPT_NO) {

			otherConditionString += "and (a.RECBOOK_NO like 'QC%' OR a.RECBOOK_NO like 'AR%')";

		} else if (user.getEmpid().equals("admin")) {
			otherConditionString = "";
		} else {
			message("無查詢權限!");
			
		}
		
		if(getRow()==-1){
			return "";
		} else {
			return "and b.F_INP_STAT = '歸檔' " + otherConditionString;
		}
	}

}
