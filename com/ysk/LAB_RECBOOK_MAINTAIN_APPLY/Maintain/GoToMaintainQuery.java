package com.ysk.LAB_RECBOOK_MAINTAIN_APPLY.Maintain;
//com/ysk/LAB_RECBOOK_MAINTAIN_APPLY/Maintain/GoToMaintainQuery
import SomeUtils._hproc;

public class GoToMaintainQuery extends _hproc {

	@Override
	public String action(String paramString) throws Throwable {
		addScript("document.getElementById('flow_panel').style.display = 'none';");
		
		return paramString;
	}
}
