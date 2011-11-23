package net.rolimhf.GameEssentials.Resources;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public final class ResourceHelper {
	
	public static URL getResourcePath(Object obj, String resource)
	{
		return obj.getClass().getResource(resource);
	};
	
	public static InputStream getResourceAsStream(Object obj, String resource) {
		return obj.getClass().getResourceAsStream(resource);
	}
	
	public static Image getResourceImage(Object obj, String resource)
	{
		try {
			return ImageIO.read(obj.getClass().getResourceAsStream(resource));
		} catch (IOException e) {
			return null;
		}
	}
	
	public static Image getInternetImage(String url)  {
		try {
			return new ImageIcon(new URL(url)).getImage();
		} catch (MalformedURLException e) {
			return null;
		}
	}
	
	public static OutputStream getResourceWrite(Object obj, String resource)
	{
		try {
			URL url = getResourcePath(obj, resource);
			return new FileOutputStream(url.toURI().getPath());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}

	}

}
