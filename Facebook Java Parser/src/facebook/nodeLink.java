/**
 * 
 */
package facebook;

/**
 * @author Anim
 *
 */
public class nodeLink {
	int likeLink;
	int commentLink;
	String n1;
	String n2;
	/**
	 * 
	 */
	public nodeLink(String Name1, String Name2) {
		// TODO Auto-generated constructor stub
		n1=Name1;
		n2=Name2;
		likeLink=0;
		commentLink=0;
	}
	public void addLike(){
		likeLink++;
	}
	public void addComment(){
		commentLink++;
	}
	public String getName1(){
		return n1;
	}
	public String getName2(){
		return n2;
	}
	public int getLikes(){
		return likeLink;
	}
	public int getComments(){
		return commentLink;
	}
	public String displayStats(){
		String a=n1+" and "+n2+" have "+likeLink+" Like(s) in common, and "+commentLink+" Comment(s) in common.";
		return a;		
	}
}
