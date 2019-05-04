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
	public static float layout(final List<? extends UnifiedColinearLayoutEntry> layoutData, float start, float size, final PerformOneTargetLayout actuallyLayout, @Nullable final UnaryFunctionFloatToFloat convertStartValue, @Nullable final UnaryFunctionFloatToFloat convertSizeValue)
	{
		requireFinite(start);
		requireFinite(size);
		
		if (size < 0)
			size = 0;
		
		start = convertStartValue.f(start);
		size = convertSizeValue.f(size);
		
		
		float unfixedAmount;
		{
			float fixedTotal = 0;
			{
				for (final UnifiedColinearLayoutEntry c : layoutData)
				{
					if (c instanceof UnifiedFixedAmountColinearLayoutEntry)
						fixedTotal += convertSizeValue.f(((UnifiedFixedAmountColinearLayoutEntry)c).getAmount());
				}
			}
			
			unfixedAmount = size - fixedTotal;
		}
		
		
		
		float remainder;
		{
			remainder = unfixedAmount;
			
			for (final UnifiedColinearLayoutEntry c : layoutData)
			{
				if (c instanceof UnifiedInitialRemainderProportionalAmountColinearLayoutEntry)
				{
					final float proportion = ((UnifiedInitialRemainderProportionalAmountColinearLayoutEntry)c).getAmount();
					final float requestedAmount = unfixedAmount * proportion;
					final float actualAmount = convertSizeValue.f(requestedAmount);
					remainder -= actualAmount;
				}
			}
		}
		
		
		
		//Actually lay out! :D
		float cursor = start;
		int i = 0;
		boolean hasRemainder = false;
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
					requestedAmount_ = unfixedAmount * proportion;
				}
				else if (c instanceof UnifiedFinalRemainderColinearLayoutEntry)
				{
					if (hasRemainder)
						throw new IllegalStateException("More than one remainder!!");
					else
						hasRemainder = true;
					
					requestedAmount_ = remainder;
				}
				else
				{
					throw newClassCastExceptionOrNullPointerException(c);
				}
				
				if (requestedAmount_ < 0)
					requestedAmount = 0;
				else
					requestedAmount = requestedAmount_;
			}
			
			final float actualStart = convertStartValue.f(cursor);
			final float actualSize = convertSizeValue.f(requestedAmount);
			
			//Actually lay *this* member out! :D
			actuallyLayout.layout(i, actualStart, actualSize);
			
			cursor = actualStart + actualSize;
			i++;
		}
		
		
		return cursor;
	}
	
	
	
	
	
	
	
	
	
	public static LayoutResult layoutToMemory(final List<? extends UnifiedColinearLayoutEntry> layoutData, final float start, final float size, @Nullable final UnaryFunctionFloatToFloat convertStartValue, @Nullable final UnaryFunctionFloatToFloat convertSizeValue)
	{
		final int n = layoutData.size();
		final float[] starts = new float[n];
		final float[] sizes = new float[n];
		
		final float totalEnd = layout(layoutData, start, size, new PerformOneTargetLayout()
		{
			@Override
			public void layout(final int i, final float start, final float size)
			{
				starts[i] = start;
				sizes[i] = size;
			}
		}, convertStartValue, convertSizeValue);
		
		return new LayoutResult(starts, sizes, totalEnd);
	}
}
