package rebound.hci.graphics2d.gui.layout.colinear.data.targetless;

import rebound.annotations.semantic.SignalInterface;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.ColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.InitialRemainderProportionalAmountColinearLayoutEntry;

@SignalInterface
public class TargetlessInitialRemainderProportionalAmountColinearLayoutEntry
implements TargetlessColinearLayoutEntry
{
	protected final float amount;
	
	public TargetlessInitialRemainderProportionalAmountColinearLayoutEntry(final float amount)
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
		return new InitialRemainderProportionalAmountColinearLayoutEntry(this.amount, target);
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
		final TargetlessInitialRemainderProportionalAmountColinearLayoutEntry other = (TargetlessInitialRemainderProportionalAmountColinearLayoutEntry) obj;
		if (Float.floatToIntBits(this.amount) != Float.floatToIntBits(other.amount))
			return false;
		return true;
	}
}
