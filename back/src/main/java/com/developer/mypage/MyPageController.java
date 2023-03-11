package com.developer.mypage;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.appliedlesson.service.AppliedLessonService;
import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.exception.RemoveException;
import com.developer.favoriteslesson.dto.FavoritesLessonDTO;
import com.developer.favoriteslesson.service.FavoritesLessonService;
import com.developer.favoritesstudyroom.dto.FavoritesStudyroomDTO;
import com.developer.favoritesstudyroom.service.FavoritesStudyroomService;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.service.LessonService;
import com.developer.lessonreview.dto.LessonReviewDTO;
import com.developer.lessonreview.service.LessonReviewService;
import com.developer.reservation.dto.ReservationDTO;
import com.developer.reservation.service.ReservationService;
import com.developer.roomreview.dto.RoomReviewDTO;
import com.developer.roomreview.dto.RoomReviewDTO.RoomReviewInsertDTO;
import com.developer.roomreview.service.RoomReviewService;
import com.developer.userreview.dto.UserReviewDTO;
import com.developer.userreview.service.UserReviewService;
import com.developer.users.dto.UsersDTO;
import com.developer.users.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("mypage/*")
@RequiredArgsConstructor
public class MyPageController {

	private final AppliedLessonService alService;
	private final LessonService lService;
	private final UserReviewService urService;
	private final UsersService uService;
	private final FavoritesStudyroomService fsService;;
	private final FavoritesLessonService flService;
	private final LessonReviewService lrservice;
	private final ReservationService rService;
	private final RoomReviewService rrservice;

	
	/**
	 * 튜터메인
	 * @author Jin
	 * @param tutorId
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "tutor/{tutorId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tutorMain(@PathVariable String tutorId) throws FindException{
		MyPageDTO.TutorMainDTO dto = new MyPageDTO.TutorMainDTO();
		
		List<LessonDTO.GetLessonByUser1> lList = lService.getLessonByUser1(tutorId);
		List<LessonDTO.GetLessonByUser2> lList2 = lService.getLessonByUser2(tutorId);
		List<LessonDTO.GetLessonByUser3> lList3 = lService.getLessonByUser3(tutorId);
		dto.setList(lList);
		dto.setList2(lList2);
		dto.setList3(lList3);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	/**
	 * 튜터 진행예정수업 상세페이지
	 * @author Jin
	 * @param lessonSeq
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "tutor/upcoming/detail/{lessonSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tutorUpcoming(@PathVariable Long lessonSeq) throws FindException{
		MyPageDTO.TutorUpcomingDTO dto = new MyPageDTO.TutorUpcomingDTO();
		
		List<AppliedLessonDTO.NotYetUserByAppliedLessonDTO> notYetList = alService.getLessonNotApplyUser(lessonSeq);
		List<AppliedLessonDTO.ApproveUserByAppliedLessonDTO> approveList = alService.getLessonApplyUser(lessonSeq);
		List<LessonDTO.selectLessonDTO> lList = lService.getLessonDetail(lessonSeq);
		dto.setNotYetList(notYetList);
		dto.setApproveList(approveList);
		dto.setLList(lList);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	

	/**
	 * 튜터 진행중인수업 상세페이지
	 * @author Jin
	 * @param lessonSeq
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "tutor/ongoing/detail/{lessonSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tutorOngoing(@PathVariable Long lessonSeq) throws FindException{
		MyPageDTO.TutorOngoingDTO dto = new MyPageDTO.TutorOngoingDTO();
		
		List<LessonDTO.selectLessonDTO> lList = lService.getLessonDetail(lessonSeq);
		List<AppliedLessonDTO.ApproveUserByAppliedLessonDTO> approveList = alService.getLessonApplyUser(lessonSeq);
		dto.setAlList(approveList);
		dto.setLList(lList);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	

	/**
	 * 튜티 진행 예정 수업 목록(승인, 미승인)
	 * @author Jin
	 * @param userId
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "tutee/upcoming/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> tuteeUpcoming(@PathVariable String userId) throws FindException{
		MyPageDTO.TuteeUpcomingDTO dto = new MyPageDTO.TuteeUpcomingDTO();
		
		List<LessonDTO.applyLessonBytutee> applyList = lService.upComingLesson(userId);
		List<LessonDTO.notYetLessonBytutee> notYetList = lService.getApplyLesson(userId);
		dto.setApplyList(applyList);
		dto.setNotYetlist(notYetList);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	/**
	 * 내 클래스에 신청한 튜티 승인하기
	 * @author Jin
	 * @param applySeq
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@PatchMapping(value = "tutor/upcoming/detail/apply/{applySeq}")
	public ResponseEntity<?> updateApplyLesson(@PathVariable Long applySeq, HttpSession session) throws FindException {
		applySeq = 1L;
		if(applySeq == null) {
			return new ResponseEntity<>("수업 신청 내역이 없습니다.", HttpStatus.BAD_REQUEST);
		} else {
			alService.updateApplyLesson(applySeq);
			return new ResponseEntity<>(HttpStatus.OK);
		}	
	}


	/**
	 * 내 클래스에 신청한 튜티 거절하기
	 * @author Jin
	 * @param applySeq
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@PatchMapping(value = {"tutor/upcoming/detail/notapply/{applySeq}"})
	public ResponseEntity<?> updateNotApplyLesson(@PathVariable Long applySeq, HttpSession session) throws FindException {
		applySeq = 1L;
		if(applySeq == null) {
			return new ResponseEntity<>("수업 신청 내역이 없습니다.", HttpStatus.BAD_REQUEST);
		} else {
			alService.updateNotApplyLesson(applySeq);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
	
	/**
	 * 튜터의 진행예정 수업 리스트 출력하기(개별페이지)
	 * @author Jin
	 * @param tutorId
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "tutor/upcoming/{tutorId}" )
	public ResponseEntity<?> getLessonByUser1(@PathVariable String tutorId) throws FindException{
		List<LessonDTO.GetLessonByUser1> list = lService.getLessonByUser1(tutorId);
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	

	/**
	 * 튜터의 진행중인 수업 리스트 출력하기(개별페이지)
	 * @author Jin
	 * @param tutorId
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "tutor/ongoing/{tutorId}" )
	public ResponseEntity<?> getLessonByUser2(@PathVariable String tutorId) throws FindException{
		List<LessonDTO.GetLessonByUser2> list = lService.getLessonByUser2(tutorId);
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	

	/**
	 * 진행완료된 수업 리스트 출력하기(개별페이지)
	 * @author GH
	 * @param tutorId
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "tutor/completed/{tutorId}" )
	public ResponseEntity<?> getLessonByUser3(@PathVariable String tutorId) throws FindException{
		List<LessonDTO.GetLessonByUser3> list = lService.getLessonByUser3(tutorId);
		return new ResponseEntity<>(list, HttpStatus.OK);		
	}
	

	/**
	 * 튜터의 나의 수업 수정하기
	 * @author Jin
	 * @param lessonSeq
	 * @param lDTO
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@PatchMapping(value = "tutor/upcoming/detail/update/{lessonSeq}")
	public ResponseEntity<?> updateLesson(@PathVariable Long lessonSeq,@RequestBody LessonDTO.selectLessonDTO lDTO, HttpSession session) throws FindException {
		lService.updates(lDTO);
		return new ResponseEntity<>(HttpStatus.OK);	
	}
	

	/**
	 * 튜터의 나의 수업 삭제하기
	 * @author Jin
	 * @param lessonSeq
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@PatchMapping(value = "tutor/upcoming/detail/delete/{lessonSeq}")
	public ResponseEntity<?> deleteLesson(@PathVariable Long lessonSeq, HttpSession session) throws FindException{
		if(lessonSeq == null) {
			return new ResponseEntity<>("수업 내역이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
		} else {
			lService.deleteLesson(lessonSeq);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	

	/**
	 * 튜티가 수강중인 수업 리스트
	 * @author Jin
	 * @param userId
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "tutee/ongoing/{userId}")
	public ResponseEntity<?> onGoinLesson(@PathVariable String userId) throws FindException{
		List<LessonDTO.applyLessonBytutee> list = lService.onGoingLesson(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	

	/**
	 * 수업에 참여할 튜티의 이전 수업 후기 목록
	 * @author Jin
	 * @param userId
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "tutor/upcoming/detail/tuteereview/{userId}")
	public ResponseEntity<?> getTuteeReview(@PathVariable String userId) throws FindException{
		List<UserReviewDTO.getTuteeReview> list = urService.getTuteeReview(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}


	/**
	 * 사용자 상세정보
	 * @author Jin
	 * @param userId
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "{userId}")
	public ResponseEntity<?> getUser(@PathVariable String userId) throws FindException{
		if(userId == null) {
			return new ResponseEntity<>("존재하지 않는 회원입니다.",HttpStatus.BAD_REQUEST);
		} else {
			UsersDTO.UsersDetailDTO usersDTO = uService.getUser(userId);
			return new ResponseEntity<>(usersDTO, HttpStatus.OK);
		}
	}
	

	/**
	 * 사용자 정보 수정하기
	 * @author Jin
	 * @param userId
	 * @param uDTO
	 * @return
	 * @throws FindException
	 * @throws AddException
	 */
	@PatchMapping(value = "update/{userId}")
	public ResponseEntity<?> updateLesson(@PathVariable String userId, @RequestBody UsersDTO uDTO) throws FindException, AddException{
		uService.addUsers(uDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	/**
	 * 사용자 탈퇴하기
	 * @author Jin
	 * @param userId
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@PatchMapping(value = "delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable String userId, HttpSession session) throws FindException{
		if(userId == null) {
			return new ResponseEntity<>("수업 내역이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
		} else {
			uService.deleteUser(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}	

	
	/**
	 * [FavoritesStudyroom] 나의 스터디카페 즐겨찾기 목록
	 * @author SR
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "favoritesstudyroom", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listFavStudyroom(HttpSession session) throws FindException {

		String userId = (String) session.getAttribute("logined");
		
		List<FavoritesStudyroomDTO.favStudyroomListDTO> list = fsService.listFavStudyroom(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

   /**
    * [Reservation] 아이디값을 받아와 전체 예약내역을 최신순으로 출력한다
    * 
    * @author ds
    * @param userId 유저 아이디
    * @return List<ReservationDTO.selectMyResHistoryDTO> 유저의 전체 예약 내역(최신순)
    * @throws 전체정보 출력시 FindException예외발생한다
    */
   @GetMapping(value = "studyroom", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
   public ResponseEntity<?> getMyResHistoery(HttpSession session) throws FindException {
	   String logined = (String) session.getAttribute("logined");
	   System.out.println("세션아이디는: "+logined);
	   if(logined!=null) {
		   
		   List<ReservationDTO.selectMyResHistoryDTO> list = rService.selectMyResHistory(logined);
		   return new ResponseEntity<>(list, HttpStatus.OK);
	   }else {
		   return new ResponseEntity<>("로그인하세요", HttpStatus.BAD_REQUEST);
	   }
   }

   /**
    * [Reservation] 아이디값으로 후기를 작성하지 않은 예약리스트를 출력한다
    * [RoomReview] 사용자의 스터디카페 이용후기 목록을 출력한다
    * 
    * @author ds
    * @param userId
    * @return List<Object[]> 유저의 작성한 이용후기 리스트
    */
   @GetMapping(value = "roomreview", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
   public ResponseEntity<?> getRqRmRv(HttpSession session) throws FindException {
	   String logined = (String) session.getAttribute("logined");
	   if (logined == null) { // 로그인 안한 경우
			throw new FindException("로그인하세요");
		}
	   MyPageDTO.getRoomReviewList dto = new MyPageDTO.getRoomReviewList();
      List<ReservationDTO.selectRmRvDTO> list1 = rService.selectMyReqRmRv(logined);
      List<RoomReviewDTO.selectMyRmRvDTO> list2 = rrservice.selectMyRmRv(logined);
      dto.setSelectRmRvDTO(list1);
      dto.setSelectMyRmRvDTO(list2);
      return new ResponseEntity<>(dto, HttpStatus.OK);
   }


   /**
    * [RoomReview] 후기를 작성한다
    * 
    * @author ds
    * @param resSeq, content, star
    */
   @PostMapping(value = "roomreview/add", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> addRoomReview(@RequestBody RoomReviewInsertDTO rrDTO) throws AddException {
      rrservice.insertReview(rrDTO);
      return new ResponseEntity<>(rrDTO, HttpStatus.OK);
   }

   /**
	 * [RoomReview] 예약시퀀스를 받아 해당 후기상세출력한다
	 * @author ds
	 * @param resSeq 예약 시퀀스
	 * @return RoomReview 유저의 작성한 이용후기 상세정보
	 */
	@GetMapping(value = "roomreview/{resSeq}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> getReviewDetailByResSeq(@PathVariable Long resSeq) throws FindException{
		List<RoomReviewDTO.selectMyRmRvDetailDTO> list = rrservice.selectRmRvDetail(resSeq);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	

   
   /**
    * [FavoritesLesson] 나의 수업 즐겨찾기 목록 확인
    * 
    * @author moonone
    * @param userId 사용자아이디
    * @return 즐겨찾기목록
    * @throws FindException
    */
   @GetMapping(value="favoriteslesson", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<?> list(HttpSession session) throws FindException {
      String logined = (String) session.getAttribute("logined");
      if (logined != null) {
         List<FavoritesLessonDTO.flListDTO> flDTO = flService.listFavLesson(logined);
         return new ResponseEntity<>(flDTO, HttpStatus.OK);
      }
      return new ResponseEntity<>("로그인하세요", HttpStatus.BAD_REQUEST);
   }

   /**
    * [LessonReview] 후기를 작성하지 않은 수업 목록 확인
    * 
    * @author moonone
    * @param userId 사용자아이디
    * @return 수업목록
    * @throws FindException
    */
   @GetMapping(value = "tutee/lessonreview")
   public ResponseEntity<?> noWriteLReview(HttpSession session) throws FindException {
      String logined = (String) session.getAttribute("logined");
      List<LessonReviewDTO.noWriteLReviewDTO> list = lrservice.noWriteLReview(logined);
      return new ResponseEntity<>(list, HttpStatus.OK);
   }

   /**
    * [LessonReview] 튜터의 수업에 대한 후기 추가 및 수정
    * 
    * @author moonone
    * @param lrDTO 작성한 후기
    * @throws FindException
    */
   @PostMapping(value = "tutee/lessonreview/{applySeq}")
   public ResponseEntity<?> addReview(@RequestBody LessonReviewDTO.lrDTO lrDTO, @PathVariable Long applySeq) throws AddException, FindException {
	   lrservice.addReview(lrDTO, applySeq);
      return new ResponseEntity<>(HttpStatus.OK);
   }

   /**
    * [LessonReview] 작성한 후기 목록 확인
    * 
    * @author moonone
    * @param userId 사용자아이디
    * @return 후기목록
    * @throws FindException
    */
   @GetMapping(value = "tutee/myreview")
   public ResponseEntity<?> lReviewList(HttpSession session) throws FindException {
      String logined = (String) session.getAttribute("logined");
      List<LessonReviewDTO.listLRListDTO> list = lrservice.lReviewList(logined);
      return new ResponseEntity<>(list, HttpStatus.OK);
   }
   
   /**
	 * [Lesson] 진행 완료된 수업 이름출력
	 * @author choigeunhyeong
	 * @param tutorId
	 * @return
	 * @throws FindException
	 */
	@GetMapping(value = "tutor/completed" )
	public ResponseEntity<?> getLessonByUser3(HttpSession session) throws FindException{
		String logined = (String) session.getAttribute("logined");
		if (logined != null) {
		List<LessonDTO.GetLessonByUser3> list = lService.getLessonByUser3(logined);
		return new ResponseEntity<>(list, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("로그인하세요", HttpStatus.BAD_REQUEST);
		}
	}
	
  /**
   * [AppliedLesson] 진행완료된 클래스 페이지 클래스명, 수강했던 튜티목록
   * 
   * @author choigeunhyeong
   * @param lessonSeq
   * @return
   * @throws FindException
   */
  @GetMapping(value = "tutor/completed/addreview/{lessonSeq}")
  public ResponseEntity<?> selectClassAndTutee(@PathVariable Long lessonSeq) throws FindException {
     List<UsersDTO.getNameDTO> list = alService.selectClassAndTutee(lessonSeq);
     return new ResponseEntity<>(list, HttpStatus.OK);
  }

  /**
   * [Userreview] 튜티가 튜터에게 받은 수업 후기 작성
   * @author choigeunhyeong
   * @param addReviewDTO
   * @param applySeqRv
   * @return
   * @throws AddException
   */
  @PostMapping(value = "tutor/completed/addreview/{applySeqRv}")
  public ResponseEntity<?> addReview(UserReviewDTO.addReviewDTO addReviewDTO, @PathVariable Long applySeqRv)
        throws AddException {
     urService.addUserReview(addReviewDTO, applySeqRv);
     return new ResponseEntity<>(HttpStatus.OK);
  }
  
  /**
   * [Lesson, appliedLesson] 튜터 진행완료된수업 상세페이지 
   * @author choigeunhyeong
   * @param lessonSeq
   * @return
   * @throws FindException
   */
  @GetMapping(value = "tutor/completed/detail/{lessonSeq}")
  public ResponseEntity<?> tutorCompletedDetail(@PathVariable Long lessonSeq) throws FindException{
	   MyPageDTO.tutorCompletedDetailDTO dto = new MyPageDTO.tutorCompletedDetailDTO();
	   
	   List<AppliedLessonDTO.ApproveUserByAppliedLessonDTO> Userlist = alService.getLessonApplyUser(lessonSeq);
	   List<LessonDTO.selectLessonDTO> Lessonlist = lService.getLessonDetail(lessonSeq);
	   List<UsersDTO.getCompletedClassDTO> reviewList = alService.selectCompletedClassList(lessonSeq);
	   
	   dto.setSelectLessonDTO(Lessonlist);
	   dto.setUserAppliedLessonDTO(Userlist);
	   dto.setCompletedlessonReviewDTO(reviewList);
	   return new ResponseEntity<>(dto, HttpStatus.OK); 
  }
	/**
	 * [Reservation] 예약내역 1건을 삭제한다.
	 * 
	 * @author DS
	 * @param resSeq
	 * @param session
	 * @return
	 * @throws RemoveException
	 */
	@DeleteMapping(value = "studyroom/{resSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> ListReservation(@PathVariable long resSeq) throws RemoveException {
		rService.deleteReservation(resSeq);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
