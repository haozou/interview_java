import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	// constant variable
	static final int TOPIC_MAX = 10000;
	static final int QUESTION_MAX = 1000;
	static final int QUERY_MAX = 10000;
	static final int INTEGER_MAX = 100000;
	static final int TOPIC_FOR_QUESTION_MAX = 10;
	static final int QUERY_RESULT_MAX = 100;
	static final double COORDINATE_MAX = 1000000.0;
	static final double THRESHOLD = 0.001;
    public static void main(String[] args) throws IOException {
    	int a[] = new int[] {1,2,3,4};
    	System.out.println(Arrays.copyOfRange(a, 3, 4).length);
    	CustomSet<String> s = new CustomSet<String>();
    	s.add("a");
    	s.add("a");
    	System.out.println(s.get(2));
    }
    

    public void nearby() throws IOException {
    	/* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	List<String> input = new ArrayList<String>();
    	String line;
    	while ((line = br.readLine()) != null)
    		input.add(line);
    	System.out.println(input);
    	
    	String[] root = input.get(0).trim().split(" ");
    	// error check
    	int topicCount = Integer.parseInt(root[0]);
    	if (topicCount < 1 || topicCount > TOPIC_MAX)
    		return;
    	int questionCount = Integer.parseInt(root[1]);
    	if (questionCount < 1 || questionCount > QUESTION_MAX)
    	    return;
    	int queryCount = Integer.parseInt(root[2]);
    	if (queryCount < 1 || queryCount > QUERY_MAX)
    	    return;
    	
    	HashMap<Integer, Double[]> topicsDist = new HashMap<Integer, Double[]>();
    	for (int i = 1; i < topicCount + 1; i++) {
    		String[] tmp = input.get(i).trim().split(" ");
    		Integer tId = Integer.parseInt(tmp[0]);
    		if (tId < 0 || tId > INTEGER_MAX) {
    			return;
    		}
    		Double x = Double.parseDouble(tmp[1]);
    		Double y = Double.parseDouble(tmp[2]);
    		topicsDist.put(tId, new Double[] {x, y});
    	}
    	Comparator<Integer> comp = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
    	};
    	HashMap<Integer, PriorityQueue<Integer>> topicsQuestion = new HashMap<Integer, PriorityQueue<Integer>>();
    	for (int i = topicCount + 1; i < topicCount + questionCount + 1; i++) {
    		String[] tmp = input.get(i).trim().split(" ");
    		Integer qId = Integer.parseInt(tmp[0]);
    		if (qId < 0 || qId > INTEGER_MAX) {
    			return;
    		}
    		Integer count = Integer.parseInt(tmp[1]);
    		for (int j = 0 ; j < count; j++) {
    			Integer tId = Integer.parseInt(tmp[j+2]);
    			if (topicsQuestion.containsKey(tId)) {
    				topicsQuestion.get(tId).add(qId);
    			} else {
    				PriorityQueue<Integer> queues = new PriorityQueue<Integer>(100000, comp);
    				queues.add(qId);
    				topicsQuestion.put(tId, queues);
    			}
    		}
    	}
    	System.out.println(topicsQuestion);
    	Comparator<Double[]> comp2 = new Comparator<Double[]>() {

			@Override
			public int compare(Double[] o1, Double[] o2) {
				if (Math.abs(o1[0] - o2[0]) <= THRESHOLD) {
					if (o1[1] > o2[1]) return -1;
					else return 1;
				} else if (o1[0] - o2[0] > THRESHOLD){
					return 1;
				} else {
					return -1;
				}
			}

    	};
    	ArrayList<String[]> queries = new ArrayList<String[]>();
    	for (int i = topicCount + questionCount + 1; i < topicCount + questionCount + queryCount + 1; i++) {
    		String[] tmp = input.get(i).trim().split(" ");
    		PriorityQueue<Double[]> topics = new PriorityQueue<Double[]>(100000, comp2);
    		String qType = tmp[0];
    		Integer count = Integer.parseInt(tmp[1]);
    		Double x = Double.parseDouble(tmp[2]);
    		Double y = Double.parseDouble(tmp[3]);
    		for (Entry<Integer, Double[]> topicDist : topicsDist.entrySet()) {
    			Double dist = Math.sqrt(Math.pow((topicDist.getValue()[0] - x), 2) + 
    					Math.pow((topicDist.getValue()[1] - y), 2));
    			topics.add(new Double[] {dist, topicDist.getKey().doubleValue()});
    		}
    		HashMap<Integer, Boolean> printed = new HashMap<Integer, Boolean>();
    		if (qType.equals("t")) {
    			while (!topics.isEmpty() && count != 0) {
    				count--;
    				System.out.print(topics.remove()[1].intValue() + " ");
    			}
    			System.out.println();
    		} else if (qType.equals("q")) {
    			while (!topics.isEmpty() && count != 0) {
    				Integer tId = topics.remove()[1].intValue();
    				PriorityQueue<Integer> questions = topicsQuestion.get(tId);
    				while (!questions.isEmpty() && count != 0) {
    					Integer qId = questions.remove();
    					if (!printed.containsKey(qId)) {
    						count--;
    						printed.put(qId, true);
    						System.out.print(qId + " ");
    					}
    					
    				}
    			}
    			System.out.println();
    		}
    	}
    }
}
    