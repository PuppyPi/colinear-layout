package rebound.hci.graphics2d.gui.layout.colinear.data.adt;

import static rebound.math.SmallFloatMathUtilities.*;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import rebound.annotations.semantic.SignalType;
import rebound.hci.graphics2d.gui.layout.colinear.data.unified.UnifiedColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.unified.UnifiedFinalRemainderColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.unified.UnifiedFixedAmountColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.unified.UnifiedInitialRemainderProportionalAmountColinearLayoutEntry;

/**
 * Note that you can make this contain a target that could never be layed-out, like a {@link String} or something.
 * This is just pure data, so you can use it however you want! :D
 */
@SignalType
public abstract class ColinearLayoutEntry<Leaf>  //sealed
implements UnifiedColinearLayoutEntry
{
	protected final @Nonnull ColinearLayoutable<Leaf> target;
	
	public ColinearLayoutEntry(@Nullable final @Nonnull ColinearLayoutable<Leaf> target)
	{
		this.target = target;
	}
	
	public @Nonnull ColinearLayoutable<Leaf> getTarget()
	{
		return this.target;
	}
	
	
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColinearLayoutEntry other = (ColinearLayoutEntry)obj;
		if (target == null)
		{
			if (other.target != null)
				return false;
		}
		else if (!target.equals(other.target))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@SignalType
	public static class FixedAmount<Leaf>
	extends ColinearLayoutEntry<Leaf>
	implements UnifiedFixedAmountColinearLayoutEntry
	{
		protected final double amount;
		
		public FixedAmount(final double amount, final ColinearLayoutable<Leaf> target)
		{
			super(target);
			this.amount = requireFinite(amount);
		}
		
		@Override
		public float getAmount()
		{
			return (float)this.amount;
		}
		
		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			long temp;
			temp = Double.doubleToLongBits(amount);
			result = prime * result + (int)(temp ^ (temp >>> 32));
			return result;
		}
		
		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			FixedAmount other = (FixedAmount)obj;
			if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
				return false;
			return true;
		}
		
		@Override
		public String toString()
		{
			return "FixedAmount [amount=" + amount + ", target=" + target + "]";
		}
	}
	
	
	
	
	@SignalType
	public static class InitialRemainderProportional<Leaf>
	extends ColinearLayoutEntry<Leaf>
	implements UnifiedInitialRemainderProportionalAmountColinearLayoutEntry
	{
		protected final double amount;
		
		public InitialRemainderProportional(final double amount, final @Nonnull ColinearLayoutable<Leaf> target)
		{
			super(target);
			this.amount = requireFinite(amount);
		}
		
		@Override
		public float getAmount()
		{
			return (float)this.amount;
		}
		
		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			long temp;
			temp = Double.doubleToLongBits(amount);
			result = prime * result + (int)(temp ^ (temp >>> 32));
			return result;
		}
		
		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			InitialRemainderProportional other = (InitialRemainderProportional)obj;
			if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
				return false;
			return true;
		}
		
		@Override
		public String toString()
		{
			return "InitialRemainderProportional [amount=" + amount + ", target=" + target + "]";
		}
	}
	
	
	
	
	@SignalType
	public static class FinalRemainder<Leaf>
	extends ColinearLayoutEntry<Leaf>
	implements UnifiedFinalRemainderColinearLayoutEntry
	{
		public FinalRemainder(final @Nonnull ColinearLayoutable<Leaf> target)
		{
			super(target);
		}
		
		@Override
		public String toString()
		{
			return "FinalRemainder [target=" + target + "]";
		}
	}
}
