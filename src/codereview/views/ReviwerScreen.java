package codereview.views;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.swing.JOptionPane;

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
import classes.Player;
import classes.Points;
import classes.Segment;
import codereview.data.DataHandler;
import codereview.viewsoverride.CR_Composite;

public class ReviwerScreen extends CR_Composite {
	private Text codeText;
	private Text reviewText;
	private StarRating starRating;

	/**
	 * Create the composite.
	 * 
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

		starRating = new StarRating(this, SWT.NONE);
		starRating.setBounds(10, 208, 226, 25);
		starRating.setSelection(0);

		Button backBtn = new Button(this, SWT.NONE);
		backBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				goBackToMainMenu();
			}
		});
		backBtn.setImage(SWTResourceManager.getImage(ReviwerScreen.class, "/codereview/assets/back.png"));
		backBtn.setBounds(210, 13, 25, 25);

		Label reviewLbl = new Label(this, SWT.WRAP);
		reviewLbl.setBounds(46, 46, 190, 30);
		reviewLbl.setText("Double Click the code to open it in the browser.");

		codeText = new Text(this, SWT.BORDER | SWT.WRAP);
		codeText.setEditable(false);
		codeText.setText(seg.getCode());
		codeText.setToolTipText(seg.getComment());
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
							new URL("http://" + Cons.PATH_TO_SERVER + "/codereview/segmentView.php?sid=" + seg.getSegId()));
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		reviewText = new Text(this, SWT.BORDER);
		reviewText.setBounds(10, 239, 226, 96);

		Button sendBtn = new Button(this, SWT.NONE);
		sendBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if(!reviewText.getText().equals("") && starRating.getSelection()>0){
						new DataHandler().saveReview(seg.getSegId(), starRating.getSelection(), reviewText.getText(),
								player.getId());
						MainScreen.updatePlayerPoints(player, Points.POINTS_FOR_CREATING_NEW_REVIEW);
						goBackToMainMenu();
					}else if(starRating.getSelection()==0){
						JOptionPane.showMessageDialog(null, "Please set your score for this code.", "Fill all the fields", JOptionPane.ERROR_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "Please write your review about the code.", "Fill all the fields", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		sendBtn.setBounds(161, 341, 75, 25);
		sendBtn.setText("Send");

		Label randomAvatar = new Label(this, SWT.NONE);
		// TODO change number of images to be dynamic
		randomAvatar.setImage(SWTResourceManager.getImage(ReviwerScreen.class,
				"/codereview/assets/avatars/" + (new Random().nextInt(Cons.NUMBER_OF_IMAGES) + 1) + ".jpg"));
		randomAvatar.setBounds(10, 46, 30, 30);

	}

	protected void goBackToMainMenu() {
		setVisible(false);
		MainScreen.initializeReviewsList(Cons.MAIN_MENU_SCREEN);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
