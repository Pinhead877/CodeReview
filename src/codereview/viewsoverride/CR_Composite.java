package codereview.viewsoverride;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;

public class CR_Composite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CR_Composite(Composite parent, int style) {
		super(parent, style);
		setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		setBackground(SWTResourceManager.getColor(240, 240, 240));
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
