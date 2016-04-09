package com.example.spring.model.post;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.spring.model.comment.Comment;
import com.example.spring.model.user.User;



@Entity
@Table(name="posts")
public class Post implements Comparable<Post>{
	
	//Define hibernate table and states
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="post_id")
	private int id;
	
	@Column(name="title",columnDefinition="VARCHAR(50)",unique=false,nullable=false)
    private String title;
    @Column(name="description",columnDefinition="VARCHAR(50)",unique=false,nullable=false)
    private String description;
    @Column(name="category",columnDefinition="VARCHAR(50)",unique=false,nullable=false)
    private String category;
    @Column(name="counts_views",columnDefinition="INT",unique=false,nullable=true)
	private int countsOfViews;
    @Column(name="counts_likes",columnDefinition="INT",unique=false,nullable=true)
	private int countsOfLikes;
    @Column(name="date_upload")
    @Temporal(TemporalType.TIMESTAMP)
   	private Date dateOfUpload;
    @Column(name="path",columnDefinition="VARCHAR(150)",unique=true,nullable=true)
   	private String path;
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinTable( name="post_comment", 
                joinColumns=@JoinColumn(name="post_id"), 
                inverseJoinColumns=@JoinColumn(name="comment_id"))
	private Set<Comment> commentsOfPost=new HashSet<>();
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="user_id")
    private User user;
    
    @ManyToMany(mappedBy="likedPosts", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<User> usersWhoLike = new HashSet<User>();
    
    @ManyToMany(cascade={CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(name="post_tag",
    joinColumns={@JoinColumn(name="post_id")},
    inverseJoinColumns={@JoinColumn(name="tag_id")})
    private Set<Tag> tagsOfPost = new HashSet<Tag>();
    
    //Getters and Setters
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	 

	public int getCountsOfViews() {
		return countsOfViews;
	}

	public void setCountsOfViews(int countsOfViews) {
		this.countsOfViews = countsOfViews;
	}

	public int getCountsOfLikes() {
		return countsOfLikes;
	}

	public void setCountsOfLikes(int countsOfLikes) {
		this.countsOfLikes = countsOfLikes;
	}

	public Date getDateOfUpload() {
		return dateOfUpload;
	}

	public void setDateOfUpload(Date dateOfUpload) {
		this.dateOfUpload = dateOfUpload;
	}

	public Set<Comment> getCommentsOfPost() {
		return commentsOfPost;
	}

	public void setCommentsOfPost(Set<Comment> commentsOfPost) {
		this.commentsOfPost = commentsOfPost;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPath(String path){
		this.path=path;
	}
	public String getPath(){
		return this.path;
	}
	
	public Set<User> getUsersWhoLike() {
		return usersWhoLike;
	}

	public void setUsersWhoLike(Set<User> usersWhoLike) {
		this.usersWhoLike = usersWhoLike;
	}

	public Set<Tag> getTagsOfPost() {
		return tagsOfPost;
	}

	public void setTagsOfPost(Set<Tag> tagsOfPost) {
		this.tagsOfPost = tagsOfPost;
	}
    
	//HashCode and Equals
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (id != other.id)
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public int compareTo(Post o) {
		
		if(this.id > o.id){
			return -1;
		}else if(this.id == o.id){
			return 0;
		}else{
			return 1;
		}
		
	}

}
