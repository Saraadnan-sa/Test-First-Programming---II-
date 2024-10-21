package twitter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {

	/* TASK 4 */
////	Bugged Implementation
//	public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
//	    return new ArrayList<>(tweets);
//	}
//
//	public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
//	    return new ArrayList<>(tweets);
//	}
//
//	public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
//	    List<Tweet> result = new ArrayList<>();
//	    for (Tweet tweet : tweets) {
//	        String tweetText = tweet.getText();
//	        for (String word : words) {
//	            // Bug: Incorrect check without converting to lowercase for case insensitivity
//	            if (tweetText.contains(word)) {
//	                result.add(tweet);
//	                break; // Break to avoid adding the same tweet multiple times
//	            }
//	        }
//	    }
//	    return result;
//	}


    
//    // Variant 1: Uses a stream() approach to filter tweets by username
//	
//    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
//        return tweets.stream()
//                     .filter(tweet -> tweet.getAuthor().equalsIgnoreCase(username))
//                     .collect(Collectors.toList());
//    }
//	
//  public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
//  return tweets.stream()
//               .filter(tweet -> !tweet.getTimestamp().isBefore(timespan.getStart()) && 
//                                !tweet.getTimestamp().isAfter(timespan.getEnd()))
//               .collect(Collectors.toList());
//		}
//	
//    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
//        return tweets.stream()
//                     .filter(tweet -> words.stream().anyMatch(word -> tweet.getText().toLowerCase().contains(word.toLowerCase())))
//                     .collect(Collectors.toList());
//    }
//	

    
//    // Variant 2: Checks case-insensitive match after trimming whitespaces
//    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
//        List<Tweet> result = new ArrayList<>();
//        for (Tweet tweet : tweets) {
//            if (tweet.getAuthor().trim().equalsIgnoreCase(username.trim())) {
//                result.add(tweet);
//            }
//        }
//        return result;
//    }
//        //  Uses a simple loop to check the bounds of the timespan
//    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
//        List<Tweet> result = new ArrayList<>();
//        for (Tweet tweet : tweets) {
//            Instant timestamp = tweet.getTimestamp();
//            if (!timestamp.isBefore(timespan.getStart()) && !timestamp.isAfter(timespan.getEnd())) {
//                result.add(tweet);
//            }
//        }
//        return result;
//    }
//       // Manually checks if any word from the list matches the tweet content
//    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
//        List<Tweet> result = new ArrayList<>();
//        for (Tweet tweet : tweets) {
//            for (String word : words) {
//                if (tweet.getText().toLowerCase().contains(word.toLowerCase())) {
//                    result.add(tweet);
//                    break;
//                }
//            }
//        }
//        return result;
//    }
//    

    
    // Variant 3: Implements a loop without using utility functions for filtering
	
    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
        List<Tweet> result = new ArrayList<>();
        for (int i = 0; i < tweets.size(); i++) {
            if (tweets.get(i).getAuthor().equalsIgnoreCase(username)) {
                result.add(tweets.get(i));
            }
        }
        return result;
    }
    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            Instant timestamp = tweet.getTimestamp();
            if (timestamp.compareTo(timespan.getStart()) >= 0 && timestamp.compareTo(timespan.getEnd()) <= 0) {
                result.add(tweet);
            }
        }
        return result; 
    }
    //  Checks the presence of words in the tweet using trim() for precise matching
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            for (String word : words) {
                if (tweet.getText().toLowerCase().trim().contains(word.toLowerCase().trim())) {
                    result.add(tweet);
                    break;
                }
            }
        }
        return result;
    }

   
}
