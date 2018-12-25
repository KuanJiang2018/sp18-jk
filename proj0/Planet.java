public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	private static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet rocinante) {
		double xDistance = xxPos - rocinante.xxPos;
		double yDistance = yyPos - rocinante.yyPos;
		return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
	}

	public double calcForceExertedBy(Planet p) {
		double distance = calcDistance(p);
		return G * mass * p.mass / (distance * distance);
	}

	public double calcForceExertedByX(Planet p) {
		double F = calcForceExertedBy(p);
		return F * (p.xxPos - xxPos) / calcDistance(p);
	}

	public double calcForceExertedByY(Planet p) {
		double F = calcForceExertedBy(p);
		return F * (p.yyPos - yyPos) / calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] ps) {
		double net = 0;
		for (Planet p : ps) {
			if (p.equals(this)) continue;
			net += calcForceExertedByX(p);
		}

		return net;
	}

	public double calcNetForceExertedByY(Planet[] ps) {
		double net = 0;
		for (Planet p : ps) {
			if (p.equals(this)) continue;
			net += calcForceExertedByY(p);
		}

		return net;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel = this.xxVel + dt * aX;
		this.yyVel = this.yyVel + dt * aY;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}

	public void draw() {
		String path = "images/";
		StdDraw.picture(this.xxPos, this.yyPos, path + this.imgFileName);
	}

	@Override
	public boolean equals(Object p) {
		return this == p;
	}
}