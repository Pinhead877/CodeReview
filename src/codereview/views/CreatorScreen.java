package codereview.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import codereview.viewsoverride.CR_Composite;

public class CreatorScreen extends CR_Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	
	private Text searchTextBox;

	public CreatorScreen(Composite parent, int style) {
		super(parent, style);
		setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		
		Label ReviewsWaitingLabel = new Label(this, SWT.NONE);
		ReviewsWaitingLabel.setText("Reviews Waiting:");
		ReviewsWaitingLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		ReviewsWaitingLabel.setBounds(10, 159, 156, 23);
		
		Label ReviewsIWroteLabel = new Label(this, SWT.NONE);
		ReviewsIWroteLabel.setText("Reviews I Wrote:");
		ReviewsIWroteLabel.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		ReviewsIWroteLabel.setBounds(10, 130, 156, 23);
		
		Label segmentIwroteNum = new Label(this, SWT.NONE);
		segmentIwroteNum.setText("36");
		segmentIwroteNum.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		segmentIwroteNum.setAlignment(SWT.RIGHT);
		segmentIwroteNum.setBounds(172, 36, 64, 23);
		
		Label qualitysIWroteLabel = new Label(this, SWT.NONE);
		qualitysIWroteLabel.setText("Quality I Wrote:");
		qualitysIWroteLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		qualitysIWroteLabel.setBounds(10, 65, 156, 23);
		
		Label qualitysIWroteNum = new Label(this, SWT.NONE);
		qualitysIWroteNum.setText("4");
		qualitysIWroteNum.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		qualitysIWroteNum.setAlignment(SWT.RIGHT);
		qualitysIWroteNum.setBounds(172, 65, 64, 23);
		
		Label MyReviewsLabel = new Label(this, SWT.BORDER);
		MyReviewsLabel.setText("My Reviews");
		MyReviewsLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		MyReviewsLabel.setAlignment(SWT.CENTER);
		MyReviewsLabel.setBounds(10, 94, 226, 30);
		
		Button searchButton = new Button(this, SWT.NONE);
		searchButton.setImage(SWTResourceManager.getImage(MainScreen.class, "/codereview/assets/search.png"));
		searchButton.setBounds(206, 204, 30, 30);
		
		Label ReviewsIWroteNum = new Label(this, SWT.NONE);
		ReviewsIWroteNum.setText("47");
		ReviewsIWroteNum.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		ReviewsIWroteNum.setAlignment(SWT.RIGHT);
		ReviewsIWroteNum.setBounds(172, 130, 64, 23);
		
		Label SegmentsIWroteLabel = new Label(this, SWT.NONE);
		SegmentsIWroteLabel.setText("Segments I Wrote:");
		SegmentsIWroteLabel.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		SegmentsIWroteLabel.setBounds(10, 36, 156, 23);
		
		Label MySegmentsLabel = new Label(this, SWT.BORDER);
		MySegmentsLabel.setText("My Segments");
		MySegmentsLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		MySegmentsLabel.setAlignment(SWT.CENTER);
		MySegmentsLabel.setBounds(10, 0, 226, 30);
		
		searchTextBox = new Text(this, SWT.BORDER);
		searchTextBox.setToolTipText("Search...");
		searchTextBox.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		searchTextBox.setBounds(10, 204, 190, 30);
		
		Label ReviewsWatingLabel = new Label(this, SWT.NONE);
		ReviewsWatingLabel.setText("3");
		ReviewsWatingLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		ReviewsWatingLabel.setAlignment(SWT.RIGHT);
		ReviewsWatingLabel.setBounds(172, 159, 64, 23);
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
