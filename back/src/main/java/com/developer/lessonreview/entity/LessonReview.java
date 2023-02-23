package com.developer.lessonreview.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.developer.appliedlesson.entity.AppliedLesson;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="LESSON_REVIEW")
@DynamicInsert @DynamicUpdate

@Setter@Getter
@NoArgsConstructor @AllArgsConstructor
@JsonFormat(pattern = "yy-MM-dd", timezone = "Asia/Seoul")
public class LessonReview {
	@Id
	@Column(name="apply_seq")
	private Long applySeq;
	@Column(name="cdate", columnDefinition = "DATE DEFAULT SYSDATE")
	private Date cDate;
	@Column(name="review", nullable = false)
	private String review;
	@Column(name="star", nullable = false)
	private Integer star;	
	
	@MapsId("applySeq")
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="apply_seq", nullable = true)
	private AppliedLesson appliedLesson;
}
