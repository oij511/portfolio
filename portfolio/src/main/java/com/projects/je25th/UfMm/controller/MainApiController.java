package com.projects.je25th.UfMm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.je25th.UfMm.dto.AjaxJson;
import com.projects.je25th.UfMm.dto.AuthInfo;
import com.projects.je25th.UfMm.dto.Hashtag;
import com.projects.je25th.UfMm.dto.Memo;
import com.projects.je25th.UfMm.service.MemoService;
import com.projects.je25th.UfMm.service.MyUtill;

@RestController//JSON 형식으로 응답 
@RequestMapping("unfolded-memo")
public class MainApiController {

	@Autowired
	MemoService memoService;
	
	@GetMapping("/main/{page}")
	public AjaxJson viewMemoList(@PathVariable(name="page")int page, HttpSession session) {
		System.out.println("[UfMm] main/all Get");
		
		AuthInfo authInfo = MyUtill.getAuthInfo(session);
		
		//json으로 메모 리스트를 보내기
		List<Memo> result = memoService.viewMemoListByUserIdx(authInfo.getIdx(), page);
		AjaxJson send = new AjaxJson(result==null? false : true, result);
		return send;
	}
	
	@GetMapping("/hashtag/all")
	public AjaxJson sendAllHashtag(HttpSession session) {
		System.out.println("[UfMm] hashtag/all Get");
		
		AuthInfo authInfo = MyUtill.getAuthInfo(session);
		
		List<Hashtag> result = memoService.getAllHashtag(authInfo.getIdx());
		AjaxJson send = new AjaxJson(result==null? false : true, result);
		return send;
	}
		
}
