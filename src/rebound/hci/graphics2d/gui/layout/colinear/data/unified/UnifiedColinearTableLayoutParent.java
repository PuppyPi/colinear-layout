package rebound.hci.graphics2d.gui.layout.colinear.data.unified;

import java.util.List;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetless.TargetlessColinearLayoutEntry;

public interface UnifiedColinearTableLayoutParent
extends UnifiedColinearTypeLayoutParent
{
	public List<TargetlessColinearLayoutEntry> getColumnLayouts();
	public List<TargetlessColinearLayoutEntry> getRowLayouts();
}
