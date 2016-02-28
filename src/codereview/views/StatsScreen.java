package codereview.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.Player;
import codereview.data.DataHandler;

import org.eclipse.swt.widgets.Button;

public class StatsScreen extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public StatsScreen(Composite parent, int style, Player player) {
		super(parent, style);
		
		Label statsLogo = new Label(this, SWT.BORDER);
		statsLogo.setText("Stats");
		statsLogo.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		statsLogo.setAlignment(SWT.CENTER);
		statsLogo.setBounds(10, 10, 140, 30);
		
		Button back = new Button(this, SWT.NONE);
		back.setImage(SWTResourceManager.getImage(StatsScreen.class, "/codereview/assets/back.png"));
		back.setBounds(204, 10, 25, 25);
		back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setVisible(false);
				MainScreen.initializeProfileScreen();
			}
		});
		
		Label segWroteLabel = new Label(this, SWT.NONE);
		segWroteLabel.setBounds(10, 46, 140, 15);
		segWroteLabel.setText("Segments Wrote: ");
		
		Label segWroteNum = new Label(this, SWT.NONE);
		segWroteNum.setAlignment(SWT.RIGHT);
		segWroteNum.setBounds(174, 46, 55, 15);
		try {
			segWroteNum.setText(""+new  DataHandler().getNumberOfSegmentsByPlayer(player.getId()));
		} catch (Exception e) {
			segWroteNum.setText("NA");
		}
		
		Label revWroteLabel = new Label(this, SWT.NONE);
		revWroteLabel.setText("Reviews Wrote: ");
		revWroteLabel.setBounds(10, 67, 140, 15);
		
		Label revWroteNum = new Label(this, SWT.NONE);
		revWroteNum.setAlignment(SWT.RIGHT);
		try {
			revWroteNum.setText(""+new DataHandler().getNumberOfReviewsByPlayer(player.getId()));
		} catch (Exception e) {
			revWroteNum.setText("NA");
		}
		revWroteNum.setBounds(174, 67, 55, 15);
		
		Label revRecievedLabel = new Label(this, SWT.NONE);
		revRecievedLabel.setText("Reviews Recieved:");
		revRecievedLabel.setBounds(10, 88, 140, 15);
		
		Label revRecvNum = new Label(this, SWT.NONE);
		revRecvNum.setAlignment(SWT.RIGHT);
		try {
			revRecvNum.setText(""+new DataHandler().getNumberOfReviewsRecievedByPlayer(player));
		} catch (Exception e1) {
			revRecvNum.setText("NA");
		}
		revRecvNum.setBounds(174, 88, 55, 15);
		
		Label avgWordsLabel = new Label(this, SWT.NONE);
		avgWordsLabel.setText("Average Words in Review:");
		avgWordsLabel.setBounds(10, 109, 140, 15);
		
		Label avgWordsRevNum = new Label(this, SWT.NONE);
		avgWordsRevNum.setAlignment(SWT.RIGHT);
		try {
			avgWordsRevNum.setText(""+new DataHandler().getAverageNumverOfWordsInReview(player));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			avgWordsRevNum.setText("NA");
		}
		avgWordsRevNum.setBounds(174, 109, 55, 15);
		
		Label avgScoreRecievedLabel = new Label(this, SWT.NONE);
		avgScoreRecievedLabel.setText("Average Score Recieved:");
		avgScoreRecievedLabel.setBounds(10, 130, 140, 15);
		
		Label avgScoreRcvNum = new Label(this, SWT.NONE);
		avgScoreRcvNum.setAlignment(SWT.RIGHT);
		try {
			avgScoreRcvNum.setText(""+new DataHandler().getAverageScoreRecievedByPlayer(player));
		} catch (Exception e) {
			avgScoreRcvNum.setText("NA");
		}
		avgScoreRcvNum.setBounds(174, 130, 55, 15);
		
		Label avgScoreGivenLabel = new Label(this, SWT.NONE);
		avgScoreGivenLabel.setText("Average Score Given:");
		avgScoreGivenLabel.setBounds(10, 151, 140, 15);
		
		Label avgScoreGivNum = new Label(this, SWT.NONE);
		avgScoreGivNum.setAlignment(SWT.RIGHT);
		try {
			avgScoreGivNum.setText(""+new DataHandler().getAverageScoreGivenByPlayer(player));
		} catch (Exception e) {
			avgScoreGivNum.setText("NA");
		}
		avgScoreGivNum.setBounds(174, 151, 55, 15);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
