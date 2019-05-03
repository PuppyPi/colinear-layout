package rebound.hci.graphics2d.gui.layout.colinear;

import static rebound.math.SmallFloatMathUtilities.*;
import static rebound.util.BasicExceptionUtilities.*;
import java.util.List;
import javax.annotation.Nullable;
import rebound.hci.graphics2d.gui.layout.colinear.data.unified.UnifiedColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.unified.UnifiedFinalRemainderColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.unified.UnifiedFixedAmountColinearLayoutEntry;
import rebound.hci.graphics2d.gui.layout.colinear.data.unified.UnifiedInitialRemainderProportionalAmountColinearLayoutEntry;
import rebound.util.functional.FunctionInterfaces.UnaryFunctionFloatToFloat;

public class PerformColinearLayout
{
	public static float layout(final List<? extends UnifiedColinearLayoutEntry> layoutData, final float start, float size, final PerformOneTargetLayout actuallyLayout)
	{
		if (size < 0 || !isFinite(size))
			size = 0;
		
		
		float remainder;
		{
			float fixedTotal = 0;
			{
				for (final UnifiedColinearLayoutEntry c : layoutData)
				{
					if (c instanceof UnifiedFixedAmountColinearLayoutEntry)
						fixedTotal += ((UnifiedFixedAmountColinearLayoutEntry)c).getAmount();
				}
			}
			
			remainder = size - fixedTotal;
		}
		
		
		float proportionTotal = 0;
		{
			for (final UnifiedColinearLayoutEntry c : layoutData)
			{
				if (c instanceof UnifiedInitialRemainderProportionalAmountColinearLayoutEntry)
					proportionTotal += ((UnifiedInitialRemainderProportionalAmountColinearLayoutEntry)c).getAmount();
			}
		}
		
		
		
		//Actually lay out! :D
		float cursor = start;
		
		int i = 0;
		for (final UnifiedColinearLayoutEntry c : layoutData)
		{
			final float requestedAmount;
			{
				final float requestedAmount_;
				
				if (c instanceof UnifiedFixedAmountColinearLayoutEntry)
				{
					requestedAmount_ = ((UnifiedFixedAmountColinearLayoutEntry)c).getAmount();
				}
				else if (c instanceof UnifiedInitialRemainderProportionalAmountColinearLayoutEntry)
				{
					final float proportion = ((UnifiedInitialRemainderProportionalAmountColinearLayoutEntry)c).getAmount();
					requestedAmount_ = remainder * proportion;
				}
				else if (c instanceof UnifiedFinalRemainderColinearLayoutEntry)
				{
					final float proportion = 1 - proportionTotal;
					requestedAmount_ = remainder * proportion;
				}
				else
				{
					throw newClassCastExceptionOrNullPointerException(c);
				}
				
				if (requestedAmount_ < 0 || !isFinite(requestedAmount_))
					requestedAmount = 0;
				else
					requestedAmount = requestedAmount_;
			}
			
			
			
			//Actually lay *this* member out! :D
			final float actualEnd;
			{
				final float thisStart = cursor;
				final float thisSize = requestedAmount;
				
				actualEnd = actuallyLayout.layout(i, thisStart, thisSize);
			}
			
			
			cursor = actualEnd;
			i++;
		}
		
		
		return cursor;
	}
	
	
	
	
	
	
	
	
	
	public static LayoutResult layoutToMemory(final List<? extends UnifiedColinearLayoutEntry> layoutData, final float start, final float size, @Nullable final UnaryFunctionFloatToFloat startConverter, @Nullable final UnaryFunctionFloatToFloat sizeConverter)
	{
		final int n = layoutData.size();
		final float[] starts = new float[n];
		final float[] sizes = new float[n];
		
		final float totalSize = layout(layoutData, start, size, new PerformOneTargetLayout()
		{
			@Override
			public float layout(final int i, final float start, final float size)
			{
				final float actualStart = startConverter == null ? start : startConverter.f(start);
				final float actualSize = sizeConverter == null ? size : sizeConverter.f(size);
				
				starts[i] = actualStart;
				sizes[i] = actualSize;
				
				return actualStart + actualSize;
			}
		});
		
		return new LayoutResult(starts, sizes, totalSize);
	}
}
