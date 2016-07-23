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

		int level =((player.getPoints()/100)+1);

		Label playerPicLabel = new Label(this, SWT.NONE);
		playerPicLabel.setAlignment(SWT.CENTER);
		playerPicLabel.setImage(SWTResourceManager.getImage(MainScreen.class, "/codereview/assets/avatars/profile/"+player.getImagePath()+".jpg"));
		playerPicLabel.setBounds(10, 10, 64, 64);

		Label levelPic = new Label(this, SWT.NONE);
		levelPic.setAlignment(SWT.CENTER);
		try{
			if(level>10)
				throw new Exception("Level is very high for this Demo!");
			levelPic.setImage(SWTResourceManager.getImage(MainScreen.class, "/codereview/assets/levels/level"+level+".png"));
		}catch(Exception e){
			e.printStackTrace();
			levelPic.setImage(SWTResourceManager.getImage(MainScreen.class, "/codereview/assets/levels/levelinf.png"));
		}
		levelPic.setBounds(172, 10, 64, 64);

		//		Label MyTeamScore = new Label(this, SWT.NONE);
		//		MyTeamScore.setText("My Team Score:");
		//		MyTeamScore.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		//		MyTeamScore.setBounds(10, 131, 156, 30);
		//		
		//		Label teamScoreNum = new Label(this, SWT.NONE);
		//		teamScoreNum.setText(player.getTeam().getPoints()+"");
		//		teamScoreNum.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		//		teamScoreNum.setAlignment(SWT.RIGHT);
		//		teamScoreNum.setBounds(172, 131, 64, 30);

		Label myScoreNum = new Label(this, SWT.NONE);
		myScoreNum.setText(player.getPoints()+"");
		myScoreNum.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		myScoreNum.setAlignment(SWT.RIGHT);
		myScoreNum.setBounds(96, 131, 42, 30);

		Label MyScore = new Label(this, SWT.NONE);
		MyScore.setText("My Score:");
		MyScore.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		MyScore.setBounds(10, 131, 89, 30);

		Label playerNameLbl = new Label(this, SWT.NONE);
		playerNameLbl.setText("Hi "+player.getName());
		playerNameLbl.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		playerNameLbl.setBounds(10, 95, 226, 30);
		playerNameLbl.setAlignment(SWT.CENTER);


		Label lblLevel = new Label(this, SWT.NONE);
		lblLevel.setText("Lvl:");
		lblLevel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblLevel.setBounds(169, 131, 26, 30);

		Label label = new Label(this, SWT.NONE);
		label.setText(""+level);
		label.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		label.setAlignment(SWT.RIGHT);
		label.setBounds(201, 131, 35, 30);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
