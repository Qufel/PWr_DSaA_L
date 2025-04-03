package dsaa.lab04;

import static java.lang.Math.*;

public class Link implements Comparable<Link>{
	public String ref;
	public int weight;
	public Link(String ref) {
		this.ref=ref;
		weight=1;
	}

	public Link(String ref, int weight) {
		this.ref=ref;
		this.weight=weight;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;

		if (!(obj instanceof Link))
			return false;

		Link l = (Link) obj;

		if (this.ref.equals(l.ref))
			return true;

		return false;
	}

	@Override
	public String toString() {
		return ref+"("+weight+")";
	}

	@Override
	public int compareTo(Link another) {

		if (another == null)
			return -1;

		return this.ref.compareTo(another.ref);
	}
}

