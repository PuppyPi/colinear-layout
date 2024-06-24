package rebound.hci.graphics2d.gui.layout.colinear.data.adt;

/**
 * @param <Leaf>  Whether this can be null or not is part of the Type Parameterization conceptually!  In some systems, Emptiness isn't possible, so this must be Non-null, in systems where it is, a special Blank value might be used, or null might be!  It's up to the user/parameterizer!  \:3/
 */
public class ColinearLayoutableLeaf<Leaf>
implements ColinearLayoutable<Leaf>
{
	protected final Leaf leaf;
	
	public ColinearLayoutableLeaf(Leaf leaf)
	{
		this.leaf = leaf;
	}
	
	public Leaf getLeaf()
	{
		return leaf;
	}
	
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((leaf == null) ? 0 : leaf.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColinearLayoutableLeaf other = (ColinearLayoutableLeaf)obj;
		if (leaf == null)
		{
			if (other.leaf != null)
				return false;
		}
		else if (!leaf.equals(other.leaf))
			return false;
		return true;
	}
}
