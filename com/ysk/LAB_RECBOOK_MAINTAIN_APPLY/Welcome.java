package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;

//import com.ysk.bean.UserInfoViewBean;
import SomeUtils._hproc;

public class Welcome extends _hproc {
	@Override
	public String action(String value) throws Throwable {
		// �i�۩wHTML�����U��쪺�w�]�ȻP���s���ʧ@
		// �ǤJ�� value
		if (POSITION != 5) {
			setNewView("queryPage");
		}
		return value;
	}

	public String getInformation() {
		return "---------------Welcome(\u9001\u51fa\u67e5\u8a62).html_action()----------------";
	}
}