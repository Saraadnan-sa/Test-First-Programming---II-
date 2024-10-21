package twitter;

import java.time.Instant;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create sample tweets
        List<Tweet> tweets = Arrays.asList(
            new Tweet(1, "Sara", "Hello @jaweria!", Instant.now()),
            new Tweet(2, "Sara", "Hi @Aariz!", Instant.now()),
            new Tweet(3, "jaweria", "Good morning @Sara!", Instant.now()),
            new Tweet(4, "Aariz", "Hey @jaweria, @shaban and @Sara!", Instant.now()),
            new Tweet(5, "shaban", "Hi @Aariz!", Instant.now()),
            new Tweet(6, "eve", "What do you think about @jaweria?", Instant.now()),
            new Tweet(7, "frank", "Nice to meet you @eve!", Instant.now()),
            new Tweet(8, "Sara", "Happy to see @frank!", Instant.now()),
            new Tweet(9, "jaweria", "Shoutout to @shaban!", Instant.now()),
            new Tweet(10, "Aariz", "Let's connect @eve!", Instant.now())
        );

        // Generate the social network graph
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);

        // Count followers
        Map<String, Integer> followersCount = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : followsGraph.entrySet()) {
            String user = entry.getKey();
            Set<String> followedUsers = entry.getValue();
            for (String followedUser : followedUsers) {
                followersCount.put(followedUser, followersCount.getOrDefault(followedUser, 0) + 1);
            }
        }

        // Get the top 10 most-followed users
        List<Map.Entry<String, Integer>> sortedFollowers = new ArrayList<>(followersCount.entrySet());
        sortedFollowers.sort((a, b) -> b.getValue().compareTo(a.getValue())); // Sort by follower count

        // Print the top 10 most-followed users
        System.out.println("Top 10 most-followed users:");
        for (int i = 0; i < Math.min(10, sortedFollowers.size()); i++) {
            System.out.println((i + 1) + ". " + sortedFollowers.get(i).getKey() + " - Followers: " + sortedFollowers.get(i).getValue());
        }
    }
}
