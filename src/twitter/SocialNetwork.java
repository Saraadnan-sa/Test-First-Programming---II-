package twitter;

import java.util.*;

/**
 * SocialNetwork provides methods that operate on a social network.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     */
	public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
	    Map<String, Set<String>> followsGraph = new HashMap<>();
	    
	    for (Tweet tweet : tweets) {
	        String author = tweet.getAuthor().toLowerCase();  // Case-insensitive usernames
	        Set<String> mentionedUsers = getMentions(tweet.getText());
	        
	        if (!mentionedUsers.isEmpty()) {
	            // Add author only if there are mentions
	            followsGraph.putIfAbsent(author, new HashSet<>());
	            followsGraph.get(author).addAll(mentionedUsers);
	        }
	    }
	    
	    return followsGraph;
	}


    private static Set<String> getMentions(String text) {
        Set<String> mentions = new HashSet<>();
        
        String[] words = text.split("\\s+");  // Split text into words
        for (String word : words) {
            if (word.startsWith("@") && word.length() > 1) {
                String username = word.substring(1).replaceAll("[^a-zA-Z0-9_-]", "").toLowerCase();
                mentions.add(username);
            }
        }
        
        return mentions;
    }


    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        Map<String, Integer> followerCount = new HashMap<>();
        
        // Iterate over each user's followed users
        for (Set<String> followedUsers : followsGraph.values()) {
            for (String user : followedUsers) {
                // Increase the follower count for each mentioned user
                followerCount.put(user, followerCount.getOrDefault(user, 0) + 1);
            }
        }
        
        // Create a list of all distinct users from followerCount map
        List<String> influencers = new ArrayList<>(followerCount.keySet());
        
        // Sort the list of users by follower count in descending order
        influencers.sort((user1, user2) -> followerCount.get(user2) - followerCount.get(user1));
        
        return influencers;
    }
}
