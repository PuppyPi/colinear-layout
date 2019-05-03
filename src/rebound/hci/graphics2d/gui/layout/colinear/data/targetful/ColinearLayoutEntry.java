package rebound.hci.graphics2d.gui.layout.colinear.data.targetful;

import static java.util.Objects.*;
import javax.annotation.Nullable;
import rebound.annotations.semantic.SignalType;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetless.TargetlessColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.unified.UnifiedColinearLayoutEntry;

/**
 * Note that you can make this contain a target that could never be layed-out, like a {@link String} or something.
 * This is just pure data, so you can use it however you want! :D
 */
@SignalType
public abstract class ColinearLayoutEntry
implements UnifiedColinearLayoutEntry
{
	protected final @Nullable Object target;
	
	public ColinearLayoutEntry(@Nullable final Object target)
	{
		this.target = requireNonNull(target);
	}
	
	@Nullable
	public Object getTarget()
	{
		return this.target;
	}
	
	
	public abstract TargetlessColinearLayoutEntry minusTarget();
	
	
	
	
	
	//The subclasses *must* define these by more than just identity!
	@Override
	public abstract int hashCode();
	
	@Override
	public abstract boolean equals(Object obj);
}
