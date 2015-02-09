
public class Card {
	public enum Suit {
		CLUB (1),
		SPADES (2),
		HEARTS (3),
		DIAMONDS (4);
		int value;
		private Suit(int v) {
			this.value = v;
		}
	}
	private int card;
	private Suit suit;
	
	public Card(int r, Suit s) {
		this.card = r;
		this.suit = s;
	}
	public Card() {
		// TODO Auto-generated constructor stub
	}
	public int value() {
		return this.card;
	}
	public Suit suit() {
		return this.suit;
	}

}
