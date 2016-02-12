package codereview.views;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.Cons;
import classes.Review;
import classes.Segment;
import codereview.viewsoverride.CR_Composite;

public class SegmentReviewView extends CR_Composite{
	private Text codeText;
	private Text reviewText;
	private String screenName;

	public SegmentReviewView(Composite parent, int style, Segment seg, Review rev, int screen, int lastScreen) {
		super(parent, style);
		switch(screen){
			case 1: 
				screenName = "Review";
				break;
			case 2:
				screenName = "Segment";
				break;
		}
		
		if(seg==null){
			seg = rev.getSeg();
		}else{
			rev = seg.getReview();
		}
		
		Label headerLbl = new Label(this, SWT.BORDER);
		headerLbl.setText(screenName+" View");
		headerLbl.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		headerLbl.setAlignment(SWT.CENTER);
		headerLbl.setBounds(10, 10, 194, 30);
		
		Button backBtn = new Button(this, SWT.NONE);
		backBtn.setImage(SWTResourceManager.getImage(SegmentReviewView.class, "/codereview/assets/back.png"));
		backBtn.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				//TODO - add return to the last screen
				setVisible(false);
				//MainScreen.initializeMainMenu();
				if(lastScreen == Cons.REVIEWS_WROTE_SCREEN){
					MainScreen.initializeReviewsWroteList();
				}else if(lastScreen == Cons.SEGMENTS_WROTE_SCREEN){
					MainScreen.initializeSegmentWroteList();
				}
			}
		});
		backBtn.setBounds(210, 13, 25, 25);
		
		Label explainTextLbl = new Label(this, SWT.NONE);
		explainTextLbl.setText("Read what you wrote.");
		explainTextLbl.setBounds(46, 46, 190, 30);
		
		Label avatarImageLbl = new Label(this, SWT.NONE);
		//int numOfPics = new File("/codereview/assets/avatars/").listFiles().length;
		avatarImageLbl.setImage(SWTResourceManager.getImage(SegmentReviewView.class, "/codereview/assets/avatars/"+(new Random().nextInt(Cons.NUMBER_OF_IMAGES)+1)+".jpg"));
		avatarImageLbl.setBounds(10, 46, 30, 30);
		
		
		codeText = new Text(this, SWT.BORDER | SWT.WRAP);
		codeText.setToolTipText(seg.getComment());
		codeText.setText(seg.getCode());
		codeText.setEditable(false);
		codeText.setBounds(10, 82, 226, 120);
		
		StarRating starRating = new StarRating(this, SWT.NONE);
		starRating.setBounds(10, 208, 226, 25);
		starRating.setSelection(rev.getScore());
		starRating.setEnabled(false);
		
		reviewText = new Text(this, SWT.BORDER | SWT.WRAP);
		reviewText.setText(rev.getReview());
		reviewText.setEditable(false);
		reviewText.setBounds(10, 239, 226, 96);
		
	}

}
