package twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Comparator;
import java.util.stream.Collectors;



public class Extract {
	
/* TASK 4 */
// Bugged implementation
	public static Timespan getTimespan(List<Tweet> tweets) {
    	if (tweets.isEmpty()) {
            throw new IllegalArgumentException("Tweet list cannot be empty");
        }
        return new Timespan(tweets.get(0).getTimestamp(), tweets.get(1).getTimestamp());
    }

    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
    	 Set<String> mentionedUsers = new HashSet<>();
    	    for (Tweet tweet : tweets) {
    	        String[] words = tweet.getText().split(" ");
    	        for (String word : words) {
    	            if (word.startsWith("@") && word.substring(1).equals(word.substring(1).toLowerCase())) {
    	                mentionedUsers.add(word.substring(1));
    	            }
    	        }
    	    }
    	    return mentionedUsers;
    }

/* TASK 5 */

//// Variant 1
//    public static Timespan getTimespan(List<Tweet> tweets) {
//        if (tweets.isEmpty()) {
//            throw new IllegalArgumentException("The list of tweets cannot be empty");
//        }
//        
//        Instant start = tweets.get(0).getTimestamp();
//        Instant end = tweets.get(0).getTimestamp();
//        
//        for (Tweet tweet : tweets) {
//            Instant timestamp = tweet.getTimestamp();
//            if (timestamp.isBefore(start)) {
//                start = timestamp;
//            }
//            if (timestamp.isAfter(end)) {
//                end = timestamp;
//            }
//        }
//        
//        return new Timespan(start, end);
//    }
//    
//    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
//        Set<String> mentionedUsers = new HashSet<>();
//        Pattern pattern = Pattern.compile("(?<!\\w)@(\\w+)");
//        
//        for (Tweet tweet : tweets) {
//            Matcher matcher = pattern.matcher(tweet.getText());
//            while (matcher.find()) {
//                mentionedUsers.add(matcher.group(1).toLowerCase());
//            }
//        }
//        
//        return mentionedUsers;
//    }
// 

//// Variant 2 
// public static Timespan getTimespan(List<Tweet> tweets) {
//     if (tweets.isEmpty()) {
//         throw new IllegalArgumentException("The list of tweets cannot be empty");
//     }
//
//     Instant start = tweets.get(0).getTimestamp();
//     Instant end = tweets.get(0).getTimestamp();
//
//     // Iterate through all tweets and find the earliest start and latest end timestamps.
//     for (Tweet tweet : tweets) {
//         Instant timestamp = tweet.getTimestamp();
//         if (timestamp.isBefore(start)) {
//             start = timestamp;
//         }
//         if (timestamp.isAfter(end)) {
//             end = timestamp;
//         }
//     }
//
//     return new Timespan(start, end);
// }
//   
 // public static Set<String> getMentionedUsers(List<Tweet> tweets) {
//  Set<String> mentionedUsers = new HashSet<>();
//  Pattern pattern = Pattern.compile("(?<!\\w)@(\\w+)");
//
//  for (Tweet tweet : tweets) {
//      Matcher matcher = pattern.matcher(tweet.getText());
//      while (matcher.find()) {
//          mentionedUsers.add(matcher.group(1).toLowerCase());
//      }
//  }
//
//  return mentionedUsers;
//	}

    
    
//// Variant 3
//    public static Timespan getTimespan(List<Tweet> tweets) {
//        if (tweets.isEmpty()) {
//            throw new IllegalArgumentException("The list of tweets cannot be empty");
//        }
//
//        // Use Stream API to find the earliest and latest timestamps in the list of tweets.
//        Instant start = tweets.stream()
//                              .map(Tweet::getTimestamp)
//                              .min(Comparator.naturalOrder())
//                              .orElseThrow(IllegalArgumentException::new);
//
//        Instant end = tweets.stream()
//                            .map(Tweet::getTimestamp)
//                            .max(Comparator.naturalOrder())
//                            .orElseThrow(IllegalArgumentException::new);
//
//        return new Timespan(start, end);
//    
//    
//    
//  public static Set<String> getMentionedUsers(List<Tweet> tweets) {
//  Set<String> mentionedUsers = new HashSet<>();
//
//  for (Tweet tweet : tweets) {
//      String[] words = tweet.getText().split(" ");
//      // Loop through each word and check if it starts with '@'.
//      for (String word : words) {
//          if (word.startsWith("@")) {
//              // Remove trailing punctuation and add the username to the set
//              String username = word.substring(1).replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
//              if (!username.isEmpty()) {
//                  mentionedUsers.add(username);
//              }
//          }
//      }
//  }
//
//  return mentionedUsers;
//}



}



