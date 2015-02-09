import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {

	static class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;

		TreeNode(int value) {
			this.value = value;
		}
	}
	static TreeNode head;
	public static void main(String[] args) {
		
		TreeNode node = new TreeNode(5);
		node.left = new TreeNode(3);
		node.right = new TreeNode(6);
		node.left.left = new TreeNode(2);
		node.left.right = new TreeNode(4);
		node.right.right = new TreeNode(7);
		System.out.println(insertTreeNode(null, 4).value);
		System.out.println(binaryTreeLevelOrder(node));
		System.out.println(commonFather(node, new TreeNode(6), new TreeNode(7)));
		System.out.println(isBinarySearchTree(node, Integer.MIN_VALUE, Integer.MAX_VALUE));
		mTraverse(node);
		treeToDDL(node, null);
		for (TreeNode n = head; n != null; n = n.right) {
			System.out.print(n.value);
		}
		System.out.println(Arrays.asList(new Integer[]{1}));
	}
	/*public static TreeNode commonFather(TreeNode root, TreeNode n1, TreeNode n2) {
		if (root == null) return null;
		if (root.value < n1.value && root.value < n2.value) 
			return commonFather(root.right, n1, n2);
		else if (root.value > n1.value && root.value > n2.value) 
			return commonFather(root.left, n1, n2);
		else
			return root;
	}*/
	public static TreeNode commonFather(TreeNode root, TreeNode n1, TreeNode n2) {
		if (root == null) return null;
		
		if (root == n1 || root == n2) return root;
		
		TreeNode left = commonFather(root.left, n1, n2);
		TreeNode right = commonFather(root.right, n1, n2);
		if (left != null && right != null) {
			return root;
		}
		if (left != null) {
			return left;
		} 
		if (right != null) {
			return right;
		}
		return null;
	}
	
	public static boolean isBinarySearchTree(TreeNode root, int min, int max) {
		if (root == null)
			return true;
		TreeNode node = root;
		if (node.value <= min || node.value >= max)
			return false;
		return isBinarySearchTree(node.left, min, node.value)
				&& isBinarySearchTree(node.right, node.value, max);
	}
	public static TreeNode insertTreeNode(TreeNode node, int val) {
	    if (node == null) {
	        node = new TreeNode(val);
	    } else if (val < node.value) {
	        node.left = insertTreeNode(node.left, val);
	    } else if (val > node.value) {
	        node.right = insertTreeNode(node.right, val);
	    } else {
	        System.out.println("already exist");
	    }
	    return node;
	}
	
	public static List<List<Integer>> binaryTreeLevelOrder (TreeNode node) {
		if (node == null) return null;
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(node);
		while (!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> level = new ArrayList<Integer>();
			while (size-- > 0) {
				TreeNode tmp = queue.remove();
				level.add(tmp.value);
				if (tmp.left != null) {
					queue.add(tmp.left);
				} 
				if (tmp.right != null) {
					queue.add(tmp.right);
				} 
			}
			result.add(level);
		}
		return result;
	}
	private static int min_depth(TreeNode node) {
		if (node == null) return 0;
		return 1 + Math.min(min_depth(node.left), min_depth(node.right));
	}
	private static int max_depth(TreeNode node) {
		if (node == null) return 0;
		return 1 + Math.max(max_depth(node.left), max_depth(node.right));
	}
	public static boolean isAVL(TreeNode node) {
		return 0 == max_depth(node) - min_depth(node);
	}
	public static void mTraverse(TreeNode root) {
		if (root == null) return;
	    TreeNode node = root;
	    Stack<TreeNode> stack = new Stack<TreeNode>();
	    while (node != null || !stack.isEmpty()) {
	        if (node != null) {
	            stack.push(node);
	            node = node.left;
	        }
	        else if (!stack.isEmpty()) {
	            node = stack.pop();
	            System.out.println(node.value);
	            node = node.right;
	        }
	    }
	}
	public static void preTraverse(TreeNode node) {
		if (node == null) return;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.add(node);
		while (!stack.isEmpty()) {
			TreeNode n = stack.pop();
			System.out.println(n.value);
			if (n.right != null)
				stack.push(n.right);
			if (n.left != null)
				stack.push(n.left);
		}
	}
	public static class TagNode {
	    TreeNode n;
	    char tag;
	    public TagNode(TreeNode n, char tag) {this.n = n;this.tag = tag;}
	}
	public static void postTraverse(TreeNode root) {
	    if (root == null) return;
	    TreeNode node = root;
	    Stack<TagNode> stack = new Stack<TagNode>();
	    do {
	        while (node != null) {
	            stack.push(new TagNode(node, 'L'));
	            node = node.left;
	        }
	        boolean flag = true;
	        while (flag && !stack.isEmpty()) {
	            TagNode tmp = stack.peek();
	            node = tmp.n;
	            if (tmp.tag == 'L') {
	            	tmp.tag = 'R';
	                node = node.right;
	                flag = false;
	            } else {
	                tmp = stack.pop();
	                System.out.println(tmp.n.value);
	            }
	        }
	    }while (!stack.isEmpty());
	}
	public static TreeNode binarySearch(TreeNode node, int value) {
		if (node == null) return null;
		if (node.value > value) {
			return binarySearch(node.left, value);
		} else if (node.value < value) {
			return binarySearch(node.right, value);
		} else {
			return node;
		}
	}
	public static void treeToDDL(TreeNode node, TreeNode prev) {
		if (node == null) return;
		treeToDDL(node.left, prev);
		
		node.left = prev;
		if (prev != null) {
			prev.right = node;
		} else {
			head = node;
		}
		treeToDDL(node.right, node);
	}
}
