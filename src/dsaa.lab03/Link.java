package dsaa.lab03;

public class Link{
	public String ref;
	// in the future there will be more fields
	public Link(String ref) {
		this.ref=ref;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Link)) return false;
		Link link = (Link) obj;
		return link.ref.equals(ref);
	}

	@Override
	public String toString() {
		return ref;
	}
	
}