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
