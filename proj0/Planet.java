public class Planet {

    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img)
    {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p)
    {
        xxPos = p.xxPos ;
        yyPos = p.yyPos ;
        xxVel = p.xxVel ;
        yyVel = p.yyVel ;
        mass =  p.mass;
        imgFileName = p.imgFileName ;
    }

    double calcDistance (Planet P)
    {
        double difX = this.xxPos - P.xxPos;
        double difY = this.yyPos - P.yyPos;

        return Math.sqrt((difX*difX)+ (difY*difY));
    }

    double calcForceExertedBy (Planet P)
    {
        double r = this.calcDistance(P);
        return (P.mass * this.mass * 6.67e-11) / (r*r)  ;
    }

    double calcForceExertedByX (Planet P)
    {
        double difX =  P.xxPos - this.xxPos;
        return (this.calcForceExertedBy(P) * difX) / this.calcDistance(P) ;
    }
    double calcNetForceExertedByX (Planet[] P)
    {
        double net = 0;

        for(Planet element : P )
        {
            if(element.equals(this))
                continue;
            net += this.calcForceExertedByX(element);
        }

        return net ;
    }
    double calcForceExertedByY (Planet P)
    {
        double difY = P.yyPos - this.yyPos  ;
        return (this.calcForceExertedBy(P) * difY) / this.calcDistance(P) ;
    }
    double calcNetForceExertedByY (Planet[] P)
    {
        double net = 0;

        for(Planet element : P )
        {
            if(element.equals(this))
                continue;
            net += this.calcForceExertedByY(element);
        }

        return net ;
    }

    void update(double t, double fx, double fy)
    {
        double ax = fx / this.mass;
        double ay = fy / this.mass;

        this.xxVel = this.xxVel + (ax *  t);
        this.yyVel = this.yyVel + (ay *  t);

        this.xxPos = this.xxPos + (this.xxVel *  t);
        this.yyPos = this.yyPos + (this.yyVel *  t);
    }
    void draw()
    {
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+ this.imgFileName);
    }

}
