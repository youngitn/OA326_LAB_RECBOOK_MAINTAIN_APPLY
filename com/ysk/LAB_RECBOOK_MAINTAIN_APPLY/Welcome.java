package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;

//import com.ysk.bean.UserInfoViewBean;
import SomeUtils._hproc;

public class Welcome extends _hproc {
	@Override
	public String action(String value) throws Throwable {
		// 可自定HTML版本各欄位的預設值與按鈕的動作
		// 傳入值 value
		if (POSITION != 5) {
			setNewView("queryPage");
		}
		return value;
	}

	public String getInformation() {
		return "---------------Welcome(\u9001\u51fa\u67e5\u8a62).html_action()----------------";
	}
}