package com.kh.jsp.board.model.service;

import static com.kh.jsp.common.JDBCTemplate.close;
import static com.kh.jsp.common.JDBCTemplate.commit;
import static com.kh.jsp.common.JDBCTemplate.getConnection;
import static com.kh.jsp.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kh.jsp.board.model.dao.BoardDao;
import com.kh.jsp.board.model.vo.Attachment;
import com.kh.jsp.board.model.vo.Board;

public class BoardService {
	
	public BoardService() {	}

	public ArrayList<Board> selectList(int currentPage, int limit) {
		Connection con = getConnection();
		BoardDao bDao = new BoardDao();
		
		ArrayList<Board> list = bDao.selectList(con, currentPage, limit);
		
		return list;
	}

	public int insertBoard(Board b) {
		Connection con = getConnection();
		BoardDao bDao = new BoardDao();
		
		int result = bDao.insertBoard(con, b);
		
		if(result>0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int getListCount() {
		Connection con = getConnection();
		int result =new BoardDao().getListCount(con);
		close(con);
		
		return result;
	}

	public Board selectOne(int bno) {
		Connection con = getConnection();
		BoardDao bDao = new BoardDao();
		
		Board b = bDao.selectOne(con, bno);	
		int result = 0;
		
		// SelectOne 서블릿이 호출 했을 경우에만 조회수를 증가시키는 방법
		StackTraceElement[] caller = new Throwable().getStackTrace();

		if(b != null && caller[1].getClassName().contains("SelectOne")) {
			result = bDao.updateCount(con, b);
			if(result >0) commit(con);
			else	rollback(con);
		}
		
		close(con);
		
		return b;
	}

	public int updateBoard(Board b) {
		Connection con  = getConnection();
		BoardDao bDao = new BoardDao();
		
		int result = bDao.updateBoard(con, b);
		
		if(result>0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int deleteBoard(int bno) {
		Connection con = getConnection();
		BoardDao bDao = new BoardDao();
		
		int result = bDao.deleteBoard(con, bno);
		
		if(result >0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}
	
	public int insertThumbnail(Board b, ArrayList<Attachment> fileList) {
		Connection con = getConnection();
		int result = 0;
		
		int result1 = new BoardDao().insertThumbnailContent(con, b);
		
		if(result1 > 0){
			int bno = new BoardDao().selectCurrval(con);
			System.out.println(bno);
			
			for(int i = 0; i < fileList.size(); i++){
				fileList.get(i).setBno(bno);
			}
		}
		
		int result2 = new BoardDao().insertAttachment(con, fileList);
		
		if(result1 > 0 && result2 > 0){
			commit(con);
			result = 1;
		}else{
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public ArrayList<HashMap<String, Object>> selectThumbnailList() {
		Connection con = getConnection();
		
		ArrayList<HashMap<String, Object>> list = new BoardDao().selectThumbnailList(con);
		
		close(con);
		
		return list;
	}

	public HashMap<String, Object> selectThumbnailMap(int num) {
		Connection con = getConnection();
		HashMap<String, Object> hmap = null;
		
		int result = new BoardDao().updateCount(con, num);
		
		if(result > 0) {
			commit(con);
			hmap = new BoardDao().selectThumbnailMap(con, num);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return hmap;
	}

	public Attachment selectOneAttachment(int num) {
		Connection con = getConnection();
		
		Attachment file = new BoardDao().selectOneAttachment(con, num);
		
		close(con);
		
		return file;
	}

	public List<Board> selectTop5() {
		Connection con  = getConnection();
		
		
		List<Board> bList = new BoardDao().selectTop5(con);
		
		return bList;
	}
}

