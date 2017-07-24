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
		nodes = new ArrayList<node>();
		links = new ArrayList<nodeLink>();
		comment_density = -1.0;
		like_density = -1.0;
		g = 0;
		comment_l = 0;
		like_l = 0;
	}
	public void addNode(node n){
		nodes.add(n);
	}
	public node getNode(int i){
		return nodes.get(i);
	}
	public ArrayList<node> getNodes(){
		return nodes;
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

	public int getG(){
		return g;
	}
	
	/*
	 * calculateCommentL - calculates the value of L for the comments network
	 */
	private void calculateCommentL(){
		int l = 0;
		
		for(nodeLink n:links){
			if(n.getComments() > 0) l++;
		}
		
		comment_l = l;
	}
	
	/*
	 * calculateLikeL - calculates the value of L for the likes network
	 */
	private void calculateLikeL(){
		int l = 0;
		
		for(nodeLink n:links){
			if(n.getLikes() > 0) l++;
		}
		
		like_l = l;
	}
	
	public int getCommentL(){
		return comment_l;
	}
	
	public int getLikeL(){
		return like_l;
	}
	
	/*
	 * calculates the Density of comment network
	 * @return - returns delta, the Density of the network.
	 */
	public double calculateCommentDensity(){
		calculateG();
		calculateCommentL();
		
		comment_density = (double)((2.0 * comment_l) / (g * (g - 1.0)));
		return comment_density;
	}
	
	/*
	 * calculates the Density of like network
	 * @return - returns delta, the Density of the network.
	 */
	public double calculateLikeDensity(){
		calculateG();
		calculateLikeL();
		
		like_density = (2.0 * like_l) / (g * (g - 1.0));
		return like_density;
	}
	
	public void calculateCommentCentrality(){
		String currentNode;
		int counter = 0;
		for(node n : nodes){
			currentNode = n.getName();
			for(nodeLink l : links){
				if(l.getName1() == currentNode || l.getName2() == currentNode){
					if(l.getComments() > 0) counter++;
				}
			}
			n.setCommentCentrality(counter);
			counter = 0;
		}
	}
	
	public boolean isCentralCommentNode(node target){
		int max = target.getCommentCentrality();
		for(node n : nodes){
			if(n.getCommentCentrality() > max) return false;
		}
		return true;
	}
	
	public void calculateLikeCentrality(){
		String currentNode;
		int counter = 0;
		for(node n : nodes){
			currentNode = n.getName();
			for(nodeLink l : links){
				if(l.getName1() == currentNode || l.getName2() == currentNode){
					if(l.getLikes() > 0) counter++;
				}
			}
			n.setLikeCentrality(counter);
			counter = 0;
		}
	}
	
	public boolean isCentralLikeNode(node target){
		int max = target.getLikeCentrality();
		for(node n : nodes){
			if(n.getLikeCentrality() > max) return false;
		}
		return true;
	}
}
