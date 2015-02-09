import java.util.LinkedList;
import java.util.Queue;


public class StackQueue<E> {
	Queue<E> q1 = new LinkedList<E>();
	Queue<E> q2 = new LinkedList<E>();
	
	public void push(E e) {
		q2.add(e);
		while (!q1.isEmpty()) {
			q2.add(q1.remove());
		}
		Queue<E> tmp = q1;
		q1 = q2;
		q2 = tmp;
	}
	public E pop() {
		return q1.remove();
	}
	                                                                                                            
	public void push2(E e) {
		q1.add(e);
	}
	public E pop2() {
		E e = null;
		while (!q1.isEmpty()) {
			e = q1.remove();
			if (!q1.isEmpty()) {
				q2.add(e);
			}
		}
		Queue<E> tmp = q1;
		q1 = q2;
		q2 = tmp;
		return e;
	}
}
