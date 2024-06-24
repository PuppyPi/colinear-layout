package rebound.hci.graphics2d.gui.layout.colinear.data.adt;

import static java.util.Objects.*;
import java.util.List;
import rebound.hci.graphics2d.gui.layout.colinear.data.targetless.TargetlessColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.unified.UnifiedColinearTableLayoutParent;
import rebound.util.collections.SimpleTable;

public class ColinearLayoutableTable<Leaf>
implements ColinearLayoutable<Leaf>, UnifiedColinearTableLayoutParent
{
	protected final List<TargetlessColinearLayoutEntry> columnLayouts;
	protected final List<TargetlessColinearLayoutEntry> rowLayouts;
	protected final SimpleTable<ColinearLayoutable<Leaf>> targets;
	
	public ColinearLayoutableTable(final List<TargetlessColinearLayoutEntry> columnLayouts, final List<TargetlessColinearLayoutEntry> rowLayouts, final SimpleTable<ColinearLayoutable<Leaf>> targets)
	{
		this.columnLayouts = requireNonNull(columnLayouts);
		this.rowLayouts = requireNonNull(rowLayouts);
		this.targets = requireNonNull(targets);
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
	
	public SimpleTable<ColinearLayoutable<Leaf>> getTargets()
	{
		return this.targets;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.columnLayouts == null) ? 0 : this.columnLayouts.hashCode());
		result = prime * result + ((this.rowLayouts == null) ? 0 : this.rowLayouts.hashCode());
		result = prime * result + ((this.targets == null) ? 0 : this.targets.hashCode());
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
		final ColinearLayoutableTable other = (ColinearLayoutableTable) obj;
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
		if (this.targets == null)
		{
			if (other.targets != null)
				return false;
		}
		else if (!this.targets.equals(other.targets))
			return false;
		return true;
	}
	
	@Override
	public String toString()
	{
		return "ColinearLayoutableTable [columnLayouts=" + columnLayouts + ", rowLayouts=" + rowLayouts + ", targets=" + targets + "]";
	}
}
