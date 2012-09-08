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

import java.util.logging.Level;

import com.rosaloves.bitlyj.*;
import static com.rosaloves.bitlyj.Bitly.*;

public class UrlShortener {

	public static String shortenURL(String longLink) {
		try {
			Url shortLink = as("fogest", "R_56a19843b53523cb4b5f3b5756fca0e5")
					.call(shorten(longLink));
			String shorty = shortLink.getShortUrl();
			return shorty;
		} catch (Exception e) {
			MCTrade.getPlugin().getLogger()
					.log(Level.INFO, "Error in shorteneing");
			e.printStackTrace();
		}
		return "";
	}
}
