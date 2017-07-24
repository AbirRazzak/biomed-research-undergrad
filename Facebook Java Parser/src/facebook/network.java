/**
 * 
 */
package facebook;

import java.util.ArrayList;

/**
 * @author Anim
 *
 */
public class network {
	ArrayList<node> nodes;
	ArrayList<nodeLink> links;
	double comment_density;
	double like_density;
	int g;
	int comment_l;
	int like_l;
	/**
	 * 
	 */
	public network() {
		// TODO Auto-generated constructor stub
		comment_density = -1;
		like_density = -1;
		g = 0;
		comment_l = 0;
		like_l = 0;
	}
	public void addNode(node n){
		nodes.add(n);
	}
	
	/* 
	 * setLinks() - links all members together to track like and comment frequency
	 */
	public void setLinks(){
		//links all members together to track like and comment frequency
		for(int nameNum=0; nameNum<nodes.size()-1; nameNum++){
			for(int secNum=nameNum+1; secNum<nodes.size(); secNum++){
				String a= nodes.get(nameNum).getName();
				String b= nodes.get(secNum).getName();
				links.add(new nodeLink(a,b));
			}
		}
	}
	
	public ArrayList<nodeLink> getLinks(){
		return links;
	}
	
	/*
	 * calculateG - calculates the value of g (number of nodes in network)
	 */
	private void calculateG(){
		g = nodes.size();
	}

	/*
	 * calculateCommentL - calculates the value of L for the comments network
	 */
	private void calculateCommentL(){
		int l = 0;
		
		for(nodeLink n:links){
			if(n.commentLink > 0) l++;
		}
		
		comment_l = l;
	}
	
	/*
	 * calculateLikeL - calculates the value of L for the likes network
	 */
	private void calculateLikeL(){
		int l = 0;
		
		for(nodeLink n:links){
			if(n.likeLink > 0) l++;
		}
		
		like_l = l;
	}
	
	/*
	 * calculates the Density of comment network
	 * @return - returns delta, the Density of the network.
	 */
	public double calculateCommentDensity(){
		calculateG();
		calculateCommentL();
		
		comment_density = (2 * comment_l) / (g * (g - 1));
		return comment_density;
	}
	
	/*
	 * calculates the Density of like network
	 * @return - returns delta, the Density of the network.
	 */
	public double calculateLikeDensity(){
		calculateG();
		calculateLikeL();
		
		like_density = (2 * like_l) / (g * (g - 1));
		return like_density;
	}
}
