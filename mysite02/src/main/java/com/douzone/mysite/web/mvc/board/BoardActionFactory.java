package com.douzone.mysite.web.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("write".equals(actionName)) {
			action = new WriteAction();
		} else if ("writeform".equals(actionName)) {
			action = new WriteformAction();
		} else if("view".equals(actionName)) {
			action = new ViewAction();
		} else if("viewform".equals(actionName)) {
			action = new ViewformAction();
		} else if("modify".equals(actionName)) {
			action = new ModifyAction();
		} else if("modifyform".equals(actionName)) {
			action = new ModifyformAction();
	    } else if("delete".equals(actionName)) {
	    	action = new DeleteAction();
	    } else if("deleteform".equals(actionName)) {
	    	action = new DeleteformAction();
	    } else {
			action = new ListAction();
		}
		return action;
	}

}