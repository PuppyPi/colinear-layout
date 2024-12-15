package rebound.hci.graphics2d.gui.layout.colinear;

import static rebound.util.collections.CollectionUtilities.*;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.ColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.ColinearLayoutParent;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.FinalRemainderColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.FixedAmountColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetful.InitialRemainderProportionalAmountColinearLayoutEntry;
import rebound.util.functional.FunctionInterfaces.UnaryFunction;

public class ColinearLayoutUtilities
{
	public static ColinearLayoutParent mapParent(final UnaryFunction mapper, final ColinearLayoutParent p)
	{
		return new ColinearLayoutParent(p.getAxis(), mapToList(e -> mapEntry(mapper, e), p.getEntries()));
	}
	
	public static ColinearLayoutEntry mapEntry(final UnaryFunction mapper, final ColinearLayoutEntry e)
	{
		if (e instanceof FixedAmountColinearLayoutEntry)
		{
			FixedAmountColinearLayoutEntry eC = (FixedAmountColinearLayoutEntry)e;
			return new FixedAmountColinearLayoutEntry(eC.getAmount(), mapper.f(eC.getTarget()));
		}
		else if (e instanceof InitialRemainderProportionalAmountColinearLayoutEntry)
		{
			InitialRemainderProportionalAmountColinearLayoutEntry eC = (InitialRemainderProportionalAmountColinearLayoutEntry)e;
			return new InitialRemainderProportionalAmountColinearLayoutEntry(eC.getAmount(), mapper.f(eC.getTarget()));
		}
		else
		{
			FinalRemainderColinearLayoutEntry eC = (FinalRemainderColinearLayoutEntry)e;
			return new FinalRemainderColinearLayoutEntry(mapper.f(eC.getTarget()));
		}
	}
}
