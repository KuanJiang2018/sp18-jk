public class NBody {
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] ps = readPlanets(filename);
		double radius = readRadius(filename);

		StdDraw.enableDoubleBuffering();

		StdDraw.setScale(-radius, radius);
		StdDraw.clear();

		StdDraw.picture(0, 0, "images/starfield.jpg");

		for (Planet p : ps) {
			p.draw();
		}

		
		StdDraw.show();
		// StdDraw.pause(2000);	
		int num = ps.length;

		for (double t = 0.0; t < T; t += dt) {
			// StdDraw.clear();
			// StdDraw.picture(0, 0, "images/starfield.jpg");

			double[] xForces = new double[num];
			double[] yForces = new double[num];
			for (int i = 0; i < num; i += 1) {
				double netXForce = 0;
				double netYForce = 0;
				for (int j = 0; j < num; j += 1 ) {
					if (i != j) {
						netXForce += ps[i].calcForceExertedByX(ps[j]);
						netYForce += ps[i].calcForceExertedByY(ps[j]);
					}
				}
				xForces[i] = netXForce;
				yForces[i] = netYForce;
			}

			for (int i = 0; i < num; i += 1) {
				ps[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet p : ps) {
				p.draw();
			}
			StdDraw.show();

			StdDraw.pause(10);

		}

	}

	public static double readRadius(String plantsPath) {
		In in = new In(plantsPath);
		int i = in.readInt();
		return in.readDouble();
	}

	public static Planet[] readPlanets(String plantsPath) {
		In in = new In(plantsPath);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] ps = new Planet[num];
		for (int i = 0; i < num; i += 1) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String filePath = in.readString();
			Planet p = new Planet(xP, yP, xV, yV, m, filePath);
			ps[i] = p;
		}
		return ps;
	}
}