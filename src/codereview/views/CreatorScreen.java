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

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import codereview.viewsoverride.LabelButton;

public class CreatorScreen extends CR_Composite {

	public CreatorScreen(Composite parent, int style, Player player) {
		super(parent, style);
		setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		
		LabelButton ReviewsWaitingLabel = new LabelButton(this, SWT.NONE);
		ReviewsWaitingLabel.setText("Reviews Waiting:");
		ReviewsWaitingLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		ReviewsWaitingLabel.setBounds(10, 215, 156, 23);
		
		LabelButton ReviewsIWroteLabel = new LabelButton(this, SWT.NONE);
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
		qualitysIWroteNum.setText("TEXT");
		qualitysIWroteNum.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		qualitysIWroteNum.setAlignment(SWT.RIGHT);
		qualitysIWroteNum.setBounds(172, 121, 64, 23);
		
		Label MyReviewsLabel = new Label(this, SWT.BORDER);
		MyReviewsLabel.setText("My Reviews");
		MyReviewsLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		MyReviewsLabel.setAlignment(SWT.CENTER);
		MyReviewsLabel.setBounds(10, 150, 226, 30);
		
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
		
		LabelButton SegmentsIWroteLabel = new LabelButton(this, SWT.NONE);
		SegmentsIWroteLabel.setText("Segments I Wrote:");
		SegmentsIWroteLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		SegmentsIWroteLabel.setBounds(10, 92, 156, 23);
		
		Label MySegmentsLabel = new Label(this, SWT.BORDER);
		MySegmentsLabel.setText("My Segments");
		MySegmentsLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		MySegmentsLabel.setAlignment(SWT.CENTER);
		MySegmentsLabel.setBounds(10, 56, 226, 30);
		
		Label ReviewsWatingLabel = new Label(this, SWT.NONE);
		try {
			ReviewsWatingLabel.setText(new DataHandler().getNumberOfReviewsWaitingByPlayer(player.getId()));
		} catch (Exception e1) {
			ReviewsWatingLabel.setText("???");
			e1.printStackTrace();
		}
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
