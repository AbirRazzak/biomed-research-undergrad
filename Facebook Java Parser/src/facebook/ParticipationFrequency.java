package facebook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class ParticipationFrequency {
	
	//create a hash map of comment participation frequency
	//key is participant name,value is frequency
	public static Map<String,Integer> commentFre = new HashMap<>();
		
	//create a hash map of post like frequency
	//key is participant name, value is frequency
	public static Map<String,Integer> postLikeFre = new HashMap<>();

	public static void main(String[] args) {
		
		String inputFileString = "/Users/mengnanzhao/Documents/Facebook/input0527/T10.json";
		
		getMemberName(inputFileString);
		
		getFrequency(inputFileString);
		
		try{
	        File file;
			file = new File("/Users/mengnanzhao/Documents/Facebook/outputOfFrequency0527/frequencyT10.txt");
			BufferedWriter bw = null;
			bw = new BufferedWriter(new FileWriter(file, true)); 
			
			for (Map.Entry<String,Integer> entry : commentFre.entrySet()) {
			    String key = entry.getKey();
			    int commentValue = entry.getValue();
			    int postLikeValue = postLikeFre.get(key);
			    bw.write(key + "|" + commentValue + "|" + postLikeValue);
			    bw.write("\n");    
			    
			}
			bw.close();
			
		}catch (Exception e) {
            e.printStackTrace();
        }
		

        
	}

	public static void getFrequency(String inputFileString){
        try{
        	//read json file
        	JSONTokener tokener = new JSONTokener(new FileReader(inputFileString));
        	//get json object
        	JSONObject object = new JSONObject(tokener);
        	//get json array under "data"
        	JSONArray dataArray = (JSONArray) object.getJSONArray("data");
        	//System.out.println("number of feeds: " + dataArray.length());
        	
        	//loop each object in "data" array
    		for(int j = 0;j < dataArray.length();j++){
    			JSONObject data = (JSONObject) dataArray.getJSONObject(j);
    			
    			//deal with "like" in post
    			if(data.getJSONObject("likes") != null){
    				JSONArray dataArrayInLike = (JSONArray) data.getJSONObject("likes").get("data");
    				for(int numOfLike = 0; numOfLike < dataArrayInLike.length();numOfLike++){
    					JSONObject dataInLike = (JSONObject) dataArrayInLike.getJSONObject(numOfLike);
    					String nameOfLike = dataInLike.getString("name");
    					System.out.println("name of like: " + nameOfLike);
    					
    					if(postLikeFre.containsKey(nameOfLike)){
    						int currentFre = postLikeFre.get(nameOfLike);
    						postLikeFre.put(nameOfLike, currentFre+1);
    					}
    				}
    			}

    				
    			JSONObject comments;
    			if(data.getJSONObject("comments") == null){  //in case there is no comment within the post
    				continue;
    			}else{
    				comments = (JSONObject) data.getJSONObject("comments");
    				JSONArray dataArrayInComments = (JSONArray) comments.getJSONArray("data");
    				//System.out.println(dataArrayInComments.length());
    				
    				for(int k = 0;k < dataArrayInComments.length();k++){
    					JSONObject dataInComment = (JSONObject) dataArrayInComments.getJSONObject(k);
    					String memberName;
    	    			if(dataInComment == null){
    	    				continue;}
    	    			if(dataInComment.getJSONObject("from") == null){
    	    				memberName = "Participant";
    	    			}else{
    	    				memberName = dataInComment.getJSONObject("from").getString("name");
    	    				System.out.println("name of comment: " + memberName);
    	        			if(commentFre.containsKey(memberName)){
    	        				int currentFre = commentFre.get(memberName);
    	        				commentFre.put(memberName, currentFre+1);	        				
    	        			}
    					}
    					
    				}
    			}
    		}

        }catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void getMemberName(String inputFileString){
        try{
        	//read json file
        	JSONTokener tokener = new JSONTokener(new FileReader(inputFileString));
        	//get json object
        	JSONObject object = new JSONObject(tokener);
        	//get json array under "data"
        	JSONArray dataArray = (JSONArray) object.getJSONArray("data");
        	//System.out.println("number of feeds: " + dataArray.length());
        	
        	//loop each object in "data" array
    		for(int j = 0;j < dataArray.length();j++){
    			JSONObject data = (JSONObject) dataArray.getJSONObject(j);
    			//String random = data.getJSONObject("from").getString("id");
    			
    			String memberName = data.getJSONObject("from").getString("name");
    			commentFre.put(memberName, 0);
    			postLikeFre.put(memberName, 0);
    				
    			JSONObject comments;
    			if(data.getJSONObject("comments") == null){  //in case there is no comment within the post
    				continue;
    			}else{
    				comments = (JSONObject) data.getJSONObject("comments");
    				JSONArray dataArrayInComments = (JSONArray) comments.getJSONArray("data");
    				//System.out.println(dataArrayInComments.length());
    				
    				for(int k = 0;k < dataArrayInComments.length();k++){
    					JSONObject dataInComment = (JSONObject) dataArrayInComments.getJSONObject(k);
    	    			if(dataInComment == null)
    	    				continue;
    	    			if(dataInComment.getJSONObject("from") == null){
    	    				memberName = "Participant";
    	    			}else{
    	    				memberName = dataInComment.getJSONObject("from").getString("name");
    	        			if(!commentFre.containsKey(memberName))
    	        				commentFre.put(memberName, 0);
    	        			
    	        			if(!postLikeFre.containsKey(memberName))
    	        				postLikeFre.put(memberName, 0);
    					}
    					
    				}
    			}
    		}

        }catch (Exception e) {
            e.printStackTrace();
        }
	}

}