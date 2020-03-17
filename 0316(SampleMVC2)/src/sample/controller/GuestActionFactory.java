package sample.controller;

import sample.action.Action;
import sample.action.guest.*;

public class GuestActionFactory {
	
	private GuestActionFactory() {}
	
	private static GuestActionFactory instance = new GuestActionFactory();
	
	public static GuestActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		if(command != null) {
			if(command.equals("guest_list")) {
				action = new GuestListAction();
			}else if(command.equals("guest_view")) {
				action = new GuestViewAction();
			}else if(command.equals("guest_write")) {
				action = new GuestWriteAction();
			}else if(command.equals("guest_write_pro")) {
				action = new GuestWriteProAction();
			}else if(command.equals("guest_modify")) {
				action = new GuestModifyAction();
			}else if(command.equals("guest_modify_pro")) {
				action = new GuestModifyProAction();
			}else if(command.equals("guest_delete")) {
				action = new GuestDeleteAction();
			}
		}

		System.out.println("ActionFactory : " + command);
		
		return action;
	}
}
