package rebound.hci.graphics2d.gui.layout.colinear.data.unified;

import java.util.List;
import rebound.math.geom2d.Direction2D.Axis2D;

public interface UnifiedColinearLayoutParent
extends UnifiedColinearTypeLayoutParent
{
	public Axis2D getAxis();
	public List<? extends UnifiedColinearLayoutEntry> getEntries();
}
