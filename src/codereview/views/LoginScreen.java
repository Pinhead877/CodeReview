package codereview.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.Player;
import codereview.data.Files;

public class LoginScreen extends Composite {
	private Text mailInput;
	private Text passwordInput;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LoginScreen(Composite parent, int style) {
		super(parent, style);
		
		Label logoLabel = new Label(this, SWT.BORDER | SWT.CENTER);
		logoLabel.setFont(SWTResourceManager.getFont("System", 21, SWT.BOLD));
		logoLabel.setAlignment(SWT.CENTER);
		logoLabel.setBounds(10, 10, 235, 102);
		logoLabel.setText("\r\nCode Review");
		
		Label explainLabel = new Label(this, SWT.WRAP);
		explainLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		explainLabel.setBounds(10, 118, 235, 43);
		explainLabel.setText("Login with user mail and the password you recieved.");
		
		Composite loginComp = new Composite(this, SWT.BORDER | SWT.NO_FOCUS);
		loginComp.setBounds(10, 167, 235, 172);
		
		Label mailLabel = new Label(loginComp, SWT.NONE);
		mailLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		mailLabel.setBounds(10, 10, 67, 21);
		mailLabel.setText("e-Mail:");
		
		mailInput = new Text(loginComp, SWT.BORDER);
		mailInput.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		mailInput.setBounds(10, 37, 211, 28);
		mailInput.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13 || e.keyCode==16777296){
					login(explainLabel);
				}
			}
		});
		
		Label passwordLabel = new Label(loginComp, SWT.NONE);
		passwordLabel.setText("Password:");
		passwordLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		passwordLabel.setBounds(10, 71, 89, 21);
		
		passwordInput = new Text(loginComp, SWT.BORDER | SWT.PASSWORD);
		passwordInput.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		passwordInput.setBounds(10, 98, 211, 28);
		passwordInput.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13 || e.keyCode==16777296){
					login(explainLabel);
				}
			}
		});
		
		Button loginBtn = new Button(loginComp, SWT.NONE);
		loginBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				login(explainLabel);
			}
		});
		loginBtn.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		loginBtn.setBounds(140, 132, 81, 28);
		loginBtn.setText("Login");

	}

	protected void goToFirstScreen() {
		setVisible(false);
		MainScreen.firstScreen();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	private void login(Label explainLabel) {
		String mail = mailInput.getText(), password = passwordInput.getText();
		Player player = null;
		try {
			player = MainScreen.handler.loadPlayer(mail, password);
		} catch (Exception e1) {
			explainLabel.setText("Error connecting to server...");
			e1.printStackTrace();
			return;
		}
		if(player==null){
			explainLabel.setText("User not found,\nCheck the Username or Password.");
			return;
		}
		Files.saveData(mail, password);
		MainScreen.setPlayer(player);
		goToFirstScreen();
	}
}
