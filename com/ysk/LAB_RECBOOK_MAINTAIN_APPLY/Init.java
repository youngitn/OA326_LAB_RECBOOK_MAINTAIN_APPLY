package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;

//com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.Init
import SomeUtils._hproc;
import SomeUtils.Bean.UserInfoViewBean;

/**
 * �i�J(�򥻤W��s�W�����P��)ñ�֭����ܰ��檺�{��.
 * �D�n�Ω�a�X��Ʈw���H�~�����.
 * @author b0050
 *
 */
public class Init extends _hproc {

	@Override
	public String action(String paramString) throws Throwable {
		// TODO Auto-generated method stub

		UserInfoViewBean nowUser = getUserInfo(getUser());
		setValue("REQ_EMPID", getUser());
		setValue("REQ_EMPID_NAME", nowUser.getHecname());
		setValue("REQ_DEPT_NAME", nowUser.getDepName());

		return null;
	}

}
