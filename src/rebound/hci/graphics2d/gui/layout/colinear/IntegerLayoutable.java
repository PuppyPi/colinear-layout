package rebound.hci.graphics2d.gui.layout.colinear;

import rebound.annotations.semantic.SignalType;

@SignalType
public interface IntegerLayoutable
{
	public void layout(int x, int y, int width, int height);
}
