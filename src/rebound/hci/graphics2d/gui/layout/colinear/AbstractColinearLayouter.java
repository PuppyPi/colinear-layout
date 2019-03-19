package rebound.hci.graphics2d.gui.layout.colinear;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import rebound.exceptions.StructuredClassCastException;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.ColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.ColinearLayoutParent;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.FinalRemainderColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.FixedAmountColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.InitialRemainderProportionalAmountColinearLayoutEntry;
import rebound.math.geom2d.Direction2D.Axis2D;

public abstract class AbstractColinearLayouter<TargetType>
{
	//Todo support the X and Y not being what was requested!
	/**
	 * @return the actual width and height in case, for some reason they're not what was requested (eg, rounding to integers XD).   otherwise, just pass on through the given ones (null is interpreted to do this, for your convenience) :3
	 */
	@Nullable  //for default behavior :3
	protected abstract float[] layoutLeafTarget(@Nonnull TargetType target, float x, float y, float width, float height);
	
	
	
	
	
	public AbstractColinearLayouter()
	{
	}
	
	public AbstractColinearLayouter(final ColinearLayoutParent rootEntry)
	{
		this.rootEntry = rootEntry;
	}
	
	
	
	
	
	
	protected @Nullable ColinearLayoutParent rootEntry;
	
	public ColinearLayoutParent getRootEntry()
	{
		return this.rootEntry;
	}
	
	/**
	 * Calling this does NOT automatically re-layout the contents!!  You have to call {@link #layout(float, float)} if appropriate!
	 */
	public void setRootEntry(final ColinearLayoutParent rootEntry)
	{
		this.rootEntry = rootEntry;
	}
	
	
	
	
	
	
	public float[] layout(final float width, final float height)
	{
		return layout(0, 0, width, height);
	}
	
	public float[] layout(final float x, final float y, final float width, final float height)
	{
		final ColinearLayoutParent r = this.rootEntry;
		
		if (r != null)
			return layoutSingle(r, x, y, width, height);
		else
			return new float[]{width, height};
	}
	
	
	
	protected float[] layoutSingle(final ColinearLayoutParent array, final float x, final float y, float width, float height)
	{
		if (width < 0 || !isFinite(width))
			width = 0;
		
		if (height < 0 || !isFinite(height))
			height = 0;
		
		
		
		final Axis2D axis = array.getAxis();
		final Iterable<ColinearLayoutEntry> a = array.getEntries();
		
		final boolean isY = axis == Axis2D.YVertical;
		
		final float totalAvailable = isY ? height : width;
		
		
		
		float remainder;
		{
			float fixedTotal = 0;
			{
				for (final ColinearLayoutEntry c : a)
				{
					if (c instanceof FixedAmountColinearLayoutEntry)
						fixedTotal += ((FixedAmountColinearLayoutEntry)c).getAmount();
				}
			}
			
			remainder = totalAvailable - fixedTotal;
		}
		
		
		float proportionTotal = 0;
		{
			for (final ColinearLayoutEntry c : a)
			{
				if (c instanceof InitialRemainderProportionalAmountColinearLayoutEntry)
					proportionTotal += ((InitialRemainderProportionalAmountColinearLayoutEntry)c).getAmount();
			}
		}
		
		
		
		//Actually lay out! :D
		float cursor = 0;
		
		for (final ColinearLayoutEntry c : a)
		{
			float actualAmount;
			{
				if (c instanceof FixedAmountColinearLayoutEntry)
				{
					actualAmount = ((FixedAmountColinearLayoutEntry)c).getAmount();
				}
				else if (c instanceof InitialRemainderProportionalAmountColinearLayoutEntry)
				{
					final float proportion = ((InitialRemainderProportionalAmountColinearLayoutEntry)c).getAmount();
					actualAmount = remainder * proportion;
				}
				else if (c instanceof FinalRemainderColinearLayoutEntry)
				{
					final float proportion = 1 - proportionTotal;
					actualAmount = remainder * proportion;
				}
				else
				{
					throw newClassCastExceptionOrNullPointerException(c);
				}
			}
			
			if (actualAmount < 0 || !isFinite(actualAmount))
				actualAmount = 0;
			
			
			
			//Actually lay *this* member out! :D
			{
				final float thisX = isY ? x : (x + cursor);
				final float thisY = isY ? (y + cursor) : y;
				final float thisWidth = isY ? width : actualAmount;
				final float thisHeight = isY ? actualAmount : height;
				
				final Object target = c.getTarget();
				
				if (target != null)  //null means padding/margin/empty! :D
				{
					float[] dims;
					
					if (target instanceof ColinearLayoutParent)
					{
						dims = layoutSingle((ColinearLayoutParent)target, thisX, thisY, thisWidth, thisHeight);
					}
					else
					{
						dims = layoutLeafTarget((TargetType)target, thisX, thisY, thisWidth, thisHeight);
						
						if (dims == null)
							dims = new float[]{thisWidth, thisHeight};
					}
					
					actualAmount = isY ? dims[1] : dims[0];
				}
			}
			
			
			cursor += actualAmount;
		}
		
		
		
		return isY ? new float[]{width, cursor} : new float[]{cursor, height};
	}
	
	
	
	
	
	
	
	
	//RCINLINE//
	private static boolean isFinite(final float x)
	{
		return !(Float.isInfinite(x) || isNaN(x));
	}
	
	private static boolean isNaN(final float x)
	{
		return x != x;
	}
	
	private static RuntimeException newClassCastExceptionOrNullPointerException(final Object o)
	{
		if (o == null)
			return new NullPointerException();
		else
			return new StructuredClassCastException(o.getClass());
	}
}
