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


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.Player;
import classes.Team;


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
	
	public static ReviwerScreen reviwerScreen;
	public static CreatorScreen creatorScreen;
	public static ScoreScreen scoreScreen;
	public static MainMenu mainMenuScreen;
	public static SendReviewScreen sendReviewScreen;
	
	public static Composite mainScreen;
	
	private static Team team;
	private static Player player;
	

	@Override
	public void createPartControl(Composite parent) {
		
		mainScreen = parent;
		
		player = new Player("Alex");
		team = new Team("Power Rangers");
		team.addNewTeamMember(player);
		
		parent.setLayout(null);
		parent.setBackground(SWTResourceManager.getColor(240, 240, 240));
		
		initializeScoreScreen();
		initializeMainMenu();
	}

	public static void initializeSendReviewScreen() {
		sendReviewScreen = new SendReviewScreen(mainScreen, SWT.NONE, player);
		sendReviewScreen.setBounds(0, 0, 236, 540);
		sendReviewScreen.setVisible(true);
	}

	public static void initializeReviewerScreen() {
		reviwerScreen = new ReviwerScreen(mainScreen, SWT.NONE, player);
		reviwerScreen.setVisible(true);
		reviwerScreen.setBounds(0, 169, 236, 368);
	}

	public static void initializeCreatorScreen() {
		creatorScreen = new CreatorScreen(mainScreen, SWT.NONE, player);
		creatorScreen.setVisible(true);
		creatorScreen.setBounds(0, 169, 236, 368);
	}

	public static void initializeMainMenu() {
		mainMenuScreen = new MainMenu(mainScreen, SWT.NONE, player);
		mainMenuScreen.setBounds(0, 169, 236, 368);
		mainMenuScreen.setVisible(true);
	}

	public static void initializeScoreScreen() {
		scoreScreen = new ScoreScreen(mainScreen, SWT.NONE, player);
		scoreScreen.setBounds(0, 0, 594, 167);
		scoreScreen.setVisible(true);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
}
