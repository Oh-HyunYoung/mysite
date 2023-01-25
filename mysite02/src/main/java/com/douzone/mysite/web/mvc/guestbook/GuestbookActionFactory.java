package com.douzone.mysite.web.mvc.guestbook;

import com.douzone.mysite.web.mvc.main.MainAction;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {
	
	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("deleteform".equals(actionName)) {
			action = new DeleteformAction();
		} else if("add".equals(actionName)) {
			action = new AddAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}  else {
			action = new ListAction();
		} 
		
		return action;
	}

}
