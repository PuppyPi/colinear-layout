package rebound.hci.graphics2d.gui.layout.colinear.data.targetful;

import rebound.annotations.semantic.SignalType;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetless.TargetlessColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetless.TargetlessFinalRemainderColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.unified.UnifiedFinalRemainderColinearLayoutEntry;

@SignalType
public class FinalRemainderColinearLayoutEntry
extends ColinearLayoutEntry
implements UnifiedFinalRemainderColinearLayoutEntry
{
	public FinalRemainderColinearLayoutEntry(final Object target)
	{
		super(target);
	}
	
	@Override
	public TargetlessColinearLayoutEntry minusTarget()
	{
		return TargetlessFinalRemainderColinearLayoutEntry.I;
	}
	
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.target == null) ? 0 : this.target.hashCode());
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
		final FinalRemainderColinearLayoutEntry other = (FinalRemainderColinearLayoutEntry) obj;
		if (this.target == null)
		{
			if (other.target != null)
				return false;
		}
		else if (!this.target.equals(other.target))
			return false;
		return true;
	}
}
