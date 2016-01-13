package codereview.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.Player;
import codereview.viewsoverride.CR_Composite;

public class ScoreScreen extends CR_Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ScoreScreen(Composite parent, int style, Player player) {
		super(parent, style);
		
		Label playerPicLabel = new Label(this, SWT.NONE);
		playerPicLabel.setAlignment(SWT.CENTER);
		playerPicLabel.setImage(SWTResourceManager.getImage(MainScreen.class, "/codereview/assets/Avatar.jpeg"));
		playerPicLabel.setBounds(10, 10, 64, 64);
		
		Label teamPicLabel = new Label(this, SWT.NONE);
		teamPicLabel.setAlignment(SWT.CENTER);
		teamPicLabel.setImage(SWTResourceManager.getImage(MainScreen.class, "/codereview/assets/teamAvatar.jpeg"));
		teamPicLabel.setBounds(172, 10, 64, 64);
		
		Label MyTeamScore = new Label(this, SWT.NONE);
		MyTeamScore.setText("My Team Score:");
		MyTeamScore.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		MyTeamScore.setBounds(10, 131, 156, 30);
		
		Label teamScoreNum = new Label(this, SWT.NONE);
		teamScoreNum.setText(player.getTeam().getPoints()+"");
		teamScoreNum.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		teamScoreNum.setAlignment(SWT.RIGHT);
		teamScoreNum.setBounds(172, 131, 64, 30);
		
		Label myScoreNum = new Label(this, SWT.NONE);
		myScoreNum.setText(player.getPoints()+"");
		myScoreNum.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		myScoreNum.setAlignment(SWT.RIGHT);
		myScoreNum.setBounds(172, 95, 64, 30);
		
		Label MyScore = new Label(this, SWT.NONE);
		MyScore.setText("My Score:");
		MyScore.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		MyScore.setBounds(10, 95, 156, 30);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
