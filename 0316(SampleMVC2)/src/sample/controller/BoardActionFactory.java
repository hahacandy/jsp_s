package sample.controller;

import sample.action.Action;
import sample.action.board.BoardDeleteAction;
import sample.action.board.BoardDeleteProAction;
import sample.action.board.BoardListAction;
import sample.action.board.BoardModifyAction;
import sample.action.board.BoardModifyProAction;
import sample.action.board.BoardViewAction;
import sample.action.board.BoardWriteAction;
import sample.action.board.BoardWriteProAction;

public class BoardActionFactory {
	
	private BoardActionFactory() {}
	
	private static BoardActionFactory instance = new BoardActionFactory();
	
	public static BoardActionFactory getInstance() {
		return instance;
	}
	
	
	public Action getAction(String command) {
		
		Action action = null;
		
		if(command != null) {
			if(command.equals("board_list")) {
				action = new BoardListAction();
			}else if(command.equals("board_write")) {
				action = new BoardWriteAction();
			}else if(command.equals("board_write_pro")) {
				action = new BoardWriteProAction();
			}else if(command.equals("board_view")) {
				action = new BoardViewAction();
			}else if(command.equals("board_modify")) {
				action = new BoardModifyAction();
			}else if(command.equals("board_modify_pro")) {
				action = new BoardModifyProAction();
			}else if(command.equals("board_delete")) {
				action = new BoardDeleteAction();
			}else if(command.equals("board_delete_pro")) {
				action = new BoardDeleteProAction();
			}
		}
		
		return action;
		
	}
}
