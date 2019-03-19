package rebound.hci.graphics2d.gui.layout.colinear.data.targetful;

import javax.annotation.Nullable;
import rebound.annotations.semantic.SignalInterface;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetless.TargetlessColinearLayoutEntry;

/**
 * Note that you can make this contain a target that could never be layed-out, like a {@link String} or something.
 * This is just pure data, so you can use it however you want! :D
 */
@SignalInterface
public abstract class ColinearLayoutEntry
{
	protected final @Nullable Object target;
	
	public ColinearLayoutEntry(@Nullable final Object target)
	{
		this.target = target;
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
