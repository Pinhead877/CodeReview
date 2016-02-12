package codereview.views;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.Cons;
import classes.Player;
import codereview.viewsoverride.CR_Composite;

public class MainMenu extends CR_Composite{
	private Text text;

	public MainMenu(Composite parent, int style, Player player) {
		super(parent, style);
		
		Label logout = new Label(this, SWT.NONE);
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				MainScreen.scoreScreen.setVisible(false);
				setVisible(false);
				MainScreen.setPlayer(null);
				MainScreen.initializeLoginScreen();
			}
		});
		logout.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				logout.setBackground(new Color(null, 220, 220, 220, 255));
			}
			public void mouseExit(MouseEvent e){
				logout.setBackground(new Color(null, 240, 240, 240, 255));
			}
		});
		logout.setDragDetect(false);
		logout.setImage(SWTResourceManager.getImage(MainMenu.class, "/codereview/assets/logout.png"));
		logout.setBounds(10, 10, 30, 32);
		
		Label MainMenuLabel = new Label(this, SWT.NONE);
		MainMenuLabel.setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.BOLD));
		MainMenuLabel.setAlignment(SWT.CENTER);
		MainMenuLabel.setBounds(10, 10, 226, 32);
		MainMenuLabel.setText("Main Menu");
		
		Button CreatorModeBtn = new Button(this, SWT.NONE);
		CreatorModeBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setVisible(false);
				MainScreen.initializeCreatorScreen();
			}
		});
		CreatorModeBtn.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		CreatorModeBtn.setImage(SWTResourceManager.getImage(MainMenu.class, "/codereview/assets/creator.jpg"));
		CreatorModeBtn.setBounds(32, 56, 173, 41);
		
		Button ReviewerModeBtn = new Button(this, SWT.NONE);
		ReviewerModeBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setVisible(false);
				MainScreen.initializeReviewsList(Cons.MAIN_MENU_SCREEN);
			}
		});
		ReviewerModeBtn.setImage(SWTResourceManager.getImage(MainMenu.class, "/codereview/assets/reviewer.png"));
		ReviewerModeBtn.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		ReviewerModeBtn.setBounds(32, 116, 173, 41);
		
		Button ProfileBtn = new Button(this, SWT.NONE);
		ProfileBtn.setImage(SWTResourceManager.getImage(MainMenu.class, "/codereview/assets/profile.png"));
		ProfileBtn.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		ProfileBtn.setBounds(32, 177, 173, 41);
		
		Group grpSearchBox = new Group(this, SWT.NONE);
		grpSearchBox.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		grpSearchBox.setText("Search Box");
		grpSearchBox.setBounds(10, 234, 226, 97);
		
		text = new Text(grpSearchBox, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		text.setBounds(10, 62, 185, 26);
		
		Label label = new Label(grpSearchBox, SWT.NONE);
		label.setText("Search the knowlegde base:");
		label.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		label.setBounds(10, 39, 173, 17);
		
		Button btnSearch = new Button(grpSearchBox, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					final IWebBrowser browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser("CodeReview");
					browser.openURL(new URL("http://localhost/codereview/search.php?q="+text.getText()));
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setImage(SWTResourceManager.getImage(MainMenu.class, "/codereview/assets/search.png"));
		btnSearch.setBounds(197, 62, 25, 25);
		
	}
}
