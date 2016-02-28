package codereview.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.Player;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ProfileScreen extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ProfileScreen(Composite parent, int style, Player player) {
		super(parent, style);
		
		Label logo = new Label(this, SWT.BORDER);
		logo.setText("Profile Page");
		logo.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		logo.setAlignment(SWT.CENTER);
		logo.setBounds(10, 10, 140, 30);
		
		Button backBtn = new Button(this, SWT.NONE);
		backBtn.setImage(SWTResourceManager.getImage(ProfileScreen.class, "/codereview/assets/back.png"));
		backBtn.setBounds(204, 10, 25, 25);
		backBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setVisible(false);
				MainScreen.initializeMainMenu();
			}
		});
		
		Button statsBtn = new Button(this, SWT.NONE);
		statsBtn.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		statsBtn.setBounds(10, 112, 102, 35);
		statsBtn.setText("Stats");
		statsBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setVisible(false);
				MainScreen.initializeStatsScreen();
			}
		});
		
		Button btnAchievements = new Button(this, SWT.NONE);
		btnAchievements.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		btnAchievements.setText("Achievements");
		btnAchievements.setBounds(127, 112, 102, 35);
		
		Button btnHallOfFame = new Button(this, SWT.NONE);
		btnHallOfFame.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		btnHallOfFame.setText("Hall Of Fame");
		btnHallOfFame.setBounds(10, 153, 102, 35);
		btnHallOfFame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setVisible(false);
				MainScreen.scoreScreen.setVisible(false);
				MainScreen.initializeHallOfFame();
			}
		});
		
		Label welcomeLbl = new Label(this, SWT.NONE);
		welcomeLbl.setFont(SWTResourceManager.getFont("Arial", 15, SWT.BOLD));
		welcomeLbl.setAlignment(SWT.CENTER);
		welcomeLbl.setBounds(10, 57, 219, 30);
		welcomeLbl.setText("Hi "+player.getName()+"!");
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
