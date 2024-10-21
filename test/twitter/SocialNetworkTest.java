package twitter;

import org.junit.Test;
import java.util.*;
import java.time.Instant;
import static org.junit.Assert.*;

public class SocialNetworkTest {

    // Test cases for guessFollowsGraph

	 @Test
	    public void testGuessFollowsGraphEmptyListOfTweets() {
	        List<Tweet> tweets = new ArrayList<>();
	        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);

	        assertTrue(followsGraph.isEmpty());
	    }

	    @Test
	    public void testGuessFollowsGraphTweetsWithoutMentions() {
	        Tweet tweet = new Tweet(1, "alice", "This is a tweet without mentions.", Instant.now());
	        List<Tweet> tweets = Arrays.asList(tweet);
	        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);

	        assertTrue("Graph should be empty when there are no mentions", followsGraph.isEmpty());
	    }


	    
	    @Test
	    public void testGuessFollowsGraphSingleMention() {
	        Tweet tweet = new Tweet(1, "alice", "Hello @bob!", Instant.now());
	        List<Tweet> tweets = Arrays.asList(tweet);
	        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);

	        assertTrue(followsGraph.containsKey("alice"));
	        assertTrue(followsGraph.get("alice").contains("bob"));
	    }

	    @Test
	    public void testGuessFollowsGraphMultipleMentions() {
	        Tweet tweet = new Tweet(1, "alice", "Hello @bob and @charlie!", Instant.now());
	        List<Tweet> tweets = Arrays.asList(tweet);
	        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);

	        assertTrue(followsGraph.containsKey("alice"));
	        assertTrue(followsGraph.get("alice").contains("bob"));
	        assertTrue(followsGraph.get("alice").contains("charlie"));
	    }

    @Test
    public void testGuessFollowsGraphMultipleTweetsFromOneUser() {
        Tweet tweet1 = new Tweet(1, "alice", "Hello @bob!", Instant.now());
        Tweet tweet2 = new Tweet(2, "alice", "Hi @charlie!", Instant.now());
        List<Tweet> tweets = Arrays.asList(tweet1, tweet2);
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);

        assertTrue(followsGraph.containsKey("alice"));
        assertTrue(followsGraph.get("alice").contains("bob"));
        assertTrue(followsGraph.get("alice").contains("charlie"));
    }


    // Test cases for influencers

    @Test
    public void testInfluencersEmptyGraph() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);

        assertTrue("Influencers list should be empty for an empty graph", influencers.isEmpty());
    }

    @Test
    public void testInfluencersSingleUserWithoutFollowers() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("ernie", new HashSet<>());
        
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertTrue("No influencers since no one is following anyone", influencers.isEmpty());
    }

    @Test
    public void testInfluencersSingleInfluencer() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("ernie", new HashSet<>(Arrays.asList("bert")));
        
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertEquals("Bert should be the top influencer", Arrays.asList("bert"), influencers);
    }

    @Test
    public void testInfluencersMultipleInfluencers() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("ernie", new HashSet<>(Arrays.asList("bert", "grover")));
        followsGraph.put("zoe", new HashSet<>(Arrays.asList("bert")));
        
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertEquals("Bert should be the top influencer, followed by Grover", Arrays.asList("bert", "grover"), influencers);
    }

    @Test
    public void testInfluencersTiedInfluence() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("ernie", new HashSet<>(Arrays.asList("bert")));
        followsGraph.put("zoe", new HashSet<>(Arrays.asList("grover")));
        
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertTrue("Both Bert and Grover should be influencers", influencers.containsAll(Arrays.asList("bert", "grover")));
    }
}
