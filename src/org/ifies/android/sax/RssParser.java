package org.ifies.android.sax;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The main RSS Parser for TWiTCast
 * @author Tane Piper
 */
public class RssParser extends DefaultHandler
{     
    /**
     * The constructor for the RSS Parser
     * @param url
     */
    public RssParser(String url) {
        this.urlString = url;
        this.text = new StringBuilder();
    }
    
    /**
     * Returns the feed as a RssFeed, which is a ListArray
     * @return RssFeed rssFeed
     */
    public Channel getChannel() {
        return (this.channel);
    }
   
    public void parse() throws ParserConfigurationException, SAXException, IOException {
        InputStream urlInputStream = null;
        SAXParserFactory spf = null;
        SAXParser sp = null;
        URL url = new URL(this.urlString);
        urlInputStream = url.openConnection().getInputStream();           
        spf = SAXParserFactory.newInstance();
        if (spf != null) {
        	sp = spf.newSAXParser();
            sp.parse(urlInputStream, this);
        }
        if (urlInputStream != null) urlInputStream.close();
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
    	
    	/** First lets check for the channel */
    	if (localName.equalsIgnoreCase("channel")) {
    		this.channel = new Channel();
    	}
    	
    	/** Now lets check for an item */
        if (localName.equalsIgnoreCase("item") && (this.channel != null)) {
            this.item = new Item();
            this.channel.addItem(this.item);
        }
        
        /** Now lets check for an image */
        if (localName.equalsIgnoreCase("image") && (this.channel != null)) {
        	this.imgStatus = true;
        }
            
    }
   
    /**
     * This is where we actually parse for the elements contents
     */
    public void endElement(String uri, String localName, String qName) {
    	/** Check we have an RSS Feed */
        if (this.channel == null) {
            return;
        }
        
        /** Check are at the end of an item */
        if (localName.equalsIgnoreCase("item")) {
        	this.item = null;
        }
            
        /** Check we are at the end of an image */
        if (localName.equalsIgnoreCase("image"))
            this.imgStatus = false;
       
        /** Now we need to parse which title we are in */
        if (localName.equalsIgnoreCase("title"))
        {
        	/** We are an item, so we set the item title */
            if (this.item != null){
            	this.item.setTitle(this.text.toString().trim());
            /** We are in an image */
            } else  {
            	this.channel.setTitle(this.text.toString().trim());
            }
        }       
       
        /** Now we are checking for a link */
        if (localName.equalsIgnoreCase("link")) {
        	/** Check we are in an item **/
            if (this.item != null) {
            	this.item.setLink(this.text.toString().trim());
            /** Check we are in an image */
            } else if (this.imgStatus) {
            	this.channel.setImage(this.text.toString().trim());
            /** Check we are in a channel */
            } else {
				this.channel.setLink(this.text.toString().trim());
            }
        }       
       
        /** Checking for a description */
        if (localName.equalsIgnoreCase("description")) {
        	/** Lets check we are in an item */
            if (this.item != null) {
            	this.item.setDescription(this.text.toString().trim());
            /** Lets check we are in the channel */
            } else {
            	this.channel.setDescription(this.text.toString().trim());
            }
        }
        
        /** Check for the category */
        if (localName.equalsIgnoreCase("category") && (this.item != null)) {
        	this.channel.addCategory(this.text.toString().trim());
        }
        
        this.text.setLength(0);
    }
   
    public void characters(char[] ch, int start, int length) {
        this.text.append(ch, start, length);
    }
    
    private String urlString;
    private Channel channel;
    private StringBuilder text;
    private Item item;
    private boolean imgStatus;
}