public class Planet{
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;

	static final double G = 6.67e-11;

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
}