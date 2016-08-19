package codereview.views;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.Cons;
import classes.Review;
import classes.Segment;
import codereview.data.DataHandler;
import codereview.viewsoverride.CR_Composite;

public class SegmentReviewView extends CR_Composite{
	private Text codeText;
	private Text reviewText;
	private String screenName;
	private Segment segment;
	private Review review;
	private int lastScreen;
	public SegmentReviewView(Composite parent, int style, Segment seg, Review rev, int screen, int lastScrn) {
		super(parent, style);
		
		this.lastScreen = lastScrn;
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
			try {
				if(new DataHandler().checkIfSegmentHasReviews(seg)){
					rev = new Review(-2,seg, "Multiple Reviews!\nDouble Click here to see all reviews.", 0, false);
					rev.setScore(new DataHandler().getAvgScoreOfSeg(seg));
				}else{
					rev = new Review(-1,seg,"No reviews yet...",0,false);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				rev = new Review(-1,seg, "Error: getting reviews...", 0, false);
			}
		}

		segment = seg;
		review = rev;

		System.out.println(review);

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
				setVisible(false);
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
		avatarImageLbl.setImage(SWTResourceManager.getImage(SegmentReviewView.class, "/codereview/assets/avatars/"+(new Random().nextInt(Cons.NUMBER_OF_IMAGES)+1)+".jpg"));
		avatarImageLbl.setBounds(10, 46, 30, 30);


		codeText = new Text(this, SWT.BORDER | SWT.WRAP);
		codeText.setToolTipText(seg.getComment());
		codeText.setText(seg.getCode());
		codeText.setEditable(false);
		codeText.setBounds(10, 82, 226, 120);
		codeText.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent arg0) {
			}

			@Override
			public void mouseDown(MouseEvent arg0) {
			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				try {
					final IWebBrowser browser = PlatformUI.getWorkbench().getBrowserSupport()
							.createBrowser("CodeReview");
					browser.openURL(
							new URL("http://" + Cons.PATH_TO_SERVER + "/codereview/segmentView.php?sid=" + segment.getSegId()));
				} catch (PartInitException e1) {
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
			}
		});

		StarRating starRating = new StarRating(this, SWT.NONE);
		starRating.setBounds(10, 208, 226, 25);
		starRating.setSelection(rev.getScore());
		starRating.setEnabled(false);

		reviewText = new Text(this, SWT.BORDER | SWT.WRAP);
		reviewText.setText(rev.getReview());
		reviewText.setEditable(false);
		reviewText.setBounds(10, 239, 226, 96);
		reviewText.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent arg0) {
			}

			@Override
			public void mouseDown(MouseEvent arg0) {
			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				if(review.getRevId()!=-1){
					try {
						final IWebBrowser browser = PlatformUI.getWorkbench().getBrowserSupport()
								.createBrowser("CodeReview");
						browser.openURL(
								new URL("http://" + Cons.PATH_TO_SERVER + "/codereview/reviewView.php?sid=" + segment.getSegId()));
					} catch (PartInitException e1) {
						e1.printStackTrace();
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

	}

}
