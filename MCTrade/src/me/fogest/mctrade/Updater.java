/*
 * MCTrade
 * Copyright (C) 2012 Fogest <http://fogest.net16.net> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package me.fogest.mctrade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Updater {

	private double version = 1.00;
	private double webVersion = 0.00;
	private boolean updated;
	private String inputLine;

	public Updater() {
		URL MCTrade;
		try {
			MCTrade = new URL("http://code.fogest.net16.net/version.html");

			URLConnection yc = MCTrade.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

			inputLine = in.readLine();
			webVersion = Double.parseDouble(inputLine);
			in.close();
		} catch (MalformedURLException e) {
			webVersion = 0.00;
		} catch (IOException e) {
			webVersion = 0.00;
		}
	}

	public String checkForUpdate() {
		String message = "Error checking for update";
		if (webVersion == version) {
			message = "MCTrade is currently up to date";
		} else if (webVersion == 0.00) {
			message = "There was an error retrieving the latest version of the plugin. This is not your fault!";
		} else if (webVersion != version) {
			message = "You are not using the latest version! The latest version is: " + webVersion
					+ " and you are using " + version + "Bukkit Page: http://bit.ly/MCTrade";
		}

		return message;
	}
}
