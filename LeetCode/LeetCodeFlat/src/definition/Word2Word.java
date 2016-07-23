package definition;


/*
 * 用来记录一个词是从哪个词跳过来的，这样可以
 * 在到达终点之后用来回溯到起点
 * 这个类相当于是一个链表，每个节点指向前一个节点
 * start <- string1 <- string2 <- ... <- end
 * 我希望当两个w2w表示从相同的点跳到相同的点时，他们相等
 * 但是下面默认的hashCode函数却会给两个相同的w2w返回不同的hashcode
 */
public class Word2Word{
	Word2Word from;
	String to;
	int length;
	Word2Word(Word2Word f, String t, int l) {
		from = f;
		to = t;
		length = l;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int tmp = 0;
		tmp += ((from == null) ? 0 : from.to.hashCode());
		tmp += ((to == null) ? 0 : to.hashCode());
		int result = 1;
		result = prime * tmp;
		System.out.println(result);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word2Word other = (Word2Word) obj;
		if(this.from == null || other.from == null)
			return this.to.equals(other.to);
		else 
			return (this.from.to.equals(other.from.to) && this.to.equals(other.to)) ||
					(this.from.to.equals(other.to) && this.to.equals(other.from.to));
	}
	
	
}
