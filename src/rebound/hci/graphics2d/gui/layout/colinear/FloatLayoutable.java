package rebound.hci.graphics2d.gui.layout.colinear;

import rebound.annotations.semantic.SignalType;

@SignalType
public interface FloatLayoutable
{
	public void layout(float x, float y, float width, float height);
}
