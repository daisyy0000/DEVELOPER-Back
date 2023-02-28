package com.developer.reservation.dto;

import java.util.Date;

import com.developer.hostuser.entity.HostUser;
import com.developer.roominfo.entity.RoomInfo;
import com.developer.roomreview.entity.RoomReview;
import com.developer.users.entity.Users;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ReservationDTO {
	private Long resSeq;
	private Users userId;
	private HostUser hostUser; 
	private RoomInfo roominfo;
	private String startTime;
	private String endTime;
	private Date usingDate;
	
	private RoomReview roomReview;
	
	@Data
	@NoArgsConstructor
	public static class getReservationDTO{
		private Long resSeq;
		private Date usingDate;
		private String startTime;
		private String endTime;
	}
}
