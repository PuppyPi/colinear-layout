package rebound.hci.graphics2d.gui.layout.colinear;

import java.util.List;
import javax.annotation.Nullable;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.ColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.ColinearLayoutParent;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.ColinearTableLayoutParent;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetless.TargetlessColinearLayoutEntry;
import rebound.math.geom2d.Direction2D.Axis2D;
import rebound.util.collections.SimpleTable;

public abstract class AbstractColinearLayouter<TargetType>
{
	protected abstract void layoutLeafTarget(@Nullable TargetType target, float x, float y, float width, float height);
	
	//These are eg, for rounding :>
	protected float convertX(final float x) { return x; }
	protected float convertY(final float y) { return y; }
	protected float convertWidth(final float w) { return w; }
	protected float convertHeight(final float h) { return h; }
	
	
	
	
	
	
	public AbstractColinearLayouter()
	{
	}
	
	public AbstractColinearLayouter(final Object rootEntry)
	{
		this.rootEntry = rootEntry;
	}
	
	
	
	
	
	
	protected @Nullable Object rootEntry;
	
	public Object getRootEntry()
	{
		return this.rootEntry;
	}
	
	/**
	 * Calling this does NOT automatically re-layout the contents!!  You have to call {@link #layout(float, float)} if appropriate!
	 */
	public void setRootEntry(final Object rootEntry)
	{
		this.rootEntry = rootEntry;
	}
	
	
	
	
	
	/**
	 * @return the final width/height after conversion (eg, rounding)
	 */
	public float[] layout(final float width, final float height)
	{
		return layout(0, 0, width, height);
	}
	
	/**
	 * @return the final x+width/y+height after conversion (eg, rounding)
	 */
	public float[] layout(final float x, final float y, final float width, final float height)
	{
		return layoutSomething(this.rootEntry, convertX(x), convertY(y), convertWidth(width), convertHeight(height));
	}
	
	
	
	
	
	protected float[] layoutSingleAxis(final ColinearLayoutParent layout, final float x, final float y, final float width, final float height)
	{
		final Axis2D axis = layout.getAxis();
		final List<ColinearLayoutEntry> a = layout.getEntries();
		
		final boolean isY = axis == Axis2D.YVertical;
		
		
		final float actualEnd = PerformColinearLayout.layout(a, isY ? y : x, isY ? height : width, new PerformOneTargetLayout()
		{
			@Override
			public void layout(final int i, final float start, final float size)
			{
				final float thisX = isY ? x : start;
				final float thisY = isY ? start : y;
				final float thisWidth = isY ? width : size;
				final float thisHeight = isY ? size : height;
				
				final ColinearLayoutEntry c = a.get(i);
				final Object target = c.getTarget();
				
				layoutSomething(target, thisX, thisY, thisWidth, thisHeight);
			}
		},
		isY ? this::convertY : this::convertX,
		isY ? this::convertHeight : this::convertWidth
		);
		
		
		return isY ? new float[]{x+width, actualEnd} : new float[]{actualEnd, y+height};
	}
	
	
	
	
	protected float[] layoutTable(final ColinearTableLayoutParent layout, final float x, final float y, final float width, final float height)
	{
		final List<TargetlessColinearLayoutEntry> layoutsForColumns = layout.getColumnLayouts();
		final List<TargetlessColinearLayoutEntry> layoutsForRows = layout.getRowLayouts();
		final SimpleTable<Object> targets = layout.getTargets();
		
		final LayoutResult columns = PerformColinearLayout.layoutToMemory(layoutsForColumns, x, width, this::convertX, this::convertWidth);
		final LayoutResult rows = PerformColinearLayout.layoutToMemory(layoutsForRows, y, height, this::convertY, this::convertHeight);
		
		
		final int w = targets.getNumberOfColumns();
		final int h = targets.getNumberOfRows();
		
		for (int r = 0; r < h; r++)
		{
			final float ry = rows.getStarts()[r];
			final float rh = rows.getSizes()[r];
			
			for (int c = 0; c < w; c++)
			{
				final float cx = columns.getStarts()[c];
				final float cw = columns.getSizes()[c];
				
				final Object target = targets.getCellContents(c, r);
				layoutSomething(target, cx, ry, cw, rh);
			}
		}
		
		return new float[]{columns.getEnd(), rows.getEnd()};
	}
	
	
	
	protected float[] layoutSomething(final Object target, float thisX, float thisY, float thisWidth, float thisHeight)
	{
		if (target instanceof ColinearLayoutParent)
		{
			return layoutSingleAxis((ColinearLayoutParent)target, thisX, thisY, thisWidth, thisHeight);
		}
		else if (target instanceof ColinearTableLayoutParent)
		{
			return layoutTable((ColinearTableLayoutParent)target, thisX, thisY, thisWidth, thisHeight);
		}
		else
		{
			layoutLeafTarget((TargetType)target, thisX, thisY, thisWidth, thisHeight);
			return new float[]{thisX + thisWidth, thisY + thisHeight};
		}
	}
}
