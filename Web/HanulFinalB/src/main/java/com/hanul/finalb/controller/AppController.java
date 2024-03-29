package com.hanul.finalb.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hanul.finalb.app.AppSensorService;
import com.hanul.finalb.app.DatasVO;
import com.hanul.finalb.app.UserSensorVO;
import com.hanul.finalb.app.firebase.FirebaseCloudMessageService;
import com.hanul.finalb.app.firebase.RequestDTO;
import com.hanul.finalb.common.Common;
import com.hanul.finalb.member.MemberService;
import com.hanul.finalb.member.MemberVO;
import com.hanul.finalb.notice.NoticeService;
import com.hanul.finalb.notice.NoticeVO;
import com.hanul.finalb.qna.QnaCommentVO;
import com.hanul.finalb.qna.QnaService;
import com.hanul.finalb.qna.QnaVO;

@RestController
@RequestMapping(value="/app",produces="application/text;charset=utf-8")
public class AppController {
	
	@Autowired
	MemberService memService;
	
	@Autowired
	AppSensorService appService;
	
	@Autowired
	FirebaseCloudMessageService fcmService;
	
	@Autowired
	QnaService qnaService;
	
	@Autowired
	Common comm;
	
	@Autowired
	BCryptPasswordEncoder pwEncoder;
	
	@RequestMapping("/login")
	public String login(MemberVO vo) {
		
		return new Gson().toJson(memService.member_login(vo), MemberVO.class);
	}
	@RequestMapping("/naverLogin")
	public String naverLogin(MemberVO vo) {
		if(memService.idCheck(vo.getUser_id())==0){
			System.out.println(vo.getUser_id());
			return new Gson().toJson(memService.naverJoin(vo), MemberVO.class);
		}
		memService.compareToken(vo);
		return new Gson().toJson(memService.memberInfo(vo.getUser_id()), MemberVO.class);
	}
	@RequestMapping("/join")
	public String join(MemberVO vo, MultipartFile profile_file) {
		if(memService.idCheck(vo.getUser_id())!=0) {
			return "duplicate id";
		}
		if(profile_file != null) {
			try {
				String file_id = comm.fileUpload(profile_file);
				String profile_url = comm.fileURL(file_id);
				vo.setProfile(profile_url);
				
			} catch (GeneralSecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String pw = vo.getUser_pw();
		vo.setUser_pw(pwEncoder.encode(pw));
		memService.memberJoin(vo);
		
		return "success";
	}
	@RequestMapping("/userInfo")
	public String userInfo(MemberVO vo) {
		
		return new Gson().toJson(memService.memberInfo(vo.getUser_id()), MemberVO.class);
	}
	
	@RequestMapping("/updateSensor")
	public String updateSensor(MemberVO vo) {
		return new Gson().toJson(appService.userSensorList(vo.getUser_id()), new TypeToken<List<UserSensorVO>>(){}.getType());
	}
	
	@RequestMapping("/getCctvUrl")
	public String getCctvUrl(int sensor_id) {
		return appService.getCCTVURL(sensor_id);
	}
	@RequestMapping("/datas")
	public String datas(int sensor_id) {
		return new Gson().toJson(appService.getDatas(sensor_id), new TypeToken<List<DatasVO>>(){}.getType());
	}
	@RequestMapping("/light_on")
	public String light_on() {
		String url = "http://192.168.0.30:8000/led/?led_mode=on";
		comm.requestAPI(url);
		return "led_on";
	}
	@RequestMapping("/light_off")
	public String light_off() {
		String url = "http://192.168.0.30:8000/led/?led_mode=off";
		comm.requestAPI(url);
		return "led_off";
	}
		
	//푸시 알림 테스트용
	@RequestMapping("/fcm")
	public ResponseEntity pushMessage(RequestDTO dto) throws IOException {
		fcmService.sendMessageTo(dto.getTargetToken(), dto.getTitle(), dto.getBody(), dto.getClick_action());
		
		return ResponseEntity.ok().build();
	}
	@RequestMapping("/resign")
	public String resign(MemberVO vo) throws GeneralSecurityException, IOException {
		vo = memService.memberInfo(vo.getUser_id());
		if(memService.memberResign(vo.getUser_id())==1) {
			if(vo.getProfile()!=null) {
				comm.fileDelete(comm.fileId(vo.getProfile()));
			}
		}
		return "success";
	}
	@RequestMapping("/user/update")
	public String userUpdate(MemberVO vo) {
		memService.appMemberUpdate(vo);
		return new Gson().toJson(memService.memberInfo(vo.getUser_id()), MemberVO.class);
	}
	@RequestMapping("/user/updateProfile/{user_id}")
	public String updateProfile(@PathVariable String user_id, MultipartFile profile) throws GeneralSecurityException, IOException {
		//업로드한 파일 즉시 구글 드라이브 업로드 처리
		String file_id = comm.fileUpload(profile);
		//기존 프로필 사진 정보 가져오기(가져오는 겸 프로필 데이터도 가져옴)
		MemberVO vo = memService.memberInfo(user_id);
		//제대로 업로드 된 경우 file_id가 있으니 중괄호 내부 실행
		if(file_id != null) {
			//vo에서 얻은 기존 프로필 url응 이용해 file_id를 추출하고, 구글 드라이브에서 삭제 처리
			String oldProfileId = comm.fileId(vo.getProfile());
			//프로필 아이디를 수정하고, db에 저장처리
			vo.setProfile(comm.fileURL(file_id));
			memService.appUpdateProfile(vo);
			comm.fileDelete(oldProfileId);
		}
		
		return new Gson().toJson(memService.memberInfo(user_id), MemberVO.class);
	}




	@RequestMapping("/qna")
	public String qnaList() {
		
		// QnaVO 객체들의 리스트를 가져옴
		ArrayList<QnaVO> list = (ArrayList<QnaVO>)qnaService.appQnaList();

	    // 생성된 Type을 사용하여 리스트를 JSON 문자열로 변환
		System.out.println(">> "+ new Gson().toJson(list));
	    return new Gson().toJson(list);
		
	}
	@Autowired
	NoticeService noticeService;
	
	@RequestMapping("/notice")
	public String notice() {
		
		// QnaVO 객체들의 리스트를 가져옴
		List<NoticeVO> list = noticeService.appNoticeList();

	    // 생성된 Type을 사용하여 리스트를 JSON 문자열로 변환
		System.out.println(">> "+ new Gson().toJson(list));
	    return new Gson().toJson(list);
		
	}
	
	@RequestMapping("/qnaDetail/{qna_id}")
	public String qnaDetail(@PathVariable int qna_id) {
		qnaService.qna_read(qna_id);
		String json = new Gson().toJson(qnaService.qna_info(qna_id));
	    
		System.out.println(">> "+ json);
	    return json;
	}
	@RequestMapping("/noticeDetail/{notice_id}")
	public String noticeDetail(@PathVariable int notice_id) {
		noticeService.notice_read(notice_id);
		String json = new Gson().toJson(noticeService.notice_info(notice_id));
		System.out.println(">> "+ json);
	    return json;
	}
	
	
	
	@RequestMapping("/qnaComment")
	public String qnaCommentList(int qna_id) {
		
		//서비스에서 List로 보내고, 그걸 받아서 ArrayList로 변환해줌
		ArrayList<QnaCommentVO> list = (ArrayList<QnaCommentVO>)qnaService.appQnaCommentList(qna_id);
		
	    // 생성된 Type을 사용하여 리스트를 JSON 문자열로 변환
		System.out.println(">> "+ new Gson().toJson(list));
	    return new Gson().toJson(list);
		
	}
	
	


}
