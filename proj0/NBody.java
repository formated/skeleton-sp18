public class NBody {
    private static int n ;
    private static double r ;
    private static Planet[] p;

    static void readPlanetFile(String fileName)
    {

        In in = new In(fileName);
        /* get number of pTemp[lanets */
        n  = in.readInt();
        /* get the radious of the universe */
        r  = in.readDouble();
        /* creat array of planet*/
        Planet[] pTemp = new Planet[n];

        int i = 0;
        while ( i < n )
        {
            double xp = in.readDouble();
            double yp = in.readDouble();

            double xv = in.readDouble();
            double yv = in.readDouble();

            double mass = in.readDouble();
            String imgName = in.readString();
            pTemp[i] = new Planet(xp,yp,xv,yv,mass,imgName);
            i++;
        }
        p = pTemp;
    }
   static double readRadius(String fileName)
   {
       readPlanetFile(fileName);
       return r;
   }

   static Planet[] readPlanets(String fileName)
   {
        readPlanetFile(fileName);
        return p;
   }

   public static void main(String[] args)
   {
       double T = Double.parseDouble(args[0]);
       double dt = Double.parseDouble(args[1]);
       String filename = args[2];
       Planet[] Planets =  readPlanets(filename);
       double radius = readRadius(filename);

       StdDraw.setScale(-radius*2, radius*2);
       StdDraw.picture(0, 0, "images/starfield.jpg");
       StdDraw.show(1000);
       int i = 0;
       while(i < n)
       {
           Planets[i].draw();
           i++;
       }
       int t = 0;

       while (t < T)
       {
           double[] xForces = new double[n];
           double[] yForces= new double[n];
           i = 0;
           while(i < n)
           {
               xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
               yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
               i++;
           }
           i = 0;
           while(i < n)
           {
               Planets[i].update(dt,xForces[i],yForces[i]);
               i++;
           }
           //StdDraw.clear();
           StdDraw.picture(0, 0, "images/starfield.jpg");
           i = 0;
           while(i < n)
           {
               Planets[i].draw();
               i++;
           }
           StdDraw.show(10);
           t += dt;
       }
       StdOut.printf("%d\n", Planets.length);
       StdOut.printf("%.2e\n", radius);
       for (i = 0; i < Planets.length; i++) {
           StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                   Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel, Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);
       }
   }
}