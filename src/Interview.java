import java.io.*;
import java.util.*;

import javax.imageio.IIOException;


public class Interview {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set<String> a = new TreeSet<String>();
		// TODO Auto-generated method stub
		//--------------------------------------------
		System.out.println("Factorial of a number:");
		ArrayList<Integer> nums = factorial(1239);
		for (int n:nums) {
			System.out.print(n+" ");
		}
		//--------------------------------------------
		System.out.println("\nCheck palindrome:");
		boolean flag = check_pal("abcdcba");
		System.out.println("flag:"+flag);
		//--------------------------------------------
		System.out.println("Fibonacci:");
		int num = fibonacci(5);
		int num1 = fibonacci1(0);
		System.out.println("num:"+num + " num:"+num1);
		InputStream in;
		File f = new File("test");
		try {
			if (f.exists())
			{
				in = new FileInputStream(f);
				byte[] b = new byte[3];
				System.out.println(in.read(b));
				
				
			} else {
				System.out.println("hoahao");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Card card = new Card(1, 1);
	}
	// Factorial of a number
	public static ArrayList<Integer> factorial(int n) {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		while (n > 0) {
			nums.add(n%10);
			n /= 10;
		}
		System.out.println(nums.get(1));
		return nums;
	}
	// Check palindrome
	public static boolean check_pal(String str) {
		int start = 0;
		int end = str.length()-1;
		while (start < end)
		{
			if (str.charAt(start++) != str.charAt(end--))
			{
				return false;
			}
		}
		return true;
	}
	// Fibonacci
	public static int fibonacci(int n) {
		if (n == 0 || n == 1) {
			System.out.print(n);
			return n;
		}
		else {
			int result = fibonacci(n - 1) + fibonacci(n - 2);
			System.out.print(n);
			return result;
		}
	}
	public static int fibonacci1(int n) {
		if (n == 0 || n == 1) return n;
		
		int t1 = 0;
		int t2 = 1;
		int result = 0;
		for (int i = 0; i <= n-2; i++) {
			result = t1 + t2;
			t1 = t2;
			t2 = result;
		}
		return result;
	}
}



