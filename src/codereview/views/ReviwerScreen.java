package codereview.views;

import org.eclipse.swt.widgets.Composite;

import codereview.viewsoverride.CR_Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.*;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

public class ReviwerScreen extends CR_Composite {
	private Text codeText;
	private Text reviewText;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @param player 
	 */
	public ReviwerScreen(Composite parent, int style, Player player, Segment seg) {
		super(parent, style);
		
		Label reviewLogoLbl = new Label(this, SWT.BORDER);
		reviewLogoLbl.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		reviewLogoLbl.setAlignment(SWT.CENTER);
		reviewLogoLbl.setBounds(10, 10, 194, 30);
		reviewLogoLbl.setText("Segment Review");
		
		StarRating starRating = new StarRating(this, SWT.NONE);
		starRating.setBounds(10, 208, 226, 25);
		
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
		
		Label reviewLbl = new Label(this, SWT.NONE);
		reviewLbl.setBounds(10, 46, 226, 30);
		reviewLbl.setText("Please review me...");
		
		codeText = new Text(this, SWT.BORDER);
		codeText.setEditable(false);
		codeText.setBounds(10, 82, 226, 120);
		
		reviewText = new Text(this, SWT.BORDER);
		reviewText.setBounds(10, 239, 226, 96);
		
		Button sendBtn = new Button(this, SWT.NONE);
		sendBtn.setBounds(161, 341, 75, 25);
		sendBtn.setText("Send");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
