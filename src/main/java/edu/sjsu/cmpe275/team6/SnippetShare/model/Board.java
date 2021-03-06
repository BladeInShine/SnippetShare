package edu.sjsu.cmpe275.team6.SnippetShare.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/*
 * @author Rucha
 */

@Entity
@Table(name="board")
public class Board {
	
	@Id
	@TableGenerator(name="board", initialValue=0, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="board")
    @Column(name = "bid")
	private int bid;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "isPublic")
	private boolean isPublic;

     //adding description column to the board
    @Column(name = "description")
    private String description;
	
	//One user can have many boards
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
	private User owner;
	
	@Column(name = "createdAt")
	private long createdAt;
	
	@Column(name = "updatedAt")
	private long updatedAt;

    //added to retrieve snippets
    @Column(name = "snippet")
    @OneToMany(mappedBy = "board", cascade=CascadeType.REMOVE)
    private List<Snippet> snippets;

    //when board is deleted delete the access and requests related with the baord,cascadeType = remove
	@ManyToMany(cascade=CascadeType.REMOVE)
	@JoinTable(
			name="access",
			joinColumns={@JoinColumn(name="bid", referencedColumnName="bid")},
			inverseJoinColumns={@JoinColumn(name="uid", referencedColumnName="userid")})
	private List<User> members;
	
	@ManyToMany(cascade=CascadeType.REMOVE)
	@JoinTable(
			name="request",
			joinColumns={@JoinColumn(name="bid", referencedColumnName="bid")},
			inverseJoinColumns={@JoinColumn(name="uid", referencedColumnName="userid")})
	private List<User> requestors;
	
	public Board(String title, String category, boolean isPublic) {
		this.title = title;
		this.category = category;
		this.isPublic = isPublic;
	}

    //add default constructor always to make get work
    public Board(){}

	public int getBid() {
		return bid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public User getOwner(){
		return owner;
	}

	public void setOwner(User owner) {

		this.owner = owner;
		if (!owner.getBoards().contains(this)) {
			owner.getBoards().add(this);
		}
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public List<User> getRequestors() {
		return requestors;
	}

	public void setRequestors(List<User> requestors) {
		this.requestors = requestors;
	}

	public List<Snippet> getSnippets() {
		return snippets;
	}

	public void setSnippets(List<Snippet> snippets) {
		this.snippets = snippets;
	}

    public int getNumberOfRequests(){
        return requestors.size();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public int getNumberOfSnippets(){
        return snippets.size();
    }

//    public String toString(){
//        return this.bid + "," + this.getTitle()+","
//				+ this.category +"," + this.isPublic + "," + this.getOwner()
//				+ this.createdAt+ "," + this.updatedAt;
//    }
}
