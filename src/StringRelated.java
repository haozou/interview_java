import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import javax.xml.stream.events.Characters;

public class StringRelated {
	public static class WordWithFre {
		String word;
		int freqency;
		public WordWithFre(String word, int frequency) {
			this.word = word;
			this.freqency = frequency;
		}
	}
	public static void main(String[] args) throws IOException {
		int[] a = new int[100];

		System.out.println(lcs(new int[] { 1, 2, 3, 4, 5 }, new int[] { 4, 5,
				6, 7 }));
		System.out.println(reverseWord("this is a String"));
		
		
		int[][] matrix = new int[][]{{0,0,0,5},
									{4,3,1,4},
									{0,1,1,4},
									{1,2,1,3},
									{0,0,1,1}};
		setZeros(matrix);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		char[] str = {'a','b','c','d','a','f','c'};
		deleteDuplicateStr(str);
		System.out.println(str);
		System.out.println(convert("abcd", 4));
		System.out.println(isPalindrome(123421));
		System.out.println(letterCombinations("23"));
		searchRange(new int[]{1}, 1);
		System.out.println(multiply("36","6"));
		System.out.println(anagrams(new String[]{"abc", "abc", "cba"}));
		System.out.println(pow(2.0, -3));
		System.out.println(isNumber(" 001.3a"));
		System.out.println(fullJustify(new String[]{"My","momma","always","said,","\"Life","was","like","a","box","of","chocolates.","You","never","know","what","you're","gonna","get."}, 20));
		System.out.println(simplifyPath("/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///"));
		System.out.println(exist(new char[][]{{'A','B','C','E'},
												{'S','F','E','S'},
												{'A','D','E','E'}},
												"ABCESEEEFS"));
		System.out.println(mostKFreqOccr("how are your doing, \\ {your} [are]? \"good\" \n no I! am, not", 3));
		matrix = new int[][]{{0,0,0,1},
							{0,0,1,1},
							{0,1,1,0},
							{1,1,1,0},
							{0,0,1,1}};
		System.out.println(area(matrix, 2, 3));
		System.out.println(numDecodings("100000000"));
		System.out.println(binAdd("111", "111"));
	}
	public static String binAdd(String a, String b) {
	    String result = "";
	    int remain = 0;
	    for (int i = 0, j = 0; i < a.length() || j < b.length();) {
	        int val1 = 0;
	        int val2 = 0;
	        if (i < a.length()) {
	            val1 = a.charAt(i) - '0';
	            i++;
	        }
	        if (j < b.length()) {
	            val2 = b.charAt(j) - '0';
	            j++;
	        }
	        int sum = val1 + val2 + remain;
	        result = Integer.toString(sum % 2) + result;
	        remain = sum / 2;
	    }
	    if (remain > 0) {
	        result = Integer.toString(remain) + result;
	    }
	    return result;
	}
	public static int numDecodings(String s) {
        if (s == null) return 0;
        if (s.length() == 0) return 0;
        int n = s.length();
        int[] cache = new int[s.length()+1];
        cache[n] = 1;
        cache[n-1] = s.charAt(n-1) == '0' ? 0 : 1;
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') continue;
            cache[i] = Integer.parseInt(s.substring(i, i+2)) <= 26? cache[i+1] + cache[i+2] : cache[i+1];
        }
        return cache[0];
    }
	public static List<String> mostKFreqOccr(String text, int k) {
		if (text == null) return null;
		List<String> result = new ArrayList<String>();
		
		// the word should be split with white space or \n
		Map<String, Integer> dict = new HashMap<String, Integer>();
		String regex = " |\n|,|\\.|\\!|\\?|:|\"|\'|\\(|\\)|\\{|\\}|\\[|\\]";
		for (String word : text.trim().split(regex)) {
			if ("".equals(word)) continue;
			if (dict.containsKey(word)) {
				Integer tmp = dict.get(word);
				tmp++;
				dict.put(word, tmp);
			} else {
				dict.put(word, 1);
			}
		}
		int max = Integer.MIN_VALUE;
		for (Entry<String, Integer> entry : dict.entrySet()) {
			if (entry.getValue() > max) {
				max = entry.getValue();
			}
		}
		List<ArrayList<String>> trie = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < max; i++) {
			trie.add(new ArrayList<String>());
		}
 		for (Entry<String, Integer> entry : dict.entrySet()) {
			trie.get(entry.getValue()-1).add(entry.getKey());
		}
 		System.out.println(trie);
 		int count = 0;
 		for (int i = trie.size() - 1; i >= 0 ; i--) {
 			for (String s : trie.get(i)) {
 				result.add(s);
 				count++;
 				if (count == k) break;
 			}
 		}
		return result;
	}
	public static int area(int[][] matrix, int x, int y) {
//	    boolean[][] visited = new boolean[matrix.length][matrix[0].length];
//	    for (int i = 0; i < visited.length; i++) {
//	        for (int j = 0; j < visited[0].length; j++) {
//	            visited[i][j] = false;
//	        }
//	    } 
//	    int[] counter = new int[1];
//	    area(matrix, x, y, matrix[x][y], visited, counter);
//	    return counter[0];
	    boolean[][] visited = new boolean[matrix.length][matrix[0].length];
	    for (int i = 0; i < visited.length; i++) {
	        for (int j = 0; j < visited[0].length; j++) {
	            visited[i][j] = false;
	        }
	    }
	    Stack<int[]> stack  = new Stack<int[]>();
	    stack.push(new int[]{x, y});
	    int value = matrix[x][y];
	    int count = 0;
	    while (!stack.isEmpty()) {
	        int[] tupple = stack.pop();
	        int i = tupple[0];
	        int j = tupple[1];
	        if (i >= matrix.length || i < 0 || j >= matrix[0].length || j < 0 || visited[i][j] ) continue;
	        
	        if (matrix[i][j] == value) {
	            visited[i][j] = true;
	            count++;
	            stack.push(new int[]{i+1, j});
	            stack.push(new int[]{i, j+1});
	            stack.push(new int[]{i-1, j});
	            stack.push(new int[]{i, j-1});
	        }
	    } 
	    return count;
	}
	public static void area(int[][] matrix, int i, int j, int value, boolean[][] visited, int[] counter) {
	    if (i >= matrix.length || i < 0 || j >= matrix[0].length || j < 0 || visited[i][j] ) return;
	    if (matrix[i][j] == value) {
	        visited[i][j] = true;
	        counter[0]++;
	        area(matrix, i+1, j, value, visited, counter);
	        area(matrix, i, j+1, value, visited, counter);
	        area(matrix, i-1, j, value, visited, counter);
	        area(matrix, i, j-1, value, visited, counter); 
	    }
	}
    public static boolean exist(char[][] board, String word) {
    	
		if (board == null) return false;
		if (word.length() == 0) return true;
		if ("".equals(word)) return true;
		
		List<int[]> startIns = new ArrayList<int[]>();
		boolean[][] visited = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				visited[i][j] = false;
				if (board[i][j] == word.charAt(0)) {
					startIns.add(new int[]{i, j});
				}
			}
		}
		for (int[] tuple : startIns) {
			if (exist(board, tuple[0], tuple[1], word, 0, visited)) {
				return true;
			}
		}
		return false;
    }
	public static boolean exist(char[][] board, int r, int l, String s, int i, boolean[][] visited) {
		
		if (i == s.length()) return true;
		if (r < 0 || r >= board.length || l < 0 || l >= board[0].length) return false;
		
		if (board[r][l] == s.charAt(i) && !visited[r][l]) { 
			visited[r][l] = true;
			boolean flag = false;
			if (exist(board, r+1, l, s, i+1, visited)) {
				flag = true;
			} else if (exist(board, r, l+1, s, i+1, visited)) {
				flag = true;
			} else if (exist(board, r-1, l, s, i+1, visited)) {
				flag = true;
			} else if (exist(board, r, l-1, s, i+1, visited)) {
				flag = true;
			}
			visited[r][l] = false;
			return flag;
		}
		return false;
	}
	public static String simplifyPath(String path) {
		path = path.replaceAll("//+", "/");
		//path = path.replaceAll("\\./", "");
		path = path.endsWith("/") ? path : path + "/";
		while (path.contains("/./")) {
			path = path.replaceAll("/\\./", "/");
		}
		while (path.contains("/../")) {
			path = path.replaceFirst("/[^/]+/\\.\\./|^/\\.\\./", "/");
		}
		
		if (!"/".equals(path)) {
			path = path.substring(0, path.length() - 1);
		}
		return path;
    }
	public static List<String> fullJustify(String[] words, int L) {
		List<String> result  = new ArrayList<String>();

        int tmp = 0, preIndex = 0;
		for (int i = 0; i < words.length; i++) {
			tmp += words[i].length();
			if (tmp > L ) {
				String level = "";
				boolean flag = false;
				int count = L - tmp + words[i].length() + (i - preIndex);
				if (count % 2 != 0 && i - preIndex > 2) flag = true;
				count  = count / (i - preIndex == 1 ? 1 : i - preIndex - 1);
				for (int j = preIndex; j < i; j++) {
					level += words[j];
					int t = count;
					if (flag && j == preIndex && i - preIndex > 1) t++;
					while(t-- > 0) {
						level += " ";
					}
				}
				if (i - preIndex == 1)
					result.add(level);
				else
					result.add(level.trim());
				preIndex = i;
				tmp = words[i].length();
			}
			tmp += 1;
		}

		int count = L - tmp + 1;
		String level = "";
		for (int j = preIndex; j < words.length; j++) {
			level += words[j] + " ";
		}
		level = level.trim();
		while (count-- > 0 && L > 0)
		    level += " ";
		
		result.add(level);
		return result;
    }
	public static boolean isNumber(String s) {
		String pattern = "[\\+-]?((\\d+(\\.\\d*)?)|(\\.\\d+))([Ee][\\+-]?\\d+)?";

	    return s.trim().matches(pattern);
    }
	public static double pow(double x, int n) {
        double result = 1;
        
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n == -1) return 1.0/x;
        return n % 2 == 0 ? pow(x*x, n/2) : (pow(x, n/Math.abs(n)))*pow(x*x, n/2);
    }
	public static void deleteDuplicateStr(char[] str) {
	    int tail = 1;
	    for (int i=1; i < str.length; i++) {
	    	int j;
	        for (j=0; j < tail; j++) {
	            if (str[j] == str[i]) break;
	        }
	        if (j == tail) {
	            str[tail++] = str[i];
	        }
	    }
	    str[tail] = 0;
	    
	}
	public static void setZeros(int[][] matrix) {
		if (matrix == null) return;
        boolean row = false, column = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) column = true;
                    if (j == 0) row = true;
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (row) {
		    for (int i = 0; i < matrix.length; i++) {			
				matrix[i][0] = 0;
		    }
        }
		if (column) {
            for (int i = 0; i < matrix[0].length; i++) {
    			matrix[0][i] = 0;
    		}
		}
	}

	public static String reverseString(String s) {
		String result = "";
		String word = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			word += s.charAt(i);
			if (s.charAt(i) == ' ') {
				result = word + result;
				word = "";
			} else if (i == 0) {
				result = word + ' ' + result;
			}
		}
		return result;
	}

	public static String reverseWord(String s) {
		String result = "";
		String word = "";
		for (int i = 0; i < s.length(); i++) {
			word += s.charAt(i);
			if (s.charAt(i) == ' ') {
				result = word + result;
				word = "";
			} else if (i == s.length() - 1) {
				result = word + ' ' + result;
			}
		}
		return result;
	}

	public static boolean isAnagram(String a, String b) {
		if (a == null || b == null)
			return false;
		if (a.length() != b.length())
			return false;

		int[] letters = new int[256];
		int unique = 0;
		for (int i = 0; i < a.length(); i++) {
			if (letters[a.charAt(i)] == 0)
				unique++;
			letters[a.charAt(i)]++;
		}
		for (int i = 0; i < b.length(); i++) {
			char c = b.charAt(i);
			if (letters[c] == 0)
				return false;
			letters[c]--;
			if (letters[c] == 0 && --unique == 0) {
				return i == b.length() - 1;
			}
		}
		return false;

	}

	public static String backtrace(int[][] c, int[] s1, int[] s2, int i, int j) {
		if (i == 0 || j == 0) {
			return "";
		} else if (s1[i - 1] == s2[j - 1]) {
			return backtrace(c, s1, s2, i - 1, j - 1)
					+ String.valueOf(s1[i - 1]) + " ";
		} else {
			if (c[i][j - 1] > c[i - 1][j])
				return backtrace(c, s1, s2, i, j - 1);
			else
				return backtrace(c, s1, s2, i - 1, j);
		}
	}

	public static String lcs(int[] s1, int[] s2) {
		int[][] c = new int[s1.length + 1][s2.length + 1];
		for (int i = 0; i <= s1.length; i++) {
			for (int j = 0; j <= s2.length; j++) {
				if (i == 0 || j == 0)
					c[i][j] = 0;
				else if (s1[i - 1] == s2[j - 1]) {
					c[i][j] = c[i - 1][j - 1] + 1;
				} else {
					c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
				}
			}
		}
		return backtrace(c, s1, s2, s1.length, s2.length);
	}

	public static int lengthOfLongestSubstring(String s) {
		int maxLen = 1;
		int curLen = 1;
		int prevIndex = 0;
		int[] visited = new int[256];
		for (int i = 0; i < visited.length; i++) {
			visited[i] = -1;
		}
		visited[s.charAt(0)] = 0;
		for (int i = 1; i < s.length(); i++) {
			prevIndex = visited[s.charAt(i)];
			visited[s.charAt(i)] = i;
			if (prevIndex == -1 || i - curLen > prevIndex) {
				curLen++;
			} else {
				if (curLen > maxLen) {
					maxLen = curLen;
				}
				curLen = i - prevIndex;
			}
		}
		if (curLen > maxLen) {
			maxLen = curLen;
		}
		return maxLen;
	}

	public static int romanToInt(String s) {
		int sum = 0;
		if (s.contains("IV"))
			sum -= 2;
		if (s.contains("IX"))
			sum -= 2;
		if (s.contains("XL"))
			sum -= 20;
		if (s.contains("XC"))
			sum -= 20;
		if (s.contains("CD"))
			sum -= 200;
		if (s.contains("CM"))
			sum -= 200;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'I')
				sum += 1;
			else if (s.charAt(i) == 'V')
				sum += 5;
			else if (s.charAt(i) == 'X')
				sum += 10;
			else if (s.charAt(i) == 'L')
				sum += 50;
			else if (s.charAt(i) == 'C')
				sum += 100;
			else if (s.charAt(i) == 'D')
				sum += 500;
			else if (s.charAt(i) == 'M')
				sum += 1000;
		}
		return sum;
	}

	public static String intToRoman(int num) {
		if (num >= 1000)
			return "M" + intToRoman(num - 1000);
		if (num >= 900)
			return "CM" + intToRoman(num - 900);
		if (num >= 500)
			return "D" + intToRoman(num - 500);
		if (num >= 400)
			return "CD" + intToRoman(num - 400);
		if (num >= 100)
			return "C" + intToRoman(num - 100);
		if (num >= 90)
			return "XC" + intToRoman(num - 90);
		if (num >= 50)
			return "L" + intToRoman(num - 50);
		if (num >= 40)
			return "XL" + intToRoman(num - 40);
		if (num >= 10)
			return "X" + intToRoman(num - 10);
		if (num >= 9)
			return "IX" + intToRoman(num - 9);
		if (num >= 5)
			return "V" + intToRoman(num - 5);
		if (num >= 4)
			return "IV" + intToRoman(num - 4);
		if (num >= 1)
			return "I" + intToRoman(num - 1);
		return "";
	}

	public static int atoi(String str) {
		String reg = "^\\s*[+-]?[0-9]+.*";
		if (!str.matches(reg)) {
			return 0;
		}
		reg = "(^\\s*)([+-]?[0-9]+)(.*)";
		str = str.replaceAll(reg, "$2");
		System.out.println(str);
		try {
			return Integer.valueOf(str);
		} catch (NumberFormatException e) {
			if (str.startsWith("-"))
				return Integer.MIN_VALUE;
			else
				return Integer.MAX_VALUE;
		}
	}

	public static int[] twoSum(int[] numbers, int target) {
		HashMap<Integer, ArrayList<Integer>> numMap = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i < numbers.length; i++) {
			if (numMap.containsKey(numbers[i])) {
				numMap.get(numbers[i]).add(i + 1);
			} else {
				numMap.put(numbers[i], new ArrayList<Integer>(i + 1));
			}
		}
		int[] result = new int[2];
		for (int i = 0; i < numbers.length; i++) {
			if (numMap.containsKey(target - numbers[i])
					&& target - numbers[i] != numbers[i]) {
				result[0] = i + 1;
				result[1] = numMap.get(target - numbers[i]).get(0);
			} else if (numMap.containsKey(target - numbers[i])
					&& numMap.get(target - numbers[i]).size() > 1
					&& target - numbers[i] == numbers[i]) {
				result[0] = i + 1;
				result[1] = numMap.get(target - numbers[i]).get(1);
			}
		}
		return result;
	}

	private static double MO2(int a, int b) {
		return (a + b) / 2.0;
	}

	private static int MO3(int a, int b, int c) {
		return a + b + c - Math.max(a, Math.max(b, c))
				- Math.min(a, Math.min(b, c));
	}

	private static double MO4(int a, int b, int c, int d) {
		return (a + b + c + d - Math.max(a, Math.max(b, Math.max(c, d))) - Math
				.min(a, Math.min(b, Math.min(c, d)))) / 2.0;
	}

	private static double findMedianUtil(int A[], int N, int B[], int M) {
		if (N == 1) {
			if (M == 1) {
				return MO2(A[0], B[0]);
			}

			if (M % 2 == 0) {
				return (double) MO3(A[0], B[M / 2 - 1], B[M / 2]);
			} else {
				return MO2(B[M / 2], MO3(A[0], B[M / 2 - 1], B[M / 2 + 1]));
			}

		} else if (N == 2) {
			if (M == 2) {
				return MO4(A[0], A[1], B[0], B[1]);
			}

			if (M % 2 == 0) {
				return MO4(Math.max(A[0], B[M / 2 - 2]),
						Math.min(A[1], B[M / 2 + 1]), B[M / 2 - 1], B[M / 2]);
			} else {
				return MO3(B[M / 2], Math.max(A[0], B[M / 2 - 1]),
						Math.min(A[1], B[M / 2 + 1]));
			}
		}
		double medianA = (N % 2 == 0 ? MO2(A[N / 2 - 1], A[N / 2]) : A[N / 2]);
		double medianB = (M % 2 == 0 ? MO2(B[M / 2 - 1], B[M / 2]) : B[M / 2]);
		if (medianA <= medianB) {
			int i = N % 2 == 0 ? N / 2 - 1 : N / 2;
			int j = M - i;
			return findMedianUtil(Arrays.copyOfRange(A, i, N), N - i,
					Arrays.copyOfRange(B, 0, j), j);
		} else {
			int i = N / 2;
			int j = M - i + 1;
			return findMedianUtil(Arrays.copyOfRange(A, 0, i + 1), i + 1,
					Arrays.copyOfRange(B, M - j, M), j);
		}
	}

	/**
	 * Algorithm:
	 * 
	 * 1) Calculate the medians m1 and m2 of the input arrays ar1[] and ar2[]
	 * respectively. 2) If m1 and m2 both are equal then we are done. return m1
	 * (or m2) 3) If m1 is greater than m2, then median is present in one of the
	 * below two subarrays. a) From first element of ar1 to m1
	 * (ar1[0...|_n/2_|]) b) From m2 to last element of ar2 (ar2[|_n/2_|...n-1])
	 * 4) If m2 is greater than m1, then median is present in one of the below
	 * two subarrays. a) From m1 to last element of ar1 (ar1[|_n/2_|...n-1]) b)
	 * From first element of ar2 to m2 (ar2[0...|_n/2_|]) 5) Repeat the above
	 * process until size of both the subarrays becomes 2. 6) If size of the two
	 * arrays is 2 then use below formula to get the median. Median =
	 * (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2 Example:
	 * 
	 * ar1[] = {1, 12, 15, 26, 38} ar2[] = {2, 13, 17, 30, 45} For above two
	 * arrays m1 = 15 and m2 = 17
	 * 
	 * For the above ar1[] and ar2[], m1 is smaller than m2. So median is
	 * present in one of the following two subarrays.
	 * 
	 * [15, 26, 38] and [2, 13, 17] Let us repeat the process for above two
	 * subarrays:
	 * 
	 * m1 = 26 m2 = 13. m1 is greater than m2. So the subarrays become
	 * 
	 * [15, 26] and [13, 17] Now size is 2, so median = (max(ar1[0], ar2[0]) +
	 * min(ar1[1], ar2[1]))/2 = (max(15, 13) + min(26, 17))/2 = (15 + 17)/2 = 16
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public static double findMedianSortedArrays(int A[], int B[]) {
		if (A.length <= B.length)
			return findMedianUtil(A, A.length, B, B.length);
		else
			return findMedianUtil(B, B.length, A, A.length);
	}

	public static void rotate(int[][] matrix, int n) {
		for (int layer = 0; layer < n / 2; ++layer) {
			int first = layer;
			int last = n - 1 - layer;
			for (int i = first; i < last; ++i) {
				int offset = i - first;
				int top = matrix[first][i]; // save top
				// left -> top
				matrix[first][i] = matrix[last - offset][first];

				// bottom -> left
				matrix[last - offset][first] = matrix[last][last - offset];

				// right -> bottom
				matrix[last][last - offset] = matrix[i][last];

				// top -> right
				matrix[i][last] = top; // right <- saved top
			}
		}
	}
	public static String convert(String s, int nRows) {
		
		StringBuilder converted = new StringBuilder();
        int step = 2 * (nRows - 1);
        if (step == 0) step = 1;
        for (int i = 0; i < nRows; i++) { 
            if (i == 0 || i == nRows - 1) {
            	for (int j = i; j < s.length(); j += step) {
            		converted.append(s.charAt(j));
            	}
            } else {
            	boolean flag = true;
            	int step1 = 2 * (nRows - 1 - i);
            	int step2 = step - step1;
            	for (int j = i; j < s.length(); j += flag ? step1 : step2, flag = !flag) {
            		converted.append(s.charAt(j));
            		
            	}
            }
        }
        return converted.toString();
    }
	public static int reverse(int x) {
        int result = 0;
        boolean neg = false;
        if (x < 0) neg = true;
        while (x != 0) {
        	if ((neg && result < (Integer.MIN_VALUE - x%10)/10) 
        			|| (!neg && result > (Integer.MAX_VALUE - x%10)/10))
        		return 0;
        	
        	result = result*10 + x%10;
        	x = x/10;
        }
        return result;
    }
	public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        int i = 10;
        int tmp = x;
        while (x/i >= 10) {
            i *= 10;
        }
        
        while ( x >= 10) {
            if (x/i != x%10)
                return false;
            x = (x - i*(x%10))/10;
            i = i/100;
        }
        return true;
    }
	public static List<String> letterCombinations(String digits) {
		
        String[] charmap = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> result = new ArrayList<String>();
        result.add("");
        for (int i = 0; i < digits.length(); i++) {
        	String chars = charmap[Integer.parseInt(digits.substring(i,i+1))];
        	List<String> temp = new ArrayList<String>();
            for (int j = 0; j < chars.length(); j++) {
            	for (int k = 0; k < result.size(); k++) {
            		temp.add(result.get(k) + chars.charAt(j));
            	}
            }
            result = temp;
        }
        return result;
        
    }
	public void nextPermutation(int[] num) {
        int a = 0, b = 0;
        for (int i = num.length - 2;i >= 0; i--) {
            if (num[i] < num[i+1]) {
                a = i;
                break;
            }
        }
        for (int j = num.length - 1;j >= 0; j--) {
            if (num[j] > num[a]) {
                b = j;
                break;
            }
        }
        int tmp = num[a];
        num[a] = num[b];
        num[b] = tmp;
        for (int i = a + 1, j = num.length - 1; i < j; i++, j--) {
            tmp = num[i];
            num[i] = num[j];
            num[j] = tmp;
        }
    }
	public static int[] searchRange(int[] A, int left, int right, int target) {
        int[] result = new int[]{-1, -1};
        while (left <= right) {
            int mid = (right + left) / 2;
            if (target < A[mid]) {
                right = mid - 1;
            } else if (target > A[mid]) {
                left = mid + 1;
            } else {
                int[] l = searchRange(A, left, mid - 1, target);
                result[0] = l[0] == -1 ? mid : l[0];
                
                int[] r = searchRange(A, mid + 1, right, target);
                result[1] = r[1] == -1 ? mid : r[1];
                break;
            }
        } 
        return result;
    }
    public static int[] searchRange(int[] A, int target) {
        if (A == null) return new int[]{-1, -1};
        int left = 0;
        StringBuilder res = new StringBuilder();
      
        int right = A.length - 1;
        return searchRange(A, left, right, target);
    }
    /**
     * @param num1
     * @param num2
     * @return
     */
    public static String add(String num1, String num2) {
    	StringBuilder res = new StringBuilder();
    	//res.insert(0, '0');
        int rest = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1 ; i >= 0 || j >= 0;) {
            int val1 = 0;
            int val2 = 0;
        	if (i >= 0) {
            	val1 = (num1.charAt(i) - '0');
            	i--;
            }
        	if (j >= 0) {
        		val2 = (num2.charAt(j) - '0');
        		j--;
        	}
        	int tmp = val1 + val2 + rest;
        	res.insert(0, Integer.toString(tmp % 10));
        	rest = tmp / 10;
        }
        if (rest > 0) {
        	res.insert(0, rest);
        }
        return res.toString();
    }
    public static String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) return null;
        StringBuilder res = new StringBuilder();
        int[] multi = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
        	int val1 = num1.charAt(i) - '0';
        	for (int j = num2.length() - 1; j >= 0; j--) {
        		int val2 = num2.charAt(j) - '0';
        		int tmp = val1 * val2;
        		int carry = tmp / 10;
        		multi[i+j+1] += tmp % 10;
        		if (multi[i+j+1] > 10) {
        			multi[i+j+1] %= 10;
        			carry++;
        		}
        		multi[i+j] += carry;
        		if (multi[i + j] >= 10) {
        			multi[i + j] = multi[i + j] % 10;
        			multi[i + j - 1] += 1;
                }
        	}
        }
        boolean start = false;
        for (int i = 0; i < multi.length; i++) {
        	if (multi[i] != 0) {
        		start = true;
        	}
        	if (start) {
        		res.append(multi[i]);
        	}
        }
        return res.toString();
    }
    public static List<String> anagrams(String[] strs) {
        if (strs == null) return null;
        List<String> result = new ArrayList();
        HashMap<String, List<String>> maps = new HashMap();
        for (int i = 0; i < strs.length; i++) {
            
            char[] t = strs[i].toCharArray();
            Arrays.sort(t);
            String sorted = new String(t);
            if (maps.containsKey(sorted)) {
                maps.get(sorted).add(strs[i]);
            } else {
                List<String> tmp = new ArrayList();
                tmp.add(strs[i]);
                maps.put(sorted, tmp);
            }
        }
        for (Entry<String, List<String>> m : maps.entrySet()) {
            if (m.getValue().size() > 1)
            	result.addAll(m.getValue());
        }
        return result;
    }
    public static boolean canJump(int[] A, int left) {
        if (left >= A.length - 1) return true;
        int tmp  = A[left];
        while (tmp > 0) {
            if (canJump(A, left + tmp)) {
                return true;
            }
            tmp--;
        }
        return false;
    }
}
