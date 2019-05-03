package rebound.hci.graphics2d.gui.layout.colinear.data.targetless;

import static java.util.Objects.*;
import java.util.List;
import rebound.hci.graphics2d.gui.layout.colinear.data.unified.UnifiedColinearTableLayoutParent;

public class TargetlessColinearTableLayoutParent
implements UnifiedColinearTableLayoutParent
{
	protected final List<TargetlessColinearLayoutEntry> columnLayouts;
	protected final List<TargetlessColinearLayoutEntry> rowLayouts;
	
	public TargetlessColinearTableLayoutParent(final List<TargetlessColinearLayoutEntry> columnLayouts, final List<TargetlessColinearLayoutEntry> rowLayouts)
	{
		this.columnLayouts = requireNonNull(columnLayouts);
		this.rowLayouts = requireNonNull(rowLayouts);
	}
	
	@Override
	public List<TargetlessColinearLayoutEntry> getColumnLayouts()
	{
		return this.columnLayouts;
	}
	
	@Override
	public List<TargetlessColinearLayoutEntry> getRowLayouts()
	{
		return this.rowLayouts;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.columnLayouts == null) ? 0 : this.columnLayouts.hashCode());
		result = prime * result + ((this.rowLayouts == null) ? 0 : this.rowLayouts.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TargetlessColinearTableLayoutParent other = (TargetlessColinearTableLayoutParent) obj;
		if (this.columnLayouts == null)
		{
			if (other.columnLayouts != null)
				return false;
		}
		else if (!this.columnLayouts.equals(other.columnLayouts))
			return false;
		if (this.rowLayouts == null)
		{
			if (other.rowLayouts != null)
				return false;
		}
		else if (!this.rowLayouts.equals(other.rowLayouts))
			return false;
		return true;
	}
}
