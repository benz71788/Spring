package com.json.jsonroot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.json.jsonroot.model.BoardVO;

@Service
public class JsonServiceImpl2 implements JsonService{

		@Autowired
		private DAO dao;
		
		private LogAdvice log;
		
		public JsonServiceImpl2() {
			log = new LogAdvice();
		}

		@Override
		public BoardVO get_whereid(int id) throws Exception {
			// TODO Auto-generated method stub
			log.beforeLog();
			return dao.get_whereid(id);
		}

		@Override
		public void setInsert(BoardVO b) throws Exception {
			// TODO Auto-generated method stub
			log.beforeLog();
			dao.setInsert(b);
		}

		@Override
		public List<BoardVO> selectall() throws Exception {
			// TODO Auto-generated method stub
			log.beforeLog();
			return dao.selectall();
		}

}
