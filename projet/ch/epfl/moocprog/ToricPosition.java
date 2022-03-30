package ch.epfl.moocprog;

import static ch.epfl.moocprog.app.Context.getConfig;
import static ch.epfl.moocprog.config.Config.*;

import ch.epfl.moocprog.utils.Vec2d;
import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.List;
import static ch.epfl.moocprog.config.Config.WORLD_HEIGHT;
import static ch.epfl.moocprog.config.Config.WORLD_WIDTH;

public final class ToricPosition {
	// Attributs
	private static int width = getConfig().getInt(WORLD_WIDTH);
	private static int height = getConfig().getInt(WORLD_HEIGHT);
	private Vec2d coordonnee;

	// Constructeur
	public ToricPosition(double x, double y) {
		this.coordonnee = clampedPosition(x, y);
	}

	public ToricPosition(Vec2d vec) {
		double x;
		double y;

		x = vec.getX();
		y = vec.getY();
		this.coordonnee = clampedPosition(x, y);
	}

	public ToricPosition() {
		this.coordonnee = new Vec2d(0.0, 0.0);
	}

	// Methodes
	private static Vec2d clampedPosition(double x, double y) {
		double x1 = x;
		double y1 = y;
		Vec2d coordonnee;
		
		while (x < width) {
			x1 += width;
		}
		while (x >= width) {
			x1 -= width;
		}
		while (y < height) {
			y1 += height;
		}
		while (x < height) {
			y1 -= height;
		}
		coordonnee = new Vec2d(x1, y1);
		return coordonnee;
	}

	public ToricPosition add(ToricPosition that) {
		Vec2d corThat = that.toVec2d();
		return new ToricPosition(corThat.add(this.coordonnee));
	}

	public ToricPosition add(Vec2d vec) {
		return new ToricPosition(vec);
	}

	public Vec2d toVec2d() {
		return this.coordonnee;
	}
	
	public Vec2d toricVector(ToricPosition that) {
		double d0,min;
		ToricPosition tor =that;
		List<ToricPosition> liste = new ArrayList<>();
		
		ToricPosition t1 = that.add(new Vec2d(0, height));
		ToricPosition t2 = that.add(new Vec2d(width, 0));
		ToricPosition t3 = that.add(new Vec2d(width, height));
	    liste.add(t1); 
	    liste.add(t2); 
	    liste.add(t3); 
	    min = (that.toVec2d()).distance(this.coordonnee);
	    
	    for (ToricPosition t : liste) {
	    	d0 = (t.toVec2d()).distance(this.coordonnee);
	    	if(d0<min) {
	    		min = d0;
	    		tor = t;
	    	}
	    }
		return tor.toVec2d();
	}
	
	public double toricDistance(ToricPosition that) {
		Vec2d vec = this.toricVector(that);
		return vec.length();	
	}
	
	@Override
	public String toString(){
		return coordonnee.toString();
	}
	

}
