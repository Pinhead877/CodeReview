/*******************************************************************************
 * Copyright (c) 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package codereview.views;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.part.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects usin
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class CreatorScreen extends ViewPart {
	private Text searchTextBox;
	private Composite reviwerMode, creatorMode; 
	private boolean creator;
	public CreatorScreen() {
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(null);
		
		creator = true;
		
		creatorMode = new Composite(parent, SWT.NONE);
		creatorMode.setBounds(0, 209, 594, 281);
		
		Label label_13 = new Label(creatorMode, SWT.NONE);
		label_13.setText("Reviews Waiting:");
		label_13.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_13.setBounds(10, 159, 156, 23);
		
		Label label_12 = new Label(creatorMode, SWT.NONE);
		label_12.setText("Reviews I Wrote:");
		label_12.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		label_12.setBounds(10, 130, 156, 23);
		
		Label label_9 = new Label(creatorMode, SWT.NONE);
		label_9.setText("36");
		label_9.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_9.setAlignment(SWT.RIGHT);
		label_9.setBounds(172, 36, 64, 23);
		
		Label label_8 = new Label(creatorMode, SWT.NONE);
		label_8.setText("Quality I Wrote:");
		label_8.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_8.setBounds(10, 65, 156, 23);
		
		Label label_10 = new Label(creatorMode, SWT.NONE);
		label_10.setText("4");
		label_10.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_10.setAlignment(SWT.RIGHT);
		label_10.setBounds(172, 65, 64, 23);
		
		Label label_11 = new Label(creatorMode, SWT.BORDER);
		label_11.setText("My Reviews");
		label_11.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		label_11.setAlignment(SWT.CENTER);
		label_11.setBounds(10, 94, 226, 30);
		
		Button searchButton = new Button(creatorMode, SWT.NONE);
		searchButton.setImage(SWTResourceManager.getImage(CreatorScreen.class, "/codereview/assets/search.png"));
		searchButton.setBounds(206, 204, 30, 30);
		
		Label label_14 = new Label(creatorMode, SWT.NONE);
		label_14.setText("47");
		label_14.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_14.setAlignment(SWT.RIGHT);
		label_14.setBounds(172, 130, 64, 23);
		
		Label label_7 = new Label(creatorMode, SWT.NONE);
		label_7.setText("Segments I Wrote:");
		label_7.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		label_7.setBounds(10, 36, 156, 23);
		
		Label label_6 = new Label(creatorMode, SWT.BORDER);
		label_6.setText("My Segments");
		label_6.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		label_6.setAlignment(SWT.CENTER);
		label_6.setBounds(10, 0, 226, 30);
		
		searchTextBox = new Text(creatorMode, SWT.BORDER);
		searchTextBox.setToolTipText("Search...");
		searchTextBox.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		searchTextBox.setBounds(10, 204, 190, 30);
		
		Label label_15 = new Label(creatorMode, SWT.NONE);
		label_15.setText("3");
		label_15.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_15.setAlignment(SWT.RIGHT);
		label_15.setBounds(172, 159, 64, 23);
		
		reviwerMode = new Composite(parent, SWT.NONE);
		reviwerMode.setVisible(true);
		reviwerMode.setBounds(0, 209, 594, 281);
		
		Composite scoreScreen = new Composite(parent, SWT.NONE);
		scoreScreen.setVisible(true);
		scoreScreen.setBounds(0, 0, 594, 203);
		
		Label label_16 = new Label(scoreScreen, SWT.NONE);
		label_16.setAlignment(SWT.CENTER);
		label_16.setImage(SWTResourceManager.getImage(CreatorScreen.class, "/codereview/assets/Avatar.jpeg"));
		label_16.setBounds(10, 10, 64, 64);
		
		Label label_17 = new Label(scoreScreen, SWT.NONE);
		label_17.setAlignment(SWT.CENTER);
		label_17.setImage(SWTResourceManager.getImage(CreatorScreen.class, "/codereview/assets/teamAvatar.jpeg"));
		label_17.setBounds(172, 10, 64, 64);
		
		Label label_18 = new Label(scoreScreen, SWT.NONE);
		label_18.setText("My Team Score:");
		label_18.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		label_18.setBounds(10, 131, 156, 30);
		
		Label label_19 = new Label(scoreScreen, SWT.NONE);
		label_19.setText("1578");
		label_19.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		label_19.setAlignment(SWT.RIGHT);
		label_19.setBounds(172, 131, 64, 30);
		
		Label label_20 = new Label(scoreScreen, SWT.NONE);
		label_20.setText("250");
		label_20.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		label_20.setAlignment(SWT.RIGHT);
		label_20.setBounds(172, 95, 64, 30);
		
		Label label_21 = new Label(scoreScreen, SWT.NONE);
		label_21.setText("My Score:");
		label_21.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		label_21.setBounds(10, 95, 156, 30);
		
		Button modeToggle = new Button(scoreScreen, SWT.NONE);
		modeToggle.setToolTipText("Click here to change mode");
		modeToggle.setImage(SWTResourceManager.getImage(CreatorScreen.class, "/codereview/assets/creator.jpg"));
		modeToggle.setBounds(37, 167, 176, 36);
		modeToggle.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				if(event.type == SWT.Selection){
					if(creator){
						creator = false;
						modeToggle.setImage(SWTResourceManager.getImage(CreatorScreen.class, "/codereview/assets/reviewer.png"));
						reviwerMode.setVisible(true);
						creatorMode.setVisible(false);
					}else{
						creator = true;
						modeToggle.setImage(SWTResourceManager.getImage(CreatorScreen.class, "/codereview/assets/creator.jpg"));
						reviwerMode.setVisible(false);
						creatorMode.setVisible(true);
					}
				}
			}
		});
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
}
