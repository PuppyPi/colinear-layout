package rebound.hci.graphics2d.gui.layout.colinear.data.targetless;

import rebound.annotations.semantic.SignalType;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.ColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.FinalRemainderColinearLayoutEntry;

@SignalType
public enum TargetlessFinalRemainderColinearLayoutEntry
implements TargetlessColinearLayoutEntry
{
	I;
	
	@Override
	public ColinearLayoutEntry withTarget(final Object target)
	{
		return new FinalRemainderColinearLayoutEntry(target);
	}
}
