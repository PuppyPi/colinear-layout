package rebound.hci.graphics2d.gui.layout.colinear;

import java.util.Arrays;

public class LayoutResult
{
	protected final float[] starts;
	protected final float[] sizes;
	protected final float end;
	
	public LayoutResult(final float[] starts, final float[] sizes, final float end)
	{
		this.starts = starts;
		this.sizes = sizes;
		this.end = end;
	}
	
	public float[] getStarts()
	{
		return this.starts;
	}
	
	public float[] getSizes()
	{
		return this.sizes;
	}
	
	public float getEnd()
	{
		return this.end;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(this.sizes);
		result = prime * result + Arrays.hashCode(this.starts);
		result = prime * result + Float.floatToIntBits(this.end);
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
		final LayoutResult other = (LayoutResult) obj;
		if (!Arrays.equals(this.sizes, other.sizes))
			return false;
		if (!Arrays.equals(this.starts, other.starts))
			return false;
		if (Float.floatToIntBits(this.end) != Float.floatToIntBits(other.end))
			return false;
		return true;
	}
}
