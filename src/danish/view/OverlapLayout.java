package danish.view;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * The <code>OverlapLayout</code> class is a layout manager that lays out a
 * container's components in an overlapping fashion. A component can be painted
 * "above" or "below" the previous component in the container.
 *
 * Like the GridLayout, each component is sized to the largest width and height
 * of any component in the container. The amount of overlap is controlled by
 * specifying the overlap postion of each component. You can simulate,
 * left-to-right, right-to-left, top-to-bottom, bottom-to-top and diagonal
 * layouts. As well you can "stack" components completely on top of one another.
 * In this case the components are sized to the space available in the
 * container.
 *
 * A main usage for this layout might be in the creation of "card games". A few
 * features have been added that might be handy in these cases:
 *
 * a) a "popup" feature - when a component is selected in can "popup" from its
 * regular location so it visibly stands out. To accomplish this some extra
 * space must be reserved in the container for the popup. This is done by using
 * the setPopupInsets method which allow you to control the popup direction. In
 * addition you can add/remove a simple constraint to the component. POP_UP will
 * popup the component. POP_DOWN or null will paint the component in its regular
 * location. b) when a component is made "invisible" you can reserve its
 * location in the container so all the other components don't shift.
 *
 * Note: this layout is achieved by changing the ZOrder of components in the
 * container. It will not work for all components as some compnents will always
 * paint themselves on the top of others. This seems to happen with components
 * like JButton as rollover effects are painted when a mouse moves over the
 * components.
 *
 * Base on http://tips4java.wordpress.com/2009/07/26/overlap-layout/
 */
public class OverlapLayout implements LayoutManager2, java.io.Serializable {

	private static final int PREFERRED = 0;
	private static final int MINIMUM = 1;

	//  Indicates how a component is painted
	private boolean overlapAbove;

	private Point overlapPosition;

	//  Reserve space for invisible components in the Container
	private boolean includeInvisible = true;

	//  Reserve extra space so a component can "popup"
	private Insets popupInsets = new Insets(0, 0, 0, 0);

	//  Track original order in which the components where added
	private List<Component> components = new ArrayList<>();

	//  Track a constraint added to a component
	private HashMap<Component, OverlapConstraints> constraints = new HashMap<>();

	//  Track maximum dimension of any component for easier layout
	private Dimension maximumSize = new Dimension();

	private float layoutAlignmentY = 0.5f;
	private float layoutAlignmentX = 0.5f;
	private Insets inset = new Insets(0, 0, 0, 0);

	/**
	 * Convenience constructor to provide for "stacking" of components. Each
	 * component will be stacked above the previous component and sized to fill
	 * the space of the parent container.
	 */
	public OverlapLayout() {
		this(new Point(0, 0));
	}

	/**
	 * Convenience constructor. Each component will overlap above the previous
	 * component.
	 *
	 * @param overlapPosition a Point defining the relative amount of overlap
	 */
	public OverlapLayout(Point overlapPosition) {
		this(overlapPosition, true);
	}

	/**
	 * Create an overlapping layout.
	 *
	 * @param overlapPosition a Point defining the relative amount of overlap
	 * @param overlapAbove when true components are painted above the previous
	 * component, otherwise they are painted below.
	 */
	public OverlapLayout(Point overlapPosition, boolean overlapAbove) {
		this.overlapPosition = overlapPosition;
		this.overlapAbove = overlapAbove;
	}

	/**
	 * When components are overlapped above the ZOrder of each component is
	 * changed resulting in the components position in the container being
	 * changed. For example when you add a component to the end of the container
	 * it will be moved to the beginning. If you then try to access the
	 * component using Component.componentAt(), you will get the first
	 * component, not the last.
	 *
	 * This method will convert the index to you access the proper component.
	 *
	 * @param index the index to convert
	 * @return the index converted
	 */
	public int convertIndex(int index) {
		if (overlapAbove) {
			return components.size() - index - 1;
		} else {
			return index;
		}
	}

	/**
	 * Get the include invisible property
	 *
	 * @return the include invisible property
	 */
	public boolean isIncludeInvisible() {
		return includeInvisible;
	}

	/**
	 * Controls whether spaces should reserved for invisible components in the
	 * container
	 *
	 * @param includeInvisible when true, space is reserved otherwise the
	 * component is not included in the layout sizing
	 */
	public void setIncludeInvisible(boolean includeInvisible) {
		this.includeInvisible = includeInvisible;
	}

	/**
	 * Get the overlapping position of each component
	 *
	 * @return the Point representing the overlapped position
	 */
	public Point getOverlapPosition() {
		return overlapPosition;
	}

	/**
	 * Specify the position where the overlapped component should be painted.
	 *
	 * @param overlapPosition the position where the next component is painted
	 */
	public void setOverlapPosition(Point overlapPosition) {
		this.overlapPosition = overlapPosition;
	}

	/**
	 * Get the popup insets
	 *
	 * @return the popup insets
	 */
	public Insets getPopupInsets() {
		return popupInsets;
	}

	/**
	 * Define extra space to be reserved by the container. This will allow
	 * components to be "popped up" if required. Generally space would only be
	 * reserved on one side of the container.
	 *
	 * @param popupInsets Insets defining extra space for a particular side of
	 * the container.
	 */
	public void setPopupInsets(Insets popupInsets) {
		this.popupInsets = popupInsets;
	}

	public void setInset(Insets inset) {
		if (inset != null) {
			this.inset = inset;
		}
	}

	/**
	 * Gets the constraints for the specified component.
	 *
	 * @param component the component to be queried
	 * @return the constraint for the specified component, or null if component
	 * is null or is not present in this layout
	 */
	public OverlapConstraints getConstraints(Component component) {
		return constraints.get(component);
	}

	/**
	 * Adds the specified component with the specified name to the layout.
	 *
	 * @param name the name of the component
	 * @param comp the component to be added
	 */
	@Override
	public void addLayoutComponent(String name, Component comp) {
	}

	/*
	 *	Keep track of any specified constraint for the component.
	 */
	@Override
	public void addLayoutComponent(Component component, Object constraint) {

		if (constraint == null) {
			constraints.remove(component);
		} else if (constraint instanceof OverlapConstraints) {
			constraints.put(component, (OverlapConstraints) constraint);
		} else {
			String message = "Constraint parameter must be of type OverlapConstraints";
			throw new IllegalArgumentException(message);
		}

		//  Keep a separate List of components in the order in which they where
		//  added to the Container. This makes layout easier. First we need
		//  to find the position the component was added at. We can't depend
		//  on the component order in the parent Container as changing the
		//  Z-Order also changes the order in the Container
		if (!components.contains(component)) {
			Container parent = component.getParent();

			int size = 0;
			if (parent != null) {
				size = parent.getComponentCount();
			}

			for (int i = 0; i < size; i++) {
				Component c = parent.getComponent(i);

				if (c == component) {
					components.add(i, component);

					//  Need to change Z-Order so added components are painted
					//  above the previously added component.
					if (overlapAbove) {
						parent.setComponentZOrder(component, size - i - 1);
					}

					break;
				}
			}
		}
	}

	/**
	 * Removes the specified component from the layout.
	 *
	 * @param component the component to be removed
	 */
	@Override
	public void removeLayoutComponent(Component component) {
		components.remove(component);
		constraints.remove(component);
	}

	/**
	 * Determine the minimum size on the Container
	 *
	 * @param	parent the container in which to do the layout
	 * @return	the minimum dimensions needed to lay out the subcomponents of the
	 * specified container
	 */
	@Override
	public Dimension minimumLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			return getLayoutSize(parent, MINIMUM);
		}
	}

	/**
	 * Determine the preferred size on the Container
	 *
	 * @param	parent the container in which to do the layout
	 * @return the preferred dimensions to lay out the subcomponents of the
	 * specified container
	 */
	@Override
	public Dimension preferredLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			return getLayoutSize(parent, PREFERRED);
		}
	}

	/*
	 *  The calculation for minimum/preferred size it the same. The only
	 *  difference is the need to use the minimum or preferred size of the
	 *  component in the calculation.
	 *
	 *  @param	 parent  the container in which to do the layout
	 *  @param	 type    either MINIMUM or PREFERRED
	 */
	private Dimension getLayoutSize(Container parent, int type) {
		int visibleComponents = 0;
		int width = 0;
		int height = 0;

		//  All components will be resized to the maximum dimension
		for (Component component : components) {
			if (component.isVisible() || includeInvisible) {
				OverlapConstraints c = getConstraints(component);
				if (c == null || c.overlap) {
					Dimension size = getDimension(component, type);
					width = Math.max(size.width, width);
					height = Math.max(size.height, height);
					visibleComponents++;
				}
			}
		}
		if (visibleComponents == 0 || width == 0 || height == 0) {
			maximumSize.setSize(0, 0);
			return new Dimension(0, 0);
		}

		if (overlapPosition.x == 0 && overlapPosition.y == 0) {
			return new Dimension(parent.getWidth(), parent.getHeight());
		}

		visibleComponents--;
		Insets parentInsets = parent.getInsets();

		Dimension sizeWithOverlap = new Dimension(
				(int) (width * (1 + visibleComponents * Math.abs(overlapPosition.x / 100.0))),
				(int) (height * (1 + visibleComponents * Math.abs(overlapPosition.y / 100.0)))
		);

		Dimension sizeAvailable = new Dimension(
				(int) (parent.getWidth() - parentInsets.left - parentInsets.right - popupInsets.left - popupInsets.right - inset.left - inset.right),
				(int) (parent.getHeight() - parentInsets.top - parentInsets.bottom - popupInsets.top - popupInsets.bottom - inset.bottom - inset.top)
		);

		if (((double) sizeAvailable.height / (double) sizeAvailable.width) > ((double) sizeWithOverlap.height / (double) sizeWithOverlap.width)) {
			sizeWithOverlap.height = (sizeWithOverlap.height * sizeAvailable.width) / sizeWithOverlap.width;
			sizeWithOverlap.width = sizeAvailable.width;
		} else {
			sizeWithOverlap.width = (sizeWithOverlap.width * sizeAvailable.height) / sizeWithOverlap.height;
			sizeWithOverlap.height = sizeAvailable.height;
		}

		maximumSize.setSize(
				(int) (sizeWithOverlap.width / (1 + visibleComponents * Math.abs(overlapPosition.x / 100.0))),
				(int) (sizeWithOverlap.height / (1 + visibleComponents * Math.abs(overlapPosition.y / 100.0)))
		);

		return new Dimension(sizeWithOverlap);
	}

	private Dimension getDimension(Component component, int type) {
		switch (type) {
			case PREFERRED:
				return component.getPreferredSize();
			case MINIMUM:
				return component.getMinimumSize();
			default:
				return new Dimension(0, 0);
		}
	}

	/**
	 * Lays out the specified container using this layout.
	 *
	 * @param	parent the container in which to do the layout
	 */
	@Override
	public void layoutContainer(Container parent) {
		synchronized (parent.getTreeLock()) {
			Dimension layoutSize = this.getLayoutSize(parent, PREFERRED);

			int size = components.size();

			if (size == 0) {
				return;
			}

			//  Determine location of first component
			Point location = new Point(0, 0);
			Insets parentInsets = parent.getInsets();

			//  Layout right-to-left, else left-to-right
			if (overlapPosition.x < 0) {
				location.x = (int) (parent.getWidth() - maximumSize.width - parentInsets.right - popupInsets.right - inset.right - (parent.getWidth() - layoutSize.width) * layoutAlignmentX);
			} else {
				location.x = (int) (parentInsets.left + popupInsets.left + inset.left + (parent.getWidth() - layoutSize.width) * layoutAlignmentX);
			}

			//  Layout bottom-to-top, else top-to-bottom
			if (overlapPosition.y < 0) {
				location.y = (int) (parent.getHeight() - maximumSize.height - parentInsets.bottom - popupInsets.bottom - inset.bottom - (parent.getHeight() - layoutSize.height) * layoutAlignmentY);
			} else {
				location.y = (int) (parentInsets.top + popupInsets.top + inset.top + (parent.getHeight() - layoutSize.height) * layoutAlignmentY);
			}

			//  Set the size and location for each component
			for (int i = 0; i < size; i++) {
				Component component = components.get(i);

				if (component.isVisible()
						|| includeInvisible) {
					//  When components are "stacked" resize each component to fill
					//  the size of the parent container

					if (overlapPosition.x == 0 && overlapPosition.y == 0) {
						int width = parent.getWidth() - parentInsets.left - parentInsets.right;
						int height = parent.getHeight() - parentInsets.top - parentInsets.bottom;
						component.setSize(width, height);
					} else { //  resize each component to be the same size
						component.setSize(maximumSize);
					}

					//  Set location of the component
					int x = location.x;
					int y = location.y;

					//  Adjust location when component is "popped up"
					OverlapConstraints constraint = constraints.get(component);

					if (constraint != null
							&& constraint.popup) {
						x += popupInsets.right - popupInsets.left;
						y += popupInsets.bottom - popupInsets.top;
					}

					component.setLocation(x, y);

					//  Calculate location of next component using the overlap offsets
					if (i + 1 < size) {
						OverlapConstraints c = constraints.get(components.get(i + 1));
						if (c == null || c.overlap) {
							location.x += overlapPosition.x * component.getWidth() / 100;
							location.y += overlapPosition.y * component.getHeight() / 100;
						}
					}
				}
			}
		}
	}

	/**
	 * There is no maximum.
	 */
	@Override
	public Dimension maximumLayoutSize(Container target) {
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	/**
	 * Returns the alignment along the x axis. Use center alignment.
	 */
	@Override
	public float getLayoutAlignmentX(Container parent) {
		return this.layoutAlignmentX;
	}

	/**
	 * Sets the horizontal alignement with a number between 0 and 1.
	 *
	 * @param f The alignement. 0 for left, 1 for right.
	 */
	public void setLayoutAlignmentX(float f) {
		if (f >= 0 && f <= 1) {
			this.layoutAlignmentX = f;
		}
	}

	/**
	 * Returns the alignment along the y axis. Use center alignment.
	 */
	@Override
	public float getLayoutAlignmentY(Container parent) {
		return this.layoutAlignmentY;
	}

	/**
	 * Sets the vertical alignement with a number between 0 and 1.
	 *
	 * @param f The alignement. 0 for up, 1 for down.
	 */
	public void setLayoutAlignmentY(float f) {
		if (f >= 0 && f <= 1) {
			this.layoutAlignmentY = f;
		}
	}

	/**
	 * Invalidates the layout, indicating that if the layout manager has cached
	 * information it should be discarded.
	 */
	@Override
	public void invalidateLayout(Container target) {
		// remove constraints here?
	}

	/**
	 * Returns the string representation of this column layout's values.
	 *
	 * @return	a string representation of this grid layout
	 */
	@Override
	public String toString() {
		return getClass().getName()
				+ "[overlapAbove=" + overlapAbove
				+ ",overlapPosition=" + overlapPosition
				+ "]";
	}
}
