package rebound.hci.graphics2d.gui.layout.colinear;

import java.util.Arrays;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.ColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.ColinearLayoutParent;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.FinalRemainderColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.FixedAmountColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.InitialRemainderProportionalAmountColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetless.TargetlessColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetless.TargetlessColinearLayoutParent;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetless.TargetlessFinalRemainderColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetless.TargetlessFixedAmountColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetless.TargetlessInitialRemainderProportionalAmountColinearLayoutEntry;
import rebound.math.geom2d.Direction2D.Axis2D;

/**
 * • The target Objects are to be either an actual target leaf component/object to lay out, or another {@link ColinearLayoutParent}  :33
 * 
 * • Floats are used in this library to make it work better in contexts like inside Android phone apps :3
 */
public class ColinearLayout
{
	public static ColinearLayoutParent clepC(final Axis2D axis, final Iterable<ColinearLayoutEntry> subentries)
	{
		return new ColinearLayoutParent(axis, subentries);
	}
	
	public static ColinearLayoutParent clepV(final Axis2D axis, final ColinearLayoutEntry... subentries)
	{
		return clepC(axis, Arrays.asList(subentries));
	}
	
	public static FixedAmountColinearLayoutEntry clefix(final float amount, final Object target)
	{
		return new FixedAmountColinearLayoutEntry(amount, target);
	}
	
	public static InitialRemainderProportionalAmountColinearLayoutEntry cleprp(final float amount, final Object target)
	{
		return new InitialRemainderProportionalAmountColinearLayoutEntry(amount, target);
	}
	
	public static FinalRemainderColinearLayoutEntry clerem(final Object target)
	{
		return new FinalRemainderColinearLayoutEntry(target);
	}
	
	
	
	
	
	
	
	
	
	public static TargetlessColinearLayoutParent clxp(final Axis2D axis, final Iterable<TargetlessColinearLayoutEntry> subentries)
	{
		return new TargetlessColinearLayoutParent(axis, subentries);
	}
	
	public static TargetlessColinearLayoutParent clxpV(final Axis2D axis, final TargetlessColinearLayoutEntry... subentries)
	{
		return clxp(axis, Arrays.asList(subentries));
	}
	
	public static TargetlessFixedAmountColinearLayoutEntry clxfix(final float amount)
	{
		return new TargetlessFixedAmountColinearLayoutEntry(amount);
	}
	
	public static TargetlessInitialRemainderProportionalAmountColinearLayoutEntry clxprp(final float amount)
	{
		return new TargetlessInitialRemainderProportionalAmountColinearLayoutEntry(amount);
	}
	
	public static TargetlessFinalRemainderColinearLayoutEntry clxrem()
	{
		return TargetlessFinalRemainderColinearLayoutEntry.I;
	}
}
