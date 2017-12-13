package com.niit.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="blogpost")
public class BlogPost {
	
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
		@NotEmpty
		private String blogTitle;
		@Lob
		private String blogContent;
		@ManyToOne
		private User postedBy;
		private Date postedOn;
		private boolean approved;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getBlogTitle() {
			return blogTitle;
		}
		public void setBlogTitle(String blogTitle) {
			this.blogTitle = blogTitle;
		}
		public String getBlogContent() {
			return blogContent;
		}
		public void setBlogContent(String blogContent) {
			this.blogContent = blogContent;
		}
		public User getPostedBy() {
			return postedBy;
		}
		public void setPostedBy(User postedBy) {
			this.postedBy = postedBy;
		}
		public Date getPostedOn() {
			return postedOn;
		}
		public void setPostedOn(Date postedOn) {
			this.postedOn = postedOn;
		}
		public boolean isApproved() {
			return approved;
		}
		public void setApproved(boolean approved) {
			this.approved = approved;
		}
		

}
