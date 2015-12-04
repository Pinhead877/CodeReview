package codereview.views;

import org.eclipse.swt.widgets.Composite;

import codereview.viewsoverride.CR_Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.Player;

import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ReviwerScreen extends CR_Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @param player 
	 */
	public ReviwerScreen(Composite parent, int style, Player player) {
		super(parent, style);
		
		Label lblNewLabel = new Label(this, SWT.BORDER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(10, 10, 194, 30);
		lblNewLabel.setText("Segment Review");
		
		StarRating starRating = new StarRating(this, SWT.NONE);
		starRating.setBounds(12, 208, 226, 42);
		
		Button backBtn = new Button(this, SWT.NONE);
		backBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setVisible(false);
				MainScreen.mainMenuScreen.setVisible(true);
			}
		});
		backBtn.setImage(SWTResourceManager.getImage(ReviwerScreen.class, "/codereview/assets/back.png"));
		backBtn.setBounds(210, 13, 25, 25);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
