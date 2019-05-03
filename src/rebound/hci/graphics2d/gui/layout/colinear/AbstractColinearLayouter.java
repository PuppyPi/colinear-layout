package rebound.hci.graphics2d.gui.layout.colinear;

import java.util.List;
import javax.annotation.Nullable;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.ColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.ColinearLayoutParent;
import rebound.math.geom2d.Direction2D.Axis2D;

public abstract class AbstractColinearLayouter<TargetType>
{
	//Todo support the X and Y not being what was requested!
	/**
	 * @return the actual width and height in case, for some reason they're not what was requested (eg, rounding to integers XD).   otherwise, just pass on through the given ones (null is interpreted to do this, for your convenience) :3
	 */
	@Nullable
	protected abstract float[] layoutLeafTarget(@Nullable TargetType target, float x, float y, float width, float height);
	
	
	
	
	
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
	
	
	
	
	
	protected float[] layoutSingle(final ColinearLayoutParent array, final float x, final float y, final float width, final float height)
	{
		final Axis2D axis = array.getAxis();
		final List<ColinearLayoutEntry> a = array.getEntries();
		
		final boolean isY = axis == Axis2D.YVertical;
		
		
		final float actual = PerformColinearLayout.layout(a, isY ? y : x, isY ? height : width, new PerformOneTargetLayout()
		{
			@Override
			public float layout(final int i, final float start, final float size)
			{
				final float thisX = isY ? x : (x + start);
				final float thisY = isY ? (y + start) : y;
				final float thisWidth = isY ? width : size;
				final float thisHeight = isY ? size : height;
				
				final ColinearLayoutEntry c = a.get(i);
				final Object target = c.getTarget();
				
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
				
				return isY ? dims[1] : dims[0];
			}
		});
		
		
		return isY ? new float[]{width, actual} : new float[]{actual, height};
	}
}
