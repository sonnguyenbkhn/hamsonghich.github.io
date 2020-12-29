
public class saiso_ad {
	public static void main(String[] args) {
 
		double[] arrPx=new double [100];	
		double[] arrPy=new double [100];	
		double[] arrPz=new double [100];	
		
		double [] arrq1=new double [100];	
		double [] arrq2=new double [100];	
		double [] arrq3=new double [100];	
		double [] arrq4=new double [100];	
		
		double [] arrdel_a1=new double [100];	
		double [] arrdel_a2=new double [100];	
		double [] arrdel_d14=new double [100];	
		
		
		
		double a[]= {350,250}; 
		double d14=167;
		double dr=0.5;
		double P[]= {350,200,100}; // set up diem chuan
		double phi=30*Math.PI/180;
		double Q[]=Q(P,phi);    // bien khop chuan 
		for(int i=1;i<=7;i++) {
		double [] Pdr=Pdr(P, dr,i); // tinh diem khi da co sai so dr
		
		// sai so a d
		double[] delta_AD=delta_AD(Pdr, Q); //[del a1 del a2 del d14];
		
		double a1_=a[0]+delta_AD[0];
		double a2_=a[1]+delta_AD[1];
		double d14_=d14+delta_AD[2];
		
	/*	arrPx[i]=Pdr[0];
		arrPy[i]=Pdr[1];
		arrPz[i]=Pdr[2];
		arrq1[i]=Q[0];
		arrq2[i]=Q[1];
		arrq3[i]=Q[2];
		arrq4[i]=Q[3];
		arrdel_a1[i]=delta_AD[0];
		arrdel_a2[i]=delta_AD[1];
		arrdel_d14[i]=delta_AD[2];*/
		
		
		System.out.println("px="+Pdr[0]);
		System.out.println("py="+Pdr[1]);
		System.out.println("pz="+Pdr[2]);
		
		System.out.println("q1="+Q[0]);
		System.out.println("q2="+Q[1]);
		System.out.println("q3="+Q[2]);
		System.out.println("q4="+Q[3]);
		
		System.out.println("a1="+a1_);  
		System.out.println("a2="+a2_);
		System.out.println("d14="+d14_);
		
		System.out.println("delta_a1="+delta_AD[0]);
		System.out.println("delta_a2="+delta_AD[1]);
		System.out.println("delta_d14="+delta_AD[2]);
		
		System.out.println("---------------------------------"+i);
		}
	}

	public static double[] Q(double[] P,double phi) {
		double px = P[0];
		double py = P[1];
		double pz = P[2];

		double a1 = 350;
		double a2 = 250;
		double d1 = 250;
		double d4 = 83;
		double cq2 = (Math.pow(px, 2) + Math.pow(py, 2) - Math.pow(a1, 2) - Math.pow(a2, 2)) / (2 * a1 * a2);
		double sq2 = Math.sqrt(1 - Math.pow(cq2, 2));
		double q2 = Math.atan2(sq2, cq2);

		double cq1 = (a1 * px + a2 * (px * cq2 + py * sq2)) / (Math.pow(py, 2) + Math.pow(px, 2));
		double sq1 = (a1 * py + a2 * (py * cq2 - px * sq2)) / (Math.pow(py, 2) + Math.pow(px, 2));
		double q1 = Math.atan2(sq1, cq1);

		double q3 = -d4 + d1 - pz;
		double q4 = -Math.atan2(Math.sin(-phi),Math.cos(phi) ) + q1 + q2;
		double[] Q = { q1, q2, q3 ,q4};
		return Q;
	}

	public static double[] Pdr(double[] P, double dr, int chose) {
		double px = P[0];
		double py = P[1];
		double pz = P[2];
		double[] Pdr = { px, py, pz };
		switch (chose) {
		
		case 2:
			Pdr = new double[] { px + dr, py, pz };
			break;
		case 3:
			Pdr = new double[] { px, py + dr, pz };
			break;
		case 4:
			Pdr = new double[] { px - dr, py, pz };
			break;
		case 5:
			Pdr = new double[] { px, py - dr, pz };
			break;
		case 6:
			Pdr = new double[] { px, py, pz - dr };
			break;
		case 7:
			Pdr = new double[] { px, py, pz + dr };
			break;
		default:
			break;
		}
		return Pdr;
	}


	public static double[] delta_AD(double[] Pdr, double[] Q) {
		// giu nguyen q tim a d
		double a11 = 350;
		double a22 = 250;
		double d11 = 250;
		double d44 = 83;
		double px = Pdr[0];
		double py = Pdr[1];
		double pz = Pdr[2];

		double q1 = Q[0];
		double q2 = Q[1];
		double q3 = Q[2];

		double a2 = (py * Math.cos(q1) - px * Math.sin(q1)) / Math.sin(q2);
		double a1 = px * Math.cos(q1) - a2 * Math.cos(q2) + py * Math.sin(q1);
		double d14 = pz + q3;

		double delta_a1 = (a11 - a1);
		double delta_a2 = (a22 - a2);
		double delta_d14 = (d14 - (d11 - d44));

		double[] AD = { delta_a1, delta_a2, delta_d14 };
		return AD;

	}
}
