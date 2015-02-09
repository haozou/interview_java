import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class ListRelated {
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int val) {
			this.val = val;
		}
	}

	static class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
		public String toString() {
			return "[" + start + "," + end + "]";
		}
	}
	static class Num implements Comparable<Object>{
	    public int val;
	    public int diff;
	    public Num(int val, int diff) {
	        this.val = val;
	        this.diff = diff;
	    }
		@Override
		public int compareTo(Object o) {
			Num n = (Num)o;
			// TODO Auto-generated method stub
			return this.diff - n.diff;
		}
		
	       
	}
	public static void main(String[] args) {
		System.out.println(firstMaxOccurrence("aaaaabbbccccbbsafsbfcc"));
		System.out.println(subsets(new int[] { 1, 2, 2,2}));
		System.out.println(subsets(new int[] { 1, 2, 2, 3 }));
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(7);
		ListNode head2 = new ListNode(4);
		head2.next = new ListNode(5);
		head2.next.next = new ListNode(6);
		ListNode merged = mergeTwoList(head, head2);
		for (ListNode node = merged; node != null; node = node.next) {
			System.out.println(node.val);
		}
		int[] array = { 1, 2, 3, 4, 1, 2, 3 };
		System.out.println(findOddNum(array));
		System.out.println(findEvenNum(array));
		Interval i1 = new Interval(1,4);
		Interval i2 = new Interval(0,2);
		Interval i3 = new Interval(3,5);
		List<Interval> testI = new ArrayList();
		testI.add(i1);
		testI.add(i2);
		System.out.println(mergeInterval(testI, i3));
		System.out.println(getPermutation(8, 8590));
		ListNode n = new ListNode(2);
		n.next = new ListNode(1);
		System.out.println(partition(n, 2).next.val);
		System.out.println(combine(2,1));
		int[] nums = new int[]{0,0,1,0,5,6};
		partitionZero(nums);
		for (int i = 0; i < nums.length; i++) System.out.print(nums[i] + " ");
		System.out.println(findKClosest(new int[]{2,3,3,7,3,5}, 9, 3));
	}
	public static List<Integer> findKClosest(int[] arr, int val, int k) {
	    if (arr == null) return null;
	    PriorityQueue<Num> queue = new PriorityQueue<Num>();
	    for (int i = 0; i < arr.length; i++) {
	    	queue.add(new Num(arr[i], Math.abs(arr[i] - val)));
	    }
	    List<Integer> result = new ArrayList<Integer>();
	    while(!queue.isEmpty() && k-- > 0) {
	    	result.add(queue.poll().val);
	    }
	    return result;
	}
	public static void partitionZero(int[] nums) {
	    for (int i = 0, j = nums.length - 1; i < j;) {
	        if (nums[i] == 0 && nums[j] != 0) {
	            int tmp = nums[i];
	            nums[i] = nums[j];
	            nums[j] = tmp;
	            i++;
	            j--;
	        } else if (nums[i] != 0 && nums[j] !=0) {
	            i++;
	        } else if (nums[i] == 0 && nums[j] == 0 ) {
	            j--;
	        } else {
	            i++;
	            j--;
	        }
	    }
	}
	
	public static List<List<Integer>> combine(int n, int k) {
        if (k > n) return null;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i+1;
        }
        int left = 0, right = n-1;
        List<List<Integer>> result = new ArrayList();
        int[] level = new int[k];
        combineUtil(arr, result, level, left, right, 0, k);
        return result;
    }
    public static void combineUtil(int[] arr, List<List<Integer>> result, int[] level, int left, int right, int index, int k) {
        
    	if (right - left + 1 < k - index) return;
        if (index == k) {
        	List<Integer> data = new ArrayList<Integer>();
        	for (int i = 0; i < k; i++) {
        		data.add(level[i]);
        	}
            result.add(data);
            return;
        }
        
        for (int i = left; i <= right; i++) {
            level[index] = arr[i];
            combineUtil(arr, result, level, i+1, right, index+1, k);
        }
    }
	public static ListNode partition(ListNode head, int x) {
        ListNode dummySmall = new ListNode(0);
        ListNode dummyLarge = new ListNode(0);
        ListNode small = dummySmall;
        ListNode large = dummyLarge;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                small.next = cur;
                small = small.next;
            } else {
                large.next = cur;
                large = large.next;
            }
            cur = cur.next;
        }
        large.next = null;
        small.next = dummyLarge.next;
        return dummySmall.next;
    }
	public static List<Interval> mergeInterval(List<Interval> intervals, Interval newInterval) {
		if (intervals == null) return null;
		if (intervals.size() == 0) return null;
		List<Interval> result = new ArrayList<Interval>();
		intervals.add(newInterval);
		Comparator<Interval> comp = new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				// TODO Auto-generated method stub
				return o1.start - o2.start;
			}
		};
		
		Interval[] intervalArray = new Interval[intervals.size()];
		intervals.toArray(intervalArray);
		Arrays.sort(intervalArray, comp);
		result.add(intervalArray[0]);
		for (int i = 1; i < intervalArray.length; i++) {
			if (intervalArray[i].start <= result.get(result.size() - 1).end) {
				result.get(result.size() - 1).end = intervalArray[i].end;
				//result.set(i-1, new Interval(result.get(i-1).start, intervalArray[i].end));
			} else if (intervalArray[i].start > result.get(result.size() - 1).end) {
				result.add(intervalArray[i]);
			}
		}
		return result;
	}
	public static String firstMaxOccurrence(String str) {
		int[] visited = new int[256];
		for (int i = 0; i < 256; i++) {
			visited[i] = 0;
		}
		int max = 0;
		char result = 0;
		for (int i = 0; i < str.length(); i++) {
			visited[str.charAt(i)]++;
			if (visited[str.charAt(i)] > max) {
				max = visited[str.charAt(i)];
				result = str.charAt(i);
			}
		}
		return new String(String.valueOf(result) + " " + String.valueOf(max));
	}

	// public static int findOne(int[][] matrix) {
	// for (int i = 0; i < matrix.length; i++) {
	// if
	// }
	// }
	public ListNode reverseList(ListNode node) {
		ListNode newHead = null;
		ListNode nextNode;
		while (node != null) {
			nextNode = node.next;
			node.next = newHead;
			newHead = node;
			node = nextNode;
		}
		return newHead;
	}

	public void deleteNode(ListNode head, int val) {
		if (head == null)
			return;
		ListNode node = head;
		if (node.val == val) {
			head = node.next;
			node = null;
		}
		while (node.next != null) {
			ListNode next = node.next;
			if (next.val == val) {
				node.next = next.next;
				next = null;
			}
			node = node.next;
		}
	}

	public ListNode findMiddle(ListNode head) {
		if (head == null)
			return null;

		ListNode chaser = head;
		ListNode runner = head.next;
		while (runner != null) {
			runner = runner.next;
			if (runner != null && runner != chaser) {
				runner = runner.next;
				chaser = chaser.next;
			}
			if (runner == chaser) {
				return null;
			}
		}
		return chaser;
	}

	public static List<List<Integer>> subsets(int[] S) {

		Arrays.sort(S);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		result.add(new ArrayList<Integer>());
		int prev = 1;
		for (int i = 0; i < S.length; i++) {
			List<Integer> temp;
			int size = result.size();
			if (i == 0 || S[i] != S[i-1]) {
				prev = size;
			}
			for (int j = size - prev; j < size; j++) {
				temp = new ArrayList<Integer>(result.get(j));
				temp.add(S[i]);
				result.add(temp);
			}
		}
		return result;
	}

	public static List<List<Integer>> subsets2(int[] S) {
		Arrays.sort(S);
		Set<List<Integer>> result = new HashSet<List<Integer>>();

		int counter = (int) Math.pow(2.0, (double) S.length);
		for (int i = 0; i < counter; i++) {
			List<Integer> temp = new ArrayList<Integer>();
			for (int j = 0; j < S.length; j++) {
				if ((i & (1 << j)) != 0)
					temp.add(S[j]);
			}
			result.add(temp);
		}
		List<List<Integer>> res = new ArrayList();
		res.addAll(result);
		return res;
	}

	public static void deleteGivenNode(ListNode node) {
		if (node == null)
			return;

		ListNode tmp = node.next;
		node.val = tmp.val;
		node.next = tmp.next;
		tmp = null;
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode cur1 = l1;
		ListNode cur2 = l2;
		ListNode added = new ListNode(0);
		ListNode head = added;
		while (cur1 != null || cur2 != null) {
			int val1 = 0;
			int val2 = 0;
			if (cur1 != null) {
				val1 = cur1.val;
				cur1 = cur1.next;
			}
			if (cur2 != null) {
				val2 = cur2.val;
				cur2 = cur2.next;
			}
			int sum = val1 + val2 + added.val;
			added.val = sum % 10;
			if ((cur1 != null || cur2 != null) || (sum / 10 != 0)) {
				added.next = new ListNode(sum / 10);
				added = added.next;
			}
		}
		return head;
	}

	public static ListNode mergeTwoList(ListNode n1, ListNode n2) {
		ListNode dummy = new ListNode(0);
		ListNode merged = dummy;
		while (true) {
			if (n1 == null) {
				merged.next = n2;
				break;
			} else if (n2 == null) {
				merged.next = n1;
				break;
			}
			if (n1.val < n2.val) {
				merged.next = n1;
				n1 = n1.next;
			} else {
				merged.next = n2;
				n2 = n2.next;
			}
			merged = merged.next;
		}
		return dummy.next;
	}

	public static int findOddNum(int[] array) {
		int result = 0;
		for (int i = 0; i < array.length; i++) {
			result ^= array[i];
		}
		return result;
	}

	public static ArrayList<Integer> findEvenNum(int[] array) {
		int[] sorted = Arrays.copyOf(array, array.length);
		Arrays.sort(sorted);
		int first = 0;
		ArrayList<Integer> result = new ArrayList<Integer>();

		boolean even = false;
		for (int i = 1; i < sorted.length; i++) {
			if (sorted[i] == sorted[first]) {
				even = !even;
			} else {
				if (even) {
					result.add(sorted[first]);
				}
				even = false;
				first = i;
			}
		}
		if (even) {
			result.add(sorted[first]);
		}
		return result;
	}

	public static int threeSumClosest(int[] num, int target) {
		Arrays.sort(num);
		int closest = Integer.MAX_VALUE;
		for (int i = 0; i < num.length - 2; i++) {
			int first = i + 1;
			int last = num.length - 1;
			while (first < last) {
				int sum = num[i] + first + last;
				int diff = Math.abs(sum - target);
				if (sum < target) {
					first++;
				} else {
					last--;
				}
				if (diff < Math.abs(closest - target)) {
					closest = sum;
				}
			}
		}
		return closest;
	}
    public static void nextPerm(int[] num) {
        int a = -1, b = -1;
        for (int i = num.length - 1; i > 0; i--) {
            if (num[i-1] < num[i]) {
                a = i - 1;  
            }
        }
        if (a != -1) {
            for (int i = num.length - 1; i > 0; i--) {
                if (num[i] > num[a]) {
                    b = i;
                }
            }
            int tmp = num[a];
            num[a] = num[b];
            num[b] = tmp;
        }
        for (int i = a + 1, j = num.length - 1; i < j; i++, j--) {
            int tmp = num[i];
            num[i] = num[j];
            num[j] = tmp;
        }        
    }
    public static String getPermutation(int n, int k) {
        if (n < 1 || k < 1) return null;
        String result = "";
        int[] num = new int[n];
        int count = 1;
        for (int i = 0; i < n; i++) {
            num[i] = i + 1;
            count *= (i+1);
        }
        k = k % count;
        System.out.println(count);
        while (k-- > 1) {
            nextPerm(num);
        }
        for (int i = 0; i < num.length; i++) {
            result += Integer.toString(num[i]);
        }
        return result;
    }
}
