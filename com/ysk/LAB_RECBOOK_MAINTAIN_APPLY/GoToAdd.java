package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY;

//import com.ysk.bean.UserInfoViewBean;
import SomeUtils._hproc;
import SomeUtils.Bean.UserInfoViewBean;
import SomeUtils.DAO.UserInfoViewDAO;


/**
 * �i�J�s�W������,�ݭn�@�ֱa�X����Ʀb���]�w.
 * �D�n�Ω�a�X��Ʈw���H�~�����.
 * @author b0050
 *
 */
public class GoToAdd extends _hproc {
	@Override
	public String action(String value) throws Throwable {
		// �i�۩wHTML�����U��쪺�w�]�ȻP���s���ʧ@
		// �ǤJ�� value
		UserInfoViewDAO ud = new UserInfoViewDAO(getTalk());
		UserInfoViewBean nowUser = ud.getUserInfo(getUser());
		ud = null ;
		setValue("DATE", getToday("YYYYmmdd"));
		setValue("REQ_EMPID", getUser());
		setValue("REQ_EMPID_NAME",nowUser.getHecname());
		setValue("REQ_DEPT_NAME",nowUser.getDepName());
		return value;
	}

	public String getInformation() {
		return "---------------DO_QUERY(\u9001\u51fa\u67e5\u8a62).html_action()----------------";
	}
}