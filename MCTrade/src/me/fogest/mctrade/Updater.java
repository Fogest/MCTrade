//Made by h31ix <http://forums.bukkit.org/members/h31ix.1195/>

/*
 * Updater for Bukkit.
 *
 * This class provides the means to safetly and easily update a plugin, or check to see if it is updated using dev.bukkit.org
 */

package me.fogest.mctrade;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Updater 
{
    private Plugin plugin;
    private UpdateType type;
    private String versionTitle;
    private URL url; // Connecting to RSS
    private static final String DBOUrl = "http://dev.bukkit.org/server-mods/"; // Slugs will be appended to this to get to the project's RSS feed
    private String [] noUpdateTag = {"-DEV","-PRE"}; // If the version number contains one of these, don't update.
    private String updateFolder = YamlConfiguration.loadConfiguration(new File("bukkit.yml")).getString("settings.update-folder"); // The folder that downloads will be placed in
    // Strings for reading RSS
    private static final String TITLE = "title";
    private static final String LINK = "link";
    private static final String ITEM = "item";    
    
    /**
    * Gives the dev the result of the update process. Can be obtained by called getResult().
    */     
    public enum UpdateResult
    {
        /**
        * The updater found an update, and has readied it to be loaded the next time the server restarts/reloads.
        */        
        SUCCESS(1),
        /**
        * The updater did not find an update, and nothing was downloaded.
        */        
        NO_UPDATE(2),
        /**
        * The updater found an update, but was unable to download it.
        */        
        FAIL_DOWNLOAD(3),
        /**
        * For some reason, the updater was unable to contact dev.bukkit.org to download the file.
        */        
        FAIL_DBO(4),
        /**
        * When running the version check, the file on DBO did not contain the a version in the format 'vVersion' such as 'v1.0'.
        */        
        FAIL_NOVERSION(5),
        /**
        * The slug provided by the plugin running the updater was invalid and doesn't exist on DBO.
        */        
        FAIL_BADSLUG(6),
        /**
        * The updater found an update, but because of the UpdateType being set to NO_DOWNLOAD, it wasn't downloaded.
        */        
        UPDATE_AVAILABLE(7);        
        
        private static final Map<Integer, Updater.UpdateResult> valueList = new HashMap<Integer, Updater.UpdateResult>();
        private final int value;
        
        private UpdateResult(int value)
        {
            this.value = value;
        }
        
        public int getValue()
        {
            return this.value;
        }
        
        public static Updater.UpdateResult getResult(int value)
        {
            return valueList.get(value);
        }
        
        static
        {
            for(Updater.UpdateResult result : Updater.UpdateResult.values())
            {
                valueList.put(result.value, result);
            }
        }
    }
    
    /**
    * Allows the dev to specify the type of update that will be run.
    */     
    public enum UpdateType
    {
        /**
        * Run a version check, and then if the file is out of date, download the newest version.
        */        
        DEFAULT(1),
        /**
        * Don't run a version check, just find the latest update and download it.
        */        
        NO_VERSION_CHECK(2),
        /**
        * Get information about the version and the download size, but don't actually download anything.
        */        
        NO_DOWNLOAD(3);
        
        private static final Map<Integer, Updater.UpdateType> valueList = new HashMap<Integer, Updater.UpdateType>();
        private final int value;
        
        private UpdateType(int value)
        {
            this.value = value;
        }
        
        public int getValue()
        {
            return this.value;
        }
        
        public static Updater.UpdateType getResult(int value)
        {
            return valueList.get(value);
        }
        
        static
        {
            for(Updater.UpdateType result : Updater.UpdateType.values())
            {
                valueList.put(result.value, result);
            }
        }
    }    
    
    /**
     * Initialize the updater
     * 
     * @param plugin
     *            The plugin that is checking for an update.
     * @param slug
     *            The dev.bukkit.org slug of the project (http://dev.bukkit.org/server-mods/SLUG_IS_HERE)
     * @param file
     *            The file that the plugin is running from, get this by doing this.getFile() from within your main class.
     * @param type
     *            Specify the type of update this will be. See {@link UpdateType}
     * @param announce
     *            True if the program should announce the progress of new updates in console
     */ 
    public Updater(Plugin plugin, String slug)
    {
        this.plugin = plugin;
        try 
        {
            // Obtain the results of the project's file feed
            url = new URL(DBOUrl + slug + "/files.rss");
        } 
        catch (MalformedURLException ex) 
        {
            // The slug doesn't exist
            plugin.getLogger().warning("The author of this plugin has misconfigured their Auto Update system");
            plugin.getLogger().warning("The project slug added ('" + slug + "') is invalid, and does not exist on dev.bukkit.org");
        }
        if(url != null)
        {
            // Obtain the results of the project's file feed
            readFeed();
            if(versionCheck(versionTitle))
            {
            	plugin.getLogger().info("A new version of MCTrade has been released on Bukkit Dev!");
            	plugin.getLogger().info("http://dev.bukkit.org/server-mods/mctrade/files/ to view the latest releases");
            	plugin.getLogger().info("Your using version: "+ plugin.getDescription().getVersion() + ", the latest version is: " + getLatestVersionString());
            }
        }
    }
    
    /**
     * Get the version string latest file avaliable online.
     */      
    public String getLatestVersionString()
    {
        return versionTitle;
    }
    
    /**
     * Check to see if the program should continue by evaluation whether the plugin is already updated, or shouldn't be updated
     */
    private boolean versionCheck(String title)
    {
        if(type != UpdateType.NO_VERSION_CHECK)
        {
            String version = plugin.getDescription().getVersion();
            if(title.split("v").length == 2)
            {
                String remoteVersion = title.split("v")[1].split(" ")[0]; // Get the newest file's version number
                if(hasTag(version) || version.equalsIgnoreCase(remoteVersion))
                {
                    return false;
                }
            }
            else
            {
                // The file's name did not contain the string 'vVersion'
                plugin.getLogger().warning("The author of this plugin has misconfigured their Auto Update system");
                plugin.getLogger().warning("Files uploaded to BukkitDev should contain the version number, seperated from the name by a 'v', such as PluginName v1.0");
                plugin.getLogger().warning("Please notify the author (" + plugin.getDescription().getAuthors().get(0) + ") of this error.");
                return false;
            }
        }
        return true;
    }
        
    /**
     * Evaluate whether the version number is marked showing that it should not be updated by this program
     */  
    private boolean hasTag(String version)
    {
        for(String string : noUpdateTag)
        {
            if(version.contains(string))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Part of RSS Reader by Vogella, modified by H31IX for use with Bukkit
     */     
    @SuppressWarnings("null")
    private void readFeed() 
    {
        try 
        {
            // Set header values intial to the empty string
            String title = "";
            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // Read the XML document
            while (eventReader.hasNext()) 
            {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) 
                {
                    if (event.asStartElement().getName().getLocalPart().equals(TITLE)) 
                    {                  
                        event = eventReader.nextEvent();
                        title = event.asCharacters().getData();
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart().equals(LINK)) 
                    {                  
                        event = eventReader.nextEvent();
                        event.asCharacters().getData();
                        continue;
                    }
                } 
                else if (event.isEndElement()) 
                {
                    if (event.asEndElement().getName().getLocalPart().equals(ITEM)) 
                    {
                        // Store the title and link of the first entry we get - the first file on the list is all we need
                        versionTitle = title;
                        // All done, we don't need to know about older files.
                        break;
                    }
                }
            }
        } 
        catch (XMLStreamException e) 
        {
            throw new RuntimeException(e);
        }
    }  

    /**
     * Open the RSS feed
     */    
    private InputStream read() 
    {
        try 
        {
            return url.openStream();
        } 
        catch (IOException e) 
        {
            throw new RuntimeException(e);
        }
    }
}