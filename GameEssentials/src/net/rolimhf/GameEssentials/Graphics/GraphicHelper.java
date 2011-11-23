package net.rolimhf.GameEssentials.Graphics;

import java.applet.Applet;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.geom.Rectangle2D;
import java.awt.image.MemoryImageSource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public final class GraphicHelper {
	private GraphicHelper(){}
	
	/**
	 * Draws a string. Justify: middle
	 * @param graphics
	 * @param str
	 * @param x
	 * @param y
	 */
	public static void drawCenteredString(Graphics2D graphics, String str, int x, int y){
		Rectangle2D rect = graphics.getFont().getStringBounds(str, graphics.getFontRenderContext());
		int width = (int) (x - rect.getWidth() / 2);
		graphics.drawString(str, width, y);
	}
	
	/**
	 * Draws a string. Justify: right
	 * @param graphics
	 * @param str
	 * @param x
	 * @param y
	 */
	public static void drawRightString(Graphics2D graphics, String str, int x, int y) {
		Rectangle2D rect = graphics.getFont().getStringBounds(str, graphics.getFontRenderContext());
		int width = (int) (x - rect.getWidth());
		graphics.drawString(str, width, y);		
	}
	
	/**
	 * Draws a image at x, y from resource.
	 * @param window
	 * @param graphics
	 * @param source
	 * @param path
	 * @param x
	 * @param y
	 */
	public static void drawImageResource(Window window, Graphics2D graphics, Class<?> source, String path, int x, int y){
		URL resource = source.getResource(path);
		Image image;
		try {
			 image = ImageIO.read(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return;
		}
		
		graphics.drawImage(image, x, y, window);
	}
	
	/**
	 * Draws an image from resource.
	 * @param window
	 * @param graphics
	 * @param source
	 * @param path
	 * @param x
	 * @param y
	 */
	public static void drawImageResource(Window window, Graphics2D graphics, Object source, String path, int x, int y) {
		drawImageResource(window, graphics, source.getClass(), path, x, y);
	}
	
	/**
	 * Draws an image
	 * @param window
	 * @param graphics
	 * @param image
	 * @param x
	 * @param y
	 */
	public static void drawImage(Window window, Graphics2D graphics, Image image, int x, int y) {
		graphics.drawImage(image, x, y, window);
	}
	
	/**
	 * Get the center of a window.
	 * @param w
	 * @return
	 */
	public static int[] getCenter(Window w) {
		return new int[] {w.getWidth()/2, w.getHeight()/2};
	}
	
	/**
	 * Get the center of the rectangle x, y, with, height.
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @return
	 */
	public static int[] getCenter(int x, int y, int w, int h) {
		return new int[] {x+w/2, y+h/2};
	}
	
	/**
	 * Draws a image from Internet.
	 * @param window
	 * @param graphics
	 * @param url
	 * @param x
	 * @param y
	 */
	public static void fetchInternetImage(Window window, Graphics2D graphics, String url, int x, int y) {
		URL inetUrl;
		try {
			inetUrl = new URL(url);
		} catch (MalformedURLException e) {
			return;
		}
		
		try {
			graphics.drawImage(ImageIO.read(inetUrl), x, y, window);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return;
		}
	}
	
	/**
	 * Draws a image centered on screen.
	 * @param window
	 * @param g
	 * @param image
	 */
	public static void drawCenteredImage(Window window, Graphics2D g, Image image) {
		int image_width = image.getWidth(window);
		int image_height = image.getHeight(window);
		
		int screen_width = window.getWidth();
		int screen_height = window.getHeight();
		
		int x = screen_width / 2 - image_width / 2;
		int y = screen_height / 2 - image_height / 2;
		
		g.drawImage(image, x, y, window);
	}
	
	
	/**
	 * Macht den Cursor unsichtbar.
	 */
	public static void hidec(Window w) {
		int[] pixels = new int[16 * 16];
		Image image = Toolkit.getDefaultToolkit().createImage(
		        new MemoryImageSource(16, 16, pixels, 0, 16));
		Cursor transparentCursor =
		        Toolkit.getDefaultToolkit().createCustomCursor
		             (image, new Point(0, 0), "invisibleCursor");
		
		w.setCursor(transparentCursor);
	}
	
	/**
	 * Draw an image centered at x and y.
	 * @param g
	 * @param img
	 * @param x
	 * @param y
	 */
	public static void drawCenteredImage(Graphics2D g, Image img, int x, int y) {
		int new_x = x - img.getWidth(null)/2;
		g.drawImage(img, new_x, y, null);
	}

	/**
	 * Draws an image centrered at screen.
	 * @param window
	 * @param g
	 * @param image
	 */
	public static void drawCenteredImage(Applet window, Graphics2D g, Image image) {
		int image_width = image.getWidth(window);
		int image_height = image.getHeight(window);
		
		int screen_width = window.getWidth();
		int screen_height = window.getHeight();
		
		int x = screen_width / 2 - image_width / 2;
		int y = screen_height / 2 - image_height / 2;
		
		g.drawImage(image, x, y, window);
		
	}
	
	/**
	 * Applet helper.
	 */
	public static void setApplet(Applet applet) {
		applet.setVisible(false);
	}
	
	public static void drawImageTile(Graphics2D graphics, Image image, int srcx, int srcy, int dstx, int dsty, int width, int height) {
		int srcx2 = srcx + width;
		int srcy2 = srcy + height;
		int dstx2 = dstx + width;
		int dsty2 = dsty + height;
		
		graphics.drawImage(image, dstx, dsty, dstx2, dsty2, srcx, srcy, srcx2, srcy2, null);
	}
	
	public static void drawImageTile(Graphics2D graphics, Image image, int srcx, int srcy, int dstx, int dsty, int width, int height, double scalex, double scaley) {
		int srcx2 = srcx + width;
		int srcy2 = srcy + height;
		int dstx2 = (int) ((dstx + width)*scalex);
		int dsty2 = (int) ((dsty + height)*scaley);
		
		graphics.drawImage(image, dstx, dsty, dstx2, dsty2, srcx, srcy, srcx2, srcy2, null);		
	}

}
