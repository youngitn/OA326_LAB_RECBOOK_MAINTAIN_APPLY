package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;
/**
 * 用於取得簽核人員的規則表,當中資料成員涉為int,方便使用switch.
 */
public interface LAB_RECBOOK_MAINTAIN_APPLY_FINAL_CONFIG {
	/**
	 * 取得研發-實驗室紀錄簿管理人於  table HRUSER_DEPT_BAS 中的號碼. 
	 */
	final static int RD_LabManager_HRUSER_DEPT_BAS = 100136;
	
	/**
	 * 取得QC-實驗室紀錄簿管理人於  table HRUSER_DEPT_BAS 中的號碼. 
	 */
	final static int QC_LabManager_HRUSER_DEPT_BAS = 100137;
	
	/**
	 * 研發處  
	 */
	final static int RD_DEPT_NO = 13;
	
	/**
	 * 品管課 
	 */
	final static int QC_DEPT_NO = 18;
}
