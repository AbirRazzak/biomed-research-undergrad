/**
 * 
 */
package facebook;

/**
 * @author Anim
 *
 */
public class node {
	String name;	//stores the node's ID
	int comment_centrality; //stores the node's centrality in comment network
	int like_centrality; //stores the node's centrality in like network
	/**
	 * 
	 */
	public node(String ID) {
		// TODO Auto-generated constructor stub
		name = ID;
		comment_centrality = -1;
		like_centrality = -1;
	}
	public String getName(){
		return name;
	}
	public void setCommentCentrality(int c){
		comment_centrality = c;
	}
	public int getCommentCentrality(){
		return comment_centrality;
	}
	public void setLikeCentrality(int l){
		like_centrality = l;
	}
	public int getLikeCentrality(){
		return like_centrality;
	}
}
