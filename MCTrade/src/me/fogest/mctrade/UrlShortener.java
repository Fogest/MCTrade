package me.fogest.mctrade;

import com.rosaloves.bitlyj.*;
import static com.rosaloves.bitlyj.Bitly.*;

public class UrlShortener {
	
	public static String shortenURL(String longLink) {
		Url shortLink = as("fogest", "R_56a19843b53523cb4b5f3b5756fca0e5").call(shorten(longLink));
		return shortLink.toString();
	}
}
