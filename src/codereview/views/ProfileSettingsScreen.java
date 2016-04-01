package codereview.views;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.Player;
import org.eclipse.swt.widgets.Group;

public class ProfileSettingsScreen extends Composite {
	private Text nameInput;
	private Text passInput;
	private String [] files = {"Batman", "Beard", "Beard2", "Bob", "Cat", "Cat2", "Coockie", "Default", "Devil", "Dog", "Dragon", "Fallout", "Garfield", "Happy", "Homer", "Old", "Wolf"};

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @param player 
	 */
	public ProfileSettingsScreen(Composite parent, int style, Player player) {
		super(parent, style);

		Label screenLogo = new Label(this, SWT.BORDER);
		screenLogo.setText("Profile Settings");
		screenLogo.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		screenLogo.setAlignment(SWT.CENTER);
		screenLogo.setBounds(10, 10, 194, 30);

		Button backBtn = new Button(this, SWT.NONE);
		backBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				backFunction();
			}

		});
		backBtn.setImage(SWTResourceManager.getImage(ProfileSettingsScreen.class, "/codereview/assets/back.png"));
		backBtn.setBounds(210, 13, 25, 25);

		Label mainText = new Label(this, SWT.NONE);
		mainText.setText("Explaination about what to do...");
		mainText.setBounds(10, 46, 225, 55);

		Label nameLbl = new Label(this, SWT.NONE);
		nameLbl.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		nameLbl.setBounds(10, 107, 100, 21);
		nameLbl.setText("Display Name:");

		nameInput = new Text(this, SWT.BORDER);
		nameInput.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		nameInput.setBounds(116, 107, 119, 21);

		Label lblPassword = new Label(this, SWT.NONE);
		lblPassword.setText("Password:");
		lblPassword.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblPassword.setBounds(10, 134, 100, 21);

		passInput = new Text(this, SWT.BORDER);
		passInput.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		passInput.setBounds(116, 134, 119, 21);

		Group grpPickAnAvatar = new Group(this, SWT.NONE);
		grpPickAnAvatar.setText("Pick an Avatar");
		grpPickAnAvatar.setBounds(10, 161, 225, 62);

		ToolBar toolBar = new ToolBar(grpPickAnAvatar, SWT.VERTICAL);
		toolBar.setBounds(10, 17, 205, 35);

		ToolItem item = new ToolItem(toolBar, SWT.DROP_DOWN);
		item.setText(player.getImagePath());
		item.setWidth(100);

		DropdownSelectionListener listenerOne = new DropdownSelectionListener(item);

		Button saveBtn = new Button(this, SWT.NONE);
		saveBtn.setBounds(147, 229, 88, 36);
		saveBtn.setText("Save");
		saveBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					player.setImagePath(item.getText());
					String tempName = nameInput.getText();
					if(!tempName.equals("") && tempName.length()<=10 && tempName.length()>=2){
						player.setName(tempName);
						MainScreen.handler.updatePlayerName(player);
					}else if(tempName.length()>10 || (tempName.length()<3 && !tempName.equals(""))){
						JOptionPane.showMessageDialog(null, "Name must be atleast 2 chars and 10 chars atmost.", "Name Length Error", JOptionPane.ERROR_MESSAGE);
						nameInput.setText("");
						return;
					}
					if(!passInput.getText().equals("")){
						// TODO finish the password save
						MainScreen.handler.updatePassword(player, passInput.getText());
					}
					MainScreen.handler.updateAvatar(player);
					MainScreen.scoreScreen.setVisible(false);
					MainScreen.initializeScoreScreen();
					backFunction();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		item.addSelectionListener(listenerOne);
		
		for(String text: files){
			listenerOne.add(text);
		}

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	private void backFunction() {
		setVisible(false);
		MainScreen.initializeProfileScreen();
	}

	class DropdownSelectionListener extends SelectionAdapter {
		private ToolItem dropdown;

		private Menu menu;

		public DropdownSelectionListener(ToolItem dropdown) {
			this.dropdown = dropdown;
			menu = new Menu(dropdown.getParent().getShell());
		}

		public void add(String item) {
			MenuItem menuItem = new MenuItem(menu, SWT.NONE);
			menuItem.setText(item);
			menuItem.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					MenuItem selected = (MenuItem) event.widget;
					dropdown.setText(selected.getText());
				}
			});
		}

		public void widgetSelected(SelectionEvent event) {
			if (event.detail == SWT.ARROW) {
				ToolItem item = (ToolItem) event.widget;
				Rectangle rect = item.getBounds();
				Point pt = item.getParent().toDisplay(new Point(rect.x, rect.y));
				menu.setLocation(pt.x, pt.y + rect.height);
				menu.setVisible(true);
			} else {
				System.out.println(dropdown.getText() + " Pressed");
			}
		}
	}
}
