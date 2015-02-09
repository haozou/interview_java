import java.util.Arrays;
import java.util.Iterator;

public class CustomSet<E> implements java.io.Serializable, Cloneable {
	/**
	 * default initial capacity of the Set
	 */
	private static final int DEFAUTL_SIZE = 10;
	/**
	 * shared empty array
	 */
	private static final Object[] EMPTY_ELEMENT = {};
	/**
	 * the array buffer
	 */
	private transient Object[] element;
	/**
	 * the size of array
	 */
	private int size;

	public CustomSet(int capacity) {
		super();
		if (capacity < 0) {
			throw new IllegalArgumentException("Illegal Capacity: " + capacity);
		}
		this.element = new Object[capacity];
	}

	public CustomSet() {
		super();
		this.element = EMPTY_ELEMENT;
	}

	// Positional Access Operations

	@SuppressWarnings("unchecked")
	E element(int index) {
		return (E) element[index];
	}

	public E get(int index) {
		if (index >= size) {
			System.out.println("nima");
			return null;
		}
		return this.element(index);
	}

	private void grow(int newCapacity) {
		// minCapacity is usually close to size, so this is a win:
		element = Arrays.copyOf(element, newCapacity);
	}

	private void ensureCapacityInternal(int minCapacity) {
		if (element == EMPTY_ELEMENT) {
			minCapacity = Math.max(DEFAUTL_SIZE, minCapacity);
		}
		// overflow-conscious code
		if (minCapacity - element.length > 0)
			grow(minCapacity);
	}

	public boolean exist(E e) {
		if (e == null) {
			for (int i = 0; i < this.size; i++) {
				if (e == this.element[i])
					return true;
			}
		} else {
			for (int i = 0; i < this.size; i++) {
				if (e.equals(this.element[i]))
					return true;
			}
		}
		return false;
	}

	public void add(E e) {
		if (this.exist(e))
			return;
		this.ensureCapacityInternal(this.size + 1);
		element[size++] = e;
	}

	public void remove(int index) {
		if (index >= size) return;

		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(element, index + 1, element, index,
					numMoved);
		element[--size] = null; // clear to let GC do its work
	}
	public int size() {
		return this.size;
	}
}
