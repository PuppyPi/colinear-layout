package rebound.hci.graphics2d.gui.awt.layout.colinear;

import static java.lang.Math.*;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
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
	
	public ColinearAwtLayoutManager(final ColinearLayoutParent rootEntry)
	{
		super(rootEntry);
	}
	
	
	
	
	@Override
	public void layoutContainer(final Container parent)
	{
		super.layout(parent.getWidth(), parent.getHeight());
	}
	
	@Override
	protected float[] layoutLeafTarget(final Object target, final float x, final float y, final float width, final float height)
	{
		final Component component = (Component) target;
		
		final int widthInteger = round(width);
		final int heightInteger = round(height);
		
		component.setLocation(round(x), round(y));
		component.setSize(widthInteger, heightInteger);
		
		return new float[]{widthInteger, heightInteger};
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
