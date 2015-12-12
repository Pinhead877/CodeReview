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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.*;
import codereview.data.DataHandler;

public class MainScreen extends ViewPart {
	public MainScreen() {
		
	}
	
	public static ReviwerScreen reviwerScreen;
	public static CreatorScreen creatorScreen;
	public static ScoreScreen scoreScreen;
	public static MainMenu mainMenuScreen;
	public static SendReviewScreen sendReviewScreen;
	public static ReviewsList reviewsListScreen;
	
	public static Composite mainScreen;
	
	private static Team team;
	private static Player player;
	
	public static DataHandler handler;
	

	@Override
	public void createPartControl(Composite parent) {
		
		try {
			handler = new DataHandler();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mainScreen = parent;
		
		player = new Player("Alex", team, "alex@google.com");
		team = new Team("Power Rangers");
		team.addNewTeamMember(player);
		
		try {
			team.setId(handler.saveTeamAndGetID(team));
			player.setId(handler.savePlayerAndGetID(player, "123456789"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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

	public static void initializeReviewerScreen(Segment seg) {
		reviwerScreen = new ReviwerScreen(mainScreen, SWT.NONE, player, seg);
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
	
	public static void initializeReviewsList() {
		reviewsListScreen = new ReviewsList(mainScreen, SWT.NONE, player);
		reviewsListScreen.setBounds(0, 169, 236, 368);
		reviewsListScreen.setVisible(true);
	}
	
	public static Player getPlayer() {
		return player;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
}
