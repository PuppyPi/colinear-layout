package rebound.hci.graphics2d.gui.layout.colinear.data.targetless;

import rebound.annotations.semantic.SignalInterface;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.ColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.FixedAmountColinearLayoutEntry;

@SignalInterface
public class TargetlessFixedAmountColinearLayoutEntry
implements TargetlessColinearLayoutEntry
{
	protected final float amount;
	
	public TargetlessFixedAmountColinearLayoutEntry(final float amount)
	{
		this.amount = amount;
	}
	
	public float getAmount()
	{
		return this.amount;
	}
	
	@Override
	public ColinearLayoutEntry withTarget(final Object target)
	{
		return new FixedAmountColinearLayoutEntry(this.amount, target);
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(this.amount);
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
		final TargetlessFixedAmountColinearLayoutEntry other = (TargetlessFixedAmountColinearLayoutEntry) obj;
		if (Float.floatToIntBits(this.amount) != Float.floatToIntBits(other.amount))
			return false;
		return true;
	}
}
