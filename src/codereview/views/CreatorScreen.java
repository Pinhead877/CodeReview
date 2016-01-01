package codereview.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.Player;
import codereview.data.DataHandler;
import codereview.viewsoverride.CR_Composite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CreatorScreen extends CR_Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	
	private Text searchTextBox;

	public CreatorScreen(Composite parent, int style, Player player) {
		super(parent, style);
		setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		
		Label ReviewsWaitingLabel = new Label(this, SWT.NONE);
		ReviewsWaitingLabel.setTouchEnabled(true);
		ReviewsWaitingLabel.setText("Reviews Waiting:");
		ReviewsWaitingLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		ReviewsWaitingLabel.setBounds(10, 215, 156, 23);
		
		Label ReviewsIWroteLabel = new Label(this, SWT.NONE);
		ReviewsIWroteLabel.setText("Reviews I Wrote:");
		ReviewsIWroteLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		ReviewsIWroteLabel.setBounds(10, 186, 156, 23);
		
		Label segmentIwroteNum = new Label(this, SWT.NONE);
		try {
			segmentIwroteNum.setText(new DataHandler().getNumberOfSegmentsByPlayer(player.getId()));
		} catch (Exception e1) {
			segmentIwroteNum.setText("???");
			e1.printStackTrace();
		}
		segmentIwroteNum.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		segmentIwroteNum.setAlignment(SWT.RIGHT);
		segmentIwroteNum.setBounds(172, 92, 64, 23);
		
		Label qualitysIWroteLabel = new Label(this, SWT.NONE);
		qualitysIWroteLabel.setText("Quality I Wrote:");
		qualitysIWroteLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		qualitysIWroteLabel.setBounds(10, 121, 156, 23);
		
		Label qualitysIWroteNum = new Label(this, SWT.NONE);
		qualitysIWroteNum.setText("4");
		qualitysIWroteNum.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		qualitysIWroteNum.setAlignment(SWT.RIGHT);
		qualitysIWroteNum.setBounds(172, 121, 64, 23);
		
		Label MyReviewsLabel = new Label(this, SWT.BORDER);
		MyReviewsLabel.setText("My Reviews");
		MyReviewsLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		MyReviewsLabel.setAlignment(SWT.CENTER);
		MyReviewsLabel.setBounds(10, 150, 226, 30);
		
		Button searchButton = new Button(this, SWT.NONE);
		searchButton.setImage(SWTResourceManager.getImage(MainScreen.class, "/codereview/assets/search.png"));
		searchButton.setBounds(206, 286, 30, 30);
		
		Label ReviewsIWroteNum = new Label(this, SWT.NONE);
		try {
			ReviewsIWroteNum.setText(new DataHandler().getNumberOfReviewsByPlayer(player.getId()));
		} catch (Exception e1) {
			ReviewsIWroteNum.setText("???");
			e1.printStackTrace();
		}
		ReviewsIWroteNum.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		ReviewsIWroteNum.setAlignment(SWT.RIGHT);
		ReviewsIWroteNum.setBounds(172, 186, 64, 23);
		
		Label SegmentsIWroteLabel = new Label(this, SWT.NONE);
		SegmentsIWroteLabel.setText("Segments I Wrote:");
		SegmentsIWroteLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		SegmentsIWroteLabel.setBounds(10, 92, 156, 23);
		
		Label MySegmentsLabel = new Label(this, SWT.BORDER);
		MySegmentsLabel.setText("My Segments");
		MySegmentsLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		MySegmentsLabel.setAlignment(SWT.CENTER);
		MySegmentsLabel.setBounds(10, 56, 226, 30);
		
		searchTextBox = new Text(this, SWT.BORDER);
		searchTextBox.setToolTipText("Search...");
		searchTextBox.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		searchTextBox.setBounds(10, 286, 190, 30);
		
		Label ReviewsWatingLabel = new Label(this, SWT.NONE);
		ReviewsWatingLabel.setText("3");
		ReviewsWatingLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		ReviewsWatingLabel.setAlignment(SWT.RIGHT);
		ReviewsWatingLabel.setBounds(172, 215, 64, 23);
		
		Label lblCreatorMode = new Label(this, SWT.NONE);
		lblCreatorMode.setAlignment(SWT.CENTER);
		lblCreatorMode.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblCreatorMode.setBounds(34, 10, 166, 30);
		lblCreatorMode.setText("Creator Mode");
		
		Button backBtn = new Button(this, SWT.NONE);
		backBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setVisible(false);
				MainScreen.initializeMainMenu();
			}
		});
		backBtn.setImage(SWTResourceManager.getImage(CreatorScreen.class, "/codereview/assets/back.png"));
		backBtn.setBounds(211, 13, 25, 25);
		
		Button askForAReviewBtn = new Button(this, SWT.NONE);
		askForAReviewBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setVisible(false);
				MainScreen.scoreScreen.setVisible(false);
				MainScreen.initializeSendReviewScreen();
			}
		});
		askForAReviewBtn.setBounds(10, 244, 100, 30);
		askForAReviewBtn.setText("Ask For a Review");
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
