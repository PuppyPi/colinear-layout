package rebound.hci.graphics2d.gui.layout.colinear.data.targetful;

import javax.annotation.Nonnull;
import rebound.math.geom2d.Direction2D.Axis2D;

public class ColinearLayoutParent
{
	protected final Axis2D axis;
	protected final Iterable<ColinearLayoutEntry> entries;
	
	public ColinearLayoutParent(final @Nonnull Axis2D axis, final @Nonnull Iterable<ColinearLayoutEntry> entries)
	{
		this.axis = axis;
		this.entries = entries;
	}
	
	public Axis2D getAxis()
	{
		return this.axis;
	}
	
	public Iterable<ColinearLayoutEntry> getEntries()
	{
		return this.entries;
	}
	
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.axis == null) ? 0 : this.axis.hashCode());
		result = prime * result + ((this.entries == null) ? 0 : this.entries.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ColinearLayoutParent other = (ColinearLayoutParent) obj;
		if (this.axis != other.axis)
			return false;
		if (this.entries == null)
		{
			if (other.entries != null)
				return false;
		}
		else if (!this.entries.equals(other.entries))
			return false;
		return true;
	}
}
