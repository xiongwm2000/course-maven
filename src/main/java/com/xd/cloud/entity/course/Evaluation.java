package com.xd.cloud.entity.course;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xd.cloud.entity.BaseEntity;
import com.xd.cloud.entity.core.User;


/**
 * 评价
 * 
 * @author 云计算应用与开发项目组
 * @since  V1.0
 * 
 */
@Entity
@Table(name = "t_evaluation")
public class Evaluation extends BaseEntity{
	//评价的课程
	private int courseid;
	//评价的用户
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private User user;
    //评价
	private int score;
	private String evatime;
	private String ecommnent;
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getEvatime() {
		return evatime;
	}
	public void setEvatime(String evatime) {
		this.evatime = evatime;
	}
	public String getEcommnent() {
		return ecommnent;
	}
	public void setEcommnent(String ecommnent) {
		this.ecommnent = ecommnent;
	}
	
}
