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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

public class MainScreen extends ViewPart {
	public MainScreen() {
		
	}
	
	public static ReviwerScreen reviwerMode;
	public static CreatorScreen creatorScreen;
	public static ScoreScreen scoreScreen;
	public static MainMenu mainMenu;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(null);
		
		parent.setBackground(SWTResourceManager.getColor(240, 240, 240));
		
		scoreScreen = new ScoreScreen(parent, SWT.NONE);
		scoreScreen.setBounds(0, 0, 594, 167);
		scoreScreen.setVisible(true);
		
		mainMenu = new MainMenu(parent, SWT.NONE);
		mainMenu.setBounds(0, 169, 236, 368);
		mainMenu.setVisible(true);
		
		creatorScreen = new CreatorScreen(parent, SWT.NONE);
		creatorScreen.setVisible(false);
		creatorScreen.setBounds(0, 169, 236, 368);
		
		reviwerMode = new ReviwerScreen(parent, SWT.NONE);
		reviwerMode.setVisible(false);
		reviwerMode.setBounds(0, 169, 236, 368);
		
	}

	public static SelectionAdapter GoToCreatorScreen = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			mainMenu.setVisible(false);
			creatorScreen.setVisible(true);
		}
	};
	
	public static SelectionAdapter GoToReviwerScreen = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			mainMenu.setVisible(false);
			reviwerMode.setVisible(true);
		}
	};
	
	

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
}
