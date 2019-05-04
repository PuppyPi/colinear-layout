package rebound.hci.graphics2d.gui.awt.layout.colinear;

import static java.lang.Math.*;
import static rebound.math.SmallFloatMathUtilities.*;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.annotation.Nullable;
import rebound.hci.graphics2d.gui.layout.colinear.AbstractColinearLayouter;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.ColinearLayoutParent;

public class ColinearAwtLayoutManager
extends AbstractColinearLayouter
implements LayoutManager
{
	public ColinearAwtLayoutManager()
	{
		super();
	}
	
	public ColinearAwtLayoutManager(Object rootEntry)
	{
		super(rootEntry);
	}
	
	
	
	
	
	
	@Override
	public void layoutContainer(final Container parent)
	{
		super.layout(parent.getWidth(), parent.getHeight());
	}
	
	@Override
	protected void layoutLeafTarget(final @Nullable Object target, final float x, final float y, final float width, final float height)
	{
		final int xInteger = (int)x;
		final int yInteger = (int)y;
		final int widthInteger = (int)width;
		final int heightInteger = (int)height;
		
		if (target != null)
		{
			final Component component = (Component) target;
			component.setLocation(xInteger, yInteger);
			component.setSize(widthInteger, heightInteger);
		}
	}
	
	@Override
	protected float convertX(final float x)
	{
		return roundFloorS32(x);
	}
	
	@Override
	protected float convertY(final float y)
	{
		return roundFloorS32(y);
	}
	
	@Override
	protected float convertWidth(final float w)
	{
		return round(w);
	}
	
	@Override
	protected float convertHeight(final float h)
	{
		return round(h);
	}
	
	
	
	
	
	
	
	@Override
	public void addLayoutComponent(final String name, final Component comp)
	{
	}
	
	@Override
	public void removeLayoutComponent(final Component comp)
	{
	}
	
	
	
	
	@Override
	public Dimension preferredLayoutSize(final Container parent)
	{
		//Todo use the fixed ones for this! :DD
		return null;
	}
	
	@Override
	public Dimension minimumLayoutSize(final Container parent)
	{
		//Todo use the fixed ones for this! :DD
		return null;
	}
}
